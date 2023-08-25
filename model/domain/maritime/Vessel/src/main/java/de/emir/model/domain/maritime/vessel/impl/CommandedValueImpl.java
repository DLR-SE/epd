package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.NotificationType;


/**

 * Represents a generic value that has been commanded
 * (typically by the captain) to be achieved in the future
 * @generated 
 */
@UMLImplementation(classifier = CommandedValue.class)
abstract public class CommandedValueImpl extends UObjectImpl implements CommandedValue  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CommandedValueImpl(){
		super();
		//set the default values and assign them to this instance 
		setCreationTime(mCreationTime);
		setSource(mSource);
	}

	/**
	 Optional source of the command (for example the captain) 
	 * @generated 
	 */
	private ModelReference mSource = null;

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CommandedValueImpl(final CommandedValue _copy) {
		mCreationTime = _copy.getCreationTime();
		mSource = _copy.getSource();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.CommandedValue;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CommandedValueImpl(Time _creationTime, ModelReference _source) {
		mCreationTime = _creationTime; 
		mSource = _source; 
	}

	/**
	 time of creation 
	 * @generated 
	 */
	private Time mCreationTime = null;

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CommandedValueImpl{" +
		"}";
	}

	/**
	 time of creation 
	 * @generated 
	 */
	public void setCreationTime(Time _creationTime) {
		Notification<Time> notification = basicSet(mCreationTime, _creationTime, VesselPackage.Literals.CommandedValue_creationTime);
		mCreationTime = _creationTime;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 time of creation 
	 * @generated 
	 */
	public Time getCreationTime() {
		return mCreationTime;
	}

	/**
	 Optional source of the command (for example the captain) 
	 * @generated 
	 */
	public void setSource(ModelReference _source) {
		Notification<ModelReference> notification = basicSet(mSource, _source, VesselPackage.Literals.CommandedValue_source);
		mSource = _source;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Optional source of the command (for example the captain) 
	 * @generated 
	 */
	public ModelReference getSource() {
		return mSource;
	}
}
