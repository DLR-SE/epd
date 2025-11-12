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
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.DistanceImpl;
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
public class CSVRouteFileImpl extends RouteImportImpl implements ICSVRouteFile {

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

    private double parseDouble(String field){
        return Double.parseDouble(field.replace(",", "."));
    }

	private Route loadCSV(BufferedReader reader, CoordinateReferenceSystem crs) {
			Route route = new RouteImpl();
	        WayPoints wps = new WayPointsImpl();
	        route.setWaypoints(wps);
	        try {
	            boolean firstLine = true;
	            String line;

	            while ((line = reader.readLine()) != null) {
	                // Ignore empty lines and comments
	                if (line.isEmpty() || line.startsWith("//") || line.startsWith("#")) {
	                    continue;
	                }
	                // Split line by tab
	                String[] fields = line.split(";");
	                // Handle first line name, departure, destination
	                if (firstLine) {
                        String expected = "name;latitude;longitude;speedMin;speedMax;portsideXTD;starboardXTD".toLowerCase();

                        if (!line.toLowerCase().replace(" ", "").equals(expected)){
                            throw new IOException(
                                    "CSV header does not have the expected format (" + expected + ") got: " + line
                            );
                        }

                        firstLine = false;
	                    continue;
	                }

                    // Handle waypoint lines
                    if (fields.length < 3) {
                        ULog.error("Waypoint line has less than three fields: " + line);
                        return null;
                    }

                    // Create new waypoint
                    Waypoint wp = new WaypointImpl();
                    // Get position
                    wp.setPosition(
                        new CoordinateImpl(
                            parseDouble(fields[1]),
                            parseDouble(fields[2]),
                            crs
                        )
                    );

                    // if no fields are left, we just continue with the next waypoint
                    if (fields.length < 4){
                        // Add waypoint
                        route.getWaypoints().getWaypoints().add(wp);
                        continue;
                    }

                    // Create leg
                    LegImpl leg = new LegImpl();
                    // allow fields without content
                    if (!fields[3].isEmpty()){
                        leg.setPlanSpeedMin(
                                new SpeedImpl(
                                        parseDouble(fields[3]),
                                        SpeedUnit.KNOTS
                                )
                        );
                    }

                    // if no fields are left, we just continue with the next waypoint
                    if (fields.length < 5){
                        // set leg
                        wp.setLeg(leg);
                        // Add waypoint
                        route.getWaypoints().getWaypoints().add(wp);
                        continue;
                    }
                    // allow fields without content
                    if (!fields[4].isEmpty()) {
                        leg.setPlanSpeedMax(
                                new SpeedImpl(
                                        parseDouble(fields[4]),
                                        SpeedUnit.KNOTS
                                )
                        );
                    }

                    // if no fields are left, we just continue with the next waypoint
                    if (fields.length < 6){
                        // set leg
                        wp.setLeg(leg);
                        // Add waypoint
                        route.getWaypoints().getWaypoints().add(wp);
                        continue;
                    }
                    // allow fields without content
                    if (!fields[5].isEmpty()){
                        leg.setPortsideXTD(
                                new DistanceImpl(
                                        parseDouble(fields[5]),
                                        DistanceUnit.NAUTICAL_MILES
                                )
                        );
                    }

                    // if no fields are left, we just continue with the next waypoint
                    if (fields.length < 7){
                        // set leg
                        wp.setLeg(leg);
                        // Add waypoint
                        route.getWaypoints().getWaypoints().add(wp);
                        continue;
                    }
                    // allow fields without content
                    if (!fields[6].isEmpty()){
                        leg.setStarboardXTD(
                                new DistanceImpl(
                                        parseDouble(fields[6]),
                                        DistanceUnit.NAUTICAL_MILES
                                )
                        );
                    }

                    // set leg
                    wp.setLeg(leg);
                    // Add waypoint
                    route.getWaypoints().getWaypoints().add(wp);
	            }

	        } catch (Exception e) {
	            ULog.error("Failed to load route file: " + e.getMessage());
                return null;
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
		return new FileNameExtensionFilter("Comma Separated Values", "csv", "CSV");
	}
}
