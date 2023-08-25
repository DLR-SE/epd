package de.emir.model.universal.physics.impl;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.physics.Force;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.Torque;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.impl.DirectedMeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Force.class)
public class ForceImpl extends DirectedMeasureImpl implements Force  
{
	
	
	/**
	 *	@generated 
	 */
	private Vector mDirection = null;
	/**
	 *	@generated 
	 */
	private Coordinate mOrigin = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ForceImpl(){
		super();
		//set the default values and assign them to this instance 
		setDirection(mDirection);
		setOrigin(mOrigin);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ForceImpl(final Force _copy) {
		super(_copy);
		mDirection = _copy.getDirection();
		mOrigin = _copy.getOrigin();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ForceImpl(Vector _direction, Coordinate _origin) {
		super();
		mDirection = _direction; 
		mOrigin = _origin; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Force;
	}

	/**
	 *	@generated 
	 */
	public void setDirection(Vector _direction) {
		Notification<Vector> notification = basicSet(mDirection, _direction, PhysicsPackage.Literals.Force_direction);
		mDirection = _direction;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Vector getDirection() {
		return mDirection;
	}

	/**
	 *	@generated 
	 */
	public void setOrigin(Coordinate _origin) {
		Notification<Coordinate> notification = basicSet(mOrigin, _origin, PhysicsPackage.Literals.Force_origin);
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

	

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getMagnitude()
	{
		//TODO: 
		throw new UnsupportedOperationException("getMagnitude not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Torque getTorque(final Coordinate atPosition)
	{
		//TODO: 
		throw new UnsupportedOperationException("getTorque not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Force getForceAtPosition(final Coordinate atPosition)
	{
		//TODO: 
		throw new UnsupportedOperationException("getForceAtPosition not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ForceImpl{" +
		"}";
	}
}
