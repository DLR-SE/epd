package de.emir.model.application.vehicle.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.TrackElement;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

 * One track point
 * A track usually is a set of positions (with additional information) that have been seen in the past
 * @generated 
 */
@UMLImplementation(classifier = TrackElement.class)
public class TrackElementImpl extends UObjectImpl implements TrackElement  
{
	
	
	/**
	 Time of this measurement 
	 * @generated 
	 */
	private Time mTime = null;
	/**
	 Coordiante of the object at the given time 
	 * @generated 
	 */
	private Coordinate mCoordinate = null;
	/**
	 orientation of the object at the given time 
	 * @generated 
	 */
	private Angle mHeading = null;
	/**
	 course / direction of movement, that may differ from the orientation, of the object at the given time 
	 * @generated 
	 */
	private Angle mCourse = null;
	/**
	 magnitude of movement in direction of course 
	 * @generated 
	 */
	private Speed mSpeed = null;
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated not
	 */
	private Object mSource = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackElementImpl(){
		super();
		//set the default values and assign them to this instance 
		setTime(mTime);
		setCoordinate(mCoordinate);
		setHeading(mHeading);
		setCourse(mCourse);
		setSpeed(mSpeed);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackElementImpl(final TrackElement _copy) {
		mTime = _copy.getTime();
		mCoordinate = _copy.getCoordinate();
		mHeading = _copy.getHeading();
		mCourse = _copy.getCourse();
		mSpeed = _copy.getSpeed();
		mSource = _copy.getSource();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackElementImpl(Time _time, Coordinate _coordinate, Angle _heading, Angle _course, Speed _speed, Object _source) {
		mTime = _time; 
		mCoordinate = _coordinate; 
		mHeading = _heading; 
		mCourse = _course; 
		mSpeed = _speed; 
		mSource = _source; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.TrackElement;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Time of this measurement 
	 * @generated 
	 */
	public void setTime(Time _time) {
		Notification<Time> notification = basicSet(mTime, _time, VehiclePackage.Literals.TrackElement_time);
		mTime = _time;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Time of this measurement 
	 * @generated 
	 */
	public Time getTime() {
		return mTime;
	}
	/**
	 Coordiante of the object at the given time 
	 * @generated 
	 */
	public void setCoordinate(Coordinate _coordinate) {
		Notification<Coordinate> notification = basicSet(mCoordinate, _coordinate, VehiclePackage.Literals.TrackElement_coordinate);
		mCoordinate = _coordinate;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Coordiante of the object at the given time 
	 * @generated 
	 */
	public Coordinate getCoordinate() {
		return mCoordinate;
	}
	/**
	 orientation of the object at the given time 
	 * @generated 
	 */
	public void setHeading(Angle _heading) {
		Notification<Angle> notification = basicSet(mHeading, _heading, VehiclePackage.Literals.TrackElement_heading);
		mHeading = _heading;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 orientation of the object at the given time 
	 * @generated 
	 */
	public Angle getHeading() {
		return mHeading;
	}
	/**
	 course / direction of movement, that may differ from the orientation, of the object at the given time 
	 * @generated 
	 */
	public void setCourse(Angle _course) {
		Notification<Angle> notification = basicSet(mCourse, _course, VehiclePackage.Literals.TrackElement_course);
		mCourse = _course;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 course / direction of movement, that may differ from the orientation, of the object at the given time 
	 * @generated 
	 */
	public Angle getCourse() {
		return mCourse;
	}
	/**
	 magnitude of movement in direction of course 
	 * @generated 
	 */
	public void setSpeed(Speed _speed) {
		Notification<Speed> notification = basicSet(mSpeed, _speed, VehiclePackage.Literals.TrackElement_speed);
		mSpeed = _speed;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 magnitude of movement in direction of course 
	 * @generated 
	 */
	public Speed getSpeed() {
		return mSpeed;
	}
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated 
	 */
	public void setSource(Object _source) {
		if (needNotification(VehiclePackage.Literals.TrackElement_source)){
			Object _oldValue = mSource;
			mSource = _source;
			notify(_oldValue, mSource, VehiclePackage.Literals.TrackElement_source, NotificationType.SET);
		}else{
			mSource = _source;
		}
	}
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated 
	 */
	public Object getSource() {
		return mSource;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackElementImpl{" +
		"}";
	}
}
