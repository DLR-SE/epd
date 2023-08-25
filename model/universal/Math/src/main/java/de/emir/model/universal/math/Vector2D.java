package de.emir.model.universal.math;

import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Vector2D extends Vector 
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

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Vector2D add(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	void addLocal(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	Vector2D sub(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	void subLocal(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	Vector2D mult(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	void multLocal(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	Vector2D mult(final double other);
	
	/**
	 *	@generated 
	 */
	void multLocal(final double other);
	
	/**
	 *	@generated 
	 */
	Vector2D div(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	void divLocal(final Vector2D other);
	
	/**
	 *	@generated 
	 */
	Vector2D div(final double scalar);
	
	/**
	 *	@generated 
	 */
	void divLocal(final double scalar);
	
	/**
	 rotates this instance clock wise (CW) around the given radian 
	 * @generated 
	 */
	void rotateLocalCW(final double angle_radian);
	
	/**
	 rotates a copy of this vector around the given radian in clock wise order 
	 * @generated 
	 */
	Vector2D rotateCW(final double angle_radian);
	
	/**
	 rotates this instance counter clock wise (CCW) around the given radian 
	 * @generated 
	 */
	void rotateLocalCCW(final double angle_radian);
	
	/**
	 rotates a copy of this vector around the given radian in counter clock wise order 
	 * @generated 
	 */
	Vector2D rotateCCW(final double angle_radian);
	
	/**
	 *	@generated 
	 */
	double dot(final Vector2D other);
	
	/**
	 Performs a cross product calculation with another vector, 
	 * in 2D this produces a scalar
	 * @generated 
	 */
	double cross(final Vector2D other);
	/**
	 *	@generated 
	 */
	Vector2D normalize();
	/**
	 *	@generated 
	 */
	void normalizeLocal();
	
	boolean equals(Vector2D other, double epsilon);
	
}
