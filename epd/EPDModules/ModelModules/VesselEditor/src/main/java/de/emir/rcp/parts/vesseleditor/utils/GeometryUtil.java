package de.emir.rcp.parts.vesseleditor.utils;

import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.Geometry;

public class GeometryUtil {

    public static Geometry getGeometry(View view, ObjectSurfaceInformation osi) {
         if (osi == null)
            return null;
        if (view == View.TOP) {
            return osi.getGeometry();
        } else {
            if (osi instanceof MultiViewObjectSurfaceInforamtion) {
                if (view == View.FRONT)
                    return ((MultiViewObjectSurfaceInforamtion) osi).getFrontGeometry();
                else if (view == View.SIDE)
                    return ((MultiViewObjectSurfaceInforamtion) osi).getSideGeometry();
            }
        }
        return null;
    }
}
