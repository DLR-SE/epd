package de.emir.model.universal.math;

import de.emir.model.universal.math.Function1;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface StringFunction1 extends Function1 
{
	/**
	 textual definition of the function. 
	 * The definition string needs to have at least one x or X variable and no other non digit values
	 * for example
	 * log(x) - y * (sqrt(x^cos(x)))
	 * @ntoe x and X will be handled as the same variable
	 * @generated 
	 */
	@UMLProperty(name = "definition", associationType = AssociationType.PROPERTY)
	public void setDefinition(String _definition);
	/**
	 textual definition of the function. 
	 * The definition string needs to have at least one x or X variable and no other non digit values
	 * for example
	 * log(x) - y * (sqrt(x^cos(x)))
	 * @ntoe x and X will be handled as the same variable
	 * @generated 
	 */
	@UMLProperty(name = "definition", associationType = AssociationType.PROPERTY)
	public String getDefinition();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	boolean isValid();
	
}
