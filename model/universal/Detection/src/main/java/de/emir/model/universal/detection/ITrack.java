package de.emir.model.universal.detection;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**

    * A track is a collection of TrackPoints associated to a TrackedTarget. It represents historical
    * positions associated to a TrackedTarget. This is for example produced by a tracking system.
 * @generated 
 */
@UMLClass(name = "Track", parent = IdentifiedObject.class)	
public interface ITrack extends IdentifiedObject 
{
	/**
	 Unique identifier of the Track. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(String _id);
	/**
	 Unique identifier of the Track. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public String getId();
	/**
	 Timestamp when the Track was last updated. 
	 * @generated 
	 */
	@UMLProperty(name = "lastUpdate", associationType = AssociationType.PROPERTY)
	public void setLastUpdate(Time _lastUpdate);
	/**
	 Timestamp when the Track was last updated. 
	 * @generated 
	 */
	@UMLProperty(name = "lastUpdate", associationType = AssociationType.PROPERTY)
	public Time getLastUpdate();
	/**
	 TrackedTarget reference which is associated to the Track. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public void setReference(ITrackedTarget _reference);
	/**
	 TrackedTarget reference which is associated to the Track. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public ITrackedTarget getReference();
	/**
	 List of TrackPoints associated to a Track. 
	 * @generated 
	 */
	@UMLProperty(name = "trackPoints", associationType = AssociationType.PROPERTY)
	public List<ITrackPoint> getTrackPoints();

	
}
