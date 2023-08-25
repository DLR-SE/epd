package de.emir.model.universal.core;

import de.emir.model.universal.core.AbstractInformation;
import de.emir.model.universal.core.IdentifiedObject;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "AbstractFeature", isAbstract = true, parent = IdentifiedObject.class)	
public interface AbstractFeature extends IdentifiedObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "informationObjects", associationType = AssociationType.NONE)
	public List<AbstractInformation> getInformationObjects();
	
}
