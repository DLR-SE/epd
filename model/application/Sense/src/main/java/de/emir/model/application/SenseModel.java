package de.emir.model.application;

import de.emir.model.application.sense.SensePackage;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

 * The Sense Package is used to describe basic concepts of sensors and their observations. 
 * 
 * A Sensor is modeled as the physical representation of a sensing device. Each Sensor consist of at least
 * one Port, that describe a measurement, observed by the sensor, in a specific area. 
 * 
 * Besides the Sensor the Sense Package describes a basic Observation, as a container for a sensor measurement. 
 * The concrete sensor measurement is described in an inherited Observation. 
 * 
 * The third complex of the Sense Package is the description of an ErrorModel, that describes the expected measurement error 
 * for a sensor observation. 
 * 
 * \ingroup application 
 * @generated 
 */
public class SenseModel implements UCorePlugin {
	
	
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
		ULog.debug("initialize model Sense ... ");
		//initialize referenced models
		PhysicsModel.init();
		CoreModel.init();
		
		//initialize sub packages
		SensePackage.theInstance.init();
		PortsPackage.theInstance.init();
		MeasurementsPackage.theInstance.init();
		ULog.debug("... model Sense initialized");
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
