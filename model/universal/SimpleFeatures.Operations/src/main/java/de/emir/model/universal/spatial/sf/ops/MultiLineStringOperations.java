package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.MultiLineString;
import de.emir.model.universal.spatial.sf.delegate.IMultiLineStringDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.MultiLineStringImpl;

/**
 *	@generated 
 */
public class MultiLineStringOperations extends GeometryOperations implements IMultiLineStringDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		assert self instanceof MultiLineString;
        MultiLineString mls = (MultiLineString) self;
		return new org.locationtech.jts.geom.MultiLineString(getNativeLineStrings(mls), sGeometryFactory);
	}

    @Override
    public Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.MultiLineString){
            org.locationtech.jts.geom.MultiLineString nativeGeometry = (org.locationtech.jts.geom.MultiLineString) self;

            MultiLineString multiLineString = new MultiLineStringImpl();
            // map all internal LineString objects
            for (int i = 0; i < nativeGeometry.getNumGeometries(); i++){
                org.locationtech.jts.geom.Geometry geom = nativeGeometry.getGeometryN(i);
                // create a copy, this will call the line string operations to create a new object
                Geometry newGeometry = GeometryOperationUtil.createUCoreGeometry(geom, crs);

                assert newGeometry instanceof LineString;

                multiLineString.getLines().add(
                        (LineString) newGeometry
                );
            }

            return multiLineString;
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

    protected org.locationtech.jts.geom.LineString[] getNativeLineStrings(MultiLineString self) {
		org.locationtech.jts.geom.LineString[] result = new org.locationtech.jts.geom.LineString[self.getNumGeometries()];
		for (int i = 0; i < self.getNumGeometries(); i++) {
			LineStringOperations geoOp = new LineStringOperations(); 
			result[i] = (org.locationtech.jts.geom.LineString) geoOp.createNativeGeometry(self.getGeometry(i));
		}
		return result;
	}

	@Override
	public int numCoordinates(Geometry self) {
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self);
        return nativeGeometry.getNumPoints();
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
		MultiLineString mls = (MultiLineString) self;
		return mls.getNumGeometries();
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		MultiLineString mls = (MultiLineString) self;
		return mls.getGeometry(idx);
	}

	@Override
	public CoordinateSequence getCoordinates(de.emir.model.universal.spatial.Geometry self) {
		throw new UnsupportedOperationException("getCoordinates is not implemented for MultiLineStringOperations. Use getGeometry to work with the contained objects.");
	}
}
