package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.AngularDistance;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

    * Represents a measured distance to detected objects surrounding the vessel.
                            * The AngularDistance is a wrapper for modeling multiple opening degrees for one sensor where
                            * each opening degree is a specific range which is defined by the minimum opening angle and maximum opening angle
                            * which is measured based on the ships relative northern position. It should be used as
                            * part of the ReferencedDistance which specifies the origin point of the distance and the source identifier.
 * @generated 
 */
@UMLImplementation(classifier = AngularDistance.class)
public class AngularDistanceImpl extends UObjectImpl implements AngularDistance  
{
	
	
	/**
	 The minimum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	private Angle mMinAngle = null;
	/**
	 The maximum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	private Angle mMaxAngle = null;
	/**
	 The distance detected by the sensor. It is constrained by the minAngle and maxAngle which specify the FOV. 
	 * @generated 
	 */
	private Distance mDistance = null;
	/**
	 Detection timestamp. 
	 * @generated 
	 */
	private Time mTimestamp = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularDistanceImpl(){
		super();
		//set the default values and assign them to this instance 
		setMinAngle(mMinAngle);
		setMaxAngle(mMaxAngle);
		setDistance(mDistance);
		setTimestamp(mTimestamp);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularDistanceImpl(final AngularDistance _copy) {
		mMinAngle = _copy.getMinAngle();
		mMaxAngle = _copy.getMaxAngle();
		mDistance = _copy.getDistance();
		mTimestamp = _copy.getTimestamp();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularDistanceImpl(Angle _minAngle, Angle _maxAngle, Distance _distance, Time _timestamp) {
		mMinAngle = _minAngle; 
		mMaxAngle = _maxAngle; 
		mDistance = _distance; 
		mTimestamp = _timestamp; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.AngularDistance;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 The minimum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	public void setMinAngle(Angle _minAngle) {
		Notification<Angle> notification = basicSet(mMinAngle, _minAngle, VesselPackage.Literals.AngularDistance_minAngle);
		mMinAngle = _minAngle;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 The minimum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	public Angle getMinAngle() {
		return mMinAngle;
	}
	/**
	 The maximum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	public void setMaxAngle(Angle _maxAngle) {
		Notification<Angle> notification = basicSet(mMaxAngle, _maxAngle, VesselPackage.Literals.AngularDistance_maxAngle);
		mMaxAngle = _maxAngle;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 The maximum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	public Angle getMaxAngle() {
		return mMaxAngle;
	}
	/**
	 The distance detected by the sensor. It is constrained by the minAngle and maxAngle which specify the FOV. 
	 * @generated 
	 */
	public void setDistance(Distance _distance) {
		Notification<Distance> notification = basicSet(mDistance, _distance, VesselPackage.Literals.AngularDistance_distance);
		mDistance = _distance;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 The distance detected by the sensor. It is constrained by the minAngle and maxAngle which specify the FOV. 
	 * @generated 
	 */
	public Distance getDistance() {
		return mDistance;
	}
	/**
	 Detection timestamp. 
	 * @generated 
	 */
	public void setTimestamp(Time _timestamp) {
		Notification<Time> notification = basicSet(mTimestamp, _timestamp, VesselPackage.Literals.AngularDistance_timestamp);
		mTimestamp = _timestamp;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Detection timestamp. 
	 * @generated 
	 */
	public Time getTimestamp() {
		return mTimestamp;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AngularDistanceImpl{" +
		"}";
	}
}
