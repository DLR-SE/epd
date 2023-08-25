package de.emir.model.application.sense.impl;

import java.util.List;

import de.emir.model.application.sense.Emitter;
import de.emir.model.application.sense.SensePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Emitter.class)
abstract public class EmitterImpl extends PhysicalObjectImpl implements Emitter  
{
	
	
	/**
	 Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value
	 * if the areaOfInfluence is not defined, the emitted value is visible everywhere. 
	 * @generated 
	 */
	private Geometry mAreaOfInfluence = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EmitterImpl(){
		super();
		//set the default values and assign them to this instance 
		setAreaOfInfluence(mAreaOfInfluence);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EmitterImpl(final Emitter _copy) {
		super(_copy);
		mAreaOfInfluence = _copy.getAreaOfInfluence();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EmitterImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, Geometry _areaOfInfluence) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mAreaOfInfluence = _areaOfInfluence; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SensePackage.Literals.Emitter;
	}

	/**
	 Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value
	 * if the areaOfInfluence is not defined, the emitted value is visible everywhere. 
	 * @generated 
	 */
	public void setAreaOfInfluence(Geometry _areaOfInfluence) {
		Notification<Geometry> notification = basicSet(mAreaOfInfluence, _areaOfInfluence, SensePackage.Literals.Emitter_areaOfInfluence);
		mAreaOfInfluence = _areaOfInfluence;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value
	 * if the areaOfInfluence is not defined, the emitted value is visible everywhere. 
	 * @generated 
	 */
	public Geometry getAreaOfInfluence() {
		return mAreaOfInfluence;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EmitterImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
