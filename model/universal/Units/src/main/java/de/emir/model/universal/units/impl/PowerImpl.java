package de.emir.model.universal.units.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.Power;
import de.emir.model.universal.units.PowerUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Power.class)
public class PowerImpl extends MeasureImpl implements Power  
{
	
	
	/**
	 *	@generated 
	 */
	private PowerUnit mUnit = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PowerImpl(){
		super();
		//set the default values and assign them to this instance 
		setUnit(mUnit);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PowerImpl(final Power _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PowerImpl(double _value, PowerUnit _unit) {
		super(_value);
		mUnit = _unit; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Power;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setUnit(PowerUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Power_unit)){
			PowerUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Power_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}
	/**
	 *	@generated 
	 */
	public PowerUnit getUnit() {
		return mUnit;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getAs(final PowerUnit dst_unit)
	{
		//TODO: 
		throw new UnsupportedOperationException("getAs not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void set(final double value, final PowerUnit unit)
	{
		//TODO: 
		throw new UnsupportedOperationException("set not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void set(final Power other)
	{
		//TODO: 
		throw new UnsupportedOperationException("set not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean smaller(final Power other)
	{
		//TODO: 
		throw new UnsupportedOperationException("smaller not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean greater(final Power other)
	{
		//TODO: 
		throw new UnsupportedOperationException("greater not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean equals(final Power other)
	{
		//TODO: 
		throw new UnsupportedOperationException("equals not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PowerImpl{" +
		" value = " + getValue() + 
		"}";
	}
}
