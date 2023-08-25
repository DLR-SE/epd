package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface AutopilotAlarm extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "creationTime", associationType = AssociationType.COMPOSITE)
	public void setCreationTime(Time _creationTime);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "creationTime", associationType = AssociationType.COMPOSITE)
	public Time getCreationTime();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "message", associationType = AssociationType.PROPERTY)
	public void setMessage(String _message);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "message", associationType = AssociationType.PROPERTY)
	public String getMessage();

	
}
