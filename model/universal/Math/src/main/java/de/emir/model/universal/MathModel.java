package de.emir.model.universal;

import de.emir.model.universal.math.MathPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 
* insert documentation for the model 
* 
 * @generated 
 */
public class MathModel implements UCorePlugin {
	
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
		ULog.debug("initialize model Math ... ");
		//initialize referenced models
		CoreModel.init();
		
		//initialize sub packages
		MathPackage.theInstance.init();
		ULog.debug("... model Math initialized");
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
