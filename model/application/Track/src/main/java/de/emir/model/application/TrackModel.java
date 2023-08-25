package de.emir.model.application;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.application.track.TrackPackage;
import de.emir.model.universal.PhysicsModel;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class TrackModel implements UCorePlugin {
	
	
	/**
	 * @generated
	 */
	private static boolean initialized = false;
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Track ... ", 1);
		//initialize referenced models
		PhysicsModel.init();
		
		//initialize sub packages
		TrackPackage.theInstance.init();
		ULog.debug(-1, "... model Track initialized");
	}
	
	/**
	 * @generated 
	 */
	@Override
	public void initializePlugin(){
		//Method is called by plugin loader
		init();
	}
}
