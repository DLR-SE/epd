package de.emir.model.universal.units.impl;

import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.Rotation;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Quaternion.class)
public class QuaternionImpl extends UObjectImpl implements Quaternion , Orientation, Rotation 
{
	private final static double HALF_PI = Math.PI * 0.5;
	
	/**
	 *	@generated 
	 */
	private double mX = 0.0;
	/**
	 *	@generated 
	 */
	private double mY = 0.0;
	/**
	 *	@generated 
	 */
	private double mZ = 0.0;
	/**
	 *	@generated 
	 */
	private double mW = 0.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public QuaternionImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public QuaternionImpl(final Quaternion _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
		mZ = _copy.getZ();
		mW = _copy.getW();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public QuaternionImpl(double _x, double _y, double _z, double _w) {
		mX = _x;
		mY = _y;
		mZ = _z;
		mW = _w;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Quaternion;
	}

	/**
	 *	@generated 
	 */
	public void setX(double _x) {
		if (needNotification(UnitsPackage.Literals.Quaternion_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, UnitsPackage.Literals.Quaternion_x, NotificationType.SET);
		}else{
			mX = _x;
		}
	}

	/**
	 *	@generated 
	 */
	public double getX() {
		return mX;
	}

	/**
	 *	@generated 
	 */
	public void setY(double _y) {
		if (needNotification(UnitsPackage.Literals.Quaternion_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, UnitsPackage.Literals.Quaternion_y, NotificationType.SET);
		}else{
			mY = _y;
		}
	}

	/**
	 *	@generated 
	 */
	public double getY() {
		return mY;
	}

	/**
	 *	@generated 
	 */
	public void setZ(double _z) {
		if (needNotification(UnitsPackage.Literals.Quaternion_z)){
			double _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, UnitsPackage.Literals.Quaternion_z, NotificationType.SET);
		}else{
			mZ = _z;
		}
	}

	/**
	 *	@generated 
	 */
	public double getZ() {
		return mZ;
	}

	/**
	 *	@generated 
	 */
	public void setW(double _w) {
		if (needNotification(UnitsPackage.Literals.Quaternion_w)){
			double _oldValue = mW;
			mW = _w;
			notify(_oldValue, mW, UnitsPackage.Literals.Quaternion_w, NotificationType.SET);
		}else{
			mW = _w;
		}
	}

