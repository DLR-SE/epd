package de.emir.service.routeservices.impl;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.service.routeservices.ICSVRouteFile;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


/**
 *	@generated
 */
@UMLImplementation(classifier = ICSVRouteFile.class)
public class CSVRouteFileImpl extends RouteImportImpl implements ICSVRouteFile
{



	/**
	 *	Default constructor
	 *	@generated
	 */
	public CSVRouteFileImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CSVRouteFileImpl(final ICSVRouteFile _copy) {
		super(_copy);
	}


	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.CSVRouteFile;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CSVRouteFileImpl{" +
		"}";
	}

	@Override
	public Route importRoute(Object definition, CoordinateReferenceSystem crs) {
		BufferedReader reader = new BufferedReader(new StringReader(definition.toString()));
		return loadCSV(reader, crs);
	}

	private Route loadCSV(BufferedReader reader, CoordinateReferenceSystem crs){
			Route route = new RouteImpl();
	        WayPoints wps = new WayPointsImpl();
	        route.setWaypoints(wps);
	        try {
	            boolean firstLine = true;
	            String line = null;
	            String formatErrorMsg = "Unrecognized route format";

	            while ((line = reader.readLine()) != null) {
	                // Ignore empty lines and comments
	                if (line.length() == 0 || line.startsWith("//") || line.startsWith("#")) {
	                    continue;
	                }
	                // Split line by tab
	                String[] fields = line.split(";");
	                // Handle first line name\tdeparture\tdestination
	                if (firstLine) {
	                    if (fields.length == 0) {
	                        ULog.error("First line has no fields: " + line);
	                        throw new IOException(formatErrorMsg);
	                    }
	                    route.setName(fields[0]);
	                    if (fields.length >= 3) {
//	                        route.setDestination(fields[2]);
	                    }
	                    if (fields.length >= 2) {
//	                        route.setDeparture(fields[1]);
	                    }
	                    firstLine = false;
	                    formatErrorMsg = "Error in route format";
	                } else {
	                    // Handle waypoint lines
	                    if (fields.length < 3) {
	                        ULog.error("Waypoint line has less than seven fields: " + line);
	                        throw new IOException(formatErrorMsg);
	                    }

	                    // Create new waypoint
	                    Waypoint wp = new WaypointImpl();
	                    // Create leg
	                    LegImpl leg = new LegImpl();
	                    leg.setPlanSpeedMax(new SpeedImpl(Double.parseDouble(fields[3].replace(",", ".")),SpeedUnit.KNOTS));
	                    // Set name
	                    wp.setName(fields[0]);
	                    wp.setLeg(leg);
	                    // Get position
	                    try {
	                        wp.setPosition(new CoordinateImpl(Double.parseDouble(fields[1].replace(",", ".")),Double.parseDouble(fields[2].replace(",", ".")), crs));
	                    } catch (Exception e) {
	                        throw new IOException(formatErrorMsg + ": Error in position");
	                    }

	                    // Add waypoint
	                    route.getWaypoints().getWaypoints().add(wp);

	                }
	            }

	        } catch (IOException e) {
	            ULog.error("Failed to load route file: " + e.getMessage());
	            try {
					throw new IOException("Error reading route file");
				} catch (IOException e1) {
					ULog.error(e);
					return null;
				}
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
						ULog.error(e);
	                }
	            }
	        }

	        return route;
	}

	@Override
	public FileNameExtensionFilter getFileExtension() {
		return new FileNameExtensionFilter("Comma Separated Values", "cvs", "CVS");
	}
}
