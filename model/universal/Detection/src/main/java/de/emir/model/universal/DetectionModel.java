package de.emir.model.universal;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.detection.TargetPackage;

/**

* The detection model represents all objects which are detected by tracking and detection systems such as RADAR.
* In the models context, the differentiation between Targets and TrackedTargets are made. Target is a single detection
* point of a detection system consisting of the reference location of the sensor, detection timestamp, bearing and
* distance to the location of the detection sensor. While it can be represented with a georeferenced location,
* this is usually done by calculating the position from the relation to the detection sensor. TrackedTargets on the
* other hand represent Targets which tracks are tracked by a tracking system. Each TrackedTarget has a position and
* a corresponding Track consisting of TrackPoint observation points.
 * @generated 
 */
public class DetectionModel implements UCorePlugin {
	
	
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
		ULog.debug("initialize model Target ... ", 1);
		//initialize referenced models
		PhysicsModel.init();
		SpatialModel.init();
		UnitsModel.init();
		CoreModel.init();
		
		//initialize sub packages
		TargetPackage.theInstance.init();
		ULog.debug(-1, "... model Target initialized");
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