	/**
	 *	@generated 
	 */
	public double getW() {
		return mW;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void fromEuler(final Euler e)
	{
		double x = e.getX() != null ? e.getX().getAs(AngleUnit.RADIAN) : 0.0;
		double y = e.getY() != null ? e.getY().getAs(AngleUnit.RADIAN) : 0.0;
		double z = e.getZ() != null ? e.getZ().getAs(AngleUnit.RADIAN) : 0.0;
		set(x,y,z, AngleUnit.RADIAN);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Quaternion multiply(final Quaternion other)
	{
		//TODO: 
		throw new UnsupportedOperationException("multiply not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Quaternion toQuaternion()
	{
		return this;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Euler toEuler()
	{
		return toEuler(null);
	}

	/**
     * <code>toAngles</code> returns this quaternion converted to Euler
     * rotation angles (yaw,roll,pitch).<br/>
     * Note that the result is not always 100% accurate due to the implications of euler angles.
     * @see <a href="http://www.euclideanspace.com/maths/geometry/rotations/conversions/quaternionToEuler/index.htm">http://www.euclideanspace.com/maths/geometry/rotations/conversions/quaternionToEuler/index.htm</a>
     * 
     * @param in instance to store the angles in. if null, a new Euler instance is created
     * @return Euler instance (in, if not null or new EulerImpl)
     */
	public Euler toEuler(Euler in){
		double[] angles = new double[3];

        double sqw = mW * mW;
        double sqx = mX * mX;
        double sqy = mY * mY;
        double sqz = mZ * mZ;
        double unit = sqx + sqy + sqz + sqw; // if normalized is one, otherwise
        // is correction factor
        double test = mX * mY + mZ * mW;
        if (test > 0.499 * unit) { // singularity at north pole
            angles[1] = 2 * Math.atan2(mX, mW);
            angles[2] = HALF_PI;
            angles[0] = 0;
        } else if (test < -0.499 * unit) { // singularity at south pole
            angles[1] = -2 * Math.atan2(mX, mW);
            angles[2] = -HALF_PI;
            angles[0] = 0;
        } else {
            angles[1] = Math.atan2(2 * mY * mW - 2 * mX * mZ, sqx - sqy - sqz + sqw); // roll or heading 
            angles[2] = Math.asin(2 * test / unit); // pitch or attitude
            angles[0] = Math.atan2(2 * mX * mW - 2 * mY * mZ, -sqx + sqy - sqz + sqw); // yaw or bank
        }
        if (in == null){
        	return new EulerImpl(angles[0], angles[1], angles[2], AngleUnit.RADIAN);
        }else
        	in.set(angles[0], angles[1], angles[2], AngleUnit.RADIAN);
        return in;
	}
	/**
	 * @inheritDoc
	 * @generated
	 */
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
	public void set(double _x, double _y, double _z, AngleUnit unit) {
//		taken from com.jme3.math.Quaternion
		double zAngle = _z;
		double yAngle = _y;
		double xAngle = _x;
		if (unit != AngleUnit.RADIAN){
			zAngle = Math.toRadians(_z);
			yAngle = Math.toRadians(_y);
			xAngle = Math.toRadians(_x);
		}
		double angle;
		double sinY, sinZ, sinX, cosY, cosZ, cosX;
        angle = zAngle * 0.5f;
        sinZ = Math.sin(angle);
        cosZ = Math.cos(angle);
        angle = yAngle * 0.5f;
        sinY = Math.sin(angle);
        cosY = Math.cos(angle);
        angle = xAngle * 0.5f;
        sinX = Math.sin(angle);
        cosX = Math.cos(angle);

        // variables used to reduce multiplication calls.
        double cosYXcosZ = cosY * cosZ;
        double sinYXsinZ = sinY * sinZ;
        double cosYXsinZ = cosY * sinZ;
        double sinYXcosZ = sinY * cosZ;

        //using setter to support the callbacks / eAdapters
        setW(cosYXcosZ * cosX - sinYXsinZ * sinX);
        setX(cosYXcosZ * sinX + sinYXsinZ * cosX);
        setY(sinYXcosZ * cosX + cosYXsinZ * sinX);
        setZ(cosYXsinZ * cosX - sinYXcosZ * sinX);

        normalizeLocal();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double x, final double y, final double z, final double w)
	{
		setX(x);
		setY(y);
		setZ(z);
		setW(w);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Quaternion value)
	{
		set(value.getX(), value.getY(), value.getZ(), value.getW());
	}

	public static double invSqrt(double fValue) {
		return (float) (1.0f / Math.sqrt(fValue));
	}
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void normalizeLocal()
	{
		double n = invSqrt(norm());
        mX *= n;
        mY *= n;
        mZ *= n;
        mW *= n;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Quaternion normalize()
	{
		Quaternion q = new QuaternionImpl(this);
		q.normalizeLocal();
		return q;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return getX() + "; " + getY() + "; " + getZ() + "; " + getW();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double norm()
	{
		return mW * mW + mX * mX + mY * mY + mZ * mZ;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D transform2D(final Vector2D v)
	{//TODO: Is this realy valid?
		 double vx = v.getX(), vy = v.getY(), vz = 0;//v.getZ();
         double xx = mW * mW * vx + 2 * mY * mW * vz - 2 * mZ * mW * vy + mX * mX
                 * vx + 2 * mY * mX * vy + 2 * mZ * mX * vz - mZ * mZ * vx - mY
                 * mY * vx;
         double yy = 2 * mX * mY * vx + mY * mY * vy + 2 * mZ * mY * vz + 2 * mW
                 * mZ * vx - mZ * mZ * vy + mW * mW * vy - 2 * mX * mW * vz - mX
                 * mX * vy;
         return new Vector2DImpl(xx, yy);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D transform3D(final Vector3D v)
	{
		 double vx = v.getX(), vy = v.getY(), vz = v.getZ();
         double xx = mW * mW * vx + 2 * mY * mW * vz - 2 * mZ * mW * vy + mX * mX
                 * vx + 2 * mY * mX * vy + 2 * mZ * mX * vz - mZ * mZ * vx - mY
                 * mY * vx;
         double yy = 2 * mX * mY * vx + mY * mY * vy + 2 * mZ * mY * vz + 2 * mW
                 * mZ * vx - mZ * mZ * vy + mW * mW * vy - 2 * mX * mW * vz - mX
                 * mX * vy;
         double zz = 2 * mX * mZ * vx + 2 * mY * mZ * vy + mZ * mZ * vz - 2 * mW
                 * mY * vx - mY * mY * vz + 2 * mW * mX * vy - mX * mX * vz + mW
                 * mW * vz;
         return new Vector3DImpl(xx, yy, zz);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "QuaternionImpl{" +
		" x = " + getX() + 
		" y = " + getY() + 
		" z = " + getZ() + 
		" w = " + getW() + 
		"}";
	}
}
