package de.emir.model.application.vehicle.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.Trajectory;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**

 * A Trajectory defines the planned path of the vehicle. 
 * The difference between a trajectory and a route is a time behaviour and maybe some additional meta data. 
 * In this case the trajectory is build up from different segments with an desired speed, which represents the time behaviour.<br> 
 * 
 * @generated 
 */
@UMLImplementation(classifier = Trajectory.class)
public class TrajectoryImpl extends UObjectImpl implements Trajectory  
{
	
	
	/**
	 *	@generated 
	 */
	private List<TrajectorySegment> mSegments = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrajectoryImpl(){
		super();
		//set the default values and assign them to this instance 
		setSource(mSource);
	}



	/**
	 Optional: name of the Trajectory, may be used to identify the Trajectory 
	 * @generated 
	 */
	private String mName = "";
	/**
	 Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route 
	 * @generated 
	 */
	private ModelReference mSource = null;

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrajectoryImpl(final Trajectory _copy) {
		mName = _copy.getName();
		mSegments = _copy.getSegments();
		mSource = _copy.getSource();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.Trajectory;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrajectoryImpl(String _name, List<TrajectorySegment> _segments, ModelReference _source) {
		mName = _name;
		mSegments = _segments; 
		mSource = _source; 
	}

	/**
	 Optional: name of the Trajectory, may be used to identify the Trajectory 
	 * @generated 
	 */
	public void setName(String _name) {
		if (needNotification(VehiclePackage.Literals.Trajectory_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, VehiclePackage.Literals.Trajectory_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public List<TrajectorySegment> getSegments() {
		if (mSegments == null) {
			mSegments = new UContainmentList<TrajectorySegment>(this, VehiclePackage.theInstance.getTrajectory_segments()); 
		}
		return mSegments;
	}

	/**
	 Optional: name of the Trajectory, may be used to identify the Trajectory 
	 * @generated 
	 */
	public String getName() {
		return mName;
	}

	/**
	 Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route 
	 * @generated 
	 */
	public void setSource(ModelReference _source) {
		Notification<ModelReference> notification = basicSet(mSource, _source, VehiclePackage.Literals.Trajectory_source);
		mSource = _source;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public TrajectorySegment getNearestSegment(final Coordinate location, final Orientation orientation)
	{
		//TODO: 
		//  returns the next valid segment  for this trajectory
		throw new UnsupportedOperationException("getNearestSegment not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrajectoryImpl{" +
		" name = " + getName() + 
		"}";
	}

	/**
	 Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route 
	 * @generated 
	 */
	public ModelReference getSource() {
		return mSource;
	}
}
