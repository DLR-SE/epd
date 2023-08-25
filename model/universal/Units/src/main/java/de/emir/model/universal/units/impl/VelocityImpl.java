package de.emir.model.universal.units.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector4DImpl;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.impl.DirectedMeasureImpl;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * Velocity is the instantaneous rate of change of position with time in a specified direction.  
 * The magnitude of the vector representing a value for velocity is expressed in units of measure appropriate for speed.
 * \source ISO 19103 Conceptual Schema Language 
 * @generated 
 */
@UMLImplementation(classifier = Velocity.class)
public class VelocityImpl extends DirectedMeasureImpl implements Velocity  
{
	
	
	/**
	 *	@generated not
	 */
	private Vector mValue = new Vector2DImpl();
	/**
	 *	@generated 
	 */
	private SpeedUnit mUnit = new SpeedUnitImpl();
	/**
	 *	@generated 
	 */
	private CoordinateReferenceSystem mCrs = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VelocityImpl(){
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
	public VelocityImpl(final Velocity _copy) {
		super(_copy);
		mValue = _copy.getValue();
		mUnit = _copy.getUnit();
		mCrs = _copy.getCrs();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VelocityImpl(Vector _value, SpeedUnit _unit, CoordinateReferenceSystem _crs) {
		super();
		mValue = _value; 
		mUnit = _unit; 
		mCrs = _crs; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Velocity;
	}

	/**
	 *	@generated 
	 */
	public void setValue(Vector _value) {
		Notification<Vector> notification = basicSet(mValue, _value, UnitsPackage.Literals.Velocity_value);
		mValue = _value;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Vector getValue() {
		return mValue;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(SpeedUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Velocity_unit)){
			SpeedUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Velocity_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public SpeedUnit getUnit() {
		return mUnit;
	}

	/**
	 *	@generated 
	 */
	public void setCrs(CoordinateReferenceSystem _crs) {
		Notification<CoordinateReferenceSystem> notification = basicSet(mCrs, _crs, UnitsPackage.Literals.Velocity_crs);
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
	public Vector getAs(final SpeedUnit targetUnit)
	{
		if (this.getUnit().equals(targetUnit))
			return mValue;
		double meter_per_second_f = mUnit.getDistanceUnit().getFactorToMeter() / mUnit.getTimeUnit().getFactorToSecond();
		double f = meter_per_second_f * targetUnit.getDistanceUnit().getFactorFromMeter() / targetUnit.getTimeUnit().getFactorFromSecond();
		if (mValue instanceof Vector2D)
			return ((Vector2D) mValue).mult(f);
		else if (mValue instanceof Vector3D)
			return ((Vector3D)mValue).mult(f);
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector interpolate(final Vector current, final Time etablished)
	{
		//TODO: FIXME: This will always return the distance in meters, is that what we want?
		Vector mps = getAs(SpeedUnit.METER_PER_SECOND);
		double factor = etablished.getAs(TimeUnit.SECOND);
		double vx = 0, vy = 0, vz = 0;
		if (mValue instanceof Vector2D){
			vx = ((Vector2D) mps).getX();
			vy = ((Vector2D) mps).getY();
		}else if (mValue instanceof Vector3D){
			vx = ((Vector3D) mps).getX();
			vy = ((Vector3D) mps).getY();
			vz = ((Vector3D) mps).getZ();
		}
		
		double x = current.get(0) + vx * factor;
		double y = current.get(1) + vy * factor;
		if (current.dimensions() == 2)
			return new Vector2DImpl(x, y);
		else
			return new Vector3DImpl(x, y, current.get(2) + vz * factor);
	}

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
	public Speed getMagnitude()
	{
		if (mValue == null || mUnit == null)
			return null;
		return new SpeedImpl(mValue.getLength(), mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Vector _value, final SpeedUnit _unit, final CoordinateReferenceSystem _crs)
	{
		if (mValue == null) setValue(_value); else getValue().set(_value); 
		if (mUnit == null) setUnit(_unit); else getUnit().set(_unit);
		setCrs(_crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Velocity other)
	{
		set(other.getValue(), other.getUnit(), other.getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Velocity get(final CoordinateReferenceSystem targetCRS)
	{
		CoordinateReferenceSystem crs = getCrs();
		if (crs == null || crs.equals(targetCRS))
			return new VelocityImpl(this);
		Vector v = CRSUtils.transformDirection(getValue(), crs, targetCRS);
		return new VelocityImpl(v, getUnit(), targetCRS);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VelocityImpl{" +
		"}";
	}
}
