package de.emir.model.application.sense.measurements.impl;

import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.measurements.TemperatureMeasurement;
import de.emir.model.universal.units.Temperature;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 Measurement containing the temperature of something 
 * @generated 
 */
@UMLImplementation(classifier = TemperatureMeasurement.class)
public class TemperatureMeasurementImpl extends MeasurementImpl implements TemperatureMeasurement  
{
	
	
	/**
	 *	@generated 
	 */
	private Temperature mTemperature = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TemperatureMeasurementImpl(){
		super();
		//set the default values and assign them to this instance 
		setTemperature(mTemperature);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TemperatureMeasurementImpl(final TemperatureMeasurement _copy) {
		super(_copy);
		mTemperature = _copy.getTemperature();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TemperatureMeasurementImpl(Time _timestamp, SensorPort _sender, Temperature _temperature) {
		super(_timestamp,_sender);
		mTemperature = _temperature; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MeasurementsPackage.Literals.TemperatureMeasurement;
	}

	/**
	 *	@generated 
	 */
	public void setTemperature(Temperature _temperature) {
		Notification<Temperature> notification = basicSet(mTemperature, _temperature, MeasurementsPackage.Literals.TemperatureMeasurement_temperature);
		mTemperature = _temperature;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Temperature getTemperature() {
		return mTemperature;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TemperatureMeasurementImpl{" +
		"}";
	}
}
