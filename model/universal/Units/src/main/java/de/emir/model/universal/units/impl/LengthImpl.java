package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * The measure of distance as an integral, i.e. the limit of an infinite sum of distances between points on a curve.  For example the length of curve, the perimeter of a polygon as the length of the boundary.
 * @generated 
 */
@UMLImplementation(classifier = Length.class)
public class LengthImpl extends MeasureImpl implements Length  
{
	
	
	/**
	 *	@generated 
	 */
	private DistanceUnit mUnit = DistanceUnit.METER;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public LengthImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LengthImpl(final Length _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LengthImpl(double _value, DistanceUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Length;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(DistanceUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Length_unit)){
			DistanceUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Length_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public DistanceUnit getUnit() {
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
	public double getAs(final DistanceUnit targetUnit)
	{
		if (targetUnit == mUnit)
			return mValue;
		return mUnit.getFactorToMeter() * mValue * targetUnit.getFactorFromMeter(); 
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final DistanceUnit unit)
	{
		setUnit(unit);
		setValue(value);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Length other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return getValue() + " [" + getUnit().getName() + "]";
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final Length other)
	{
		return mValue < other.getAs(mUnit); 
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final Length other)
	{
		return mValue > other.getAs(mUnit); 
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final Length other)
	{
		return mValue == other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length add(final Length other)
	{
		Length n = new LengthImpl(this);
		n.addLocal(other);
		return n;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Length other)
	{
		setValue(getValue() + other.getAs(getUnit()));
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LengthImpl{" +
		" value = " + getValue() + 
		"}";
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Length other)
	{
		setValue(getValue() - getAs(getUnit()));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length divide(final double factor)
	{
		return new LengthImpl(getValue() / factor, getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void divideLocal(final double factor)
	{
		setValue(getValue() / factor);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length multiply(final double factor)
	{
		return new LengthImpl(getValue() * factor , getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multiplyLocal(final double factor)
	{
		setValue(getValue() * factor);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length lerp(final Length other, final double factor)
	{
		if (other == null)
			return new LengthImpl(this);
		return new LengthImpl(lerp(getValue(), other.getAs(getUnit()), factor), getUnit());
	}


	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length sub(final Length other)
	{
		Length n = new LengthImpl(this);
		n.subLocal(other);
		return n;
	}
}
