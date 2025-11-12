package de.emir.model.universal.detection.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.detection.ITrack;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.detection.TargetPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**

    * A TrackedTarget is an object that is detected by a tracking system. The name, properties and
    * position is inherited from PhysicalObject.
 * @generated 
 */
@UMLImplementation(classifier = ITrackedTarget.class)
public class TrackedTargetImpl extends PhysicalObjectImpl implements ITrackedTarget  
{
	
	
	/**
	 Unique identifier of the TrackedTarget. 
	 * @generated 
	 */
	private String mId = "";
	/**
	 Track of the TrackedTarget. 
	 * @generated 
	 */
	private ITrack mTrack = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackedTargetImpl(){
		super();
		//set the default values and assign them to this instance 
		setTrack(mTrack);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackedTargetImpl(final ITrackedTarget _copy) {
		super(_copy);
		mId = _copy.getId();
		mTrack = _copy.getTrack();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackedTargetImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children, String _id, ITrack _track) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose,_characteristics,_capabilities,_children);
		mId = _id;
		mTrack = _track; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TargetPackage.Literals.TrackedTarget;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Unique identifier of the TrackedTarget. 
	 * @generated 
	 */
	public void setId(String _id) {
		if (needNotification(TargetPackage.Literals.TrackedTarget_id)){
			String _oldValue = mId;
			mId = _id;
			notify(_oldValue, _id, TargetPackage.Literals.TrackedTarget_id, NotificationType.SET);
		}else{
			mId = _id;
		}
	}
	/**
	 Unique identifier of the TrackedTarget. 
	 * @generated 
	 */
	public String getId() {
		return mId;
	}
	/**
	 Track of the TrackedTarget. 
	 * @generated 
	 */
	public void setTrack(ITrack _track) {
		Notification<ITrack> notification = basicSet(mTrack, _track, TargetPackage.Literals.TrackedTarget_track);
		mTrack = _track;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Track of the TrackedTarget. 
	 * @generated 
	 */
	public ITrack getTrack() {
		return mTrack;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackedTargetImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" id = " + getId() + 
		"}";
	}
}
