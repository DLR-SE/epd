package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.AngularAcceleration;
import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.AngularAccelerationUnitImpl;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * Undirected: AngularAcceleration is the rate of change of the angular velocity as a function of time.
 * In calculus terms, AngularAcceleration is the second derivative of angle with respect to time or, alternately, the first derivative of the angular velocity with respect to time.
 * The SI units for AngularAcceleration are rad / s^2 (radians per second squared or radians per second per second), however it may be defined using other combinations of units, as defined in AngularAccelerationUnit
 *    
 * \source (oriented) ISO 19130 - SD_UndirectedAngularAcceleration
 * \note for the directed version see AngularVelocity
 * @generated 
 */
@UMLImplementation(classifier = AngularAcceleration.class)
public class AngularAccelerationImpl extends MeasureImpl implements AngularAcceleration  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularAccelerationUnit mUnit = new AngularAccelerationUnitImpl();

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularAccelerationImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularAccelerationImpl(final AngularAcceleration _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularAccelerationImpl(double _value, AngularAccelerationUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AngularAcceleration;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(AngularAccelerationUnit _unit) {
		if (needNotification(UnitsPackage.Literals.AngularAcceleration_unit)){
			AngularAccelerationUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.AngularAcceleration_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngularAccelerationUnit getUnit() {
		return mUnit;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getAs(final AngularAccelerationUnit unit)
	{
		//TODO: 
		//  
		//  * returns the angular acceleration in the provided unit 
		//  
		throw new UnsupportedOperationException("getAs not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void set(final double value, final AngularAccelerationUnit unit)
	{
		//TODO: 
		throw new UnsupportedOperationException("set not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void set(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("set not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean smaller(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("smaller not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean greater(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("greater not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean equals(final AngularAcceleration other, final double epsilon)
	{
		//TODO: 
		throw new UnsupportedOperationException("equals not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public AngularAcceleration add(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("add not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void addLocal(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("addLocal not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public AngularAcceleration sub(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("sub not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void subLocal(final AngularAcceleration other)
	{
		//TODO: 
		throw new UnsupportedOperationException("subLocal not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed getDeltaSpeedOverTime(final Time etablished)
	{
		double f = etablished.getAs(getUnit().getTimeUnit());
		AngularSpeed res = new AngularSpeedImpl();
		res.set(mValue * f, mUnit.getSpeedUnit());
		return res;
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public String readableString()
	{
		//TODO: 
		throw new UnsupportedOperationException("readableString not yet implemented");
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "AngularAccelerationImpl{" +
		" value = " + getValue() + "[" + getUnit() + "]" +
		"}";
	}
}
