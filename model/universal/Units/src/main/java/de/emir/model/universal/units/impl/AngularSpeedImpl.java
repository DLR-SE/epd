package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * Undirected: angular distance divided by time
 * \source (oriented) ISO 19130
 * \note for the directed version see AngularVelocity
 * @generated 
 */
@UMLImplementation(classifier = AngularSpeed.class)
public class AngularSpeedImpl extends MeasureImpl implements AngularSpeed  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularSpeedUnit mUnit = new AngularSpeedUnitImpl();

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularSpeedImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularSpeedImpl(final AngularSpeed _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularSpeedImpl(double _value, AngularSpeedUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AngularSpeed;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(AngularSpeedUnit _unit) {
		if (needNotification(UnitsPackage.Literals.AngularSpeed_unit)){
			AngularSpeedUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.AngularSpeed_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeedUnit getUnit() {
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
	public double getAs(final AngularSpeedUnit targetUnit)
	{
		if (targetUnit.equals(this.mUnit))
			return mValue;
		if (mUnit.getAngleUnit() == AngleUnit.DEGREE){
			double deg_p_s = mValue / mUnit.getTimeUnit().getFactorToSecond();
			double deg_p_target_time = deg_p_s / targetUnit.getTimeUnit().getFactorFromSecond();
			if (targetUnit.getAngleUnit() == AngleUnit.DEGREE)
				return deg_p_target_time;
			else if (targetUnit.getAngleUnit() == AngleUnit.REVOLUTION)
				return deg_p_target_time / 360.0;
			else
				return Math.toRadians(deg_p_target_time);
		} else if (mUnit.getAngleUnit() == AngleUnit.REVOLUTION) {
			double rev_p_s = mValue / mUnit.getTimeUnit().getFactorToSecond();
			double rev_p_target_time = rev_p_s / targetUnit.getTimeUnit().getFactorFromSecond();
			if (targetUnit.getAngleUnit() == AngleUnit.REVOLUTION)
				return rev_p_target_time;
			else if (targetUnit.getAngleUnit() == AngleUnit.DEGREE)
				return rev_p_target_time * 360.0;
			else
				return rev_p_target_time * (2 * Math.PI);
		} else {
			double rad_p_sec = mValue / mUnit.getTimeUnit().getFactorFromSecond();
			double rad_p_target_time = rad_p_sec * targetUnit.getTimeUnit().getFactorFromSecond();
			if (targetUnit.getAngleUnit() == AngleUnit.RADIAN)
				return rad_p_target_time;
			else if (targetUnit.getAngleUnit() == AngleUnit.REVOLUTION)
				return rad_p_target_time / (2 * Math.PI);
			else
				return Math.toDegrees(rad_p_target_time);
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final AngularSpeedUnit unit)
	{
		this.mValue = value;
		if (this.mUnit == null)
			this.mUnit = unit;
		else{
			this.mUnit.setAngleUnit(unit.getAngleUnit());
			this.mUnit.setTimeUnit(unit.getTimeUnit());
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final AngularSpeed value)
	{
		set(value.getValue(), value.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final AngularSpeed other)
	{
		return mValue < other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final AngularSpeed other)
	{
		return mValue > other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final AngularSpeed other, final double epsilon)
	{
		return Math.abs(mValue - other.getAs(mUnit)) < epsilon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed add(final AngularSpeed other)
	{
		return new AngularSpeedImpl(mValue + other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final AngularSpeed other)
	{
		mValue += other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed sub(final AngularSpeed other)
	{
		return new AngularSpeedImpl(mValue - other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final AngularSpeed other)
	{
		mValue -= other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 * @deprecated use integrate instead
	 */
	@Override
	@Deprecated
	public Angle getDeltaAngleOverTime(final Time etablished)
	{
		return integrate(etablished);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle integrate(final Time etablished)
	{
		double et = etablished.getAs(mUnit.getTimeUnit());
		return new AngleImpl(mValue * et, mUnit.getAngleUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return mValue + " " + getUnit().readableString();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AngularSpeedImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
