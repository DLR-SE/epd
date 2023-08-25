package de.emir.model.universal.units.impl;


import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Volume;
import de.emir.model.universal.units.VolumeUnit;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Volume.class)
public class VolumeImpl extends MeasureImpl implements Volume  
{
	
	
	/**
	 *	@generated 
	 */
	private VolumeUnit mUnit = VolumeUnit.CUBIC_METER;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public VolumeImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VolumeImpl(final Volume _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VolumeImpl(double _value, VolumeUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 *	@generated 
	 */
	public void setUnit(VolumeUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Volume_unit)){
			VolumeUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Volume_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Volume;
	}

	/**
	 *	@generated 
	 */
	public VolumeUnit getUnit() {
		return mUnit;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public double getAs(final VolumeUnit dst_unit)
	{
		if (dst_unit == getUnit()) return getValue();
		return mUnit.getFactorToCubicMeter() * mValue * dst_unit.getFactorFromCubicMeter();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void set(final double value, final VolumeUnit unit)
	{
		setValue(value);
		setUnit(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void set(final Volume other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean smaller(final Volume other)
	{
		return getAs(other.getUnit()) < other.getValue();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean equals(final Volume other)
	{
		return getAs(other.getUnit()) == other.getValue();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean greater(final Volume other)
	{
		return getAs(other.getUnit()) > other.getValue();
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
		return mValue + "[" + getUnit() + "]";
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VolumeImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
