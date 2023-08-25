package de.emir.model.universal.core;

import de.emir.model.universal.core.IdentifiedObject;
import java.util.List;

import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * An Observer is used to observe external events and execute some operation if such an event occurs
 * @generated 
 */
@UMLClass(isAbstract = true, parent = IdentifiedObject.class)	
public interface Observer extends IdentifiedObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "operations", associationType = AssociationType.PROPERTY)
	public List<IValueChangeListener> getOperations();

	
}
