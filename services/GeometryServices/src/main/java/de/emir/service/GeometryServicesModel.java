package de.emir.service;

import de.emir.model.universal.CRSModel;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.MathModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.service.geometry.GeometryPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* insert documentation for the model 
* 
 * @generated 
 */
public class GeometryServicesModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model GeometryServices ... ");
		//initialize referenced models
		CRSModel.init();
		CoreModel.init();
		MathModel.init();
		SpatialModel.init();
		UnitsModel.init();
		
		//initialize sub packages
		GeometryPackage.init();
		ULog.debug("... model GeometryServices initialized");
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
