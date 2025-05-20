package de.emir.model.universal;

import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.model.universal.CRSModel;
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
public class PhysicsModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Physics ... ");
		//initialize referenced models
		UnitsModel.init();
		SpatialModel.init();
		CoreModel.init();
		
		//initialize sub packages
		PhysicsPackage.theInstance.init();
		ULog.debug("... model Physics initialized");
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
