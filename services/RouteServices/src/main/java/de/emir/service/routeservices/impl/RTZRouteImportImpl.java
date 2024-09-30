package de.emir.service.routeservices.impl;

import de.emir.model.domain.maritime.iec61174.*;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.service.routeservices.IRTZRouteImport;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.utils.XMLReaderIgnoreCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Collection;


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
@UMLImplementation(classifier = IRTZRouteImport.class)
public class RTZRouteImportImpl extends RouteImportImpl implements IRTZRouteImport  
{
	private static final Logger LOG = LoggerFactory.getLogger(RTZRouteImportImpl.class);
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RTZRouteImportImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RTZRouteImportImpl(final IRTZRouteImport _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.RTZRouteImport;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RTZRouteImportImpl{" +
		"}";
	}

	@Override
	public Route importRoute(Object definition, CoordinateReferenceSystem crs) {
		XMLReaderIgnoreCase reader = new XMLReaderIgnoreCase();
		try {
			reader.parseStream(new StringBufferInputStream(definition.toString()));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Node rootNode = null;
		rootNode = reader.getRootNode();
		if (rootNode == null || !rootNode.getNodeName().equalsIgnoreCase("route")) {
			rootNode = reader.findNodes("route").item(0);
			if (rootNode == null || !rootNode.getNodeName().equalsIgnoreCase("route")) {
				rootNode = reader.findNodes("Route").item(0);
			}
		}
		if (rootNode == null || !rootNode.getNodeName().equalsIgnoreCase("route")) {
			LOG.error("Could not parse Route.");
			return null;
		}
		Route route = new RouteImpl();
		
		String name = reader.getAttributeValue(rootNode, "name");
		if (name != null && !name.isEmpty()) {
			route.setName(name);
		}
		
		Node routeInfoNode = reader.findNodes(rootNode, "routeInfo").item(0);		
		if (routeInfoNode != null){
			String routeName = reader.getAttributeValue(routeInfoNode, "routeName");
			route.setName(routeName);
		}
		Node waypointsNode = reader.findNodes(rootNode, "waypoints").item(0);
		WayPoints wps = new WayPointsImpl();
		
		Collection<Node> waypointNodes = reader.getAllNodes(waypointsNode, "waypoint");
		for (Node wpNode : waypointNodes){
			String wpName = reader.getAttributeValue(wpNode, "name");
			float wpRadius = reader.getAttributeValueAsFloat(wpNode, "radius");
			Waypoint wp = new WaypointImpl();
			wp.setName(wpName);
			wp.setRadius(wpRadius);
			
			Node posNode = reader.getChild(wpNode, "position");
			double lat = reader.getAttributeValueAsFloat(posNode, "lat");
			double lon = reader.getAttributeValueAsFloat(posNode, "lon");
			Coordinate coord = new CoordinateImpl(lat, lon, CRSUtils.WGS84_2D);
			wp.setPosition(coord.get(crs));
			
			Node legNode = reader.getChild(wpNode, "leg");
			if (legNode != null){
				Leg leg = new LegImpl();
				
				double starboardXTD = reader.getAttributeValueAsDouble(legNode, "starboardXTD");
				leg.setStarboardXTD(new DistanceImpl(starboardXTD, DistanceUnit.NAUTICAL_MILES));
				
				double portsideXTD = reader.getAttributeValueAsDouble(legNode, "portsideXTD");
				leg.setPortsideXTD(new DistanceImpl(portsideXTD, DistanceUnit.NAUTICAL_MILES));
				
				String geomType = reader.getAttributeValue(legNode, "geometryType");
				if (geomType.equals("Orthodrome"))
					leg.setGeometryType(LegGeometryType.Orthodome);
				else
					leg.setGeometryType(LegGeometryType.Loxodome);
				
				double maxSpeed = reader.getAttributeValueAsDouble(legNode, "speedMax");
				leg.setPlanSpeedMax(new SpeedImpl(maxSpeed, SpeedUnit.KNOTS));
				
				wp.setLeg(leg);
			}
			
			wps.getWaypoints().add(wp);
		}
		route.setWaypoints(wps);
		return route;
	}

	@Override
	public FileNameExtensionFilter getFileExtension() {
		return new FileNameExtensionFilter("Route plan exchange format", "rtz", "RTZ");
	}
}
