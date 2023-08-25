package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.domain.maritime.vessel.impl.CommandedValueImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Abstract command, related to steering, like direction / heading / speed
 * typically a command that is given and executed by some nautical officer
 * @generated 
 */
@UMLImplementation(classifier = SteeringCommand.class)
abstract public class SteeringCommandImpl extends CommandedValueImpl implements SteeringCommand  
{
	
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SteeringCommandImpl(){
		super();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SteeringCommandImpl(Time _creationTime, ModelReference _source) {
		super(_creationTime,_source);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SteeringCommandImpl(final SteeringCommand _copy) {
		super(_copy);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.SteeringCommand;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SteeringCommandImpl{" +
		"}";
	}
}
