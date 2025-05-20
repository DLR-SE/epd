package de.emir.model.universal;

import de.emir.model.universal.io.IoPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* 
* Insert your copyright text
 * @generated 
 */
public class IOModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model IO ... ");
		//initialize referenced models
		CoreModel.init();
		
		//initialize sub packages
		IoPackage.theInstance.init();
		ULog.debug("... model IO initialized");
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
