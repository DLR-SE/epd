package de.emir.model.universal.spatial.sf.ops;

import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.delegate.ILineStringDelegationInterface;

/**
 *	@generated 
 */
public class LineStringOperations extends GeometryOperations implements ILineStringDelegationInterface{
	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		LineString ls = (LineString) self;
		
		return new org.locationtech.jts.geom.LineString(getPoints(ls), sGeometryFactory);
	}

	protected CoordinateSequence getPoints(LineString self) {
		int size = self.getPoints().numCoordinates();
		CoordinateArraySequence cas = new CoordinateArraySequence(size);
		org.locationtech.jts.geom.Coordinate[] r = new org.locationtech.jts.geom.Coordinate[size];
		
		for (int i = 0; i < size; i++){
			Coordinate c = self.getPoints().getCoordinate(i);
			r[i] = new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ());
		}
		return new CoordinateArraySequence(r);
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
}
