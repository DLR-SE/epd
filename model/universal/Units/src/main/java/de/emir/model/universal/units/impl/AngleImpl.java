package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * The amount of rotation needed to bring one line or plane into coincidence with another, generally measured in radians or degrees.
 * @generated 
 */
@UMLImplementation(classifier = Angle.class)
public class AngleImpl extends MeasureImpl implements Angle  
{
	
	
	/**
	 *	@generated 
	 */
	private AngleUnit mUnit = AngleUnit.RADIAN;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngleImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngleImpl(final Angle _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngleImpl(double _value, AngleUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Angle;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(AngleUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Angle_unit)){
			AngleUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Angle_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngleUnit getUnit() {
		return mUnit;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	public static final double PI_2 = Math.PI * 2;
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void normalizeLocal()
	{
		if (mValue == Double.POSITIVE_INFINITY || mValue == Double.NEGATIVE_INFINITY)
			mValue = 0; //FIXME: but we don't want to run into an endless loop
		if (mUnit == AngleUnit.DEGREE){
			while(mValue < 0) mValue += 360.0;
			while(mValue > 360.0) mValue -= 360.0;
		}else{
			while(mValue < 0) mValue += PI_2;
			while(mValue > PI_2) mValue -= PI_2;
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void normalize180Local()
	{
		if (mUnit == AngleUnit.DEGREE){
			while(mValue < -180) mValue += 360.0;
			while(mValue > 180.0) mValue -= 360.0;
		}else{
			while(mValue < -Math.PI) mValue += PI_2;
			while(mValue > Math.PI) mValue -= PI_2;
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle normalize180()
	{
		Angle c = new AngleImpl(this);
		c.normalize180Local();
		return c;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle normalize()
	{
		Angle c = new AngleImpl(this);
		c.normalizeLocal();
		return c;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double get(final AngleUnit unit)
	{
		return getAs(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getAs(final AngleUnit unit)
	{
		if (unit == this.mUnit)
			return mValue;
		if (unit == AngleUnit.DEGREE && this.mUnit == AngleUnit.RADIAN)
			return Math.toDegrees(mValue);
		if (unit == AngleUnit.DEGREE && this.mUnit == AngleUnit.REVOLUTION)
			return mValue * 360.0;
		if(unit == AngleUnit.RADIAN && this.mUnit == AngleUnit.DEGREE)
			return Math.toRadians(mValue);
		if(unit == AngleUnit.RADIAN && this.mUnit == AngleUnit.REVOLUTION)
			return mValue * PI_2;
		if(unit == AngleUnit.REVOLUTION && this.mUnit == AngleUnit.DEGREE)
			return mValue / 360.0;
		return mValue / PI_2;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final AngleUnit unit)
	{
		setValue(value);
		setUnit(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Angle other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle add(final Angle other)
	{
		return new AngleImpl(mValue + other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle add(final double value, final AngleUnit unit)
	{
		return new AngleImpl(value + getAs(unit), unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Angle other)
	{
		setValue(mValue + other.getAs(mUnit));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final double value, final AngleUnit unit)
	{
		set(value + getAs(unit), unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle sub(final Angle other)
	{
		return new AngleImpl(mValue - other.getAs(mUnit), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle sub(final double value, final AngleUnit unit)
	{
		return new AngleImpl(getAs(unit)-value, unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Angle other)
	{
		setValue(mValue - other.getAs(mUnit));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final double value, final AngleUnit unit)
	{
		setValue(getAs(unit)-value);
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
	* @generated not
	*/
	@Override
	public String toString() {
		return "AngleImpl{" +
		" value = " + getValue() + " unit = " + getUnit() + 
		"}";
	}


}
