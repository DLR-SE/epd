package de.emir.model.universal.core;

import de.emir.model.universal.core.Condition;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *  @deprecated use condition from Scripting services 
 *	@generated 
 */
@UMLClass
@Deprecated
public interface JavaScriptCondition extends Condition 
{
	/**
	 an optional reference value that is provided as function parameter to the script function 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.SHARED)
	public void setReference(UObject _reference);
	/**
	 an optional reference value that is provided as function parameter to the script function 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.SHARED)
	public UObject getReference();
	/**
	 The script to evaluate.
	 * There are two options to define a script:
	 * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term "function"
	 * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called "reference". This method is used if the first word of the script is NOT "function" 
	 * @generated 
	 */
	@UMLProperty(name = "script", associationType = AssociationType.PROPERTY)
	public void setScript(String _script);
	/**
	 The script to evaluate.
	 * There are two options to define a script:
	 * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term "function"
	 * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called "reference". This method is used if the first word of the script is NOT "function" 
	 * @generated 
	 */
	@UMLProperty(name = "script", associationType = AssociationType.PROPERTY)
	public String getScript();

	
}
