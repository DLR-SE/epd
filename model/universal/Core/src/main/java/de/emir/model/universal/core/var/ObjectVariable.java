package de.emir.model.universal.core.var;

import de.emir.model.universal.core.Variable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Variable.class)	
public interface ObjectVariable extends Variable 
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
	public Object getValue();

	
}
