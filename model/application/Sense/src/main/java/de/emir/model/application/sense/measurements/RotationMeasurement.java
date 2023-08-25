package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Measurement that represents the change of the orientation over time, e.g. the rotation. 
 * This could be created by a gyroscope
 * @generated 
 */
@UMLClass(parent = Measurement.class)	
public interface RotationMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.PROPERTY)
	public void setVelocity(AngularVelocity _velocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.PROPERTY)
	public AngularVelocity getVelocity();

	
}
