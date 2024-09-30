package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigRectangle extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public void setX(int _x);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public int getX();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public void setY(int _y);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public int getY();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "width", associationType = AssociationType.PROPERTY)
	public void setWidth(int _width);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "width", associationType = AssociationType.PROPERTY)
	public int getWidth();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "height", associationType = AssociationType.PROPERTY)
	public void setHeight(int _height);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "height", associationType = AssociationType.PROPERTY)
	public int getHeight();

	
}
