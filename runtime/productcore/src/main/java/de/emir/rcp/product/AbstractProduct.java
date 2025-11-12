package de.emir.rcp.product;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.progress.NullProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public abstract class AbstractProduct {

    protected PluginManager mPluginManager;
    private ProductFile mProductFile;

    private String[] mArgs;
    private String mApplicationName;

    private static AbstractProduct mTheInstance;

    public AbstractProduct(String[] args) {
        if (mTheInstance != null)
            throw new IllegalArgumentException("There may only be one instance of AbstractProduct");
        mTheInstance = this;

        this.mArgs = args;

        init();
    }

    public static AbstractProduct getProduct() {
        return mTheInstance;
    }

    public void init() {
        try {
            // parse the arguments to extract the applications name and initialize the
            // ResourceManager
            // need to be done before we call the init method with the progress monitor, for
            // the case that the progressmonitor (for example a splash screen)
            // needs access to resources
            String appName = getApplicationName();

            // set only if not null, otherwise we may get some strange errors with the
            // ResourceManager
            if (appName != null && appName.isEmpty() == false)
                ResourceManager.applicationName = appName;

            init(new NullProgressMonitor());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            ULog.error(e);
        }
    }

    public void init(IProgressMonitor monitor) throws SAXException, IOException, ParserConfigurationException {
        ResourceManager rmgr = ResourceManager.get(AbstractProduct.class);
        System.setProperty("Product.home", rmgr.getHomePath().toString());

        File pfFile = rmgr.resolveFile(ProductFile.PRODUCT_DEFINITION);
        if (pfFile != null) {

            boolean online = readMavenOnlineFromCommandLine(getArgs());

            mProductFile = new ProductFile(pfFile);
            mPluginManager = new PluginManager(monitor, mProductFile, !online);

            File pomFile = new File("pom.xml");

            mPluginManager.registerRootApplication(
                    pomFile,
                    mProductFile.getIdentity(),
                    AbstractProduct.class.getClassLoader()
            );

            mPluginManager.load();

            mPluginManager.build();

            ULog.info("Starting Plugins...");
            mPluginManager.startPlugins();
        }

    }

    public String[] getArgs() {
        return mArgs;
    }

    protected String getApplicationName() {
        if (mApplicationName == null) {
            // check if we got an argument (-applicationName), otherwise use the default
            // value that is the program name
            mApplicationName = readApplicationNameFromCommandLine(mArgs);
            if (mApplicationName == null) {
                System.getProperties();
                String n = System.getProperty("sun.java.command");
                if (n != null && n.isEmpty() == false) {

                    String[] parts = n.split("\\s+");

                    String part = null;

                    if (parts.length > 0) {
                        part = parts[0];
                    }

                    if (part != null) {
                        int idx = part.lastIndexOf('.');
                        if (idx > 0) {
                        	String firstTry = part.substring(idx + 1);
                        	if (firstTry.toLowerCase().equals("jar")) {
                        		// Command was just the name of the .jar file, use the first part of the filename.
                        		mApplicationName = part.substring(0, idx);
                        	} else {
                        		// Command was the fully class qualified class name, use only the last part.
                        		mApplicationName = firstTry;
                        	}
                        }
                    }
                }
            }
        }
        return mApplicationName;
    }

    private boolean readMavenOnlineFromCommandLine(String[] args) {
        if (args != null) {
            for (String arg : args) {
                if (arg != null && arg.isEmpty() == false) {
                    if (arg.equalsIgnoreCase("--online")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String readApplicationNameFromCommandLine(String[] args) {
        if (args == null)
            return null;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null && args[i].isEmpty() == false) {
                    if (args[i].equalsIgnoreCase("-applicationname")) {
                        if (args.length >= i + 1)
                            return args[i + 1];
                    }
                }
            }
        }
        return null;
    }

}
