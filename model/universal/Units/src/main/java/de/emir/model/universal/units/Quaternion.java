package de.emir.model.universal.units;

import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Quaternion")	
public interface Quaternion extends Orientation, Rotation 
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
	@UMLProperty(name = "w", associationType = AssociationType.PROPERTY)
	public void setW(double _w);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "w", associationType = AssociationType.PROPERTY)
	public double getW();
	/**
	 *	@generated 
	 */
	void fromEuler(final Euler e);
	/**
	 *	@generated 
	 */
	Quaternion multiply(final Quaternion other);
	/**
	 *	@generated 
	 */
	Quaternion toQuaternion();
	/**
	 *	@generated 
	 */
	Euler toEuler();
	/**
	 *	@generated 
	 */
	Matrix3 toMatrix3();
	/**
	 set the orientation from Euler angles 
	 * @generated 
	 */
	void set(final double x, final double y, final double z, final AngleUnit unit);
	/**
	 convenience method to set all variables at once 
	 * @generated 
	 */
	void set(final double x, final double y, final double z, final double w);
	/**
	 *	@generated 
	 */
	void set(final Quaternion value);
	/**
	 *	@generated 
	 */
	void normalizeLocal();
	/**
	 *	@generated 
	 */
	Quaternion normalize();
	/**
	 
	 * returns the norm of this quaternion. This is the dot
	     * product of this quaternion with itself.
	*
	 * @generated 
	 */
	double norm();
	
}
