package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector3D;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.serialization.xml.FastXMLSerializer;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Vector3D.class)
public class Vector3DImpl extends UObjectImpl implements Vector3D , Vector 
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
	 *	Default attribute constructor
	 *	@generated
	 */
	public Vector3DImpl(double _x, double _y, double _z) {
		mX = _x;
		mY = _y;
		mZ = _z;
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
	 *	@generated 
	 */
	public void setX(double _x) {
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
	 *	@generated 
	 */
	public void setY(double _y) {
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
	 *	@generated 
	 */
	public void setZ(double _z) {
		if (needNotification(MathPackage.Literals.Vector3D_z)){
			double _oldValue = mZ;
			mZ = _z;
			notify(_oldValue, mZ, MathPackage.Literals.Vector3D_z, NotificationType.SET);
		}else{
			mZ = _z;
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void set(final double x, final double y, final double z)
	{
		setX(x); setY(y); setZ(z); //use setter to enable observation
	}

	/**
	 *	@generated 
	 */
	public double getZ() {
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
	public Vector3D add(final Vector3D other)
	{
		return new Vector3DImpl(mX + other.getX(), mY + other.getY(), mZ + other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Vector3D other)
	{
		mX += other.getX();
		mY += other.getY();
		mZ += other.getZ();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D sub(final Vector3D other)
	{
		return new Vector3DImpl(mX - other.getX(), mY - other.getY(), mZ - other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Vector3D other)
	{
		mX -= other.getX();
		mY -= other.getY();
		mZ -= other.getZ();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D mult(final Vector3D other)
	{
		return new Vector3DImpl(mX * other.getX(), mY * other.getY(), mZ * other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final Vector3D other)
	{
		mX *= other.getX();
		mY *= other.getY();
		mZ *= other.getZ();
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D mult(final double other)
	{
		return new Vector3DImpl(mX * other, mY * other, mZ * other);
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void multLocal(final double other)
	{
		mX *= other;
		mY *= other;
		mZ *= other;
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector3D div(final Vector3D other)
	{
		return new Vector3DImpl(mX / other.getX(), mY / other.getY(), mZ / other.getZ());
	}

		/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void divLocal(final Vector3D other)
	{
		mX /= other.getX();
		mY /= other.getY();
		mZ /= other.getZ();
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
		 double tempx = ( mY * other.getZ() ) - ( mZ * other.getY() );
		 double tempy = ( mZ * other.getX() ) - ( mX * other.getZ() );
	     mZ = (mX * other.getY()) - (mY * other.getX());
	     mX = tempx;
	     mY = tempy;
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
		double length = getSquareLength();
		if (length != 1.0 && length != 0.0){
			double l = 1.0 / Math.sqrt(length);
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
		//taken from irrlicht engine's vector3d.h
		double cr = Math.cos( mX );
		double sr = Math.sin( mX );
		double cp = Math.cos( mY );
		double sp = Math.sin( mY );
		double cy = Math.cos( mZ );
		double sy = Math.sin( mZ );

		double srsp = sr*sp;
		double crsp = cr*sp;

		double[] pseudoMatrix = new double[]{
			( cp*cy ), ( cp*sy ), ( -sp ),
			( srsp*cy-cr*sy ), ( srsp*sy+cr*cy ), ( sr*cp ),
			( crsp*cy+sr*sy ), ( crsp*sy-sr*cy ), ( cr*cp )};

		double dx = direction.getX(), dy = direction.getY(), dz = direction.getZ();
		return new Vector3DImpl(dx * pseudoMatrix[0] + dy * pseudoMatrix[3] + dz * pseudoMatrix[6],
								dx * pseudoMatrix[1] + dy * pseudoMatrix[4] + dz * pseudoMatrix[7],
								dx * pseudoMatrix[2] + dy * pseudoMatrix[5] + dz * pseudoMatrix[8]);
	}
	
	
	private void rotate(double xAngle, double yAngle, double zAngle) {
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
        
        double vx = mX, vy = mY, vz = mZ;
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
		 return mX * other.getX() + mY * other.getY() + mZ * other.getZ();
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
		case 0: return mX;
		case 1: return mY;
		case 2: return mZ;
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
		return mX * mX + mY * mY + mZ * mZ;
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
		return new double[]{mX, mY, mZ};
	}
}
