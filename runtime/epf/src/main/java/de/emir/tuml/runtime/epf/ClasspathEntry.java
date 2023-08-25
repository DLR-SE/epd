package de.emir.tuml.runtime.epf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.ClassPath;

import de.emir.tuml.runtime.epf.descriptors.ClassPathDescriptor;
import de.emir.tuml.runtime.epf.descriptors.PluginDescriptor;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ClassPathProvider;

/**
 * @author Stefan Behrensen
 *
 * @param <VERSION>
 */
public class ClasspathEntry<VERSION extends Comparable<VERSION>> implements ClassPathProvider {

    private ClassPathDescriptor<VERSION> 	mDescriptor;
    private ClassLoader 					mClassLoader;
    private PluginManager 					mPluginManager;

    private String 							mCoordinate; // store for faster access
    private boolean 						mStarted = false;
    private List<ClasspathEntry> 			mDependencies = null;
    private List<ClasspathEntry> 			mPluginDependencies = null;
    private ArrayList<ClasspathEntry> 		mAllDependencies;

    /**
     * Provides access to the current classpath (resources and classes).
     */
    private ClassPath mClassPath;
    private IClasspathInfo mClasspathInfo;

    /**
     * Constructor to create a ClassPathEntry.
     * 
     * @param descriptor
     * @param classLoader
     * @param pm
     * @param cpi
     */
    public ClasspathEntry(ClassPathDescriptor<VERSION> descriptor, ClassLoader classLoader, PluginManager pm,
            IClasspathInfo cpi) {
        mDescriptor = descriptor;
        mClassLoader = classLoader;
        mPluginManager = pm;
        mClasspathInfo = cpi;
    }

    public ClassLoader getClassLoader() {
        return mClassLoader;
    }

    public ClassPathDescriptor<VERSION> getDescriptor() {
        return mDescriptor;
    }

    public String getCoordinate() {
        if (mCoordinate == null)
            mCoordinate = mDescriptor.getCoordinate();
        return mCoordinate;
    }

    public boolean isPlugin() {
        return mDescriptor instanceof PluginDescriptor;
    }

    public String getPluginClass() {
        if (isPlugin() == false)
            return null;
        return ((PluginDescriptor) mDescriptor).getPluginClassName();
    }

    public boolean isStarted() {
        return mStarted;
    }

    public void _markStarted(boolean s) {
        mStarted = s;
    }

    @SuppressWarnings("rawtypes")
    public List<ClasspathEntry> getDirectDependencies() {
        if (mDependencies == null) {
            ULog.trace("Resolving dependency entries for: " + mDescriptor);
            ArrayList<ClasspathEntry> deps = new ArrayList<>();
            ArrayList<ClasspathEntry> plugs = new ArrayList<>();
            for (CoordinateElement<VERSION> coord : mDescriptor.getDependencies()) {
                ClasspathEntry depEntry = mPluginManager.getEntry(coord.getCoordinate());
                if (depEntry == null) {// do not throw an exception we may still be able to continue, but indicate a
                                       // serious issue through an error
                    ULog.error("Could not resolve all classpath dependencies for" + mDescriptor.getCoordinate()
                            + ", missing: " + coord);
                } else {
                    deps.add(depEntry);
                    if (depEntry.isPlugin())
                        plugs.add(depEntry);
                }
            }
            mDependencies = deps;
            mPluginDependencies = plugs;
        }
        return mDependencies;
    }

    /**
     * Returns a flat list of all dependencies.
     *
     * @return The flat list of all dependencies
     */
    @SuppressWarnings("rawtypes")
    public List<ClasspathEntry> getAllDependencies() {
        if (mAllDependencies == null) {
            mAllDependencies = new ArrayList<ClasspathEntry>();
            mAllDependencies.addAll(getDirectDependencies());
            for (ClasspathEntry dd : getDirectDependencies()) {
                List<ClasspathEntry> tmp = dd.getAllDependencies();
                for (ClasspathEntry<?> dde : tmp) {
                    if (!mAllDependencies.contains(dde)) {
                        mAllDependencies.add(dde);
                    }
                }
            }
            // mAllDependencies.addAll(dd.getAllDependencies());
        }
        return mAllDependencies;
    }

    public List<ClasspathEntry> getPluginDependencies() {
        if (mPluginDependencies == null) {
            getDirectDependencies(); // this also builds the plugindependencies list
        }
        return mPluginDependencies;
    }

    public void _setClassloader(ClassLoader classLoader) {
        mClassLoader = classLoader;
    }

    @Override
    public ClassPath getClassPath() {
        if (mClassPath == null) {
            try {
                mClassPath = ClassPath.from(getClassLoader());
            } catch (IOException e) {
                e.printStackTrace();
                ULog.error("Failed to create Classpath for: " + this + " Exception: " + e.getMessage());
            }
        }
        return mClassPath;
    }

    @Override
    public String toString() {
        return "ClasspathEntry: " + getCoordinate();
    }

    @Override
    public IClasspathInfo getClasspathInfo() {
        return mClasspathInfo;
    }

}
