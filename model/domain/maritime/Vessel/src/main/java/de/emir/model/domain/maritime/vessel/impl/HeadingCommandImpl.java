package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.HeadingCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.units.Angle;
import de.emir.model.domain.maritime.vessel.impl.SteeringCommandImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * The commanded heading, which is not directly depended
 * on any specific rudder angle.
 * @generated 
 */
@UMLImplementation(classifier = HeadingCommand.class)
public class HeadingCommandImpl extends SteeringCommandImpl implements HeadingCommand  
{
	
	
	/**
	 *	@generated 
	 */
	private Angle mHeading = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public HeadingCommandImpl(){
		super();
		//set the default values and assign them to this instance 
		setHeading(mHeading);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public HeadingCommandImpl(final HeadingCommand _copy) {
		super(_copy);
		mHeading = _copy.getHeading();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public HeadingCommandImpl(Time _creationTime, ModelReference _source, Angle _heading) {
		super(_creationTime,_source);
		mHeading = _heading; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.HeadingCommand;
	}

	/**
	 *	@generated 
	 */
	public void setHeading(Angle _heading) {
		Notification<Angle> notification = basicSet(mHeading, _heading, VesselPackage.Literals.HeadingCommand_heading);
		mHeading = _heading;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Angle getHeading() {
		return mHeading;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "HeadingCommandImpl{" +
		"}";
	}
}
