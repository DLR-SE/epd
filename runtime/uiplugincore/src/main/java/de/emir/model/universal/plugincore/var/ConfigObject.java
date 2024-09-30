package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigObject extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public void setValue(Object _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public String getValue();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////

	/**
	 *	@generated 
	 */
	int getAsInteger();
	
	/**
	 *	@generated 
	 */
	double getAsDouble();
	
	/**
	 *	@generated 
	 */
	float getAsFloat();
	
	/**
	 *	@generated 
	 */
	long getAsLong();
	
	/**
	 *	@generated 
	 */
	boolean getAsBoolean();
	
}
