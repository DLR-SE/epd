package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.DefaultWayPoint;
import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = IECElementWithExtension.class)	
public interface WayPoints extends IECElementWithExtension 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "defaultWaypoint", associationType = AssociationType.COMPOSITE)
	public void setDefaultWaypoint(DefaultWayPoint _defaultWaypoint);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "defaultWaypoint", associationType = AssociationType.COMPOSITE)
	public DefaultWayPoint getDefaultWaypoint();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "waypoints", associationType = AssociationType.COMPOSITE)
	public List<Waypoint> getWaypoints();
	
}
