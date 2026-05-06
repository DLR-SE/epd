package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.ReferencedDistance;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**

    * Stores the measured distances from the vessel to detected objects in the vessels surroundings.
 * @generated 
 */
@UMLClass(parent = VesselCharacteristic.class)	
public interface DistanceCharacteristic extends VesselCharacteristic 
{
	/**
	 List of ReferenceDistance measurements. The source identifier of each ReferencedDistance should act as the key, i.e. for each source only the most recent ReferencedDistance should exist. 
	 * @generated 
	 */
	@UMLProperty(name = "distances", associationType = AssociationType.COMPOSITE)
	public List<ReferencedDistance> getDistances();
	
}
