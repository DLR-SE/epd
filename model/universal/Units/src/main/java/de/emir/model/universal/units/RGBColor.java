package de.emir.model.universal.units;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "RGBColor")	
public interface RGBColor extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "r", associationType = AssociationType.PROPERTY)
	public void setR(float _r);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "r", associationType = AssociationType.PROPERTY)
	public float getR();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "g", associationType = AssociationType.PROPERTY)
	public void setG(float _g);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "g", associationType = AssociationType.PROPERTY)
	public float getG();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "b", associationType = AssociationType.PROPERTY)
	public void setB(float _b);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "b", associationType = AssociationType.PROPERTY)
	public float getB();
	/**
	 Alpha or transparency value a == 1 no transparency; a == 0 : full transparency
	 * @generated 
	 */
	@UMLProperty(name = "a", associationType = AssociationType.PROPERTY)
	public void setA(float _a);
	/**
	 Alpha or transparency value a == 1 no transparency; a == 0 : full transparency
	 * @generated 
	 */
	@UMLProperty(name = "a", associationType = AssociationType.PROPERTY)
	public float getA();
	public java.awt.Color getNative();

	
}
