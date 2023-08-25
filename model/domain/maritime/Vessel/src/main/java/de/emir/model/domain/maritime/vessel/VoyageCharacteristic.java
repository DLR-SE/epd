package de.emir.model.domain.maritime.vessel;

import java.util.List;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "VoyageCharacteristic", parent = VesselCharacteristic.class)	
public interface VoyageCharacteristic extends VesselCharacteristic 
{

	/**
	 all routes assigned to this Voyage, each route may have a name and a schedule 
	 * @generated 
	 */
	@UMLProperty(name = "routes", associationType = AssociationType.COMPOSITE)
	public List<Route> getRoutes();

	/**
	 current active route or null, if no route is active. 
	 * @note this route shall point to a route inside the routes[*] list
	 * @generated 
	 */
	@UMLProperty(name = "activeRoute", associationType = AssociationType.SHARED)
	public void setActiveRoute(Route _activeRoute);

	/**
	 current active route or null, if no route is active. 
	 * @note this route shall point to a route inside the routes[*] list
	 * @generated 
	 */
	@UMLProperty(name = "activeRoute", associationType = AssociationType.SHARED)
	public Route getActiveRoute();
	
}
