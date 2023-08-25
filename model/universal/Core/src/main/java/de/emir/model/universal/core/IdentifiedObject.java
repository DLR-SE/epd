package de.emir.model.universal.core;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.NamedElement;
import de.emir.model.universal.core.RSIdentifier;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Any object that can be identified shall inherit from this class 
 * @generated 
 */
@UMLClass(name = "IdentifiedObject", isAbstract = true, parent = NamedElement.class)	
public interface IdentifiedObject extends NamedElement 
{
	/**
	 An identifier that references the (external) definition of the object 
	 * @generated 
	 */
	@UMLProperty(name = "identifier", associationType = AssociationType.COMPOSITE)
	public void setIdentifier(RSIdentifier _identifier);
	/**
	 An identifier that references the (external) definition of the object 
	 * @generated 
	 */
	@UMLProperty(name = "identifier", associationType = AssociationType.COMPOSITE)
	public RSIdentifier getIdentifier();
	/**
	 An alternative name of the object 
	 * @generated 
	 */
	@UMLProperty(name = "allias", associationType = AssociationType.PROPERTY)
	public List<String> getAllias();
	/**
	 Comments on or information about the object 
	 * @generated 
	 */
	@UMLProperty(name = "remarks", associationType = AssociationType.PROPERTY)
	public void setRemarks(String _remarks);
	/**
	 Comments on or information about the object 
	 * @generated 
	 */
	@UMLProperty(name = "remarks", associationType = AssociationType.PROPERTY)
	public String getRemarks();
	/**
	
	 * Member Observer may be used to observe changes of structural features within this object
	 * @deprecated will be removed soon, as not used
	 * @generated 
	 */
	@UMLProperty(name = "observers", associationType = AssociationType.PROPERTY)
	public List<MemberObserver> getObservers();
	/**
	 *	@generated 
	 */
	boolean hasAlias(final String name);
	
}
