package de.emir.model.universal.detection.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.detection.ITrack;
import de.emir.model.universal.detection.ITrackPoint;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.detection.TargetPackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.List;


/**

    * A track is a collection of TrackPoints associated to a TrackedTarget. It represents historical
    * positions associated to a TrackedTarget. This is for example produced by a tracking system.
 * @generated 
 */
@UMLImplementation(classifier = ITrack.class)
public class TrackImpl extends IdentifiedObjectImpl implements ITrack  
{
	
	
	/**
	 Unique identifier of the Track. 
	 * @generated 
	 */
	private String mId = "";
	/**
	 Timestamp when the Track was last updated. 
	 * @generated 
	 */
	private Time mLastUpdate = null;
	/**
	 TrackedTarget reference which is associated to the Track. 
	 * @generated 
	 */
	private ITrackedTarget mReference = null;
	/**
	 List of TrackPoints associated to a Track. 
	 * @generated 
	 */
	private List<ITrackPoint> mTrackPoints = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackImpl(){
		super();
		//set the default values and assign them to this instance 
		setLastUpdate(mLastUpdate);
		setReference(mReference);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackImpl(final ITrack _copy) {
		super(_copy);
		mId = _copy.getId();
		mLastUpdate = _copy.getLastUpdate();
		mReference = _copy.getReference();
		mTrackPoints = _copy.getTrackPoints();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, String _id, Time _lastUpdate, ITrackedTarget _reference, List<ITrackPoint> _trackPoints) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mId = _id;
		mLastUpdate = _lastUpdate; 
		mReference = _reference; 
		mTrackPoints = _trackPoints; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TargetPackage.Literals.Track;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Unique identifier of the Track. 
	 * @generated 
	 */
	public void setId(String _id) {
		if (needNotification(TargetPackage.Literals.Track_id)){
			String _oldValue = mId;
			mId = _id;
			notify(_oldValue, _id, TargetPackage.Literals.Track_id, NotificationType.SET);
		}else{
			mId = _id;
		}
	}
	/**
	 Unique identifier of the Track. 
	 * @generated 
	 */
	public String getId() {
		return mId;
	}
	/**
	 Timestamp when the Track was last updated. 
	 * @generated 
	 */
	public void setLastUpdate(Time _lastUpdate) {
		Notification<Time> notification = basicSet(mLastUpdate, _lastUpdate, TargetPackage.Literals.Track_lastUpdate);
		mLastUpdate = _lastUpdate;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 Timestamp when the Track was last updated. 
	 * @generated 
	 */
	public Time getLastUpdate() {
		return mLastUpdate;
	}
	/**
	 TrackedTarget reference which is associated to the Track. 
	 * @generated 
	 */
	public void setReference(ITrackedTarget _reference) {
		Notification<ITrackedTarget> notification = basicSet(mReference, _reference, TargetPackage.Literals.Track_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 TrackedTarget reference which is associated to the Track. 
	 * @generated 
	 */
	public ITrackedTarget getReference() {
		return mReference;
	}
	/**
	 List of TrackPoints associated to a Track. 
	 * @generated 
	 */
	public List<ITrackPoint> getTrackPoints() {
		if (mTrackPoints == null) {
			mTrackPoints = ListUtils.<ITrackPoint>asList(this, TargetPackage.theInstance.getTrack_trackPoints()); 
		}
		return mTrackPoints;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" id = " + getId() + 
		"}";
	}
}
