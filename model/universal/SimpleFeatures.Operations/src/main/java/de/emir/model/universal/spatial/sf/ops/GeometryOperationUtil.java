package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.CoordinateOperations;
import de.emir.model.universal.spatial.ops.CoordinateSequenceOperations;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.MultiLineString;
import de.emir.model.universal.spatial.sf.MultiPolygon;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.WKTGeometry;

public class GeometryOperationUtil {
	/**
	 * This is a simple static utility to convert eMIR geometry to native geometry. This way you can make use of the
	 * advanced geometry functions from JTS.
	 * @param self The eMIR geometry to convert.
	 * @return The JTS geometry.
	 */
	public static org.locationtech.jts.geom.Geometry getNativeGeometry(Geometry self) {
		if (self instanceof Polygon) {
			PolygonOperations geoOps = new PolygonOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof LinearRing) {
			LinearRingOperations geoOps = new LinearRingOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof LineString) {
			LineStringOperations geoOps = new LineStringOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof MultiLineString) {
			MultiLineStringOperations geoOps = new MultiLineStringOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof MultiPolygon) {
			MultiPolygonOperations geoOps = new MultiPolygonOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof Point) {
			PointOperations geoOps = new PointOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof WKTGeometry) {
			WKTGeometryOperations geoOps = new WKTGeometryOperations();
			return geoOps.getNativeGeometry(self);
		} else if (self instanceof Coordinate) {
            PointOperations geoOps = new PointOperations();
            return geoOps.getNativeGeometry(self);
        } else if (self instanceof CoordinateSequence) {
            LineStringOperations geoOps = new LineStringOperations();
            return geoOps.getNativeGeometry(self);
        }
			
		return null;
	}
}
