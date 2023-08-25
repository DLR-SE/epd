package de.emir.model.application.vehicle;

import de.emir.model.application.vehicle.Track;
import de.emir.model.universal.physics.Characteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = Characteristic.class)	
public interface TrackCharacteristic extends Characteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "tracks", associationType = AssociationType.PROPERTY)
	public List<Track> getTracks();

	
}
