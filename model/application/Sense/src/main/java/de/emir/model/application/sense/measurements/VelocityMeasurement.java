package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, 
 * a wind sensor (wind speed), log (speed through water)
 * @generated 
 */
@UMLClass(parent = Measurement.class)	
public interface VelocityMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.PROPERTY)
	public void setVelocity(Velocity _velocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "velocity", associationType = AssociationType.PROPERTY)
	public Velocity getVelocity();

	
}
