package de.emir.service;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.domain.maritime.IEC61174Model;
import de.emir.model.universal.IOModel;
import de.emir.service.routeservices.RouteservicesPackage;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class RouteServicesModel implements UCorePlugin {
	
	
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
		ULog.debug("initialize model RouteServices ... ");
		//initialize referenced models
		IEC61174Model.init();
		IOModel.init();
		
		//initialize sub packages
		RouteservicesPackage.theInstance.init();
		ULog.debug("... model RouteServices initialized");
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
