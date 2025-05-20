package de.emir.model.application.sensor;

import de.emir.model.application.SenseModel;
import de.emir.model.application.sensor.ais.AisPackage;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 *	@generated  
 */
public class AISSensorModel implements UCorePlugin {
	
	
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
		ULog.debug("initialize model AISSensor ... ");
		//initialize referenced models
		SenseModel.init();
		
		//initialize sub packages
		AisPackage.theInstance.init();
		ULog.debug("... model AISSensor initialized");
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
