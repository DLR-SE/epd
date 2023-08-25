package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Mass;
import de.emir.model.universal.units.MassUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Mass.class)
public class MassImpl extends MeasureImpl implements Mass  
{
	
	
	/**
	 *	@generated 
	 */
	private MassUnit mUnit = MassUnit.KILOGRAM;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public MassImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MassImpl(final Mass _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MassImpl(double _value, MassUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Mass;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(MassUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Mass_unit)){
			MassUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Mass_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public MassUnit getUnit() {
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
	public double getAs(final MassUnit targetUnit)
	{
		if (targetUnit == mUnit)
			return mValue;
		return mUnit.getFactorToKilogram() * mValue * targetUnit.getFactorFromKilogram();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final MassUnit unit)
	{
		setValue(value);
		setUnit(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Mass other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final Mass other)
	{
		return mValue < other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final Mass other)
	{
		return mValue > other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final Mass other, final double epsilon)
	{
		return Math.abs(mValue - other.getAs(mUnit)) > epsilon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Mass add(final Mass other)
	{
		Mass copy = new MassImpl(this);
		copy.addLocal(other);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Mass other)
	{
		mValue += other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Mass sub(final Mass other)
	{
		Mass copy = new MassImpl(this);
		copy.subLocal(other);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Mass other)
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
		return mValue + mUnit.getSymbol();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MassImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
