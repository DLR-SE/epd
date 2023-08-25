package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.MachineCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.domain.maritime.vessel.impl.CommandedValueImpl;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.units.Time;


/**
 An concrete command for a machine
 * typically this commands are read by an machine and produced 
 * by an controlling instance of the vessel (for example a nautical officer) as result / decomposition of steering commands
 * @generated 
 */
@UMLImplementation(classifier = MachineCommand.class)
abstract public class MachineCommandImpl extends CommandedValueImpl implements MachineCommand  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MachineCommandImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MachineCommandImpl(final MachineCommand _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MachineCommandImpl(Time _creationTime, ModelReference _source) {
		super(_creationTime,_source);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.MachineCommand;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MachineCommandImpl{" +
		"}";
	}
}
