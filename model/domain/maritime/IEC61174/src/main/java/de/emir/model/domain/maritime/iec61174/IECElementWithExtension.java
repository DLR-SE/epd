package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.Extension;
import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true)	
public interface IECElementWithExtension extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "extensions", associationType = AssociationType.COMPOSITE)
	public List<Extension> getExtensions();
	
}
