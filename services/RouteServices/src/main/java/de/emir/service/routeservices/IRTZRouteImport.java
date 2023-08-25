package de.emir.service.routeservices;

import de.emir.service.routeservices.IRouteImport;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 Imports an RTZ (XML) route file
 * 
 * <?xml version="1.0"?>
 * <route xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" version="1.0" xmlns="http://www.cirm.org/RTZ/1/0">
 *   <routeInfo routeName="Barcelona - Las Palmas" />
 *   <waypoints>
 *     <waypoint name="" radius="0">
 *       <position lat="41.3335786967608" lon="2.16797561484445" />
 *     </waypoint>
 *     <waypoint name="" radius="0.2">
 *       <position lat="41.3276183895413" lon="2.16687065350792" />
 *       <leg starboardXTD="0.1" portsideXTD="0.1" geometryType="Orthodrome" speedMax="19" />
 *     </waypoint>
 * @generated 
 */
@UMLClass(name = "RTZRouteImport", parent = IRouteImport.class)	
public interface IRTZRouteImport extends IRouteImport 
{
	
}
