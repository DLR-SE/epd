package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.AccelerationUnit;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.AccelerationUnitImpl;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * Undirected: Acceleration is the rate of change of velocity as a function of time.
 * In calculus terms, acceleration is the second derivative of position with respect to time or, alternately, the first derivative of the velocity with respect to time.
 * The SI units for acceleration are m / s^2 (meters per second squared or meters per second per second), however it may be defined using other combinations of units, as defined in AccelerationUnit
 *    
 * \source (oriented) ISO 19130 - SD_UndirectedAcceleration
 * \note for the directed version see Velocity
 * @generated 
 */
@UMLImplementation(classifier = Acceleration.class)
public class AccelerationImpl extends MeasureImpl implements Acceleration  
{
	
	
	/**
	 *	@generated 
	 */
	private AccelerationUnit mUnit = new AccelerationUnitImpl();

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AccelerationImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AccelerationImpl(final Acceleration _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AccelerationImpl(double _value, AccelerationUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Acceleration;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(AccelerationUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Acceleration_unit)){
			AccelerationUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Acceleration_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public AccelerationUnit getUnit() {
		return mUnit;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getAs(final AccelerationUnit targetUnit)
	{
		if (targetUnit.equals(mUnit))
			return mValue;
		double m_p_ss = mValue * mUnit.getFactorToMeterPerSquareSecond();
		return m_p_ss * targetUnit.getFactorFromMeterPerSquareSecond();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final AccelerationUnit unit)
	{
		this.mValue = value;
		if (this.mUnit == null)
			this.mUnit = unit;
		else{
			this.mUnit.getSpeedUnit().set(unit.getSpeedUnit());
			this.mUnit.setTimeUnit(unit.getTimeUnit());
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Acceleration other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final Acceleration other)
	{
		return mValue < other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final Acceleration other)
	{
		return mValue > other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final Acceleration other, final double epsilon)
	{
		return Math.abs(mValue - other.getAs(mUnit)) < epsilon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Acceleration add(final Acceleration other)
	{
		return new AccelerationImpl(mValue + other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Acceleration other)
	{
		mValue += other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Acceleration sub(final Acceleration other)
	{
		return new AccelerationImpl(mValue - other.getAs(mUnit), mUnit); 
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Acceleration other)
	{
		mValue -= other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Speed getDeltaSpeedOverTime(final Time etablished)
	{
		double f = etablished.getAs(getUnit().getTimeUnit());
		return new SpeedImpl(mValue * f, mUnit.getSpeedUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		//TODO: 
		throw new UnsupportedOperationException("readableString not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AccelerationImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
