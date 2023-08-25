package de.emir.model.universal.units;
import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.units.Euler;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.model.universal.units.Quaternion;

/**

 * one's position in relation to true north, to points on the compass, or to a specific place or object.
 * \TODO: again what is the orientation defined? Against North or a specific CoordinateSystem?
 * @generated 
 */
@UMLInterface(name = "Orientation")
public interface Orientation extends UObject 
{
	
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
	/**
	 *	@generated 
	 */
	String readableString();
}
