package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
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
		assert self instanceof LinearRing;
        close((LinearRing) self);
		LinearRing ls = (LinearRing) self;
		
		return new org.locationtech.jts.geom.LinearRing(getPoints(ls), sGeometryFactory);
	}

    @Override
    public de.emir.model.universal.spatial.Geometry createUCoreGeometry(Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.LinearRing){
            LinearRing sequence = new LinearRingImpl();
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

    /**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isClosed(LinearRing self) {
		CoordinateSequence points = self.getPoints();
		Coordinate c0 = points.getCoordinate(0);
		Coordinate cN = points.getCoordinate(points.numCoordinates() - 1);

        int nbDimensions = Math.min(c0.dimension(), cN.dimension());

        if (nbDimensions == 1){
            return c0.getX() == cN.getX();
        } else if (nbDimensions == 2){
            return c0.getX() == cN.getX() && c0.getY() == cN.getY();
        } else if (nbDimensions == 3) {
            return c0.getX() == cN.getX() && c0.getY() == cN.getY() && c0.getZ() == cN.getZ();
        } else {
            throw new UnsupportedOperationException("isClosed for 3D coordinates is not yet implemented!");
        }
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void close(LinearRing self) {
		if (!isClosed(self)){
			CoordinateSequence points = self.getPoints();
			points.addCoordinate(new CoordinateImpl(points.getCoordinate(0)));
		}
	}
}
