package de.emir.model.application.sense;

import de.emir.model.application.sense.Measurement;
import java.util.List;

import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Contains the observed phenomenon / measurement.
 * the associated meta informations (like observedRegion, FeatureOfInterest, ...) are stored within the referenced port
 * @generated 
 */
@UMLClass	
public interface Observation extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public void setTimestamp(Time _timestamp);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public Time getTimestamp();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "measurements", associationType = AssociationType.COMPOSITE)
	public List<Measurement> getMeasurements();
	
}
