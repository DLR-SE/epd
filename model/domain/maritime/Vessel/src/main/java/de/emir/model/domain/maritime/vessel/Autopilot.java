package de.emir.model.domain.maritime.vessel;

import de.emir.model.application.vehicle.Trajectory;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.vessel.AutopilotAlarm;
import de.emir.model.domain.maritime.vessel.AutopilotConfiguration;
import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = VesselCharacteristic.class)	
public interface Autopilot extends VesselCharacteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "configuration", associationType = AssociationType.COMPOSITE)
	public void setConfiguration(AutopilotConfiguration _configuration);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "configuration", associationType = AssociationType.COMPOSITE)
	public AutopilotConfiguration getConfiguration();
	/**
	 current active trajectory segment that is used by this autopilot 
									 * @note this segment has to be part of the current trajectory 
	 * @generated 
	 */
	@UMLProperty(name = "segment", associationType = AssociationType.SHARED)
	public void setSegment(TrajectorySegment _segment);
	/**
	 current active trajectory segment that is used by this autopilot 
									 * @note this segment has to be part of the current trajectory 
	 * @generated 
	 */
	@UMLProperty(name = "segment", associationType = AssociationType.SHARED)
	public TrajectorySegment getSegment();
	/**
	 Trajectory the autopilot should follow 
	 * @generated 
	 */
	@UMLProperty(name = "trajectory", associationType = AssociationType.PROPERTY)
	public void setTrajectory(Trajectory _trajectory);
	/**
	 Trajectory the autopilot should follow 
	 * @generated 
	 */
	@UMLProperty(name = "trajectory", associationType = AssociationType.PROPERTY)
	public Trajectory getTrajectory();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "alarms", associationType = AssociationType.COMPOSITE)
	public List<AutopilotAlarm> getAlarms();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "lastCommand", associationType = AssociationType.COMPOSITE)
	public void setLastCommand(CommandedValue _lastCommand);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "lastCommand", associationType = AssociationType.COMPOSITE)
	public CommandedValue getLastCommand();
	
}
