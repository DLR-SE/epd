package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.NavigationStatus;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "NavigationInformation", parent = VesselCharacteristic.class)	
public interface NavigationInformation extends VesselCharacteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "status", associationType = AssociationType.PROPERTY)
	public void setStatus(NavigationStatus _status);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "status", associationType = AssociationType.PROPERTY)
	public NavigationStatus getStatus();

	
}
