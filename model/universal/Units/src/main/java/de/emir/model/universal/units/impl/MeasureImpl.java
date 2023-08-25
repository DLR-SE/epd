package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

 * The result from performing the act or process of ascertaining the extent, dimensions, or quantity of some entity.
 * A measure is always associated to a unit of measure. Ratio measures where the two base measures are in the same units (often considered unit-less) should be associated to UnitOfMeasure (same meter/meter for map scale) so that conversions to non-unitless ratios can be accomplished (such as miles/inch).
 * @generated 
 */
@UMLImplementation(classifier = Measure.class)
abstract public class MeasureImpl extends UObjectImpl implements Measure  
{
	
	
	/**
	 The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*
	 * @generated not
	 */
	protected double mValue = 0.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MeasureImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MeasureImpl(final Measure _copy) {
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MeasureImpl(double _value) {
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Measure;
	}

	/**
	 The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*
	 * @generated not
	 */
	public void setValue(double _value) {
		if (_value != mValue) { 
			if (needNotification(UnitsPackage.Literals.Measure_value)){
				double _oldValue = mValue;
				mValue = _value;
				notify(_oldValue, mValue, UnitsPackage.Literals.Measure_value, NotificationType.SET);
			}else{
				mValue = _value;
			}
		}
	}

	/**
	 The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*
	 * @generated 
	 */
	public double getValue() {
		return mValue;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public double roundLocal(final int numDecimals)
	{
		double old = getValue();
		setValue(round(old, numDecimals));
		return old;		
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MeasureImpl{" +
		" value = " + getValue() + 
		"}";
	}
	
	
	
	
	protected double lerp(double a, double b, double f)
	{
	    return a + f * (b - a);
	}

	public static double round(double value, int numDecimals) {
		double mult = Math.pow(10, numDecimals);
		long tmp = (long)((value * mult) + 0.5); //+0.5 for buissness rounding 
		return tmp / mult;
	}
}
