package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @generated
 */
@UMLImplementation(classifier = Vector2D.class)
public class Vector2DImpl extends UObjectImpl implements Vector2D, Vector {

	/**
	 *	@generated 
	 */
	private double mX = 0.0;
	/**
	 *	@generated 
	 */
	private double mY = 0.0;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public Vector2DImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Vector2DImpl(final Vector2D _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Vector2DImpl(double _x, double _y) {
		mX = _x;
		mY = _y;
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
	 *	@generated 
	 */
	public void setX(double _x) {
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
	 *	@generated 
	 */
	public void setY(double _y) {
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

	//////////////////////////////////////////////////////////////////
	// Operations //
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D add(final Vector2D other) {
		return new Vector2DImpl(mX + other.getX(), mY + other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Vector2D other) {
		mX += other.getX();
		mY += other.getY();
		if (mX != mX)
			throw new NullPointerException();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D sub(final Vector2D other) {
		return new Vector2DImpl(mX - other.getX(), mY - other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Vector2D other) {
		mX -= other.getX();
		mY -= other.getY();
		
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D mult(final Vector2D other) {
		return new Vector2DImpl(mX * other.getX(), mY * other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final Vector2D other) {
		mX *= other.getX();
		mY *= other.getY();
		if (mX != mX)
			throw new NullPointerException();
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
		mX *= other;
		mY *= other;
		if (mX != mX)
			throw new NullPointerException();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector2D div(final Vector2D other) {
		return new Vector2DImpl(mX / other.getX(), mY / other.getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void divLocal(final Vector2D other) {
		mX /= other.getX();
		mY /= other.getY();
		if (mX != mX)
			throw new NullPointerException();
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
		mX /= scalar;
		mY /= scalar;
		if (mX != mX)
			throw new NullPointerException();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void rotateLocalCW(final double angle_radian) {
		double cos = Math.cos(angle_radian);
		double sin = Math.sin(angle_radian);
		double xx = mX * cos + mY * sin;
		double yy = mX * -sin + mY * cos;
		mX = xx;
		mY = yy;
		if (mX != mX)
			throw new NullPointerException();
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
		double cos = Math.cos(angle_radian);
		double sin = Math.sin(angle_radian);
		double xx = mX * cos + mY * -sin;
		double yy = mX * sin + mY * cos;
		mX = xx;
		mY = yy;
		if (mX != mX)
			throw new NullPointerException();
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
		return mX * other.getX() + mY * other.getY();
	}

	/**
	 * @inheritDoc 
	 * @generated not
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
		double l = getLength();
		if (l != 0.0) {
			double f = 1.0 / getLength();
			multLocal(f);
		}else {
			setX(0.0);
			setY(Double.MIN_VALUE);
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double cross(final Vector2D other) {
		return mX * other.getY() - mY * other.getX();
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
			return mX;
		case 1:
			return mY;
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	/**
	 * @inheritDoc
	 * @generated not
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
		setX(v.get(0));
		setY(v.get(1));
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
		return mX * mX + mY * mY;
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
		return new double[]{mX, mY};
	}

	@Override
	public boolean equals(Vector2D o, double e) {
		return Math.abs(mX - o.getX()) < e && Math.abs(mY - o.getY()) < e;
	}
}
