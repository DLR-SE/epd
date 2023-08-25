package de.emir.tuml.runtime.epf.descriptors;

import de.emir.tuml.runtime.epf.CoordinateElement;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassPathDescriptor<VERSION extends Comparable<VERSION>> extends CoordinateElement<VERSION> {
	
    protected URL[] 								mURLs = null;
    protected List<CoordinateElement<VERSION>> 		mDependencies = new ArrayList<>();


    public ClassPathDescriptor(String name, String desc, VERSION v, URL[] urls) {
        super(name, desc, v);
        mURLs = urls;
    }

    public void addURL(URL url) {
        if (mURLs == null)
            mURLs = new URL[] { url };
        else {
            URL[] newU = Arrays.copyOf(mURLs, mURLs.length + 1);
            newU[mURLs.length] = url;
            mURLs = newU;
        }
    }

    public List<CoordinateElement<VERSION>> getDependencies() {
        return Collections.unmodifiableList(mDependencies);
    }

    public void addDependency(String depId, VERSION depV) {
        mDependencies.add(new CoordinateElement<VERSION>(depId, null, depV));
    }

    public void addDependency(CoordinateElement<VERSION> dep) {
        mDependencies.add(dep);
    }

    public URL[] getURLs() {
        return mURLs;
    }
    
}
