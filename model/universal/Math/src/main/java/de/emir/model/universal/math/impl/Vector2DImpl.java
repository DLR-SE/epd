package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @generated
 * @implNote If the length of the vector is 0 the last valid angle will be used. This deviates from legacy behavior where the angle of a vector with length 0 is always Pi/2.
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
@UMLImplementation(classifier = Vector2D.class)
public class Vector2DImpl extends UObjectImpl implements Vector2D, Vector {

	/**
	 *	@generated
	 */
	// Always use setter to change this value. Otherwise, the notifications won't be propagated and angle won't update!
	// Steps for changing this value are: retain angle -> check notification necessity -> change value -> notify
	private double mX = 0.0; // First value "X" of vector.

	/**
	 *	@generated
	 */
	// Always use setter to change this value. Otherwise, the notifications won't be propagated and angle won't update!
	// Steps for changing this value are: retain angle -> check notification necessity -> change value -> notify
	private double mY = 0.0; // Second value "Y" of vector.

	/**
	 * Angle of vector in polar representations. Unit is radian.
	 * @implNote This only for internal representation and may be dirty -> see mAngleDirty flag. Only access this with getAngle().
	 */
	private double mAngle = 0.0; // Unit: rad; Range: [0, 2*Pi]

	private boolean mAngleDirty = false; // if dirty update angle before use.

	/**
	 *	Default constructor
	 *	@generated
	 */
	public Vector2DImpl(){
		super();
	}

	/**
	 *	Default attribute constructor
	 *	@generated not
	 */
	public Vector2DImpl(double _x, double _y) {
		set(_x, _y);
	}

	public Vector2DImpl(double _x, double _y, double _angle, boolean _angleDirty) {
		set(_x, _y);
		mAngle = _angle;
		mAngleDirty = _angleDirty;
	}

	/**
	 * Copy constructor that is capable of retaining the angle
	 * @param _copy object to copy
	 */
	public Vector2DImpl(final Vector2DImpl _copy) {
		this(_copy.mX, _copy.mY, _copy.mAngle, _copy.mAngleDirty);
	}

	/**
	 *	Default copy constructor
	 *	@generated not
	 */
	public Vector2DImpl(final Vector2D _copy) {
		this(_copy.getX(), _copy.getY());
	}
	public Vector2DImpl(double[] v) {
		this(v[0], v[1]);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.Vector2D;
	}

