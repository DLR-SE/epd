package de.emir.model.application.vehicle.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialUtils;
import de.emir.model.universal.spatial.SpatialUtils.LineSegment;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = TrajectorySegment.class)
public class TrajectorySegmentImpl extends UObjectImpl implements TrajectorySegment  
{
	
	
	/**
	 *	@generated 
	 */
	private String mName = "";
	/**
	 *	@generated 
	 */
	private int mId = 0;
	/**
	 *	@generated 
	 */
	private Coordinate mOrigin = null;
	/**
	 *	@generated 
	 */
	private Coordinate mDestination = null;
	/**
	 *	@generated 
	 */
	private Speed mSpeed = null;
	/**
	 *	@generated 
	 */
	private Distance mAllowedPortCTE = null;
	/**
	 *	@generated 
	 */
	private Distance mAllowedStarboardCTE = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrajectorySegmentImpl(){
		super();
		//set the default values and assign them to this instance 
		setOrigin(mOrigin);
		setDestination(mDestination);
		setSpeed(mSpeed);
		setAllowedPortCTE(mAllowedPortCTE);
		setAllowedStarboardCTE(mAllowedStarboardCTE);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrajectorySegmentImpl(final TrajectorySegment _copy) {
		mName = _copy.getName();
		mId = _copy.getId();
		mOrigin = _copy.getOrigin();
		mDestination = _copy.getDestination();
		mSpeed = _copy.getSpeed();
		mAllowedPortCTE = _copy.getAllowedPortCTE();
		mAllowedStarboardCTE = _copy.getAllowedStarboardCTE();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrajectorySegmentImpl(String _name, int _id, Coordinate _origin, Coordinate _destination, Speed _speed, Distance _allowedPortCTE, Distance _allowedStarboardCTE) {
		mName = _name;
		mId = _id;
		mOrigin = _origin; 
		mDestination = _destination; 
		mSpeed = _speed; 
		mAllowedPortCTE = _allowedPortCTE; 
		mAllowedStarboardCTE = _allowedStarboardCTE; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.TrajectorySegment;
	}
	
	/**
	 *	@generated 
	 */
	public void setName(String _name) {
		if (needNotification(VehiclePackage.Literals.TrajectorySegment_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, VehiclePackage.Literals.TrajectorySegment_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	/**
	 *	@generated 
	 */
	public String getName() {
		return mName;
	}
	/**
	 *	@generated 
	 */
	public void setId(int _id) {
		if (needNotification(VehiclePackage.Literals.TrajectorySegment_id)){
			int _oldValue = mId;
			mId = _id;
			notify(_oldValue, mId, VehiclePackage.Literals.TrajectorySegment_id, NotificationType.SET);
		}else{
			mId = _id;
		}
	}

	/**
	 *	@generated 
	 */
	public int getId() {
		return mId;
	}
	/**
	 *	@generated 
	 */
	public void setOrigin(Coordinate _origin) {
		Notification<Coordinate> notification = basicSet(mOrigin, _origin, VehiclePackage.Literals.TrajectorySegment_origin);
		mOrigin = _origin;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Coordinate getOrigin() {
		return mOrigin;
	}
	/**
	 *	@generated 
	 */
	public void setDestination(Coordinate _destination) {
		Notification<Coordinate> notification = basicSet(mDestination, _destination, VehiclePackage.Literals.TrajectorySegment_destination);
		mDestination = _destination;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Coordinate getDestination() {
		return mDestination;
	}
	/**
	 *	@generated 
	 */
	public void setSpeed(Speed _speed) {
		Notification<Speed> notification = basicSet(mSpeed, _speed, VehiclePackage.Literals.TrajectorySegment_speed);
		mSpeed = _speed;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Speed getSpeed() {
		return mSpeed;
	}
	/**
	 *	@generated 
	 */
	public void setAllowedPortCTE(Distance _allowedPortCTE) {
		Notification<Distance> notification = basicSet(mAllowedPortCTE, _allowedPortCTE, VehiclePackage.Literals.TrajectorySegment_allowedPortCTE);
		mAllowedPortCTE = _allowedPortCTE;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Distance getAllowedPortCTE() {
		return mAllowedPortCTE;
	}
	/**
	 *	@generated 
	 */
	public void setAllowedStarboardCTE(Distance _allowedStarboardCTE) {
		Notification<Distance> notification = basicSet(mAllowedStarboardCTE, _allowedStarboardCTE, VehiclePackage.Literals.TrajectorySegment_allowedStarboardCTE);
		mAllowedStarboardCTE = _allowedStarboardCTE;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Distance getAllowedStarboardCTE() {
		return mAllowedStarboardCTE;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Distance getDistance(final Coordinate loc)
	{
		Coordinate proj = SpatialUtils.getNearestPointOnRay(new LineSegment(getOrigin(), getDestination()), loc);
		if (proj != null) 
			return proj.getDistance(loc);
		return null;
	}

	Angle 	mOrientation = null;
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Angle getOrientation()
	{
		if (mOrientation == null) {
			mOrientation = getOrigin().getAzimuth(getDestination());
			registerListener(new IValueChangeListener() {
				@Override
				public void onValueChange(Notification notification) { 
					//ensure that we can react on changes of coordinates
					if (notification.getFeature() == VehiclePackage.Literals.TrajectorySegment_origin || notification.getFeature() == VehiclePackage.Literals.TrajectorySegment_destination) {
						mOrientation = null;
						removeListener(this);
					}
				}
			});
		}
		return mOrientation;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrajectorySegmentImpl{" +
		" name = " + getName() + 
		" id = " + getId() + 
		"}";
	}
}
