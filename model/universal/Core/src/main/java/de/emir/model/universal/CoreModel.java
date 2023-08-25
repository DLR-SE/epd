package de.emir.model.universal;

import de.emir.model.universal.core.CorePackage;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 
* insert documentation for the model 
*  
 * @generated 
 */
public class CoreModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model Core ... ", 1);
		//initialize referenced models
		UCoreModel.init();
		
		//initialize sub packages
		CorePackage.theInstance.init();
		VarPackage.theInstance.init();
		ULog.debug(-1, "... model Core initialized");
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
