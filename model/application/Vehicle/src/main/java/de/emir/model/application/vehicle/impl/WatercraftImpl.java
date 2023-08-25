package de.emir.model.application.vehicle.impl;

import java.util.List;

import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.application.vehicle.Watercraft;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.application.vehicle.impl.VehicleImpl;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * A watercraft, such as a barge, boat, ship or sailboat, successfully passes over a 
 * body of water, such as a sea, ocean, lake, canal or river.
 * @generated 
 */
@UMLImplementation(classifier = Watercraft.class)
public class WatercraftImpl extends VehicleImpl implements Watercraft  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WatercraftImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WatercraftImpl(final Watercraft _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WatercraftImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.Watercraft;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WatercraftImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
