package de.emir.tuml.ucore;

import de.emir.tuml.ucore.runtime.serialization.xml.XMLCompatibilityManager;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * 
 * insert documentation for the model
 * 
 * @generated
 */
public class UCoreModel {

    /**
     * @generated not
     */
    public static void init() {
    	if (initialized)
    		return ;
    	initialized = true;
        UtilsPackage.init();
        // initialize sub packages
        RuntimePackage.init();

        ExtensionPointManager.registerExtensionPoint(XMLCompatibilityManager.class.getName(), new XMLCompatibilityManager());
    }

    /**
     * @generated not
     */
    public void initializePlugin() {
        // Method is called by plugin loader
        init();
    }

    /**
     * @generated
     */
    private static boolean initialized = false;
}
