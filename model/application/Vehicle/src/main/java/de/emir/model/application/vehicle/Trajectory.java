package de.emir.model.application.vehicle;

import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**

 * A Trajectory defines the planned path of the vehicle. 
 * The difference between a trajectory and a route is a time behaviour and maybe some additional meta data. 
 * In this case the trajectory is build up from different segments with an desired speed, which represents the time behaviour.<br> 
 * 
 * @generated 
 */
@UMLClass	
public interface Trajectory extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "segments", associationType = AssociationType.COMPOSITE)
	public List<TrajectorySegment> getSegments();

	/**
	 Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public void setSource(ModelReference _source);

	/**
	 Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public ModelReference getSource();

	/**
	 Optional: name of the Trajectory, may be used to identify the Trajectory 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public void setName(String _name);
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 returns the next valid segment  for this trajectory
	 * @generated 
	 */
	TrajectorySegment getNearestSegment(final Coordinate location, final Orientation orientation);

	/**
	 Optional: name of the Trajectory, may be used to identify the Trajectory 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public String getName();
	
}
