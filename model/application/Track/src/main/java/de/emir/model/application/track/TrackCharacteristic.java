package de.emir.model.application.track;

import de.emir.model.application.track.Track;
import de.emir.model.universal.physics.Characteristic;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = Characteristic.class)	
public interface TrackCharacteristic extends Characteristic 
{
	/**
	 maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. 
								 * if maxTrackPoints is set to < 0 no points will be deleted 
	 * @generated 
	 */
	@UMLProperty(name = "maxTrackPoints", associationType = AssociationType.PROPERTY)
	public int getMaxTrackPoints();

	/**
	 maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. 
								 * if maxTrackPoints is set to < 0 no points will be deleted 
	 * @generated 
	 */
	@UMLProperty(name = "maxTrackPoints", associationType = AssociationType.PROPERTY)
	public void setMaxTrackPoints(int _maxTrackPoints);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "track", associationType = AssociationType.PROPERTY)
	public void setTrack(Track _track);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "track", associationType = AssociationType.PROPERTY)
	public Track getTrack();

	
}
