package de.emir.service.routeservices;

import java.io.FileFilter;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.io.IOutputStream;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**

 * Exports a route from internal representatin (IEC61174) to an external format
 * @generated 
 */
@UMLClass(name = "RouteExport", isAbstract = true)	
public interface IRouteExport extends UObject 
{
	
	/**
	
	 * Exports the route to the given data stream
	 * @param route route to be exported
	 * @param stream target to which the route should be exported
	 * @generated 
	 */
	boolean exportRoute(final Route route, IOutputStream stream);
	
	FileFilter getFilter();
}
