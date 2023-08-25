package de.emir.model.application.sense;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true)	
public interface Measurement extends UObject 
{
	/**
	 Time, when the measurement was created 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public void setTimestamp(Time _timestamp);
	/**
	 Time, when the measurement was created 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public Time getTimestamp();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "sender", associationType = AssociationType.SHARED)
	public void setSender(SensorPort _sender);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "sender", associationType = AssociationType.SHARED)
	public SensorPort getSender();
	
}
