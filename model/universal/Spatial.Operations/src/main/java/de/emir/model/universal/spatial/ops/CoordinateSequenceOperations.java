package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.delegate.ICoordinateSequenceDelegationInterface;
import java.util.List;

/**

 * A coordinate sequence serves as container for the coordinates, forming a geometry
 * @generated 
 */
public class CoordinateSequenceOperations  implements ICoordinateSequenceDelegationInterface{
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public Coordinate getCoordinate(CoordinateSequence self, final int idx)
	{
		return self.getCoordinate(idx);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void setCoordinate(CoordinateSequence self, final int idx, final Coordinate value)
	{
		self.setCoordinate(idx, value);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public int dimension(CoordinateSequence self)
	{
		return self.dimension();
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public int numCoordinates(CoordinateSequence self)
	{
		return self.numCoordinates();
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void addCoordinate(CoordinateSequence self, final Coordinate value)
	{
		self.addCoordinate(value);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void addCoordinate(CoordinateSequence self, final int idx, final Coordinate value)
	{
        self.addCoordinate(idx, value);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void removeCoordinate(CoordinateSequence self, final int idx)
	{
		self.removeCoordinate(idx);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public Envelope getEnvelope(CoordinateSequence self)
	{
		return self.getEnvelope();
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void removeCoordinate(CoordinateSequence self, final Coordinate coord)
	{
        self.removeCoordinate(coord);
	}
	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public int getIndexOf(CoordinateSequence self, final Coordinate coord)
	{
		return self.getIndexOf(coord);
	}
}
