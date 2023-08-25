package de.emir.model.universal.math;

import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Vector4D extends Vector 
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

	
}
