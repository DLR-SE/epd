package de.emir.model.application;

import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class VehicleModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Vehicle ... ");
		//initialize referenced models
		PhysicsModel.init();
		
		//initialize sub packages
		VehiclePackage.theInstance.init();
		ULog.debug("... model Vehicle initialized");
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
