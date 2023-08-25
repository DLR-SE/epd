package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;

import java.util.List;

public class VesselUtils {

    public static Length getDraft(Vessel vessel) {
        if (vessel == null){
            return null;
        }
        VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(VesselDimensionCharacteristic.class, true);
        return getDraft(vdc);
    }

    public static Length getDraft(VesselDimensionCharacteristic vdc) {
        if (vdc == null) {
            return null;
        }
        WatercraftHull hull = vdc.getHull();

        if (hull == null) {
            return null;
        }

        return hull.getDraft();

    }

    public static void setDraft(Vessel vessel, Length draft) {
        if (vessel == null){
            return;
        }
        VesselDimensionCharacteristic vdc = vessel.getFirstCharacteristic(VesselDimensionCharacteristic.class, true);

        if (vdc == null) {
            vdc = new VesselDimensionCharacteristicImpl();
            vessel.getCharacteristics().add(vdc);
        }
        setDraft(vdc, draft);
    }

    public static void setDraft(VesselDimensionCharacteristic vdc, Length draft) {
        if (vdc == null) {
            return;
        }
        WatercraftHull hull = vdc.getHull();

        if (hull == null) {
            hull = new WatercraftHullImpl();
            vdc.setHull(hull);
        }

        hull.setDraft(draft);
    }

    public static String getDestination(Vessel vessel){
        if (vessel == null){
            return null;
        }
        VoyageCharacteristic voyage = vessel.getFirstCharacteristic(VoyageCharacteristic.class);
        return getDestination(voyage);
    }

    public static String getDestination(VoyageCharacteristic voyage){
        if (voyage == null){
            return null;
        }

        Route active = voyage.getActiveRoute();
        if (active == null){
            return null;
        }

        WayPoints wayPoints = active.getWaypoints();
        if (wayPoints == null){
            return null;
        }

        List<Waypoint> wps = wayPoints.getWaypoints();
        if (wps == null || wps.size() == 0){
            return null;
        }

        Waypoint wp = wps.get(wps.size() - 1);
        if (wp == null){
            return null;
        }
        return wp.getName();
    }
}
