package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector3D;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 * @generated
 * @implNote If the length of the vector is 0 the last valid angle will be used. This deviates from legacy behavior where normalization of a vector of length 0 would cause an error.
 * The default angle is 0.0.
 * When an input to a method would cause a NaN or Inf value within the vector Errors will be thrown by the setter. Input values of methods might not be checked within the method called.
 * @implSpec
 *  <ul>
 *     <li>X and Y Value of the Vector must not be NaN.</li>
 *     <ul>
 * 			<li>Any setter must throw exceptions if input is NaN.</li>
 * 			<li>If an operation results in a NaN in x or y an Exception must be thrown.</li>
 * 			<li>Any setter must throw exceptions if input is inf.</li>
 *   		<li>If an operation results in a inf in x or y an Exception must be thrown.</li>
 * 		</ul>
 * 	</ul>
 */
@UMLImplementation(classifier = Vector3D.class)
public class Vector3DImpl extends UObjectImpl implements Vector3D , Vector 
{
	
	
	/**
	 *	@generated 
	 */
	// Always use setter to change this value. Otherwise, the notifications won't be propagated and angle won't update!
	// Steps for changing this value are: retain angle -> check notification necessity -> change value -> notify
	private double mX = 0.0;
	/**
	 *	@generated 
	 */
	// Always use setter to change this value. Otherwise, the notifications won't be propagated and angle won't update!
	// Steps for changing this value are: retain angle -> check notification necessity -> change value -> notify
	private double mY = 0.0;
	/**
	 *	@generated 
	 */
	// Always use setter to change this value. Otherwise, the notifications won't be propagated and angle won't update!
	// Steps for changing this value are: retain angle -> check notification necessity -> change value -> notify
	private double mZ = 0.0;
	/**
	 * Phi Angle of vector in polar representations on the z-plane. 0.0 touches the x-axis. Unit is radian.
	 * @implNote This only for internal representation and may be dirty -> see mPhiDirty flag. Only access this with getAnglePhi().
	 */
	private boolean mPhiDirty = false;
	private double mAnglePhi = 0.0; // unit: rad; Range: [0, 2*Pi]
	/**
	 * Theta Angle of vector in polar representation. 0.0 touches the z-axis. Unit is radian.
	 * @implNote This only for internal representation and may be dirty -> see mThetaDirty flag. Only access this with getAngleTheta().
	 */
	private double mAngleTheta = 0.0; // unit: rad; Range: [0: Pi]
	private boolean mThetaDirty = false;
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Vector3DImpl(){
		super();
	}
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Vector3DImpl(final Vector3D _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
		mZ = _copy.getZ();
	}
	/**
	 * Copy constructor that is capable of retaining the angle
	 * @param _copy object to copy
	 */
	public Vector3DImpl(final Vector3DImpl _copy) {
		this(_copy.mX, _copy.mY, _copy.mZ, _copy.mAnglePhi, _copy.mPhiDirty, _copy.mAngleTheta, _copy.mThetaDirty);
	}

	/**
	 *	Default attribute constructor
	 *	@generated not
	 */
	public Vector3DImpl(double _x, double _y, double _z) {
		set(_x, _y, _z);
	}

	public Vector3DImpl(double _x, double _y, double _z, double anglePhi, boolean anglePhiDirty, double angleTheta, boolean angleThetaDirty) {
		set(_x, _y, _z);
		mAnglePhi = anglePhi;
		mPhiDirty = anglePhiDirty;
		mAngleTheta = angleTheta;
		mThetaDirty = angleThetaDirty;
	}
	
	public Vector3DImpl(double[] v) {
		this(v[0], v[1], v[2]);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.Vector3D;
	}

