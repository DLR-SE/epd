package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.spatial.sf.delegate.IPointDelegationInterface;

/**
 *	@generated 
 */
public class PointOperations extends GeometryOperations implements IPointDelegationInterface{


	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		de.emir.model.universal.spatial.sf.Point p = (de.emir.model.universal.spatial.sf.Point) self;
		Coordinate c = p.getCoordinate();
		
		return sGeometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ()));
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

}
