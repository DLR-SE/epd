package de.emir.model.application.sensor.ais;

import de.emir.model.application.sense.Emitter;
import de.emir.model.application.sensor.ais.AISEmitterClass;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * An AISEmitter is used to emit / send AISMessages. 
 * @note we distinguish between sensor (AISSensor) and emitter since not all available sensors are able to send own messages, e.g. small vessels like sailing yacht
 * (in fact this would lead to more traffic as the AIS network could handle)
 * @generated 
 */
@UMLClass(parent = Emitter.class)	
public interface AISEmitter extends Emitter 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "sendInFixedInterval", associationType = AssociationType.COMPOSITE)
	public boolean getSendInFixedInterval();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "fixedAISInvterval", associationType = AssociationType.COMPOSITE)
	public Time getFixedAISInvterval();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "sendInFixedInterval", associationType = AssociationType.COMPOSITE)
	public void setSendInFixedInterval(boolean _sendInFixedInterval);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "fixedAISInvterval", associationType = AssociationType.COMPOSITE)
	public void setFixedAISInvterval(Time _fixedAISInvterval);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "aisEmitterClass", associationType = AssociationType.COMPOSITE)
	public void setAisEmitterClass(AISEmitterClass _aisEmitterClass);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "aisEmitterClass", associationType = AssociationType.COMPOSITE)
	public AISEmitterClass getAisEmitterClass();
	
}
