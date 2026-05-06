package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.MultiPolygon;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.delegate.IMultiPolygonDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.MultiPolygonImpl;

/**
 *	@generated 
 */
public class MultiPolygonOperations extends GeometryOperations implements IMultiPolygonDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
        assert self instanceof MultiPolygon;
		MultiPolygon mpoly = (MultiPolygon) self;
		return new org.locationtech.jts.geom.MultiPolygon(getNativePolygons(mpoly), sGeometryFactory);
	}

    @Override
    public Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.MultiPolygon) {

            org.locationtech.jts.geom.MultiPolygon nativeGeometry = (org.locationtech.jts.geom.MultiPolygon) self;

            MultiPolygon multiPolygon = new MultiPolygonImpl();
            // map all internal Polygon objects
            for (int i = 0; i < nativeGeometry.getNumGeometries(); i++) {
                org.locationtech.jts.geom.Geometry geom = nativeGeometry.getGeometryN(i);
                // create a copy, this will call the polygon operations to create a new object
                Geometry newGeometry = GeometryOperationUtil.createUCoreGeometry(geom, crs);

                assert newGeometry instanceof Polygon;

                multiPolygon.getPolygons().add(
                        (Polygon) newGeometry
                );
            }

            return multiPolygon;
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

    protected  org.locationtech.jts.geom.Polygon[] getNativePolygons(MultiPolygon self) {
		org.locationtech.jts.geom.Polygon[] result = new org.locationtech.jts.geom.Polygon[self.getNumGeometries()];
		for (int i = 0; i < self.getNumGeometries(); i++) {
			PolygonOperations geoOp = new PolygonOperations(); 
			result[i] = (org.locationtech.jts.geom.Polygon) geoOp.createNativeGeometry(self.getGeometry(i));
		}
		return result;
	}

	@Override
	public int numCoordinates(Geometry self) {
        return getNativeGeometry(self).getNumPoints();
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self);
        org.locationtech.jts.geom.Coordinate nativeCoordinate = nativeGeometry.getCoordinate();
        return new CoordinateImpl(
                nativeCoordinate.getX(),
                nativeCoordinate.getY(),
                nativeCoordinate.getZ(),
                getCRS(self)
        );
	}

	@Override
	public int getNumGeometries(Geometry self) {
		MultiPolygon mpoly = (MultiPolygon) self;
		return mpoly.getNumGeometries();
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		MultiPolygon mpoly = (MultiPolygon) self;
		return mpoly.getGeometry(idx);
	}

	@Override
	public CoordinateSequence getCoordinates(de.emir.model.universal.spatial.Geometry self) {
		throw new UnsupportedOperationException("getCoordinates is not implemented for MultiPolygonOperations. Use getGeometry to work with the contained objects.");
	}
}
