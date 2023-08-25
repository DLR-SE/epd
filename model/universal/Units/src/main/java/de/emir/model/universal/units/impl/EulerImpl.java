package de.emir.model.universal.units.impl;

import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.Rotation;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Euler.class)
public class EulerImpl extends UObjectImpl implements Euler , Orientation, Rotation 
{
	
	
	/**
	 *	@generated 
	 */
	private Angle mX = null;
	/**
	 *	@generated 
	 */
	private Angle mY = null;
	/**
	 *	@generated 
	 */
	private Angle mZ = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EulerImpl(){
		super();
		//set the default values and assign them to this instance 
		setX(mX);
		setY(mY);
		setZ(mZ);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EulerImpl(final Euler _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
		mZ = _copy.getZ();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EulerImpl(Angle _x, Angle _y, Angle _z) {
		mX = _x; 
		mY = _y; 
		mZ = _z; 
	}
	
	public EulerImpl(double x, double y, double z, AngleUnit unit) {
		this(new AngleImpl(x, unit), new AngleImpl(y, unit), new AngleImpl(z, unit));
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Euler;
	}

	/**
	 *	@generated 
	 */
	public void setX(Angle _x) {
		if (needNotification(UnitsPackage.Literals.Euler_x)){
			Angle _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, UnitsPackage.Literals.Euler_x, NotificationType.SET);
		}else{
			mX = _x;
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getX() {
		return mX;
	}

	/**
	 *	@generated 
	 */
	public void setY(Angle _y) {
		if (needNotification(UnitsPackage.Literals.Euler_y)){
			Angle _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, UnitsPackage.Literals.Euler_y, NotificationType.SET);
		}else{
			mY = _y;
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getY() {
		return mY;
	}

	/**
	 *	@generated 
	 */
	public void setZ(Angle _z) {
		if (needNotification(UnitsPackage.Literals.Euler_z)){
			Angle _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, UnitsPackage.Literals.Euler_z, NotificationType.SET);
		}else{
			mZ = _z;
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getZ() {
		return mZ;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void normalize()
	{
		if (mX != null) 
			mX.normalizeLocal();
		if (mY != null)
			mY.normalizeLocal();
		if (mZ != null)
			mZ.normalizeLocal();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double _x, final double _y, final double _z, final AngleUnit u)
	{
		if (mX != null)	
			getX().set(_x, u); 
		else 
			setX(new AngleImpl(_x, u));
		if (mY != null)	
			getY().set(_y, u); 
		else 
			setY(new AngleImpl(_y, u));
		if (mZ != null)	
			getZ().set(_z, u); 
		else 
			setZ(new AngleImpl(_z, u));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Angle _x, final Angle _y, final Angle _z)
	{
		if (mX == null) setX(_x); else mX.set(_x);
		if (mY == null) setY(_y); else mY.set(_y);
		if (mZ == null) setZ(_z); else mZ.set(_z);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Euler other)
	{
		set(other.getX(), other.getY(), other.getZ());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Euler toEuler()
	{
		return this;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Quaternion toQuaternion()
	{
		Quaternion quat = new QuaternionImpl();
		quat.fromEuler(this);
		return quat;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Matrix3 toMatrix3()
	{
		//TODO: 
		throw new UnsupportedOperationException("toMatrix3 not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return getX().getAs(AngleUnit.DEGREE) + "�; " + getY().getAs(AngleUnit.DEGREE) + "�; " + getZ().getAs(AngleUnit.DEGREE)+"�";
	} 

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D transform2D(final Vector2D other)
	{
		double theta = getZ().getAs(AngleUnit.RADIAN);
		
		double cs = Math.cos(theta);
		double sn = Math.sin(theta);
		double x = other.getX();
		double y = other.getY();
		
		return new Vector2DImpl(x * cs - y * sn, x * sn + y * cs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D transform3D(final Vector3D other)
	{
		return toQuaternion().transform3D(other);
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "EulerImpl{" +
				(getX() != null ? getX().getAs(AngleUnit.DEGREE) : "null") + ", "+
				(getY() != null ? getY().getAs(AngleUnit.DEGREE) : "null") + ", "+
				(getZ() != null ? getZ().getAs(AngleUnit.DEGREE) : "null") + ", "+
		"}";
	}
}
