package de.emir.model.universal.spatial.sf.ops;

import org.locationtech.jts.geom.Geometry;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.delegate.ILinearRingDelegationInterface;

/**
 Same as the LineString but has to be closed (first coordinate has to be the same as the last) 
 * @generated 
 */
public class LinearRingOperations extends LineStringOperations implements ILinearRingDelegationInterface{
	@Override
	public Geometry createNativeGeometry(de.emir.model.universal.spatial.Geometry self) {
		close((LinearRing) self);
		LinearRing ls = (LinearRing) self;
		
		return new org.locationtech.jts.geom.LinearRing(getPoints(ls), sGeometryFactory);
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isClosed(LinearRing self)
	{
		CoordinateSequence points = self.getPoints();
		Coordinate c0 = points.getCoordinate(0);
		Coordinate cN = points.getCoordinate(points.numCoordinates() - 1);
		return c0.getX() == cN.getX() && c0.getY() == cN.getY(); // TODO: check for 3d
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void close(LinearRing self)
	{
		if (!isClosed(self)){
			CoordinateSequence points = self.getPoints();
			points.addCoordinate(new CoordinateImpl(points.getCoordinate(0)));
		}
	}
}
