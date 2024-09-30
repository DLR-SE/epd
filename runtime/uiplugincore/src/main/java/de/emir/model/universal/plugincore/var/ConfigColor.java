package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigColor extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "red", associationType = AssociationType.PROPERTY)
	public void setRed(int _red);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "red", associationType = AssociationType.PROPERTY)
	public int getRed();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "green", associationType = AssociationType.PROPERTY)
	public void setGreen(int _green);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "green", associationType = AssociationType.PROPERTY)
	public int getGreen();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "blue", associationType = AssociationType.PROPERTY)
	public void setBlue(int _blue);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "blue", associationType = AssociationType.PROPERTY)
	public int getBlue();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "alpha", associationType = AssociationType.PROPERTY)
	public void setAlpha(int _alpha);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "alpha", associationType = AssociationType.PROPERTY)
	public int getAlpha();

	
}
