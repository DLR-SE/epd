package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.AccelerationUnit;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AccelerationUnit.class)
public class AccelerationUnitImpl extends UObjectImpl implements AccelerationUnit  
{
	
	
	/**
	 *	@generated 
	 */
	private TimeUnit mTimeUnit = TimeUnit.SECOND;
	/**
	 *	@generated 
	 */
	private SpeedUnit mSpeedUnit = new SpeedUnitImpl(DistanceUnit.METER, TimeUnit.SECOND);
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AccelerationUnitImpl(){
		super();
		//set the default values and assign them to this instance 
		setTimeUnit(mTimeUnit);
		setSpeedUnit(mSpeedUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AccelerationUnitImpl(final AccelerationUnit _copy) {
		mTimeUnit = _copy.getTimeUnit();
		mSpeedUnit = _copy.getSpeedUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AccelerationUnitImpl(TimeUnit _timeUnit, SpeedUnit _speedUnit) {
		mTimeUnit = _timeUnit; 
		mSpeedUnit = _speedUnit; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof AccelerationUnit == false) return false;
		AccelerationUnit au = (AccelerationUnit)obj;
		if (getTimeUnit() != au.getTimeUnit()) return false;
		if (getSpeedUnit() != null && getSpeedUnit().equals(au.getSpeedUnit()) == false) return false;
		return true;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AccelerationUnit;
	}

	/**
	 *	@generated 
	 */
	public void setTimeUnit(TimeUnit _timeUnit) {
		if (needNotification(UnitsPackage.Literals.AccelerationUnit_timeUnit)){
			TimeUnit _oldValue = mTimeUnit;
			mTimeUnit = _timeUnit;
			notify(_oldValue, mTimeUnit, UnitsPackage.Literals.AccelerationUnit_timeUnit, NotificationType.SET);
		}else{
			mTimeUnit = _timeUnit;
		}
	}

	/**
	 *	@generated 
	 */
	public TimeUnit getTimeUnit() {
		return mTimeUnit;
	}

	/**
	 *	@generated 
	 */
	public void setSpeedUnit(SpeedUnit _speedUnit) {
		if (needNotification(UnitsPackage.Literals.AccelerationUnit_speedUnit)){
			SpeedUnit _oldValue = mSpeedUnit;
			mSpeedUnit = _speedUnit;
			notify(_oldValue, mSpeedUnit, UnitsPackage.Literals.AccelerationUnit_speedUnit, NotificationType.SET);
		}else{
			mSpeedUnit = _speedUnit;
		}
	}

	/**
	 *	@generated 
	 */
	public SpeedUnit getSpeedUnit() {
		return mSpeedUnit;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
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
		return "AccelerationUnitImpl{" +
		"}";
	}
	
	@Override
	public double getFactorToMeterPerSquareSecond() {
		return mSpeedUnit.getDistanceUnit().getFactorToMeter() / (mSpeedUnit.getTimeUnit().getFactorToSecond() * mTimeUnit.getFactorToSecond());
	}

	@Override
	public double getFactorFromMeterPerSquareSecond() {
		return mSpeedUnit.getDistanceUnit().getFactorFromMeter() / (mSpeedUnit.getTimeUnit().getFactorFromSecond() * mTimeUnit.getFactorFromSecond());
	}
}
