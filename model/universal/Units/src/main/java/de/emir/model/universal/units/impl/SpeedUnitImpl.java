package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
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
@UMLImplementation(classifier = SpeedUnit.class)
public class SpeedUnitImpl extends UObjectImpl implements SpeedUnit  
{
	
	
	/**
	 *	@generated 
	 */
	private DistanceUnit mDistanceUnit = DistanceUnit.METER;
	/**
	 *	@generated 
	 */
	private TimeUnit mTimeUnit = TimeUnit.NANOSECOND;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpeedUnitImpl(){
		super();
		//set the default values and assign them to this instance 
		setDistanceUnit(mDistanceUnit);
		setTimeUnit(mTimeUnit);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SpeedUnitImpl(final SpeedUnit _copy) {
		mDistanceUnit = _copy.getDistanceUnit();
		mTimeUnit = _copy.getTimeUnit();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SpeedUnitImpl(DistanceUnit _distanceUnit, TimeUnit _timeUnit) {
		mDistanceUnit = _distanceUnit; 
		mTimeUnit = _timeUnit; 
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof SpeedUnit == false) return false;
		SpeedUnit su = (SpeedUnit)obj;
		if (su.getDistanceUnit() != getDistanceUnit()) return false;
		if (su.getTimeUnit() != getTimeUnit()) return false;
		return true;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.SpeedUnit;
	}

	/**
	 *	@generated 
	 */
	public void setDistanceUnit(DistanceUnit _distanceUnit) {
		if (needNotification(UnitsPackage.Literals.SpeedUnit_distanceUnit)){
			DistanceUnit _oldValue = mDistanceUnit;
			mDistanceUnit = _distanceUnit;
			notify(_oldValue, mDistanceUnit, UnitsPackage.Literals.SpeedUnit_distanceUnit, NotificationType.SET);
		}else{
			mDistanceUnit = _distanceUnit;
		}
	}

	/**
	 *	@generated 
	 */
	public DistanceUnit getDistanceUnit() {
		return mDistanceUnit;
	}

	/**
	 *	@generated 
	 */
	public void setTimeUnit(TimeUnit _timeUnit) {
		if (needNotification(UnitsPackage.Literals.SpeedUnit_timeUnit)){
			TimeUnit _oldValue = mTimeUnit;
			mTimeUnit = _timeUnit;
			notify(_oldValue, mTimeUnit, UnitsPackage.Literals.SpeedUnit_timeUnit, NotificationType.SET);
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
	public void set(final DistanceUnit du, final TimeUnit tu)
	{
		setTimeUnit(tu);
		setDistanceUnit(du);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final SpeedUnit unit)
	{
		setDistanceUnit(unit.getDistanceUnit());
		setTimeUnit(unit.getTimeUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return mDistanceUnit + " * " + mTimeUnit + "^-1";
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return readableString(); //"SpeedUnitImpl{" +"}";
	}
}
