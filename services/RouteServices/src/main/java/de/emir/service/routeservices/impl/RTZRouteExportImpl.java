package de.emir.service.routeservices.impl;

import java.io.File;
import java.io.FileFilter;

import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.LegGeometryType;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.io.IOutputStream;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.service.routeservices.IRTZRouteExport;
import de.emir.service.routeservices.impl.RouteExportImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IRTZRouteExport.class)
public class RTZRouteExportImpl extends RouteExportImpl implements IRTZRouteExport  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RTZRouteExportImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RTZRouteExportImpl(final IRTZRouteExport _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.RTZRouteExport;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean exportRoute(final Route route, IOutputStream stream)
	{
		if (route == null || stream == null)
			return false;
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" version=\"1.0\" xmlns=\"http://www.cirm.org/RTZ/1/0\">\n");
		sb.append("\t<routeInfo routeName=\""+route.getName()+"\" />\n");
		sb.append("\t<waypoints>");
		WayPoints wps = route.getWaypoints();
		for (Waypoint wp : wps.getWaypoints()){
			sb.append("\t\t<waypoint name=\"" + wp.getName()+"\" radius=\""+wp.getRadius()+"\">\n");
			sb.append("\t\t\t<position lat=\""+wp.getPosition().getLatitude()+"\" lon=\""+wp.getPosition().getLongitude()+"\" />\n");
			if (wp.getLeg() != null){
				Leg leg = wp.getLeg();
				Double starboardXTD = leg.getStarboardXTD() != null ? leg.getStarboardXTD().getAs(DistanceUnit.NAUTICAL_MILES) : null;
				Double portsideXTD = leg.getPortsideXTD() != null ? leg.getPortsideXTD().getAs(DistanceUnit.NAUTICAL_MILES) : null;
				Double speedMax = leg.getPlanSpeedMax() != null ? leg.getPlanSpeedMax().getAs(SpeedUnit.KNOTS) : null;
				String geomType = leg.getGeometryType() == LegGeometryType.Loxodome ? "Loxodome" : "Orthodrome";
				sb.append("\t\t\t<leg geometryType=\"" + geomType+ "\" ");
				if (starboardXTD != null) sb.append("starboardXTD=\"" + starboardXTD.doubleValue() + "\" ");
				if (portsideXTD != null) sb.append("portsideXTD=\"" + portsideXTD.doubleValue() + "\" ");
				if (speedMax != null) sb.append("speedMax=\"" + speedMax.doubleValue() + "\" ");
				sb.append("/>\n");				
			}
			sb.append("\t\t</waypoint>\n");
		}
		sb.append("\t</waypoints>\n");
		sb.append("</route>");
		stream.write(sb.toString().getBytes());
		return true;
	}
	
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RTZRouteExportImpl{" +
		"}";
	}

	@Override
    public FileFilter getFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathName) {
            	int idx = pathName.getAbsolutePath().lastIndexOf('.');
            	if (idx < 0) return false;
            	String ext = pathName.getAbsolutePath().substring(idx+1);
            	if (ext.toLowerCase().equals("rtz"))
            		return true;
            	return false;
            }
        };
    }
}
