package de.emir.model.application.track.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackCharacteristic;
import de.emir.model.application.track.TrackPackage;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = TrackCharacteristic.class)
public class TrackCharacteristicImpl extends CharacteristicImpl implements TrackCharacteristic  
{
	
	
	/**
	 *	@generated 
	 */
	private Track mTrack = null;

	/**
	 *	Default constructor
	 *	@generated not
	 */
	public TrackCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance
		setTrack(mTrack);
		init();
	}

	/**
	 *	Default copy constructor
	 *	@generated not
	 */
	public TrackCharacteristicImpl(final TrackCharacteristic _copy) {
		super(_copy);
		mMaxTrackPoints = _copy.getMaxTrackPoints();
		mTrack = _copy.getTrack();
		init();
	}


	/**
	 maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. 
								 * if maxTrackPoints is set to < 0 no points will be deleted 
	 * @generated 
	 */
	private int mMaxTrackPoints = 1000;

	/**
	 *	Default attribute constructor
	 *	@generated not
	 */
	public TrackCharacteristicImpl(int _maxTrackPoints, Track _track) {
		super();
		mMaxTrackPoints = _maxTrackPoints;
		mTrack = _track;
		init();
	}
	
	private void init() {
		registerListener(TrackPackage.Literals.TrackCharacteristic_track, new IValueChangeListener() {
			@Override
			public void onValueChange(Notification notification) {
				NotificationType nt = notification.getType();
				if (notification.getType() == NotificationType.SET || notification.getType() == NotificationType.UNSET) {
					if (notification.getOldValue() != null && notification.getOldValue() instanceof Track) {
						((Track)notification.getOldValue()).removeListener(TrackPackage.Literals.TrackCharacteristic_track, this);
						((Track)notification.getOldValue()).removeListener(TrackPackage.Literals.Track_elements, this);
					}
					if (notification.getNewValue() != null && notification.getNewValue() instanceof Track) {
						((Track)notification.getNewValue()).registerListener(TrackPackage.Literals.TrackCharacteristic_track, this);
						((Track)notification.getNewValue()).registerListener(TrackPackage.Literals.Track_elements, this);
					}
				}else if (nt == NotificationType.ADD || nt == NotificationType.ADD_MANY) {
					if (getMaxTrackPoints() > 0 && getTrack().getElements().size() > getMaxTrackPoints()) {
						while(getTrack().getElements().size() > getMaxTrackPoints())
							getTrack().getElements().remove(0);
					}
				}
				//TODO / FIXME: This does not consider that maxTrackPoints is changed, in this case the list will be updated after the next ADD 
			}
		});
	}

	/**
	 maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. 
								 * if maxTrackPoints is set to < 0 no points will be deleted 
	 * @generated 
	 */
	public int getMaxTrackPoints() {
		return mMaxTrackPoints;
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TrackPackage.Literals.TrackCharacteristic;
	}
	
	/**
	 *	@generated 
	 */
	public Track getTrack() {
		return mTrack;
	}

	/**
	 maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. 
								 * if maxTrackPoints is set to < 0 no points will be deleted 
	 * @generated 
	 */
	public void setMaxTrackPoints(int _maxTrackPoints) {
		if (needNotification(TrackPackage.Literals.TrackCharacteristic_maxTrackPoints)){
			int _oldValue = mMaxTrackPoints;
			mMaxTrackPoints = _maxTrackPoints;
			notify(_oldValue, mMaxTrackPoints, TrackPackage.Literals.TrackCharacteristic_maxTrackPoints, NotificationType.SET);
		}else{
			mMaxTrackPoints = _maxTrackPoints;
		}
	}

	/**
	 *	@generated 
	 */
	public void setTrack(Track _track) {
		Notification<Track> notification = basicSet(mTrack, _track, TrackPackage.Literals.TrackCharacteristic_track);
		mTrack = _track;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackCharacteristicImpl{" +
		" maxTrackPoints = " + getMaxTrackPoints() + 
		"}";
	}
}
