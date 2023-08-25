package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.Measurement;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Measurement that represents the orientation of the measured object, e.g. heading. 
 * Orientation measures may be created by compass sensors. 
 * @generated 
 */
@UMLClass(parent = Measurement.class)	
public interface OrientationMeasurement extends Measurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "orientation", associationType = AssociationType.PROPERTY)
	public void setOrientation(Orientation _orientation);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "orientation", associationType = AssociationType.PROPERTY)
	public Orientation getOrientation();

	
}
