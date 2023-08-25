package de.emir.model.domain.maritime.iec61174;

import java.security.InvalidParameterException;

import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

public class RouteUtils {

	
	/**
	 * Adds a new waypoint at the end of the route
	 * besides the coordinate all other attributes for the waypoint will be copied from the previous waypoint
	 * @param route route to be modified
	 * @param coord coordinate of the new waypoint
	 */
	public static void appendWaypoint(Route route, Coordinate coord) {
		WayPoints wps = route.getWaypoints();
		Waypoint last = wps.getWaypoints().get(wps.getWaypoints().size()-1);
		Waypoint copy = UCoreUtils.copy(last);
		copy.setPosition(coord);
		wps.getWaypoints().add(copy);
	}
	/**
	 * Insert a new waypoint into the given route, between wpA and wpB. 
	 * besides the coordinate, all other attributes (e.g. leg information) will be interpolated between wpA and wpB
	 * @param route route to be modified
	 * @param wpA first waypoint (before the new one)
	 * @param wpB second waypoint (behind the new one)
	 * @param newCoord coordinate of the new waypoint
	 */
	public static void insertWaypoint(Route route, Waypoint wpA, Waypoint wpB, Coordinate newCoord) {
		
		Distance dist_start = wpA.getPosition().getDistance(newCoord);
		Distance dist_end = wpB.getPosition().getDistance(newCoord);
		double f = 0.5f;
		if (dist_start.greater(dist_end))
			f = dist_start.getAs(DistanceUnit.METER) / dist_end.getAs(DistanceUnit.METER);
		else
			f = dist_end.getAs(DistanceUnit.METER) / dist_start.getAs(DistanceUnit.METER);
		
		Waypoint newWP = new WaypointImpl();
		newWP.setName(wpA.getName()+"->"+wpB.getName());
		newWP.setPosition(newCoord);
		newWP.setRadius(lerp(wpA.getRadius(), wpB.getRadius(), f));
		
		if (wpA.getLeg() != null && wpB.getLeg() != null){			
			newWP.setLeg(lerp(wpA.getLeg(), wpB.getLeg(), f));			
		}else if (wpA.getLeg() != null){
			newWP.setLeg(new LegImpl(wpA.getLeg()));
		}else if (wpB.getLeg() != null){
			newWP.setLeg(new LegImpl(wpB.getLeg()));
		}
		insertWaypoint(route, wpA, wpB, newWP);
	}
	
	
	public static void insertWaypoint(Route route, Waypoint wpA, Waypoint wpB, Waypoint newWP) {
		if (route == null || route.getWaypoints() == null)
			throw new NullPointerException("Invalid Route");
		WayPoints wps = route.getWaypoints();
		int idxA = wps.getWaypoints().indexOf(wpA);
		int idxB = wps.getWaypoints().indexOf(wpB);
		if (idxA < 0 || idxB < 0){
			throw new InvalidParameterException("at least one of the waypoints is not part of the route");
		}
		if (Math.abs(idxA - idxB) != 1)
			throw new InvalidParameterException("Waypoint A and B are not connected");
		
		wps.getWaypoints().add(idxA+1, newWP); //thats all :)
		
	}


	public static Leg lerp(Leg ls, Leg le, double f) {
		Leg l = new LegImpl(ls);
		if (ls.getDraughtAft() != null)
			l.setDraughtAft(ls.getDraughtAft().lerp(le.getDraughtAft(), f));
		else if (le.getDraughtAft() != null)
			l.setDraughtAft(le.getDraughtAft());
		
		if (ls.getDraughtForward() != null)
			l.setDraughtForward(ls.getDraughtAft().lerp(le.getDraughtForward(), f));
		else
			l.setDraughtForward(le.getDraughtForward());
		
		if (ls.getPlanSpeedMax() != null)
			l.setPlanSpeedMax(ls.getPlanSpeedMax().lerp(le.getPlanSpeedMax(), f));
		else
			l.setPlanSpeedMax(le.getPlanSpeedMax());
		
		if (ls.getPlanSpeedMin() != null)
			l.setPlanSpeedMin(ls.getPlanSpeedMin().lerp(le.getPlanSpeedMin(), f));
		else
			l.setPlanSpeedMin(le.getPlanSpeedMin());
		
		if (ls.getPortsideXTD() != null)
			l.setPortsideXTD(ls.getPortsideXTD().lerp(le.getPortsideXTD(), f));
		else
			l.setPortsideXTD(le.getPortsideXTD());
		
		if (ls.getSafetyContour() != null)
			l.setSafetyContour(ls.getSafetyContour().lerp(le.getSafetyContour(), f));
		else
			l.setSafetyContour(le.getSafetyContour());
		
		if (ls.getSafetyDepth() != null)
			l.setSafetyDepth(ls.getSafetyDepth().lerp(le.getSafetyDepth(), f));
		else
			l.setSafetyDepth(le.getSafetyDepth());
		
		if (ls.getStarboardXTD() != null)
			l.setStarboardXTD(ls.getStarboardXTD().lerp(le.getStarboardXTD(), f));
		else
			l.setStarboardXTD(le.getStarboardXTD());
		
		return l;
	}

	public static double lerp(double a, double b, double f)
	{
	    return a + f * (b - a);
	}


	

}
