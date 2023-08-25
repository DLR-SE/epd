package de.emir.model.domain.maritime.vessel.impl;

import java.util.List;

import de.emir.model.domain.maritime.vessel.StandingCommands;
import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**

 * The StandingCommands characteristic holds all currently valid commands
 * which are not related to the machines (e.g. contains no MachineCommands)
 * Commands within this characteristic are typically given by an responsible nautical officer. 
 * @generated 
 */
@UMLImplementation(classifier = StandingCommands.class)
public class StandingCommandsImpl extends VesselCharacteristicImpl implements StandingCommands  
{
	
	
	/**
	 List of currently valid steering commands 
	 * @generated 
	 */
	private List<SteeringCommand> mCommands = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public StandingCommandsImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public StandingCommandsImpl(final StandingCommands _copy) {
		super(_copy);
		mCommands = _copy.getCommands();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public StandingCommandsImpl(List<SteeringCommand> _commands) {
		super();
		mCommands = _commands; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.StandingCommands;
	}

	/**
	 List of currently valid steering commands 
	 * @generated 
	 */
	public List<SteeringCommand> getCommands() {
		if (mCommands == null) {
			mCommands = new UContainmentList<SteeringCommand>(this, VesselPackage.theInstance.getStandingCommands_commands()); 
		}
		return mCommands;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "StandingCommandsImpl{" +
		"}";
	}
}
