package de.emir.model.universal.core.var;

import de.emir.model.universal.core.Variable;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Variable.class)	
public interface UObjectVariable extends Variable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public void setValue(UObject _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY)
	public UObject getValue();
	
}
