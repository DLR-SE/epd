package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.CommandedEngineRpm;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.domain.maritime.vessel.impl.MachineCommandImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * A command directed to change the rpm of a single engine
 * @generated 
 */
@UMLImplementation(classifier = CommandedEngineRpm.class)
public class CommandedEngineRpmImpl extends MachineCommandImpl implements CommandedEngineRpm  
{
	
	
	/**
	 *	@generated 
	 */
	private AngularSpeed mRpm = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CommandedEngineRpmImpl(){
		super();
		//set the default values and assign them to this instance 
		setRpm(mRpm);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CommandedEngineRpmImpl(final CommandedEngineRpm _copy) {
		super(_copy);
		mRpm = _copy.getRpm();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CommandedEngineRpmImpl(Time _creationTime, ModelReference _source, AngularSpeed _rpm) {
		super(_creationTime,_source);
		mRpm = _rpm; 
	}
	
	public CommandedEngineRpmImpl(ModelReference _source, AngularSpeed _rpm) {
		this(null, _source, _rpm);
	}
	public CommandedEngineRpmImpl(AngularSpeed _rpm) {
		this(null, _rpm);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.CommandedEngineRpm;
	}

	/**
	 *	@generated 
	 */
	public void setRpm(AngularSpeed _rpm) {
		Notification<AngularSpeed> notification = basicSet(mRpm, _rpm, VesselPackage.Literals.CommandedEngineRpm_rpm);
		mRpm = _rpm;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AngularSpeed getRpm() {
		return mRpm;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		double rpm = getRpm() != null ? getRpm().getAs(AngularSpeedUnit.ROUNDS_PER_MINUTE) : Double.NaN;
		return "CommandedEngineRpmImpl{ RPM = " + rpm + "[rpm] }";
	}
}
