package de.emir.epd.ais.utils;

import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The shape cache stores shapes associated to vessel geometries and allows scaling depending on the current
 * zoom level of the map layer. This is to improve performance and to not redraw geometries if the zoom changes.
 */
public class ShapeCache {
    private final Map<PhysicalObject, Shape> geometries = new ConcurrentHashMap<>();
    private final Map<PhysicalObject, ShapeWrapper> scaledGeometries = new ConcurrentHashMap<>();

    /**
     * Gets the geometry of a PhysicalObject.
     * @param object Object to extract geometry from.
     * @param ctx Current context of the map layer.
     * @return Scaled shape if found, else null.
     */
    public Shape getShape(PhysicalObject object, IDrawContext ctx) {
        return getGeometryShape(object, ctx);
    }

    /**
     * Removes a shape from the cache.
     * @param object Object for which the geometry should be removed from the cache.
     */
    public void removeShape(PhysicalObject object) {
        geometries.remove(object);
        scaledGeometries.remove(object);
    }

    /**
     * Gets the awt shape for a PhysicalObject geometry.
     * @param obj PhysicalObject to extract geometry from.
     * @param ctx Map layer context used for scalign.
     * @return Awt shape for vessel geometry or null if it does not exist.
     */
    private Shape getGeometryShape(PhysicalObject obj, IDrawContext ctx) {
        if (!geometries.containsKey(obj)) {
            Shape shape = buildLocalShape(obj);
            if(shape != null) geometries.put(obj, shape);
        }
        Shape localShape = geometries.get(obj);
        if (localShape == null) return null;
        ShapeWrapper wrapper = scaledGeometries.computeIfAbsent(obj, o -> new ShapeWrapper());
        if(wrapper.zoom != ctx.getZoom() || wrapper.shape == null) {
            wrapper.zoom = ctx.getZoom();
            wrapper.shape = buildScaledShape(localShape, ctx);
        }
        return wrapper.shape;
    }

    /**
     * Scales a true to scale shape depending on the current zoom and position on the map.
     * @param localShape LocalShape to scale.
     * @param ctx Context to use for scaling.
     * @return Scaled shape based on draw context.
     */
    private Shape buildScaledShape(Shape localShape, IDrawContext ctx) {
        double pixelsPerMeter = getPixelsPerMeter(ctx);
        AffineTransform tx = new AffineTransform();
        tx.scale(pixelsPerMeter, -pixelsPerMeter);
        return tx.createTransformedShape(localShape);
    }

    /**
     * Builds the local shape based on a PhysicalObject geometry. This is the true to scale shape which specifies
     * the general appearance of the object based on its geometry. This shape can then be scaled according to the
     * zoom level with buildScaledShape().
     * @param object PhysicalObject to extract geometry from.
     * @return Local shape based on object geometry. Null if the object has no geometry.
     */
    private Shape buildLocalShape(PhysicalObject object) {
        Shape localShape = null;
        if (object != null) {
            ObjectSurfaceInformation mOSI = object.getFirstCharacteristic(ObjectSurfaceInformation.class, true);
            if (mOSI != null && mOSI.getGeometry() != null && mOSI.getGeometry().numCoordinates() > 2) {
                Geometry geom = mOSI.getGeometry();
                Path2D.Double path = new Path2D.Double();
                CoordinateReferenceSystem base_crs = new Engineering2DImpl(
                        object.getPose().getCoordinate().toVector()
                );
                Coordinate c = new CoordinateImpl(geom.getCoordinate(0));
                c.setCrs(base_crs);
                double x = c.get(base_crs).getX();
                double y = c.get(base_crs).getY();
                path.moveTo(x, y);
                for (int i = 1; i < geom.numCoordinates(); i++) {
                    c = new CoordinateImpl(geom.getCoordinate(i));
                    c.setCrs(base_crs);
                    x = c.get(base_crs).getX();
                    y = c.get(base_crs).getY();
                    path.lineTo(x, y);
                }
                localShape = path;
            }
        }
        return localShape;
    }

    /**
     * Gets the number of pixels for a meter based on the current position on the map and the current zoom level.
     * @param ctx Context to calculate pixels for.
     * @return Number of pixels for every real world meter.
     */
    private double getPixelsPerMeter(IDrawContext ctx) {
        GeoPosition center = ctx.getCenterPosition();
        double lat  = center.getLatitude();
        double lon  = center.getLongitude();
        double oneMeterInDeg = 1.0 / (Math.cos(Math.toRadians(lat)) * 111_319.9);
        Point2D p1 = ctx.convert(lon, lat);
        Point2D p2 = ctx.convert(lon + oneMeterInDeg, lat);
        return p2.getX() - p1.getX();
    }

    /**
     * Wrapper class for storing zoom associated to shape for caching.
     */
    static class ShapeWrapper {
        int zoom;
        Shape shape;
    }

}
