package de.emir.model.universal.detection.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.detection.ITrack;
import de.emir.model.universal.detection.ITrackPoint;
import de.emir.model.universal.detection.TargetPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.LocatableObjectImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**

    * Represents a observation point for a TrackedTarget which is part of a Track. The name,
    * properties and position is inherited from PhysicalObject.
 * @generated 
 */
@UMLImplementation(classifier = ITrackPoint.class)
public class TrackPointImpl extends LocatableObjectImpl implements ITrackPoint  
{
	
	
	/**
	 Unique identifier of the TrackPoint. 
	 * @generated 
	 */
	private String mId = "";
	/**
	 Timestamp of the TrackPoint creation. 
	 * @generated 
	 */
	private Time mTimestamp = null;
	/**
	 Reference to the Track object which references to the TrackPoint. 
	 * @generated 
	 */
	private ITrack mReference = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackPointImpl(){
		super();
		//set the default values and assign them to this instance 
		setTimestamp(mTimestamp);
		setReference(mReference);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackPointImpl(final ITrackPoint _copy) {
		super(_copy);
		mId = _copy.getId();
		mTimestamp = _copy.getTimestamp();
		mReference = _copy.getReference();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackPointImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, String _id, Time _timestamp, ITrack _reference) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose);
		mId = _id;
		mTimestamp = _timestamp; 
		mReference = _reference; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TargetPackage.Literals.TrackPoint;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Unique identifier of the TrackPoint. 
	 * @generated 
	 */
	public void setId(String _id) {
		if (needNotification(TargetPackage.Literals.TrackPoint_id)){
			String _oldValue = mId;
			mId = _id;
			notify(_oldValue, _id, TargetPackage.Literals.TrackPoint_id, NotificationType.SET);
		}else{
			mId = _id;
		}
	}
	/**
	 Unique identifier of the TrackPoint. 
	 * @generated 
	 */
	public String getId() {
		return mId;
	}
	/**
	 Timestamp of the TrackPoint creation. 
	 * @generated 
	 */
	public void setTimestamp(Time _timestamp) {
		Notification<Time> notification = basicSet(mTimestamp, _timestamp, TargetPackage.Literals.TrackPoint_timestamp);
		mTimestamp = _timestamp;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Timestamp of the TrackPoint creation. 
	 * @generated 
	 */
	public Time getTimestamp() {
		return mTimestamp;
	}
	/**
	 Reference to the Track object which references to the TrackPoint. 
	 * @generated 
	 */
	public void setReference(ITrack _reference) {
		Notification<ITrack> notification = basicSet(mReference, _reference, TargetPackage.Literals.TrackPoint_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Reference to the Track object which references to the TrackPoint. 
	 * @generated 
	 */
	public ITrack getReference() {
		return mReference;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackPointImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" id = " + getId() + 
		"}";
	}
}
