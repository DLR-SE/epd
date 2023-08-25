package de.emir.model.universal.units;

import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Euler")	
public interface Euler extends Orientation, Rotation 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public void setX(Angle _x);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public Angle getX();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public void setY(Angle _y);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public Angle getY();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public void setZ(Angle _z);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public Angle getZ();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 normalizes all angles to a value between 0 and 360 Degree or 0 and 2*PI 
	 * @generated 
	 */
	void normalize();
	
	/**
	 *	@generated 
	 */
	void set(final double x, final double y, final double z, final AngleUnit unit);
	/**
	 *	@generated 
	 */
	void set(final Angle x, final Angle y, final Angle z);
	/**
	 *	@generated 
	 */
	void set(final Euler other);
	/**
	 *	@generated 
	 */
	Euler toEuler();
	/**
	 *	@generated 
	 */
	Quaternion toQuaternion();
	/**
	 *	@generated 
	 */
	Matrix3 toMatrix3();
	
}
