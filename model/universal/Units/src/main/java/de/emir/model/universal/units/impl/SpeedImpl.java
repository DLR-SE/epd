package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * Undirected: distance divided by time
 * \source (oriented) ISO 19130
 * \note for the directed version see Velocity
 * @generated 
 */
@UMLImplementation(classifier = Speed.class)
public class SpeedImpl extends MeasureImpl implements Speed  
{
	
	
	/**
	 *	@generated 
	 */
	private SpeedUnit mUnit = new SpeedUnitImpl(DistanceUnit.METER, TimeUnit.SECOND);

	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpeedImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated not
	 */
	public SpeedImpl(final Speed _copy) {
		super(_copy);
		mUnit = new SpeedUnitImpl(_copy.getUnit());
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated not
	 */
	public SpeedImpl(double _value, SpeedUnit _unit) {
		super(_value);
		mUnit = new SpeedUnitImpl(_unit); 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Speed;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(SpeedUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Speed_unit)){
			SpeedUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Speed_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public SpeedUnit getUnit() {
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
	public double getAs(final SpeedUnit targetUnit)
	{
		if (getUnit().equals(targetUnit))
			return mValue;
		double meter_per_second = mValue * mUnit.getDistanceUnit().getFactorToMeter() / mUnit.getTimeUnit().getFactorToSecond();
		return meter_per_second * targetUnit.getDistanceUnit().getFactorFromMeter() / targetUnit.getTimeUnit().getFactorFromSecond();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final SpeedUnit unit)
	{
		setValue(value);
		if (this.mUnit == null)
			setUnit(new SpeedUnitImpl(unit));
		else{
			this.mUnit.setDistanceUnit(unit.getDistanceUnit());
			this.mUnit.setTimeUnit(unit.getTimeUnit());
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Speed speed)
	{
		set(speed.getValue(), speed.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final Speed other)
	{
		return mValue < other.getAs(getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final Speed other)
	{
		return mValue > other.getAs(getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final Speed other, final double epsilon)
	{
		return Math.abs(mValue - other.getAs(getUnit())) < epsilon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Speed add(final Speed other)
	{
		return new SpeedImpl(mValue + other.getAs(getUnit()), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Speed other)
	{
		mValue += other.getAs(getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Speed sub(final Speed other)
	{
		return new SpeedImpl(mValue - other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Speed other)
	{
		mValue -= other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return getValue() + ": " + getUnit().readableString();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 * @deprecated use integrate instead
	 */
	@Override
	@Deprecated
	public Distance getDistanceOverTime(final Time etablished)
	{
		return integrate(etablished);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Distance integrate(final Time etablished)
	{
		return new DistanceImpl(mValue * etablished.getAs(getUnit().getTimeUnit()), mUnit.getDistanceUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Speed lerp(final Speed other, final double factor)
	{
		if (other == null)
			return new SpeedImpl(this);
		return new SpeedImpl(lerp(getValue(), other.getAs(getUnit()), factor), getUnit());
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SpeedImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
