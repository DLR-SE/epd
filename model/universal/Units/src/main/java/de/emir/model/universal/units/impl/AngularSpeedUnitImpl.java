package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AngularSpeedUnit.class)
public class AngularSpeedUnitImpl extends UObjectImpl implements AngularSpeedUnit  
{
	
	
	/**
	 *	@generated 
	 */
	private AngleUnit mAngleUnit = AngleUnit.RADIAN;
	/**
	 *	@generated 
	 */
	private TimeUnit mTimeUnit = TimeUnit.NANOSECOND;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularSpeedUnitImpl(){
		super();
		//set the default values and assign them to this instance 
		setAngleUnit(mAngleUnit);
		setTimeUnit(mTimeUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularSpeedUnitImpl(final AngularSpeedUnit _copy) {
		mAngleUnit = _copy.getAngleUnit();
		mTimeUnit = _copy.getTimeUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularSpeedUnitImpl(AngleUnit _angleUnit, TimeUnit _timeUnit) {
		mAngleUnit = _angleUnit; 
		mTimeUnit = _timeUnit; 
	}
	
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AngularSpeedUnit;
	}

	/**
	 *	@generated 
	 */
	public void setAngleUnit(AngleUnit _angleUnit) {
		if (needNotification(UnitsPackage.Literals.AngularSpeedUnit_angleUnit)){
			AngleUnit _oldValue = mAngleUnit;
			mAngleUnit = _angleUnit;
			notify(_oldValue, mAngleUnit, UnitsPackage.Literals.AngularSpeedUnit_angleUnit, NotificationType.SET);
		}else{
			mAngleUnit = _angleUnit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngleUnit getAngleUnit() {
		return mAngleUnit;
	}

	/**
	 *	@generated 
	 */
	public void setTimeUnit(TimeUnit _timeUnit) {
		if (needNotification(UnitsPackage.Literals.AngularSpeedUnit_timeUnit)){
			TimeUnit _oldValue = mTimeUnit;
			mTimeUnit = _timeUnit;
			notify(_oldValue, mTimeUnit, UnitsPackage.Literals.AngularSpeedUnit_timeUnit, NotificationType.SET);
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

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return mAngleUnit + " * " + mTimeUnit + "^-1";
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final AngleUnit au, final TimeUnit tu)
	{
		mAngleUnit = au;
		mTimeUnit = tu;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final AngularSpeedUnit other)
	{
		set(other.getAngleUnit(), other.getTimeUnit());
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "AngularSpeedUnitImpl{" + getAngleUnit() + " / " + getTimeUnit() +  
		"}";
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AngularSpeedUnit){
			return getAngleUnit() == ((AngularSpeedUnit)obj).getAngleUnit() && getTimeUnit() == ((AngularSpeedUnit)obj).getTimeUnit();
		}
		return false;
	}
}
