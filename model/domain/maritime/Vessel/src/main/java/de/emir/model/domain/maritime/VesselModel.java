package de.emir.model.domain.maritime;

import de.emir.model.application.VehicleModel;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.CoreModel;
import de.emir.model.domain.maritime.IEC61174Model;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class VesselModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Vessel ... ", 1);
		//initialize referenced models
		VehicleModel.init();
		IEC61174Model.init();
		
		//initialize sub packages
		VesselPackage.theInstance.init();
		ULog.debug(-1, "... model Vessel initialized");
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