	/**
	 *	@generated not
	 */
	public void setX(double _x) {
		if (Double.isNaN(_x)) {
			throw new IllegalArgumentException("X must not be NaN");
		} else if (Double.isInfinite(_x)) {
			throw new IllegalArgumentException("X must not be infinite");
		}
		retainAngle(_x, getY());
		if (needNotification(MathPackage.Literals.Vector2D_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, MathPackage.Literals.Vector2D_x, NotificationType.SET);
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
			throw new IllegalArgumentException("Y must not be infinite");
		}
		retainAngle(getX(), _y);
		if (needNotification(MathPackage.Literals.Vector2D_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, MathPackage.Literals.Vector2D_y, NotificationType.SET);
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
	 *
	 * @return Angle
	 * @implNote update internal angle if dirty
	 */
	private double getAngle() {
		if (mAngleDirty) {
			double angle = calcAngleFromCartesian(getX(), getY());
			boolean angleIsNotNan = !Double.isNaN(angle); // angle value should never be NaN.
			assert angleIsNotNan : "Internal Angle is not a number";
			mAngle = angle;
			mAngleDirty = false;
		}
		return mAngle;
	}

	/**
	 * Set x and y value at the same time.
	 * @note do not confuse this with set(idx, val)
	 * @param _x new x value
	 * @param _y new y value
	 */
	public void set(final double _x, final double _y){
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
		retainAngle(_x, _y);
		if (needNotification(MathPackage.Literals.Vector2D_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, MathPackage.Literals.Vector2D_x, NotificationType.SET);
		}else{
			mX = _x;
		}
		if (needNotification(MathPackage.Literals.Vector2D_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, MathPackage.Literals.Vector2D_y, NotificationType.SET);
		}else{
			mY = _y;
		}
	}

	//////////////////////////////////////////////////////////////////
	// Operations //
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D add(final Vector2D other) {
		return new Vector2DImpl(getX() + other.getX(), getY() + other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Vector2D other) {
		set(getX() + other.getX(), getY() + other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D sub(final Vector2D other) {
		return new Vector2DImpl(getX() - other.getX(), getY() - other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Vector2D other) {
		set(getX() - other.getX(), getY() - other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D mult(final Vector2D other) {
		return new Vector2DImpl(getX() * other.getX(), getY() * other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final Vector2D other) {
		set(getX() * other.getX(), getY() * other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D mult(final double other) {
		return new Vector2DImpl(mX * other, mY * other);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final double other) {
		set(getX() * other, getY() * other);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D div(final Vector2D other) {
		if (other.getX() == 0.0 || other.getY() == 0.0) { // IllegalArgumentException is thrown here because 0.0 is a unique invalid input
			throw new IllegalArgumentException("Division by Zero");
		}
		return new Vector2DImpl(getX() / other.getX(), getY() / other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 * @throws IllegalArgumentException if input value is 0 or NaN
	 */
	@Override
	public void divLocal(final Vector2D other) {
		if (other.getX() == 0.0 || other.getY() == 0.0) { // IllegalArgumentException is thrown here because 0.0 is a unique invalid input
			throw new IllegalArgumentException("Division by Zero");
		}
		set(getX() / other.getX(), getY() / other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D div(final double scalar) {
		return new Vector2DImpl(mX / scalar, mY / scalar);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void divLocal(final double scalar) {
		if (scalar == 0.0) { // IllegalArgumentException is thrown here because 0.0 is a unique invalid input
			throw new IllegalArgumentException("Division by Zero");
		}
		set(getX() / scalar, getY() / scalar);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void rotateLocalCW(final double angle_radian) {
		double[] sinCos = sinCos(-1 * angle_radian); // sinCos[1] = cos(angle), sinCos[0] = sin(angle), sinCos[2] = normalized angle
		double sine = sinCos[0];
		double cosine = sinCos[1];

		if (getX() == 0.0 && getY() == 0.0) { // rotate just the angle if vector has length 0
			 mAngle = normalizeAngle(getAngle() - angle_radian); // subtract (because CW) angle from current angle
			 mAngleDirty = false; // should be redundant since getAngle should set this flag
		} else {  // Using Rotation Matrix
			double xx = getX() * cosine + getY() * -sine;
			double yy = getX() * sine + getY() * cosine;
			set(xx, yy);
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D rotateCW(final double angle_radian) {
		Vector2D copy = new Vector2DImpl(this);
		copy.rotateLocalCW(angle_radian);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void rotateLocalCCW(final double angle_radian) {
		rotateLocalCW(-1 * angle_radian);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D rotateCCW(final double angle_radian)
	{
		Vector2D copy = new Vector2DImpl(this);
		copy.rotateLocalCCW(angle_radian);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double dot(final Vector2D other) {
		return getX() * other.getX() + getY() * other.getY();
	}

	/**
	 * @inheritDoc 
	 * @generated not
	 * @return normalized copy of this vector
	 */
	@Override
	public Vector2D normalize()
	{
		Vector2D copy = new Vector2DImpl(this);
		copy.normalizeLocal();
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void normalizeLocal()
	{
		double length = getLength();
		if (length == 1.0) {
			return; // no action needed since vector is already normalized
		} else if (length == 0.0){ // length == 0.0
			// if vector has length 0 use last valid angle
			double[] sinCos = sinCos(getAngle());
			set(sinCos[1], sinCos[0]);
		} else { // length != 0.0 && length != 1.0
			// divide current vector by its length and thus create a vector of length 1 that
			// retains its directional information
			double f = 1.0 / length;
			multLocal(f);
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double cross(final Vector2D other) {
		return getX() * other.getY() - getY() * other.getX();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int dimensions() {
		return 2;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double get(final int idx) {
		switch (idx) {
		case 0:
			return getX();
		case 1:
			return getY();
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 * @note do not confuse this with set(x, y)
	 */
	@Override
	public void set(final int idx, final double value) {
		switch (idx) {
		case 0:
			setX(value);
			return;
		case 1:
			setY(value);
			return;
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Vector v) {
		set(v.get(0), v.get(1));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector copy() {
		return new Vector2DImpl(this);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString() {
		return toString();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLength() {
		return Math.sqrt(getSquareLength());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getSquareLength() {
		return getX() * getX() + getY() * getY();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Vector2DImpl{" +
		" x = " + getX() + 
		" y = " + getY() + 
		"}";
	}

	@Override
	public double[] toArray() {
		return new double[]{getX(), getY()};
	}

	@Override
	public boolean equals(Vector2D o, double e) {
		return Math.abs(getX() - o.getX()) < e && Math.abs(getY() - o.getY()) < e;
	}

	//------------------- Util ---------------------------


	/**
	 * Calculate the angle of a 2-dimensional vector defined by x and y
	 * @param x part of vector
	 * @param y part of vector
	 * @return angle in radian (0, 2*pi]
	 */
	private static double calcAngleFromCartesian(double x, double y) {
		double angle = Math.atan2(y, x);  // calculate angle via atan2 resulting in a range of [-pi, pi].
		if (angle <= 0.0) {
			angle = 2*Math.PI + angle; // (0, 2*pi]
		}
		return angle;
	}


	/**
	 * Calculate sin and cos both. This is faster than calculating sin and cos separately.
	 * @param angle in radian
	 * @return double array: [sin, cos].
	 */
	public static double[] sinCos(double angle) {
		double normalizedAngle = normalizeAngle(angle);
		double cos = Math.cos(normalizedAngle);
		double sin = Math.sqrt(1 - cos*cos); // faster than Math.cos
		if (normalizedAngle > Math.PI) { // reconstruct sign
			sin *= -1;
		}

		return new double[]{sin, cos};
	}

	/**
	 * This should be called before x or y are changed.
	 * Method used to retain angle if vector has length of 0.
	 * Also manages pre-computation of angles because it works via the same mechanisms.
	 * @param _x input value of x
	 * @param _y input value of y
	 */
	private void retainAngle(double _x, double _y) {
		if (_x == 0.0 && _y == 0.0) { // retain angle
			if (mAngleDirty) { // use last angle
				double oldX = getX();
				double oldY = getY();
				// if x and y are 0 the last angle shall be retained. So the last angle cannot be dirty
				assert !(oldX == 0.0 && oldY == 0.0) : "Illegal internal angle state";

				mAngle = calcAngleFromCartesian(oldX, oldY);
				mAngleDirty = false;
			}
		}else if (getX() == _x && getY() == _y){
			assert true; // do nothing if nothing is changes.
		}else {
			// do not update angle
			mAngleDirty = true;
		}
	}

	private static double normalizeAngle(double angleToNormalize) {
		double normalizedAngle =  angleToNormalize % (2*Math.PI);  // (-2*Pi, 2*Pi)
		if (normalizedAngle < 0) {
			normalizedAngle += 2*Math.PI; // [0, 2*Pi)
		}
		return normalizedAngle;
	}
}
