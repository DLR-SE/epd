package de.emir.model.application.sensor.ais.impl;

import java.util.List;

import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.measurements.PositionMeasurement;
import de.emir.model.application.sense.ports.impl.PositionPortImpl;
import de.emir.model.application.sensor.ais.AISMeasurement;
import de.emir.model.application.sensor.ais.AISPort;
import de.emir.model.application.sensor.ais.AisPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AISPort.class)
public class AISPortImpl extends PositionPortImpl implements AISPort  
{
	
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AISPortImpl(){
		super();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AISPortImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Geometry _observedRegion, List<ErrorModel> _errorModel, PositionMeasurement _measurement) {
		super(_name,_allias,_remarks,_observers,_identifier,_observedRegion,_errorModel,_measurement);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AISPortImpl(final AISPort _copy) {
		super(_copy);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public AISMeasurement getAISMeasurement()
	{
		return (AISMeasurement)getMeasurement();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return AisPackage.Literals.AISPort;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AISPortImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
