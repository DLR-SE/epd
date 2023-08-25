package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement that holds the location of a not further specified object 
 * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional 
 * metadata like used satellites (if Satellite based sensor)
 * @generated 
 */
@UMLClass(parent = Measurement.class)	
public interface LocationMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "location", associationType = AssociationType.PROPERTY)
	public void setLocation(Coordinate _location);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "location", associationType = AssociationType.PROPERTY)
	public Coordinate getLocation();

	
}
