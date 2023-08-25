package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector4D;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Vector4D.class)
public class Vector4DImpl extends UObjectImpl implements Vector4D , Vector 
{
	
	
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
	public Vector4DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Vector4DImpl(final Vector4D _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
		mZ = _copy.getZ();
		mW = _copy.getW();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Vector4DImpl(double _x, double _y, double _z, double _w) {
		mX = _x;
		mY = _y;
		mZ = _z;
		mW = _w;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.Vector4D;
	}

	/**
	 *	@generated 
	 */
	public void setX(double _x) {
		if (needNotification(MathPackage.Literals.Vector4D_x)){
			double _oldValue = mX;
			mX = _x;
			notify(_oldValue, mX, MathPackage.Literals.Vector4D_x, NotificationType.SET);
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
		if (needNotification(MathPackage.Literals.Vector4D_y)){
			double _oldValue = mY;
			mY = _y;
			notify(_oldValue, mY, MathPackage.Literals.Vector4D_y, NotificationType.SET);
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
		if (needNotification(MathPackage.Literals.Vector4D_z)){
			double _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, MathPackage.Literals.Vector4D_z, NotificationType.SET);
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
		if (needNotification(MathPackage.Literals.Vector4D_w)){
			double _oldValue = mW;
			mW = _w;
			notify(_oldValue, mW, MathPackage.Literals.Vector4D_w, NotificationType.SET);
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
	public int dimensions()
	{
		return 4;
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double get(final int idx)
	{
		switch(idx){
		case 0: return mX;
		case 1: return mY;
		case 2: return mZ;
		case 3: return mW;
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
		case 0: setX(value);
		case 1: setY(value);
		case 2: setZ(value);
		case 3: setW(value);
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
		setX(v.get(0));
		setY(v.get(1));
		setZ(v.get(2));
		setW(v.get(3)); //Array Out Of Bound Expection wanted, if v is not of instance Vector4
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector copy()
	{
		return new Vector4DImpl(this);
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
		return mX * mX + mY * mY + mZ * mZ + mW * mW;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Vector4DImpl{" +
		" x = " + getX() + 
		" y = " + getY() + 
		" z = " + getZ() + 
		" w = " + getW() + 
		"}";
	}

	@Override
	public double[] toArray() {
		return new double[]{mX, mY, mZ, mW};
	}
}