	/**
	 *	@generated not
	 */
	public void setX(double _x) {
		if (Double.isNaN(_x)) {
			throw new IllegalArgumentException("x must not be NaN");
		} else if (Double.isInfinite(_x)) {
			throw new IllegalArgumentException("x must not be infinite");
		}
		retainPhi(_x, getY());
		retainTheta(_x, getY(), getZ());
		if (needNotification(MathPackage.Literals.Vector3D_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, MathPackage.Literals.Vector3D_x, NotificationType.SET);
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
	 *	@generated not
	 */
	public void setY(double _y) {
		if (Double.isNaN(_y)) {
			throw new IllegalArgumentException("Y must not be NaN");
		} else if (Double.isInfinite(_y)) {
			throw new IllegalArgumentException("y must not be infinite");
		}
		retainPhi(getX(), _y);
		retainTheta(getX(), _y, getZ());
		if (needNotification(MathPackage.Literals.Vector3D_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, MathPackage.Literals.Vector3D_y, NotificationType.SET);
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
	 *	@generated not
	 */
	public void setZ(double _z) {
		if (Double.isNaN(_z)) {
			throw new IllegalArgumentException("Z must not be NaN");
		} else if (Double.isInfinite(_z)) {
			throw new IllegalArgumentException("Z must not be infinite");
		}
		retainTheta(getX(), getY(), _z); // Phi does not need to be considered
		if (needNotification(MathPackage.Literals.Vector3D_z)){
			double _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, MathPackage.Literals.Vector3D_z, NotificationType.SET);
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
	 * @inheritDoc
	 * @generated not
	 */
	public void set(final double _x, final double _y, final double _z)
	{
		if (Double.isNaN(_x)) {
			throw new IllegalArgumentException("x must not be NaN");
		} else if (Double.isInfinite(_x)) {
			throw new IllegalArgumentException("x must not be infinite");
		}
		if (Double.isNaN(_y)) {
			throw new IllegalArgumentException("y must not be NaN");
		} else if (Double.isInfinite(_y)) {
			throw new IllegalArgumentException("y must not be infinite");
		}
		if (Double.isNaN(_z)) {
			throw new IllegalArgumentException("z must not be NaN");
		} else if (Double.isInfinite(_z)) {
			throw new IllegalArgumentException("z must not be infinite");
		}
		// retain angles
		retainPhi(_x, _y);
		retainTheta(_x, _y, _z);
		// notify
		if (needNotification(MathPackage.Literals.Vector3D_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, MathPackage.Literals.Vector3D_x, NotificationType.SET);
		}else{
			mX = _x;
		}
		if (needNotification(MathPackage.Literals.Vector3D_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, MathPackage.Literals.Vector3D_y, NotificationType.SET);
		}else{
			mY = _y;
		}
		if (needNotification(MathPackage.Literals.Vector3D_z)){
			double _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, MathPackage.Literals.Vector3D_z, NotificationType.SET);
		}else{
			mZ = _z;
		}
	}

	/**
	 *
	 * @return Angle Phi
	 * @implNote update internal angle if dirty
	 */
	public double getAnglePhi() {
		if (mPhiDirty) {
			double angle = calcAnglePhi(getX(), getY());
			boolean angleIsNotNan = !Double.isNaN(angle); // angle value should never be NaN.
			assert angleIsNotNan : "Internal Angle Phi is not a number";
			mAnglePhi = angle;
			mPhiDirty = false;
		}
		return mAnglePhi;
	}

	/**
	 *
	 * @return Angle Theta
	 * @implNote update internal angle if dirty
	 */
	public double getAngleTheta() {
		if (mThetaDirty) {
			double angle = calcAngleTheta(getX(), getY(), getZ());
			boolean angleIsNotNan = !Double.isNaN(angle); // angle value should never be NaN.
			assert angleIsNotNan : "Internal Angle Theta is not a number";
			mAngleTheta = angle;
			mThetaDirty = false;
		}
		return mAngleTheta;
	}

		//////////////////////////////////////////////////////////////////
		//							 Operations							//
		//////////////////////////////////////////////////////////////////
		
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D add(final Vector3D other)
	{
		return new Vector3DImpl(getX() + other.getX(), getY() + other.getY(), getZ() + other.getZ());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Vector3D other)
	{
		set(getX() + other.getX(), getY() + other.getY(), + getZ() + other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D sub(final Vector3D other)
	{
		return new Vector3DImpl(getX() - other.getX(), getY() - other.getY(), getZ() - other.getZ());
	}

	 /**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Vector3D other)
	{
		set(getX() - other.getX(), getY() - other.getY(), getZ() - other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D mult(final Vector3D other)
	{
		return new Vector3DImpl(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final Vector3D other)
	{
		set(getX() * other.getX(), getY() * other.getY(), getZ() * other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D mult(final double other)
	{
		return new Vector3DImpl(getX() * other, getY() * other, getZ() * other);
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final double other)
	{
		set(getX() * other, getY() * other, getZ() * other);
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D div(final Vector3D other)
	{
		if (other.getX() == 0.0 || other.getY() == 0.0 || other.getZ() == 0.0) { // IllegalArgumentException is thrown here because 0.0 is a unique invalid input
			throw new IllegalArgumentException("Division by Zero");
		}
		return new Vector3DImpl(getX() / other.getX(), getY() / other.getY(), getZ() / other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void divLocal(final Vector3D other)
	{
		if (other.getX() == 0.0 || other.getY() == 0.0 || other.getZ() == 0.0) { // IllegalArgumentException is thrown here because 0.0 is a unique invalid input
			throw new IllegalArgumentException("Division by Zero");
		}
		set(getX() / other.getX(), getY() / other.getY(), getZ() / other.getZ());

	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D cross(final Vector3D other)
	{
		Vector3D c = new Vector3DImpl(this);
		c.crossLocal(other);
		return c;
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	public void crossLocal(final Vector3D other)
	{
		 double tempX = ( getY() * other.getZ() ) - ( getZ() * other.getY() );
		 double tempY = ( getZ() * other.getX() ) - ( getX() * other.getZ() );
	     double tempZ = (getX() * other.getY()) - (getY() * other.getX());
	     set(tempX, tempY, tempZ);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D normalize()
	{
		Vector3D c = new Vector3DImpl(this);
		c.normalizeLocal();
		return c;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void normalizeLocal()
	{
		double length = getLength();
		if (length == 1.0) {
			return; // no action needed since vector is already normalized
		} else if (length == 0.0) {
			// if vector has length 0 use last valid angles
			double[] sinCosPhi = Vector2DImpl.sinCos(getAnglePhi()); // recycle optimized sincos from Vector2DImpl
			double[] sinCosTheta = Vector2DImpl.sinCos(getAngleTheta());
			double sinPhi = sinCosPhi[0];
			double cosPhi = sinCosPhi[1];
			double sinTheta = sinCosTheta[0];
			double cosTheta = sinCosTheta[1];

			set(sinTheta*cosPhi, sinTheta*sinPhi, cosTheta);
		} else { // length != 0.0 && length != 1.0
			// divide current vector by its length and thus create a vector of length 1 that
			// retains its directional information
			double l = 1.0 / length;
			multLocal(l);
		}

	}

		
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D rotateCW(final double xAxis, final double yAxis, final double zAxis)
	{
		Vector3D copy = new Vector3DImpl(this);
		copy.rotateLocalCW(xAxis, yAxis, zAxis);
		return copy;
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D rotateCCW(final double xAxis, final double yAxis, final double zAxis)
	{
		Vector3D copy = new Vector3DImpl(this);
		copy.rotateLocalCCW(xAxis, yAxis, zAxis);
		return copy;
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	public void rotateLocalCCW(final double xAxis, final double yAxis, final double zAxis)
	{
		rotate(xAxis, yAxis, zAxis);
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void rotateLocalCW(final double xAxis, final double yAxis, final double zAxis)
	{
		rotate(-xAxis, -yAxis, -zAxis);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D getRotationToDirection(final Vector3D direction)
	{
		return direction.rotateCCW(getX(), getY(), getZ());
	}
	
	
	private void rotate(double xAngle, double yAngle, double zAngle) {
		if (Double.isNaN(xAngle) || Double.isNaN(yAngle) || Double.isNaN(zAngle)) {
			throw new IllegalArgumentException("rotations angles can not be NaN");
		}
		if (Double.isInfinite(xAngle) || Double.isInfinite(yAngle) || Double.isInfinite(zAngle)) {
			throw new IllegalArgumentException("rotations angles can not be Infinite");
		}
		if (getX() == 0.0 && getY() == 0.0 && getZ() == 0.0) { // if vector is 0 vector still do rotation
			// rotate normalized vector, apply normalized vector to this vector and set back to 0 vector
			Vector3D normalizedVector = normalize();
			normalizedVector.rotateLocalCCW(xAngle, yAngle, zAngle);
			// do not use setter to avoid notification for intermediate calculation step
			retainPhi(normalizedVector.getX(), normalizedVector.getY());
			retainTheta(normalizedVector.getX(), normalizedVector.getY(), normalizedVector.getZ());
			mX = normalizedVector.getX();
			mY = normalizedVector.getY();
			mZ = normalizedVector.getZ();
			set(0, 0, 0); // set back to 0 vector but retain angles
		}


		double[] sinCosZ = Vector2DImpl.sinCos(zAngle/2);
		double[] sinCosY = Vector2DImpl.sinCos(yAngle/2);
		double[] sinCosX = Vector2DImpl.sinCos(xAngle/2);
        double sinZ = sinCosZ[0];
        double cosZ = sinCosZ[1];
        double sinY = sinCosY[0];
        double cosY = sinCosY[1];
        double sinX = sinCosX[0];
        double cosX = sinCosX[1];

        // variables used to reduce multiplication calls.
        double cosYXcosZ = cosY * cosZ;
        double sinYXsinZ = sinY * sinZ;
        double cosYXsinZ = cosY * sinZ;
        double sinYXcosZ = sinY * cosZ;

        //using setter to support the callbacks / eAdapters
        double qw = (cosYXcosZ * cosX - sinYXsinZ * sinX);
        double qx = (cosYXcosZ * sinX + sinYXsinZ * cosX);
        double qy = (sinYXcosZ * cosX + cosYXsinZ * sinX);
        double qz = (cosYXsinZ * cosX - sinYXcosZ * sinX);
        
        //normalize quaternion
        double n = 1.0 / (qw*qw + qx*qx + qy*qy + qz*qz);
        qx *= n;
        qy *= n;
        qz *= n;
        qw *= n;
        
        //now we hava a quaternion (qw, qx, qy, qz) build from euler angles
        
        double vx = getX(), vy = getY(), vz = getZ();
        double xx = qw * qw * vx + 2 * qy * qw * vz - 2 * qz * qw * vy + qx * qx
                * vx + 2 * qy * qx * vy + 2 * qz * qx * vz - qz * qz * vx - qy
                * qy * vx;
        double yy = 2 * qx * qy * vx + qy * qy * vy + 2 * qz * qy * vz + 2 * qw
                * qz * vx - qz * qz * vy + qw * qw * vy - 2 * qx * qw * vz - qx
                * qx * vy;
        double zz = 2 * qx * qz * vx + 2 * qy * qz * vy + qz * qz * vz - 2 * qw
                * qy * vx - qy * qy * vz + 2 * qw * qx * vy - qx * qx * vz + qw
                * qw * vz;
        set(xx, yy, zz);        
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public double dot(final Vector3D other)
	{
		 return getX() * other.getX() + getY() * other.getY() + getZ() * other.getZ();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int dimensions()
	{
		return 3;
	}



		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double get(final int idx)
	{
		switch(idx){
		case 0: return getX();
		case 1: return getY();
		case 2: return getZ();
		}
		throw new ArrayIndexOutOfBoundsException();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final int idx, final double value)
	{
		switch(idx){
		case 0: setX(value); return ;
		case 1: setY(value); return ;
		case 2: setZ(value); return ;
		}
		throw new ArrayIndexOutOfBoundsException();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */ 
	@Override
	public void set(final Vector v)
	{
		setX(v.get(0)); setY(v.get(1)); setZ(v.get(2));
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector copy()
	{
		return new Vector3DImpl(this);
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return toString();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLength()
	{
		return Math.sqrt(getSquareLength());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getSquareLength()
	{
		return getX() * getX() + getY() * getY() + getZ() * getZ();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Vector3DImpl{" +
		" x = " + getX() + 
		" y = " + getY() + 
		" z = " + getZ() + 
		"}";
	}

	@Override
	public double[] toArray() {
		return new double[]{getX(), getY(), getZ()};
	}

	//------------------- Util ---------------------------

	/**
	 * Calculate the angle of phi using by x and y
	 * @param x part of vector
	 * @param y part of vector
	 * @return angle in radian (0, 2*pi]
	 */
	private static double calcAnglePhi(double x, double y) {
		double phi = Math.atan2(y, x);  // calculate phi via atan2 resulting in a range of [-pi, pi].
		if (phi <= 0.0) {
			phi = (2*Math.PI + phi); // (0, 2*pi]
		}
		return phi;
	}

	/**
	 * Calculate the angle of theta using by x, y and z
	 * @param x part of vector
	 * @param y part of vector
	 * @param z part of vector
	 * @return angle in radian [0, pi]
	 */
	private static double calcAngleTheta(double x, double y, double z) {
		double theta = Math.acos(z / Math.sqrt(x*x + y*y + z*z));  // calculate theta via acos resulting in a range of [0, pi].

		return theta;
	}
	// Using different methods for phi and theta since sometimes both are not required at the same time
	/**
	 * This should be called before x or y are changed.
	 * Method used to retain angle if vector has length of 0 or angle phi or theta is not defined.
	 * Also manages pre-computation of angles because it works via the same mechanisms.
	 * @param _x input value of x
	 * @param _y input value of y
	 */
	private void retainPhi(double _x, double _y) {
		if (_x == 0.0 && _y == 0.0) { // retain phi
			if(mPhiDirty) { // use last angle
				double oldX = getX();
				double oldY = getY();
				assert !(oldX == 0.0 && oldY == 0.0) : "Illegal internal angle phi state";

				mAnglePhi = calcAnglePhi(oldX, oldY);
				mPhiDirty = false;
			}
		} else if (getX() == _x && getY() == _y){
			assert true; // do nothing if nothing is changes.
		} else {
			// do not update angle
			mPhiDirty = true;
		}
	}

	/**
	 * This should be called before y or z are changed.
	 * Method used to retain angle if vector has length of 0 or angle phi or theta is not defined.
	 * Also manages pre-computation of angles because it works via the same mechanisms.
	 * @param _x input value of x
	 * @param _y input value of y
	 * @param _z input value of z
	 */
	private void retainTheta(double _x, double _y, double _z) {
		if (_x == 0 &&_y == 0.0 && _z == 0.0) { // retain theta
			if(mThetaDirty) { // use last angle
				double oldX = getX();
				double oldY = getY();
				double oldZ = getZ();
				assert !(oldX == 0.0 && oldY == 0.0 && oldZ == 0.0) : "Illegal internal angle theta state";

				mAngleTheta = calcAngleTheta(oldX, oldY, oldZ);
				mThetaDirty = false;
			}
		} else if (getX() == _x && getY() == _y && getZ() == _z){
			assert true; // do nothing if nothing is changes.
		} else {
			// do not update angle
			mThetaDirty = true;
		}

	}
}
