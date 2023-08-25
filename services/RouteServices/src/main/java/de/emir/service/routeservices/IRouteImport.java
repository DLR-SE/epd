package de.emir.service.routeservices;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 Imports a route from an external route definition 
 * @generated 
 */
@UMLClass(name = "RouteImport", isAbstract = true)	
public interface IRouteImport extends UObject 
{
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 imports the route from an external route definition. 
	 * @return a valid route if the definition could be read or null if the route was not valid
	 * @generated 
	 */
	Route importRoute(final Object definition, final CoordinateReferenceSystem crs);
	
}
