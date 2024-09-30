package de.emir.service.routeservices.impl;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.service.routeservices.IGMLRouteImport;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;

import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IGMLRouteImport.class)
public class GMLRouteImportImpl extends RouteImportImpl implements IGMLRouteImport  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GMLRouteImportImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GMLRouteImportImpl(final IGMLRouteImport _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.GMLRouteImport;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GMLRouteImportImpl{" +
		"}";
	}

	@Override
	public Route importRoute(Object definition, CoordinateReferenceSystem crs) {
		String full_gml = definition.toString();
		try{
			int idx = full_gml.indexOf("<gml:coordinates");
			int idx2 = full_gml.indexOf('>', idx);
			int idx3 = full_gml.indexOf("</gml:coordinates>", idx2);
			String geom_str = full_gml.substring(idx2+1, idx3);
			String[] pairs = geom_str.split(" ");
			if (pairs.length >= 2){
				Route route = new RouteImpl();
				WayPoints wpoints = new WayPointsImpl();
				route.setWaypoints(wpoints);
				int i = 0;
				for (String p : pairs){
					String[] c_str = p.split(",");
					Coordinate coord = new CoordinateImpl(Double.parseDouble(c_str[1]), Double.parseDouble(c_str[0]), CRSUtils.WGS84_2D); //switch lat/lon since gml uses lon/lat 
					Waypoint wp = new WaypointImpl();
					wp.setPosition(coord.get(crs));
					wp.setId(i);
					wp.setName("GML_"+i++);
					wpoints.getWaypoints().add(wp);
				}
				return route;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public FileNameExtensionFilter getFileExtension() {
		return new FileNameExtensionFilter("Geography Markup Language", "gml", "GML");
	}
}
