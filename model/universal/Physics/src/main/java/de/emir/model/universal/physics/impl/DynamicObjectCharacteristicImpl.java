package de.emir.model.universal.physics.impl;

import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.physics.DynamicObjectCharacteristic;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = DynamicObjectCharacteristic.class)
public class DynamicObjectCharacteristicImpl extends CharacteristicImpl implements DynamicObjectCharacteristic  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularVelocity mAngularVelocity = null;
	/**
	 *	@generated 
	 */
	private Velocity mLinearVelocity = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DynamicObjectCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setAngularVelocity(mAngularVelocity);
		setLinearVelocity(mLinearVelocity);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DynamicObjectCharacteristicImpl(final DynamicObjectCharacteristic _copy) {
		super(_copy);
		mAngularVelocity = _copy.getAngularVelocity();
		mLinearVelocity = _copy.getLinearVelocity();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DynamicObjectCharacteristicImpl(AngularVelocity _angularVelocity, Velocity _linearVelocity) {
		super();
		mAngularVelocity = _angularVelocity; 
		mLinearVelocity = _linearVelocity; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.DynamicObjectCharacteristic;
	}

	/**
	 *	@generated 
	 */
	public void setAngularVelocity(AngularVelocity _angularVelocity) {
		Notification<AngularVelocity> notification = basicSet(mAngularVelocity, _angularVelocity, PhysicsPackage.Literals.DynamicObjectCharacteristic_angularVelocity);
		mAngularVelocity = _angularVelocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularVelocity getAngularVelocity() {
		return mAngularVelocity;
	}

	/**
	 *	@generated 
	 */
	public void setLinearVelocity(Velocity _linearVelocity) {
		Notification<Velocity> notification = basicSet(mLinearVelocity, _linearVelocity, PhysicsPackage.Literals.DynamicObjectCharacteristic_linearVelocity);
		mLinearVelocity = _linearVelocity;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Velocity getLinearVelocity() {
		return mLinearVelocity;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Velocity linVel, final AngularVelocity angVal)
	{
		if (linVel != null)
			setLinearVelocity(linVel);
		if (angVal != null)
			setAngularVelocity(angVal);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void calculate(final Coordinate _loc0, final Coordinate _loc1, final Angle _heading0, final Angle _heading1, final Time elapsed)
	{
		Coordinate loc1 = _loc1.get(_loc0.getCrs());
		Distance distance = _loc0.getDistance(loc1);
		Angle azimuth = _loc0.getAzimuth(loc1);
		Speed speed = new SpeedImpl(distance.getValue() / elapsed.getValue(), new SpeedUnitImpl(distance.getUnit(), elapsed.getUnit()));

		if (loc1.dimension() == 2){
			Vector2D dir = ((Vector2D)loc1.getCrs().getDirectionToNorth()).rotateCW(azimuth.getAs(AngleUnit.RADIAN));
			dir.multLocal(speed.getValue());
			Velocity linVel = new VelocityImpl(dir, speed.getUnit(), loc1.getCrs());
			
			Euler e = new EulerImpl(0, 0, (_heading1.getAs(AngleUnit.DEGREE) - _heading0.getAs(AngleUnit.DEGREE)) / elapsed.getValue(), AngleUnit.DEGREE);
			AngularVelocity angVel = new AngularVelocityImpl(e, new AngularSpeedUnitImpl(AngleUnit.DEGREE, elapsed.getUnit()), loc1.getCrs());
			
			set(linVel, angVel);
		}else{
			throw new UnsupportedOperationException("3D Calculation not yet implemented");
		}
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DynamicObjectCharacteristicImpl{" +
		"}";
	}
}
