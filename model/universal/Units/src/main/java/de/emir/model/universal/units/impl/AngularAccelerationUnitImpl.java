package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AngularAccelerationUnit.class)
public class AngularAccelerationUnitImpl extends UObjectImpl implements AngularAccelerationUnit  
{
	
	
	/**
	 *	@generated 
	 */
	private TimeUnit mTimeUnit = TimeUnit.NANOSECOND;
	/**
	 *	@generated 
	 */
	private AngularSpeedUnit mSpeedUnit = new AngularSpeedUnitImpl();

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularAccelerationUnitImpl(){
		super();
		//set the default values and assign them to this instance 
		setSpeedUnit(mSpeedUnit);
		setTimeUnit(mTimeUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularAccelerationUnitImpl(final AngularAccelerationUnit _copy) {
		mSpeedUnit = _copy.getSpeedUnit();
		mTimeUnit = _copy.getTimeUnit();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularAccelerationUnitImpl(AngularSpeedUnit _speedUnit, TimeUnit _timeUnit) {
		mSpeedUnit = _speedUnit; 
		mTimeUnit = _timeUnit; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof AngularAccelerationUnit == false) return false;
		AngularAccelerationUnit su = (AngularAccelerationUnit)obj;
		if (getTimeUnit() != su.getTimeUnit()) return false;
		if (getSpeedUnit() != null && getSpeedUnit().equals(su.getSpeedUnit()) == false) return false;
		return true;
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AngularAccelerationUnit;
	}

	/**
	 *	@generated 
	 */
	public void setTimeUnit(TimeUnit _timeUnit) {
		if (needNotification(UnitsPackage.Literals.AngularAccelerationUnit_timeUnit)){
			TimeUnit _oldValue = mTimeUnit;
			mTimeUnit = _timeUnit;
			notify(_oldValue, mTimeUnit, UnitsPackage.Literals.AngularAccelerationUnit_timeUnit, NotificationType.SET);
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
	public void setSpeedUnit(AngularSpeedUnit _speedUnit) {
		if (needNotification(UnitsPackage.Literals.AngularAccelerationUnit_speedUnit)){
			AngularSpeedUnit _oldValue = mSpeedUnit;
			mSpeedUnit = _speedUnit;
			notify(_oldValue, mSpeedUnit, UnitsPackage.Literals.AngularAccelerationUnit_speedUnit, NotificationType.SET);
		}else{
			mSpeedUnit = _speedUnit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeedUnit getSpeedUnit() {
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
	* @generated not
	*/
	@Override
	public String toString() {
		return "AngularAccelerationUnitImpl{" + this.getSpeedUnit() + " / " + getTimeUnit() +  
		"}";
	}
}
