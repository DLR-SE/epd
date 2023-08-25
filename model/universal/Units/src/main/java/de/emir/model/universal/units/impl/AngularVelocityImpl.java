package de.emir.model.universal.units.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.model.universal.units.impl.DirectedMeasureImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * AngularVelocity is the instantaneous rate of change of angular displacement with time in a specified direction.
 * \source ISO 19103 Conceptual Schema Language 
 * @generated 
 */
@UMLImplementation(classifier = AngularVelocity.class)
public class AngularVelocityImpl extends DirectedMeasureImpl implements AngularVelocity  
{
	
	
	/**
	 *	@generated 
	 */
	private Euler mValue = new EulerImpl();
	/**
	 *	@generated 
	 */
	private AngularSpeedUnit mUnit = new AngularSpeedUnitImpl();
	/**
	 *	@generated 
	 */
	private CoordinateReferenceSystem mCrs = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AngularVelocityImpl(){
		super();
		//set the default values and assign them to this instance 
		setValue(mValue);
		setUnit(mUnit);
		setCrs(mCrs);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AngularVelocityImpl(final AngularVelocity _copy) {
		super(_copy);
		mValue = _copy.getValue();
		mUnit = _copy.getUnit();
		mCrs = _copy.getCrs();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AngularVelocityImpl(Euler _value, AngularSpeedUnit _unit, CoordinateReferenceSystem _crs) {
		super();
		mValue = _value; 
		mUnit = _unit; 
		mCrs = _crs; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.AngularVelocity;
	}

	/**
	 *	@generated 
	 */
	public void setValue(Euler _value) {
		if (needNotification(UnitsPackage.Literals.AngularVelocity_value)){
			Euler _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, UnitsPackage.Literals.AngularVelocity_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}

	/**
	 *	@generated 
	 */
	public Euler getValue() {
		return mValue;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(AngularSpeedUnit _unit) {
		if (needNotification(UnitsPackage.Literals.AngularVelocity_unit)){
			AngularSpeedUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.AngularVelocity_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeedUnit getUnit() {
		return mUnit;
	}

	/**
	 *	@generated 
	 */
	public void setCrs(CoordinateReferenceSystem _crs) {
		Notification<CoordinateReferenceSystem> notification = basicSet(mCrs, _crs, UnitsPackage.Literals.AngularVelocity_crs);
		mCrs = _crs;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CoordinateReferenceSystem getCrs() {
		return mCrs;
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
		if (getValue() != null && getUnit() != null)
			return getValue().readableString() + getUnit().readableString();
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Euler _value, final AngularSpeedUnit _unit, final CoordinateReferenceSystem crs)
	{
		if (mValue == null)
			setValue(_value);
		else
			mValue.set(_value);
		if (mUnit == null)
			setUnit(_unit);
		else
			getUnit().set(_unit); 
		setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final AngularVelocity other)
	{
		set(other.getValue(), other.getUnit(), other.getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 * @deprecated use integrate
	 */
	@Override
	@Deprecated
	public Euler getChange(final Time elapsed)
	{
		return integrate(elapsed, new EulerImpl());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Euler integrate(final Time elapsed)
	{
		return integrate(elapsed, new EulerImpl());
	}
	
	public Euler integrate(final Time elapsed, Euler target){
		if (target == null)
			target = new EulerImpl(0,0,0,AngleUnit.RADIAN);
		if (mUnit == null || mValue == null){
			target.set(0,0,0,AngleUnit.RADIAN);
			return target;
		}
		AngleUnit au = getUnit().getAngleUnit();
		double dt = elapsed.getAs(getUnit().getTimeUnit());
		double x = mValue.getX() != null ? mValue.getX().getAs(au) : 0;
		double y = mValue.getY() != null ? mValue.getY().getAs(au) : 0;
		double z = mValue.getZ() != null ? mValue.getZ().getAs(au) : 0;		
		target.set(x*dt, y*dt, z*dt, au);
		return target;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed getX()
	{
		return new AngularSpeedImpl(getValue().getX().get(mUnit.getAngleUnit()), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed getY()
	{
		return new AngularSpeedImpl(getValue().getY().get(mUnit.getAngleUnit()), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AngularSpeed getZ()
	{
		Angle z = getValue().getZ();
		if (z != null)
			return new AngularSpeedImpl(z.getAs(mUnit.getAngleUnit()), mUnit);
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Euler getAs(final AngularSpeedUnit targetUnit)
	{
		if (getUnit().equals(targetUnit))
			return getValue();
		double x = getX().getAs(targetUnit);
		double y = getY().getAs(targetUnit);
		double z = getZ().getAs(targetUnit);
		return new EulerImpl(x,y,z,targetUnit.getAngleUnit());
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AngularVelocityImpl{" +
		"}";
	}
}
