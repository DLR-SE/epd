package de.emir.model.domain.maritime.iec61174.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.domain.maritime.iec61174.impl.IECElementWithExtensionImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Waypoint.class)
public class WaypointImpl extends IECElementWithExtensionImpl implements Waypoint  
{
	
	
	/**
	 *	@generated 
	 */
	private int mId = 0;
	/**
	 *	@generated 
	 */
	private String mName = "";
	/**
	 *	@generated 
	 */
	private double mRadius = 0.0;
	/**
	 *	@generated 
	 */
	private int mRevision = 0;
	/**
	 *	@generated 
	 */
	private Coordinate mPosition = null;
	/**
	 *	@generated 
	 */
	private Leg mLeg = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WaypointImpl(){
		super();
		//set the default values and assign them to this instance 
		setPosition(mPosition);
		setLeg(mLeg);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WaypointImpl(final Waypoint _copy) {
		super(_copy);
		mId = _copy.getId();
		mName = _copy.getName();
		mRadius = _copy.getRadius();
		mRevision = _copy.getRevision();
		mPosition = _copy.getPosition();
		mLeg = _copy.getLeg();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WaypointImpl(List<Extension> _extensions, int _id, String _name, double _radius, int _revision, Coordinate _position, Leg _leg) {
		super(_extensions);
		mId = _id;
		mName = _name;
		mRadius = _radius;
		mRevision = _revision;
		mPosition = _position; 
		mLeg = _leg; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.Waypoint;
	}

	/**
	 *	@generated 
	 */
	public void setId(int _id) {
		if (needNotification(Iec61174Package.Literals.Waypoint_id)){
			int _oldValue = mId;
			mId = _id;
			notify(_oldValue, mId, Iec61174Package.Literals.Waypoint_id, NotificationType.SET);
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
	public void setName(String _name) {
		if (needNotification(Iec61174Package.Literals.Waypoint_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, Iec61174Package.Literals.Waypoint_name, NotificationType.SET);
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
	public void setRadius(double _radius) {
		if (needNotification(Iec61174Package.Literals.Waypoint_radius)){
			double _oldValue = mRadius;
			mRadius = _radius;
			notify(_oldValue, mRadius, Iec61174Package.Literals.Waypoint_radius, NotificationType.SET);
		}else{
			mRadius = _radius;
		}
	}

	/**
	 *	@generated 
	 */
	public double getRadius() {
		return mRadius;
	}

	/**
	 *	@generated 
	 */
	public void setRevision(int _revision) {
		if (needNotification(Iec61174Package.Literals.Waypoint_revision)){
			int _oldValue = mRevision;
			mRevision = _revision;
			notify(_oldValue, mRevision, Iec61174Package.Literals.Waypoint_revision, NotificationType.SET);
		}else{
			mRevision = _revision;
		}
	}

	/**
	 *	@generated 
	 */
	public int getRevision() {
		return mRevision;
	}

	/**
	 *	@generated 
	 */
	public void setPosition(Coordinate _position) {
		Notification<Coordinate> notification = basicSet(mPosition, _position, Iec61174Package.Literals.Waypoint_position);
		mPosition = _position;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getPosition() {
		return mPosition;
	}

	/**
	 *	@generated 
	 */
	public void setLeg(Leg _leg) {
		Notification<Leg> notification = basicSet(mLeg, _leg, Iec61174Package.Literals.Waypoint_leg);
		mLeg = _leg;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Leg getLeg() {
		return mLeg;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WaypointImpl{" +
		" id = " + getId() + 
		" name = " + getName() + 
		" radius = " + getRadius() + 
		" revision = " + getRevision() + 
		"}";
	}
}
