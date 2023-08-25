package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.CommandedRudderAngle;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.domain.maritime.vessel.impl.MachineCommandImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * A specific command for a rudder angle change. Directed
 * to only one rudder.
 * @generated 
 */
@UMLImplementation(classifier = CommandedRudderAngle.class)
public class CommandedRudderAngleImpl extends MachineCommandImpl implements CommandedRudderAngle  
{
	
	
	/**
	 *	@generated 
	 */
	private Angle mAngle = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CommandedRudderAngleImpl(){
		super();
		//set the default values and assign them to this instance 
		setAngle(mAngle);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CommandedRudderAngleImpl(final CommandedRudderAngle _copy) {
		super(_copy);
		mAngle = _copy.getAngle();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CommandedRudderAngleImpl(Time _creationTime, ModelReference _source, Angle _angle) {
		super(_creationTime,_source);
		mAngle = _angle; 
	}
	
	public CommandedRudderAngleImpl(ModelReference _source, Angle _angle) {
		this(null, _source, _angle); 
	}
	public CommandedRudderAngleImpl(Angle _angle) {
		this(null, _angle); 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.CommandedRudderAngle;
	}

	/**
	 *	@generated 
	 */
	public void setAngle(Angle _angle) {
		Notification<Angle> notification = basicSet(mAngle, _angle, VesselPackage.Literals.CommandedRudderAngle_angle);
		mAngle = _angle;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getAngle() {
		return mAngle;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		double a = getAngle() != null ? getAngle().getAs(AngleUnit.DEGREE) : Double.NaN;
		return "CommandedRudderAngleImpl{ angle = " + a + "[deg] }";
	}
}
