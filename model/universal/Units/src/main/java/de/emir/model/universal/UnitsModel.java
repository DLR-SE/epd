package de.emir.model.universal;

import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.CRSModel;
import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class UnitsModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Units ... ");
		//initialize referenced models
		CRSModel.init();
		CoreModel.init();
		
		//initialize sub packages
		UnitsPackage.theInstance.init();
		ULog.debug("... model Units initialized");
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
