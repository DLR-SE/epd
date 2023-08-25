package de.emir.model.application.sensor.ais;

import de.emir.model.application.sense.ports.PositionPort;
import de.emir.model.application.sensor.ais.AISMeasurement;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(parent = PositionPort.class)	
public interface AISPort extends PositionPort 
{
	/**
	 *	@generated 
	 */
	AISMeasurement getAISMeasurement();

	
}
