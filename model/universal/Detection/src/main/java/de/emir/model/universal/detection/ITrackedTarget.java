package de.emir.model.universal.detection;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

    * A TrackedTarget is an object that is detected by a tracking system. The name, properties and
    * position is inherited from PhysicalObject. Compared to the Target, the TrackedTarget contains
 * 					    * a history of previous positions, an ID which is reused between observations and sometimes a name.
 * 					    * While Target represents a single observation point, like a plotted point, TrackedTarget
 * 					    * is a recurring object which has been identified by a tracking system.
 * @generated 
 */
@UMLClass(name = "TrackedTarget", parent = PhysicalObject.class)	
public interface ITrackedTarget extends PhysicalObject 
{
	/**
	 Unique identifier of the TrackedTarget. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(String _id);
	/**
	 Unique identifier of the TrackedTarget. 
	 * @generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public String getId();
	/**
	 Track of the TrackedTarget. 
	 * @generated 
	 */
	@UMLProperty(name = "track", associationType = AssociationType.PROPERTY)
	public void setTrack(ITrack _track);
	/**
	 Track of the TrackedTarget. 
	 * @generated 
	 */
	@UMLProperty(name = "track", associationType = AssociationType.PROPERTY)
	public ITrack getTrack();

	
}
