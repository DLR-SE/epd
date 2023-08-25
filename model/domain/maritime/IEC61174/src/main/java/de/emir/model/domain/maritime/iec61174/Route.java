package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.model.domain.maritime.iec61174.RouteSchedule;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = IECElementWithExtension.class)	
public interface Route extends IECElementWithExtension 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "waypoints", associationType = AssociationType.COMPOSITE)
	public void setWaypoints(WayPoints _waypoints);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "waypoints", associationType = AssociationType.COMPOSITE)
	public WayPoints getWaypoints();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "schedule", associationType = AssociationType.COMPOSITE)
	public void setSchedule(RouteSchedule _schedule);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "schedule", associationType = AssociationType.COMPOSITE)
	public RouteSchedule getSchedule();
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
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public void setVersion(String _version);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "version", associationType = AssociationType.PROPERTY)
	public String getVersion();
	/**
	
	 * returns the length of the overall route, from the first to the last waypoint
	 * @generated 
	 */
	Length getLength();
	/**
	 returns the length from the coordinate to the end of the route. 
	 * @generated 
	 */
	Length getLength(final Coordinate coord);
	/**
	
	 * Returns the envelope containing all waypoints of this route
	 * @generated 
	 */
	Envelope getEnvelope();

	
}
