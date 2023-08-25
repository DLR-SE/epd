package de.emir.model.application.track.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.track.TrackPackage;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

 * One track point
 * A track usually is a set of positions (with additional information) that have been seen in the past
 * @generated 
 */
@UMLImplementation(classifier = TrackPoint.class)
public class TrackPointImpl extends UObjectImpl implements TrackPoint  
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
	 * @generated 
	 */
	private UObject mSource = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackPointImpl(){
		super();
		//set the default values and assign them to this instance 
		setTime(mTime);
		setCoordinate(mCoordinate);
		setHeading(mHeading);
		setCourse(mCourse);
		setSpeed(mSpeed);
		setSource(mSource);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackPointImpl(final TrackPoint _copy) {
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
	public TrackPointImpl(Time _time, Coordinate _coordinate, Angle _heading, Angle _course, Speed _speed, UObject _source) {
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
		return TrackPackage.Literals.TrackPoint;
	}
	
	/**
	 Time of this measurement 
	 * @generated 
	 */
	public void setTime(Time _time) {
		if (needNotification(TrackPackage.Literals.TrackPoint_time)){
			Time _oldValue = mTime;
			mTime = _time;
			notify(_oldValue, mTime, TrackPackage.Literals.TrackPoint_time, NotificationType.SET);
		}else{
			mTime = _time;
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
		if (needNotification(TrackPackage.Literals.TrackPoint_coordinate)){
			Coordinate _oldValue = mCoordinate;
			mCoordinate = _coordinate;
			notify(_oldValue, mCoordinate, TrackPackage.Literals.TrackPoint_coordinate, NotificationType.SET);
		}else{
			mCoordinate = _coordinate;
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
		if (needNotification(TrackPackage.Literals.TrackPoint_heading)){
			Angle _oldValue = mHeading;
			mHeading = _heading;
			notify(_oldValue, mHeading, TrackPackage.Literals.TrackPoint_heading, NotificationType.SET);
		}else{
			mHeading = _heading;
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
		if (needNotification(TrackPackage.Literals.TrackPoint_course)){
			Angle _oldValue = mCourse;
			mCourse = _course;
			notify(_oldValue, mCourse, TrackPackage.Literals.TrackPoint_course, NotificationType.SET);
		}else{
			mCourse = _course;
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
		if (needNotification(TrackPackage.Literals.TrackPoint_speed)){
			Speed _oldValue = mSpeed;
			mSpeed = _speed;
			notify(_oldValue, mSpeed, TrackPackage.Literals.TrackPoint_speed, NotificationType.SET);
		}else{
			mSpeed = _speed;
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
	public void setSource(UObject _source) {
		Notification<UObject> notification = basicSet(mSource, _source, TrackPackage.Literals.TrackPoint_source);
		mSource = _source;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated 
	 */
	public UObject getSource() {
		return mSource;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackPointImpl{" +
		"}";
	}
}
