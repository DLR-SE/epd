package de.emir.model.universal.core;

import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A ModelPath can be used to reference to a position within the model tree or any subtree. 
 * Thereby it is the direct representation of the Pointer (defined in UCore), but without the need
 * of knowing the UStructuralFeatures. 
 *  
 * @generated 
 */
@UMLClass	
public interface ModelPath extends ModelReference 
{
	/**
	 Root element from which the pointerString starts and defines the Tree or SubTree root 
	 * @generated 
	 */
	@UMLProperty(name = "rootInstance", associationType = AssociationType.SHARED)
	public void setRootInstance(UObject _rootInstance);
	/**
	 Root element from which the pointerString starts and defines the Tree or SubTree root 
	 * @generated 
	 */
	@UMLProperty(name = "rootInstance", associationType = AssociationType.SHARED)
	public UObject getRootInstance();
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	@UMLProperty(name = "pointerString", associationType = AssociationType.PROPERTY)
	public void setPointerString(String _pointerString);
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	@UMLProperty(name = "pointerString", associationType = AssociationType.PROPERTY)
	public String getPointerString();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	
	 * Checks the pointerString, if it follows the required grammar and can create a valid pointer. 
	 * @generated 
	 */
	boolean checkPointerString();
	
}
