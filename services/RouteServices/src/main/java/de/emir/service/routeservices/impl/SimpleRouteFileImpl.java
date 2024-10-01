package de.emir.service.routeservices.impl;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.service.routeservices.ISimpleRouteFile;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


/**
 Loads a simple txt based route file, as it could be exported from the EPD
 * e.g: 
 * Route Template
 * Start56 17.000N011 00.000E10.010.1,0.10.0
 * Stop56 17.000N012 20.000E0.0000.0,0.00.5
 * @generated 
 */
@UMLImplementation(classifier = ISimpleRouteFile.class)
public class SimpleRouteFileImpl extends RouteImportImpl implements ISimpleRouteFile  
{
	
	
	/**
	 if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) 
	 * otherwise the degree minute second notation (56ï¿½17.3''
	 * @generated 
	 */
	private boolean mDezimalNotation = false;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public SimpleRouteFileImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SimpleRouteFileImpl(final ISimpleRouteFile _copy) {
		super(_copy);
		mDezimalNotation = _copy.getDezimalNotation();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SimpleRouteFileImpl(boolean _dezimalNotation) {
		super();
		mDezimalNotation = _dezimalNotation;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.SimpleRouteFile;
	}

	/**
	 if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) 
	 * otherwise the degree minute second notation (56ï¿½17.3''
	 * @generated 
	 */
	public void setDezimalNotation(boolean _dezimalNotation) {
		if (needNotification(RouteservicesPackage.Literals.SimpleRouteFile_dezimalNotation)){
			boolean _oldValue = mDezimalNotation;
			mDezimalNotation = _dezimalNotation;
			notify(_oldValue, mDezimalNotation, RouteservicesPackage.Literals.SimpleRouteFile_dezimalNotation, NotificationType.SET);
		}else{
			mDezimalNotation = _dezimalNotation;
		}
	}

	/**
	 if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) 
	 * otherwise the degree minute second notation (56ï¿½17.3''
	 * @generated 
	 */
	public boolean getDezimalNotation() {
		return mDezimalNotation;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SimpleRouteFileImpl{" +
		" dezimalNotation = " + getDezimalNotation() + 
		"}";
	}

	@Override
	public Route importRoute(Object definition, CoordinateReferenceSystem crs) {
		BufferedReader reader = new BufferedReader(new StringReader(definition.toString()));
		try {
			return loadSimple(reader, crs);
		} catch (IOException e) {
			ULog.error(e);
		}
		return null;
	}
	
	

	public static Route loadSimple(BufferedReader reader, CoordinateReferenceSystem crs) throws IOException {
		//Copied from EPD : dk.dma.epd.common.prototype.model.route.RouteLoader
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
                String[] fields = line.split("\t");
                // Handle first line name\tdeparture\tdestination
                if (firstLine) {
                    if (fields.length == 0) {
                        ULog.error("First line has no fields: " + line);
                        throw new IOException(formatErrorMsg);
                    }
                    route.setName(fields[0]);
                    if (fields.length >= 3) {
//                        route.setDestination(fields[2]);
                    }
                    if (fields.length >= 2) {
//                        route.setDeparture(fields[1]);
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
                    // Set end wp on previous leg
//                    if (previousLeg != null) {
//                        previousLeg.setEndWp(wp);
//                    }
                    // Create leg
//                    RouteLeg leg = new RouteLeg();
//                    leg.setStartWp(wp);
//                    wp.setOutLeg(leg);
//                    wp.setInLeg(previousLeg);
//                    previousLeg = leg;

                    // Set name
                    wp.setName(fields[0]);
                    // Get position
                    try {
                        wp.setPosition(new CoordinateImpl(parseLatitude(fields[1]), parseLongitude(fields[2]), crs));
                    } catch (Exception e) {
                        throw new IOException(formatErrorMsg + ": Error in position");
                    }
                    // Get turn radius
                    try {
                    	if (fields.length > 6)
                    		wp.setRadius(Double.parseDouble(fields[6].trim()));
                    } catch (Exception e) {
                        throw new IOException(formatErrorMsg + ": Error in turn radius");
                    }
                    //TODO /FIXME uncomment and append to leg

                    // Get speed
//                    try {
//                        leg.setSpeed(ParseUtils.parseDouble(fields[3].trim()));
//                    } catch (FormatException e) {
//                        throw new RouteLoadException(formatErrorMsg + ": Error in speed");
//                    }
//
//                    // Get heading
//                    try {
//                        leg.setHeading(ParseUtils.parseInt(fields[4].trim()) == 1 ? Heading.RL : Heading.GC);
//                    } catch (FormatException e) {
//                        throw new RouteLoadException(formatErrorMsg + ": Error in heading");
//                    }
//
//                    // Get XTD
//                    String xtd = fields[5];
//                    String xtdStarboard = xtd;
//                    String xtdPort = xtd;
//                    if (xtd.contains(",")) {
//                        String[] xtdParts = xtd.split(",");
//                        if (xtdParts.length != 2) {
//                            throw new RouteLoadException(formatErrorMsg + ": Error in XTD");
//                        }
//                        xtdStarboard = xtdParts[0];
//                        xtdPort = xtdParts[1];
//                    }
//                    try {
//                        leg.setXtdStarboard(ParseUtils.parseDouble(xtdStarboard.trim()));
//                        leg.setXtdPort(ParseUtils.parseDouble(xtdPort.trim()));
//                    } catch (FormatException e) {
//                        throw new RouteLoadException(formatErrorMsg + ": Error in XTD");
//                    }
//
//                    // Calculate rot
//                    wp.calcRot();

                    // Add waypoint
                    route.getWaypoints().getWaypoints().add(wp);

                }
            }
            
        } catch (IOException e) {
            ULog.error("Failed to load route file: " + e.getMessage());
            throw new IOException("Error reading route file");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        return route;
    }

	 public static double parseLatitude(String formattedString) throws IOException {
	        String[] parts = splitFormattedPos(formattedString);
	        return parseLatitude(parts[0], parts[1], parts[2]);
	    }
	    
	    public static double parseLongitude(String formattedString) throws IOException {
	        String[] parts = splitFormattedPos(formattedString);
	        return parseLongitude(parts[0], parts[1], parts[2]);
	    }
	    
	    private static String[] splitFormattedPos(String posStr) throws IOException {
	        if (posStr.length() < 4) {
	            throw new IOException();
	        }
	        String[] parts = new String[3];
	        parts[2] = posStr.substring(posStr.length() - 1);
	        posStr = posStr.substring(0, posStr.length() - 1);
	        String[] posParts = posStr.split(" ");
	        if (posParts.length != 2) {
	            throw new IOException();
	        }
	        parts[0] = posParts[0];
	        parts[1] = posParts[1];
	        
	        return parts;
	    }
	    
	    public static double parseLatitude(String hours, String minutes, String northSouth) throws IOException {
	        Integer h = Integer.parseInt(hours);
	        Double m = Double.parseDouble(minutes);
	        String ns = northSouth.trim();        
	        if (h == null || m == null || ns == null) {
	            throw new IOException();
	        }
	        if (!ns.equals("N") && !ns.equals("S")) {
	            throw new IOException();
	        }
	        double lat = h + m / 60.0; 
	        if (ns.equals("S")) {
	            lat *= -1;
	        }
	        return lat;        
	    }
	    
	    public static double parseLongitude(String hours, String minutes, String eastWest) throws IOException {
	        Integer h = Integer.parseInt(hours);
	        Double m = Double.parseDouble(minutes);
	        String ew = eastWest.trim();        
	        if (h == null || m == null || ew == null) {
	            throw new IOException();
	        }
	        if (!ew.equals("E") && !ew.equals("W")) {
	            throw new IOException();
	        }
	        double lon = h + m / 60.0; 
	        if (ew.equals("W")) {
	            lon *= -1;
	        }
	        return lon;
	    }

	@Override
	public FileNameExtensionFilter getFileExtension() {
		return new FileNameExtensionFilter("Simple route text format", "txt", "TXT");

	}
}
