package de.emir.model.application.vehicle.impl;

import java.util.List;

import de.emir.model.application.vehicle.Landcraft;
import de.emir.model.application.vehicle.VehiclePackage;
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

 * LandTransport includes all vehicles regarding rail, road and off-road transport
 * @generated 
 */
@UMLImplementation(classifier = Landcraft.class)
public class LandcraftImpl extends VehicleImpl implements Landcraft  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LandcraftImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LandcraftImpl(final Landcraft _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LandcraftImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.Landcraft;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LandcraftImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
