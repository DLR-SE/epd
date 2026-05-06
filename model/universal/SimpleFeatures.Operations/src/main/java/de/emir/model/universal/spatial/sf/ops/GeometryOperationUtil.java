package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.*;

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
        } else if (self instanceof MultiGeometry) {
            MultiGeometryOperations geoOps = new MultiGeometryOperations();
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

    /**
     * Utility function to create a UCore geometry based on a JTS geometry. Keep in mind that we do a full
     * clone here and that both geometries are not linked to each other.
     * @param self JTS geometry object
     * @param crs coordinate reference system to use
     * @return UCore geometry object
     */
    public static Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs){

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.Polygon) {
            PolygonOperations geoOps = new PolygonOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.LinearRing) {
            LinearRingOperations geoOps = new LinearRingOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.LineString) {
            LineStringOperations geoOps = new LineStringOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.MultiLineString) {
            MultiLineStringOperations geoOps = new MultiLineStringOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.MultiPolygon) {
            MultiPolygonOperations geoOps = new MultiPolygonOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.Point) {
            PointOperations geoOps = new PointOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.GeometryCollection) {
            MultiGeometryOperations geoOps = new MultiGeometryOperations();
            return geoOps.createUCoreGeometry(self, crs);
        } else if (self instanceof org.locationtech.jts.geom.CoordinateSequence) {
            // CoordinateSequence does not extend Geometry in UCore copy will be done based on LineString for now
            LineStringOperations geoOps = new LineStringOperations();
            return geoOps.createUCoreGeometry(self, crs);
        }

        return null;
    }
    
    /**
     * Count the total number of coordinates in the given geometry. Coordinates in Multi-Geometries will be added up.
     * 
     * @param geometry the geometry to count the coordinates
     * @return the total number of ccordinates
     */
    public static int countCoordinates(Geometry geometry) {
		if (geometry.getNumGeometries() < 1) {
			// No geometry -> no coordinates.
			return 0;
		} else if (geometry.getNumGeometries() > 1) {
			// Muliple geometries -> add their coordinates up.
			// Beware: potential recursion
			int result = 0;
			for (int i = 0; i < geometry.getNumGeometries(); i++) {
				result += geometry.getGeometry(i).numCoordinates();
			}
			return result;
		}
		// One geometry -> count coordinates.
		return geometry.getCoordinates().numCoordinates();
	}
    
    /**
     * Determines the smallest dimension of a coordinate within this geometry or multi-geometry. In contrast to the JTS
     * geometry API this works exclusively on the number of axis in the coordinates. 
     * 
     * @param geometry the geometry to get the coordinates from
     * @return the lowest number of axis in a coordinate within the given geometry 
     */
    public static int getMinDimension(Geometry geometry) {
    	int result = Integer.MAX_VALUE;
    	if (geometry.getNumGeometries() < 1) {
    		return 0;
    	} else if (geometry.getNumGeometries() == 1) {
    		if (geometry.numCoordinates() < 1) {
    			return 0;
    		}
    		for (int i = 0; i < geometry.numCoordinates(); i++) {
    			result = Math.min(result, geometry.getCoordinate(i).dimension());
    		}
    	} else {
    		for (int i = 0; i < geometry.getNumGeometries(); i++) {
    			result = Math.min(result, getMinDimension(geometry));
    		}
    	}
    	return result;
    }
    
    /**
     * Determines the largest dimension of a coordinate within this geometry or multi-geometry. In contrast to the JTS
     * geometry API this works exclusively on the number of axis in the coordinates. 
     * 
     * @param geometry the geometry to get the coordinates from
     * @return the largest number of axis in a coordinate within the given geometry 
     */
    public static int getMaxDimension(Geometry geometry) {
    	int result = 0;
    	if (geometry.getNumGeometries() < 1) {
    		return 0;
    	} else if (geometry.getNumGeometries() == 1) {
    		if (geometry.numCoordinates() < 1) {
    			return 0;
    		}
    		for (int i = 0; i < geometry.numCoordinates(); i++) {
    			result = Math.max(result, geometry.getCoordinate(i).dimension());
    		}
    	} else {
    		for (int i = 0; i < geometry.getNumGeometries(); i++) {
    			result = Math.max(result, getMaxDimension(geometry));
    		}
    	}
    	return result;
    }
}
