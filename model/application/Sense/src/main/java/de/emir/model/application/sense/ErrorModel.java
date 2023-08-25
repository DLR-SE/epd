package de.emir.model.application.sense;

import java.util.List;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**

 * \defgroup SensorErrorModel
 * An error model describes the expected error for parts of the sensor Observation
 * @generated 
 */
@UMLClass(isAbstract = true)	
public interface ErrorModel extends UObject 
{
	/**
	 Expected type of observation 
	 * @generated 
	 */
	@UMLProperty(name = "observationType", associationType = AssociationType.SHARED)
	public void setObservationType(UClassifier _observationType);
	/**
	 Expected type of observation 
	 * @generated 
	 */
	@UMLProperty(name = "observationType", associationType = AssociationType.SHARED)
	public UClassifier getObservationType();
	/**
	 Pointer to features, that will be affected by this error model
	 * each pointer should be positioned, relative to the observation that must inherit the observationType
	 * @generated 
	 */
	@UMLProperty(name = "pointers", associationType = AssociationType.SHARED)
	public List<Pointer> getPointers();
	
}
