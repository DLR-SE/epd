package de.emir.model.application.sensor.ais;

import de.emir.model.application.sense.measurements.PositionMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = PositionMeasurement.class)	
public interface AISMeasurement extends PositionMeasurement 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "messageID", associationType = AssociationType.PROPERTY)
	public void setMessageID(int _messageID);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "messageID", associationType = AssociationType.PROPERTY)
	public int getMessageID();

	
}
