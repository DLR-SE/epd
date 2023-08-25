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
	 * @generated
	*/
	public Coordinate getCoordinate(CoordinateSequence self, final int idx)
	{
		//TODO: 
		throw new UnsupportedOperationException("getCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public void setCoordinate(CoordinateSequence self, final int idx, final Coordinate value)
	{
		//TODO: 
		throw new UnsupportedOperationException("setCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public int dimension(CoordinateSequence self)
	{
		//TODO: 
		throw new UnsupportedOperationException("dimension not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public int numCoordinates(CoordinateSequence self)
	{
		//TODO: 
		throw new UnsupportedOperationException("numCoordinates not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public void addCoordinate(CoordinateSequence self, final Coordinate value)
	{
		//TODO: 
		throw new UnsupportedOperationException("addCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public void addCoordinate(CoordinateSequence self, final int idx, final Coordinate value)
	{
		//TODO: 
		throw new UnsupportedOperationException("addCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public void removeCoordinate(CoordinateSequence self, final int idx)
	{
		//TODO: 
		throw new UnsupportedOperationException("removeCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public Envelope getEnvelope(CoordinateSequence self)
	{
		//TODO: 
		//  returns the boundingbox containing all coordinates within this sequence 
		throw new UnsupportedOperationException("getEnvelope not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public void removeCoordinate(CoordinateSequence self, final Coordinate coord)
	{
		//TODO: 
		throw new UnsupportedOperationException("removeCoordinate not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated
	*/
	public int getIndexOf(CoordinateSequence self, final Coordinate coord)
	{
		//TODO: 
		throw new UnsupportedOperationException("getIndexOf not yet implemented");
	}
}
