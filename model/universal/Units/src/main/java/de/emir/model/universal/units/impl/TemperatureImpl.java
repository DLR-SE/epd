package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Temperature;
import de.emir.model.universal.units.TemperatureUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Temperature.class)
public class TemperatureImpl extends MeasureImpl implements Temperature  
{
	
	
	/**
	 *	@generated 
	 */
	private TemperatureUnit mUnit = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TemperatureImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TemperatureImpl(final Temperature _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TemperatureImpl(double _value, TemperatureUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Temperature;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(TemperatureUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Temperature_unit)){
			TemperatureUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Temperature_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public TemperatureUnit getUnit() {
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
	public double getAs(final TemperatureUnit unit)
	{
		if (unit == mUnit)
			return getValue();
		return getFromKelvin(getKelvin(), unit) ;
	}

	private double getFromKelvin(double kelvin, TemperatureUnit unit) {
		switch(unit){
		case KELVIN: return kelvin;
		case CELSIUS : return kelvin - 273.15;
		case FARENHEIT : return (kelvin * 9.0/5.0) - 459.67;
		}
		return 0;
	}

	private double getKelvin() {
		switch(getUnit()){
		case KELVIN: return getValue();
		case CELSIUS : return getValue() + 273.15;
		case FARENHEIT: return ( getValue() + 459.67) * 5.0/9.0; 
		}
		throw new UnsupportedOperationException("Unknown Unit");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final TemperatureUnit unit)
	{
		setValue(value); setUnit(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Temperature other)
	{
		set(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Temperature add(final Temperature other)
	{
		return add(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Temperature add(final double value, final TemperatureUnit unit)
	{
		return new TemperatureImpl(value + getAs(unit), unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Temperature other)
	{
		addLocal(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final double value, final TemperatureUnit unit)
	{
		set(value + getAs(unit), unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Temperature sub(final Temperature other)
	{
		return sub(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Temperature sub(final double value, final TemperatureUnit unit)
	{
		return new TemperatureImpl(getAs(unit) - value, unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Temperature other)
	{
		subLocal(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final double value, final TemperatureUnit unit)
	{
		set(getAs(unit) - value, unit);
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TemperatureImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
