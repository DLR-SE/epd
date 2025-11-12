package de.emir.model.universal.detection;

import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

    * Represents a observation point for a TrackedTarget which is part of a Track. The name,
    * properties and position is inherited from PhysicalObject.
 * @generated 
 */
@UMLClass(name = "TrackPoint", parent = LocatableObject.class)	
public interface ITrackPoint extends LocatableObject 
{
	/**
	 Unique identifier of the TrackPoint. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(String _id);
	/**
	 Unique identifier of the TrackPoint. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public String getId();
	/**
	 Timestamp of the TrackPoint creation. 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.PROPERTY)
	public void setTimestamp(Time _timestamp);
	/**
	 Timestamp of the TrackPoint creation. 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.PROPERTY)
	public Time getTimestamp();
	/**
	 Reference to the Track object which references to the TrackPoint. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public void setReference(ITrack _reference);
	/**
	 Reference to the Track object which references to the TrackPoint. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.PROPERTY)
	public ITrack getReference();

	
}
