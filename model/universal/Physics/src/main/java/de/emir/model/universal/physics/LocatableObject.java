package de.emir.model.universal.physics;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Each object that is located somehow shall extend this object
 * this has been separated from the PhysicalObject, since also non physical Objects 
 * like EnvironmentFactors or Information could be located somehow and may be handled the same
 * way (regarding their location operations) as a physical object. 
 * @generated 
 */
@UMLClass(isAbstract = true, parent = IdentifiedObject.class)	
public interface LocatableObject extends IdentifiedObject 
{
	/**
	 Each locatable objects builds its own coordinate system, whereas the objects center is also 
	 * the center of the reference system.
	 *
	 * @generated 
	 */
	@UMLProperty(name = "ownedCoordinateSystem", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setOwnedCoordinateSystem(RelativeEngineering2D _ownedCoordinateSystem);
	/**
	 Each locatable objects builds its own coordinate system, whereas the objects center is also 
	 * the center of the reference system.
	 *
	 * @generated 
	 */
	@UMLProperty(name = "ownedCoordinateSystem", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public RelativeEngineering2D getOwnedCoordinateSystem();
	/**
	
	 * the pose is set to readonly to indicate, that an object that has an location 
	 * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable
	 * @generated 
	 */
	@UMLProperty(name = "pose", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setPose(Pose _pose);
	/**
	
	 * the pose is set to readonly to indicate, that an object that has an location 
	 * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable
	 * @generated 
	 */
	@UMLProperty(name = "pose", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public Pose getPose();
	
}
