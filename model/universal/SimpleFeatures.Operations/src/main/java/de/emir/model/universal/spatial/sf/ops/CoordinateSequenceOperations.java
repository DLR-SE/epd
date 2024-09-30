package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.delegate.ICoordinateSequenceDelegationInterface;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

/**
 *	@generated 
 */
public class CoordinateSequenceOperations extends GeometryOperations implements ICoordinateSequenceDelegationInterface{
    /**
     * This returns a LineString as native type because jts does not handle CoordinateSequence as Geometry.
     * 
     * @param self
     * @return 
     * TODO: Find out if this works.
     */
    @Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		CoordinateSequence cs = (CoordinateSequence) self;
		
		return new org.locationtech.jts.geom.LineString(getPoints(cs), sGeometryFactory);
	}
    
    protected org.locationtech.jts.geom.CoordinateSequence getPoints(CoordinateSequence self) {
		int size = self.numCoordinates();
		CoordinateArraySequence cas = new CoordinateArraySequence(size);
		org.locationtech.jts.geom.Coordinate[] r = new org.locationtech.jts.geom.Coordinate[size];
		
		for (int i = 0; i < size; i++){
			Coordinate c = self.getCoordinate(i);
			r[i] = new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ());
		}
		return new CoordinateArraySequence(r);
	}
    
	/**
	 * @note this method is not optimized its allways recalculated
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public Envelope getEnvelope(CoordinateSequence self)
	{
		if (self == null || numCoordinates(self) == 0)
			return null;
		Envelope env = new EnvelopeImpl(getCoordinate(self, 0).copy());
		for (int i = 1; i < numCoordinates(self); i++)
			env.expandLocal(getCoordinate(self, i));
		return env;
	}

    @Override
    public Coordinate getCoordinate(CoordinateSequence self, int _idx) {
        return self.getCoordinate(_idx);
    }

    @Override
    public void setCoordinate(CoordinateSequence self, int _idx, Coordinate _value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int dimension(CoordinateSequence self) {
        return self.dimension();
    }

    @Override
    public int numCoordinates(CoordinateSequence self) {
        return self.numCoordinates();
    }

    @Override
    public void addCoordinate(CoordinateSequence self, Coordinate _value) {
        self.addCoordinate(_value);
    }

    @Override
    public void addCoordinate(CoordinateSequence self, int _idx, Coordinate _value) {
        self.addCoordinate(_idx, _value);
    }

    @Override
    public void removeCoordinate(CoordinateSequence self, int _idx) {
        self.removeCoordinate(_idx);
    }

    @Override
    public void removeCoordinate(CoordinateSequence self, Coordinate _coord) {
        self.removeCoordinate(_coord);
    }

    @Override
    public int getIndexOf(CoordinateSequence self, Coordinate _coord) {
        return self.getIndexOf(_coord);
    }

    @Override
    public int numCoordinates(Geometry self) {
        return self.numCoordinates();
    }

    @Override
    public Coordinate getCoordinate(Geometry self, int index) {
        return self.getCoordinate(index);
    }

    @Override
    public int getNumGeometries(Geometry self) {
        return self.getNumGeometries();
    }

    @Override
    public Geometry getGeometry(Geometry self, int idx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
}
