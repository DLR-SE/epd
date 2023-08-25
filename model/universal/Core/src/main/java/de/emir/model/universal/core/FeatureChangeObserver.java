package de.emir.model.universal.core;

import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.core.Observer;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Observes one or more structural features and triggers the operations, if one of the 
 * assigned trigger's fire. 
 * @generated 
 */
@UMLClass(parent = Observer.class)	
public interface FeatureChangeObserver extends Observer 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public void setReference(ModelPath _reference);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public ModelPath getReference();

	
}
