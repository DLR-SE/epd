package de.emir.model.universal.spatial;

import de.emir.model.universal.CoreModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.compatibility.GeometryXMLCompatibilityHandler;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLCompatibilityManager;

/**
 
* insert documentation for the model  
* 
 * @generated 
 */
public class SimpleFeatureModel implements UCorePlugin {
	
	/**
	 * @generated not
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model SimpleFeature ... ", 1);
		//initialize referenced models
		SpatialModel.init();
		CoreModel.init();
		
		//initialize sub packages
		SfPackage.theInstance.init();
		
		
		//register compatibility handler for geometries
		ExtensionPointManager.getExtensionPoint(XMLCompatibilityManager.class).registerCompatiblityHandler(new GeometryXMLCompatibilityHandler());
		
		
		ULog.debug(-1, "... model SimpleFeature initialized");
	}

	/**
	 * @generated 
	 */
	@Override
	public void initializePlugin(){
		//Method is called by plugin loader
		init();
	}

	/**
	 * @generated
	 */
	private static boolean initialized = false;
}
