package de.emir.model.application.sense;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Measurement;
import java.util.List;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * A sensor outputs a piece of information (an observed value), the value itself being represented by an ObservationValue.
 * [SSN] 
 * 
 * @note to observe the occurrence of new measurements, put a listener on the measurement Feature
 * @note the measurement feature is defined in specialized classifiers
 * @generated 
 */
@UMLClass(isAbstract = true, parent = IdentifiedObject.class)	
public interface SensorPort extends IdentifiedObject 
{
	/**
	
	 * describes the region for which the observation is valid.
	 * Is always given relative to the attribute pose of the associated sensor
	 * @generated 
	 */
	@UMLProperty(name = "observedRegion", associationType = AssociationType.PROPERTY)
	public void setObservedRegion(Geometry _observedRegion);
	/**
	
	 * describes the region for which the observation is valid.
	 * Is always given relative to the attribute pose of the associated sensor
	 * @generated 
	 */
	@UMLProperty(name = "observedRegion", associationType = AssociationType.PROPERTY)
	public Geometry getObservedRegion();
	/**
	
	 * optional: expected error models
	 * @generated 
	 */
	@UMLProperty(name = "errorModel", associationType = AssociationType.PROPERTY)
	public List<ErrorModel> getErrorModel();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 replace the current measurement with a new one (convenience method to avoid casting, depending on the port) 
	 * @generated 
	 */
	boolean setCurrentMeasurement(final Measurement measurement);
	
	/**
	 *	@generated 
	 */
	Measurement getLatestMeasurement();
	
}
