package de.emir.model.application.vehicle;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface TrajectorySegment extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public void setName(String _name);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public String getName();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(int _id);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public int getId();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY)
	public void setOrigin(Coordinate _origin);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY)
	public Coordinate getOrigin();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "destination", associationType = AssociationType.PROPERTY)
	public void setDestination(Coordinate _destination);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "destination", associationType = AssociationType.PROPERTY)
	public Coordinate getDestination();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.PROPERTY)
	public void setSpeed(Speed _speed);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.PROPERTY)
	public Speed getSpeed();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "allowedPortCTE", associationType = AssociationType.PROPERTY)
	public void setAllowedPortCTE(Distance _allowedPortCTE);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "allowedPortCTE", associationType = AssociationType.PROPERTY)
	public Distance getAllowedPortCTE();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "allowedStarboardCTE", associationType = AssociationType.PROPERTY)
	public void setAllowedStarboardCTE(Distance _allowedStarboardCTE);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "allowedStarboardCTE", associationType = AssociationType.PROPERTY)
	public Distance getAllowedStarboardCTE();
	/**
	 returns the smallest distance from the given coodinate to the line segment 
	 * @generated 
	 */
	Distance getDistance(final Coordinate loc);
	/**
	 returns the orientation of the vector from origin -> destination as angle against north.  
	 * @generated 
	 */
	Angle getOrientation();

	
}
