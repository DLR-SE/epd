package de.emir.model.universal.spatial.impl;

import javax.xml.namespace.QName;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.delegate.IPoseDelegationInterface;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Pose.class)
public class PoseImpl extends UObjectImpl implements Pose  
{
	
	
	/**
	 *	@generated 
	 */
	private Coordinate mCoordinate = new CoordinateImpl();
	/**
	 *	@generated 
	 */
	private Orientation mOrientation = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PoseImpl(){
		super();
		//set the default values and assign them to this instance 
		setCoordinate(mCoordinate);
		setOrientation(mOrientation);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PoseImpl(final Pose _copy) {
		mCoordinate = _copy.getCoordinate();
		mOrientation = _copy.getOrientation();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PoseImpl(Coordinate _coordinate, Orientation _orientation) {
		mCoordinate = _coordinate; 
		mOrientation = _orientation; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.Pose;
	}

	/**
	 *	@generated 
	 */
	public void setCoordinate(Coordinate _coordinate) {
		if (needNotification(SpatialPackage.Literals.Pose_coordinate)){
			Coordinate _oldValue = mCoordinate;
			mCoordinate = _coordinate;
			notify(_oldValue, mCoordinate, SpatialPackage.Literals.Pose_coordinate, NotificationType.SET);
		}else{
			mCoordinate = _coordinate;
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getCoordinate() {
		return mCoordinate;
	}

	/**
	 *	@generated 
	 */
	public void setOrientation(Orientation _orientation) {
		Notification<Orientation> notification = basicSet(mOrientation, _orientation, SpatialPackage.Literals.Pose_orientation);
		mOrientation = _orientation;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Orientation getOrientation() {
		return mOrientation;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public String readableString()
	{
		return toString();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Pose copy()
	{
		return new PoseImpl(this);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void set(final Coordinate coord, final Orientation ori)
	{
		if (mCoordinate == null)
			setCoordinate(coord);
		else
			mCoordinate.set(coord);
		
		if (mOrientation == null)
			setOrientation(ori);
		else{
			if (mOrientation instanceof Euler)
				((Euler)mOrientation).set(ori.toEuler());
			else
				((Quaternion)mOrientation).set(ori.toQuaternion());
		}
		
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "PoseImpl{" + getCoordinate() + "; " + getOrientation() + "}";
	}
}
