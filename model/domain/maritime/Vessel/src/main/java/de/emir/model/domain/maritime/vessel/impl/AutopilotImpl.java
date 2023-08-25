package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.Trajectory;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.vessel.Autopilot;
import de.emir.model.domain.maritime.vessel.AutopilotAlarm;
import de.emir.model.domain.maritime.vessel.AutopilotConfiguration;
import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Autopilot.class)
public class AutopilotImpl extends VesselCharacteristicImpl implements Autopilot  
{
	
	
	/**
	 *	@generated 
	 */
	private AutopilotConfiguration mConfiguration = null;
	/**
	 Trajectory the autopilot should follow 
	 * @generated 
	 */
	private Trajectory mTrajectory = null;
	/**
	 current active trajectory segment that is used by this autopilot 
									 * @note this segment has to be part of the current trajectory 
	 * @generated 
	 */
	private TrajectorySegment mSegment = null;
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AutopilotImpl(Trajectory _trajectory, AutopilotConfiguration _configuration, List<AutopilotAlarm> _alarms, CommandedValue _lastCommand, TrajectorySegment _segment) {
		super();
		mTrajectory = _trajectory; 
		mConfiguration = _configuration; 
		mAlarms = _alarms; 
		mLastCommand = _lastCommand; 
		mSegment = _segment; 
	}


	/**
	 *	@generated 
	 */
	private List<AutopilotAlarm> mAlarms = null;

	/**
	 Trajectory the autopilot should follow 
	 * @generated 
	 */
	public void setTrajectory(Trajectory _trajectory) {
		Notification<Trajectory> notification = basicSet(mTrajectory, _trajectory, VesselPackage.Literals.Autopilot_trajectory);
		mTrajectory = _trajectory;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Trajectory the autopilot should follow 
	 * @generated 
	 */
	public Trajectory getTrajectory() {
		return mTrajectory;
	}
	/**
	 *	@generated 
	 */
	private CommandedValue mLastCommand = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AutopilotImpl(){
		super();
		//set the default values and assign them to this instance 
		setTrajectory(mTrajectory);
		setConfiguration(mConfiguration);
		setLastCommand(mLastCommand);
		setSegment(mSegment);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AutopilotImpl(final Autopilot _copy) {
		super(_copy);
		mTrajectory = _copy.getTrajectory();
		mConfiguration = _copy.getConfiguration();
		mAlarms = _copy.getAlarms();
		mLastCommand = _copy.getLastCommand();
		mSegment = _copy.getSegment();
	}

	/**
	 current active trajectory segment that is used by this autopilot 
									 * @note this segment has to be part of the current trajectory 
	 * @generated 
	 */
	public TrajectorySegment getSegment() {
		return mSegment;
	}

	/**
	 current active trajectory segment that is used by this autopilot 
									 * @note this segment has to be part of the current trajectory 
	 * @generated 
	 */
	public void setSegment(TrajectorySegment _segment) {
		Notification<TrajectorySegment> notification = basicSet(mSegment, _segment, VesselPackage.Literals.Autopilot_segment);
		mSegment = _segment;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.Autopilot;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setConfiguration(AutopilotConfiguration _configuration) {
		Notification<AutopilotConfiguration> notification = basicSet(mConfiguration, _configuration, VesselPackage.Literals.Autopilot_configuration);
		mConfiguration = _configuration;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public AutopilotConfiguration getConfiguration() {
		return mConfiguration;
	}
	/**
	 *	@generated 
	 */
	public List<AutopilotAlarm> getAlarms() {
		if (mAlarms == null) {
			mAlarms = new UContainmentList<AutopilotAlarm>(this, VesselPackage.theInstance.getAutopilot_alarms()); 
		}
		return mAlarms;
	}
	/**
	 *	@generated 
	 */
	public void setLastCommand(CommandedValue _lastCommand) {
		Notification<CommandedValue> notification = basicSet(mLastCommand, _lastCommand, VesselPackage.Literals.Autopilot_lastCommand);
		mLastCommand = _lastCommand;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public CommandedValue getLastCommand() {
		return mLastCommand;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AutopilotImpl{" +
		"}";
	}
}
