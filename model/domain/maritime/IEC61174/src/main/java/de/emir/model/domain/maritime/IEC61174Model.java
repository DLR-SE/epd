package de.emir.model.domain.maritime;

import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class IEC61174Model implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model IEC61174 ... ");
		//initialize referenced models
		SpatialModel.init();
		
		//initialize sub packages
		Iec61174Package.theInstance.init();
		ULog.debug("... model IEC61174 initialized");
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
