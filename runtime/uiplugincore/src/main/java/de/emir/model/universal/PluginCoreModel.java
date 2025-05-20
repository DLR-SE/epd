package de.emir.model.universal;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.plugincore.PlugincorePackage;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.UCoreModel;

/**
 
* insert documentation for the model 
*  
 * @generated 
 */
public class PluginCoreModel implements UCorePlugin {
	
	
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
		ULog.debug("initialize model PluginCore ... ");
		//initialize referenced models
		UCoreModel.init();
		
		//initialize sub packages
		PlugincorePackage.theInstance.init();
		VarPackage.theInstance.init();
		ULog.debug("... model PluginCore initialized");
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
