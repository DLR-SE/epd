package de.emir.service.routeservices.impl;

import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.io.IOutputStream;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.service.routeservices.ISimpleRouteFileExport;
import de.emir.service.routeservices.impl.RouteExportImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ISimpleRouteFileExport.class)
public class SimpleRouteFileExportImpl extends RouteExportImpl implements ISimpleRouteFileExport  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SimpleRouteFileExportImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SimpleRouteFileExportImpl(final ISimpleRouteFileExport _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.SimpleRouteFileExport;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean exportRoute(final Route route, IOutputStream stream)
	{
		if (route == null)
			return false;
		StringBuilder sb = new StringBuilder();
//		sb.append("#EPD Route format, exported by eMIR\n");
		String depature = "Unknown";
		String destination = "Unknown";
		String name = (route.getName() != null && route.getName().isEmpty() == false) ? route.getName() : "Unknown";
		//first line: 
		sb.append(name + "\t" + destination + "\t" + depature + "\n");
		//waypoints
		WayPoints wps = route.getWaypoints();
		int i = 0;
		for (Waypoint wp : wps.getWaypoints()){
			String wp_name = wp.getName() != null ? wp.getName() : "WP_" + i;
			String wp_lat = writeLat(wp.getPosition().getLatitude());
			String wp_lon = writeLon(wp.getPosition().getLongitude());
			String speed = "0.0";
			if (wp.getLeg() != null){
				if (wp.getLeg().getPlanSpeedMax() != null)
					speed = "" + wp.getLeg().getPlanSpeedMax().getAs(SpeedUnit.KNOTS);
			}
			String heading = "1"; //FIXME: not clear what is meant here?
			String radius = wp.getRadius() + "";
			String xtd = "";
			if (wp.getLeg() != null){
				if (wp.getLeg().getStarboardXTD() != null)
					xtd = wp.getLeg().getStarboardXTD().getAs(DistanceUnit.NAUTICAL_MILES) + "";
				if (wp.getLeg().getPortsideXTD() != null && xtd.isEmpty() == false){
					xtd += ",";
				}
				if (wp.getLeg().getPortsideXTD() != null)
					xtd += wp.getLeg().getPortsideXTD().getAs(DistanceUnit.NAUTICAL_MILES)+"";					
			}
			if (xtd.isEmpty())
				xtd = "0.1";
			sb.append(wp_name + "\t" + wp_lat + "\t" + wp_lon + "\t" + speed + "\t" + heading + "\t" + radius + "\t" + xtd + "\n");			 
			i++;
		}
		stream.write(sb.toString().getBytes());
		return true;
	}
	
	

	private String writeLat(double latitude) {
		return toDDM(latitude, latitude < 0 ? "S" : "N");
	}

	private String writeLon(double longitude) {
		return toDDM(longitude, longitude < 0 ? "W" : "E");
	}

	private static DecimalFormat DF = null;
	static {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(); 
		otherSymbols.setDecimalSeparator('.');
		DF = new DecimalFormat("#0.000", otherSymbols);		
	}
	private String toDDM(double dd, String hemisphere) {
		return CRSUtils.getDegrees(dd) + " " + DF.format(CRSUtils.getDezimalMinute(dd)) + hemisphere;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SimpleRouteFileExportImpl{" +
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
            	if (ext.toLowerCase().equals("txt"))
            		return true;
            	return false;
            }
        };
    }
}
