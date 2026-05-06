package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.spatial.sf.delegate.IPointDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.PointImpl;

/**
 *	@generated 
 */
public class PointOperations extends GeometryOperations implements IPointDelegationInterface{


	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
        assert self instanceof de.emir.model.universal.spatial.sf.Point;
		de.emir.model.universal.spatial.sf.Point p = (de.emir.model.universal.spatial.sf.Point) self;
		Coordinate c = p.getCoordinate();
		
		return sGeometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ()));
	}

    @Override
    public Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs) {
        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.Point) {
            org.locationtech.jts.geom.Point point = (org.locationtech.jts.geom.Point) self;
            // JTS point has no field z!
            return new PointImpl(
                    new CoordinateImpl(
                            point.getX(),
                            point.getY(),
                            crs
                    )
            );
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

    @Override
	public int numCoordinates(Geometry self) {
		return 1;
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		return ((Point)self).getCoordinate();
	}

	@Override
	public int getNumGeometries(Geometry self) {
		return 1;
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		return self;
	}

	@Override
	public CoordinateSequence getCoordinates(Geometry self) {
		CoordinateSequence result = new CoordinateSequenceImpl();
		result.addCoordinate(getCoordinate(self, 0));
		return result;
	}

}
