package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.delegate.ILineStringDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;

/**
 *	@generated 
 */
public class LineStringOperations extends GeometryOperations implements ILineStringDelegationInterface{

    @Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		assert self instanceof LineString;
        LineString ls = (LineString) self;
		return new org.locationtech.jts.geom.LineString(getPoints(ls), sGeometryFactory);
	}

    @Override
    public de.emir.model.universal.spatial.Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.LineString){
            LineString sequence = new LineStringImpl();
            sequence.setPoints(new CoordinateSequenceImpl());
            for (int i = 0; i < self.getNumPoints(); i++){
                sequence.getPoints().addCoordinate(
                        new CoordinateImpl(
                                self.getCoordinates()[i].getX(),
                                self.getCoordinates()[i].getY(),
                                self.getCoordinates()[i].getZ(),
                                crs
                        )
                );
            }

            return sequence;
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

	protected org.locationtech.jts.geom.CoordinateSequence getPoints(LineString self) {
		int size = self.getPoints().numCoordinates();
		org.locationtech.jts.geom.impl.CoordinateArraySequence cas = new org.locationtech.jts.geom.impl.CoordinateArraySequence(size);
		org.locationtech.jts.geom.Coordinate[] r = new org.locationtech.jts.geom.Coordinate[size];
		
		for (int i = 0; i < size; i++){
			Coordinate c = self.getPoints().getCoordinate(i);
			r[i] = new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ());
		}
		return new org.locationtech.jts.geom.impl.CoordinateArraySequence(r);
	}

	@Override
	public int numCoordinates(Geometry self) {
		LineString ls = (LineString) self;
		if (ls.getPoints() != null)
			return ls.getPoints().numCoordinates();
		return 0;
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		LineString ls = (LineString) self;
		if (ls.getPoints() != null)
			return ls.getPoints().getCoordinate(index);
		return null;
	}

	@Override
	public int getNumGeometries(Geometry self) {
		return 1;
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		if (idx == 0) return self;
		return null;
	}

	@Override
	public CoordinateSequence getCoordinates(Geometry self) {
		CoordinateSequence result = new CoordinateSequenceImpl();
		for (int i = 0; i < self.numCoordinates(); i++) {
			result.addCoordinate(getCoordinate(self, i));
		}
		return result;
	}
}
