package de.emir.model.universal.math;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Vector3D extends Vector 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public void setX(double _x);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public double getX();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public void setY(double _y);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public double getY();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public void setZ(double _z);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public double getZ();
	/**
	 *	@generated 
	 */
	void set(final double x, final double y, final double z);

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Vector3D add(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	void addLocal(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	Vector3D sub(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	void subLocal(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	Vector3D mult(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	void multLocal(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	Vector3D mult(final double other);
	
	/**
	 *	@generated 
	 */
	void multLocal(final double other);
	
	/**
	 *	@generated 
	 */
	Vector3D div(final Vector3D other);
	
	/**
	 *	@generated 
	 */
	void divLocal(final Vector3D other);
	/**
	 *	@generated 
	 */
	Vector3D cross(final Vector3D other);
	/**
	 *	@generated 
	 */
	void crossLocal(final Vector3D other);
	/**
	 *	@generated 
	 */
	Vector3D normalize();
	/**
	 *	@generated 
	 */
	void normalizeLocal();
	/**
	 *	@generated 
	 */
	double dot(final Vector3D other);
	/**
	 rotates this instance clock wise (CW) 
	 * @note this actually corresponds to a multiplication with a quaternion build from euler angles
	 *   
	 * @param xAxis rotation around the x - Axis in radians
	 * @param yAxis rotation around the y - axis in radians
	 * @param zAxis rotation around the z - axis in radians
	 * @generated 
	 */
	void rotateLocalCW(final double xAxis, final double yAxis, final double zAxis);
	/**
	 rotates a copy of this vector around the given radians in clock wise order
	 *  @note this actually corresponds to a multiplication with a quaternion build from euler angles
	 *   
	 * @param xAxis rotation around the x - Axis in radians
	 * @param yAxis rotation around the y - axis in radians
	 * @param zAxis rotation around the z - axis in radians
	 *
	 * @generated 
	 */
	Vector3D rotateCW(final double xAxis, final double yAxis, final double zAxis);
	/**
	 rotates this instance counter clock wise (CCW) 
	  * @note this actually corresponds to a multiplication with a quaternion build from euler angles
	 *   
	 * @param xAxis rotation around the x - Axis in radians
	 * @param yAxis rotation around the y - axis in radians
	 * @param zAxis rotation around the z - axis in radians
	 *
	 * @generated 
	 */
	void rotateLocalCCW(final double xAxis, final double yAxis, final double zAxis);
	/**
	 rotates a copy of this vector around the given radians
	 * @note this actually corresponds to a multiplication with a quaternion build from euler angles
	 *   
	 * @param xAxis rotation around the x - Axis in radians
	 * @param yAxis rotation around the y - axis in radians
	 * @param zAxis rotation around the z - axis in radians
	 *
	 * @generated 
	 */
	Vector3D rotateCCW(final double xAxis, final double yAxis, final double zAxis);
	/**
	 Builds a direction vector from (this) rotation vector.
	 * This vector is assumed to be a rotation vector composed of 3 Euler angle rotations, in degrees.
	 * The implementation performs the same calculations as using a matrix to do the rotation.
	 * @param[in] forwards  The direction representing "forwards" which will be rotated by this vector.
	 * @return A direction vector calculated by rotating the forwards direction by the 3 Euler angles (in radian) represented by this vector. 
	 *
	 * @generated 
	 */
	Vector3D getRotationToDirection(final Vector3D direction);
	
	
}
