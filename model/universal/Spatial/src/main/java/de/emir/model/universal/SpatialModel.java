package de.emir.model.universal;

import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.CRSModel;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.MathModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model  
*    
 * @generated 
 */
public class SpatialModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Spatial ... ", 1);
		//initialize referenced models
		MathModel.init();
		UnitsModel.init();
		CRSModel.init();
		
		//initialize sub packages
		SpatialPackage.theInstance.init();
		ULog.debug(-1, "... model Spatial initialized");
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
