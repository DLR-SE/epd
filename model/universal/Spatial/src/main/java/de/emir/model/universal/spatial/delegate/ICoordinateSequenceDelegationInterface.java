package de.emir.model.universal.spatial.delegate;

import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;

/**
*	delegation interface
*	@generated
*/
public interface ICoordinateSequenceDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	Coordinate getCoordinate(CoordinateSequence self, final int _idx);
	/**
	 *	@generated 
	 */
	void setCoordinate(CoordinateSequence self, final int _idx, final Coordinate _value);
	/**
	 *	@generated 
	 */
	int dimension(CoordinateSequence self);
	/**
	 *	@generated 
	 */
	int numCoordinates(CoordinateSequence self);
	/**
	 *	@generated 
	 */
	void addCoordinate(CoordinateSequence self, final Coordinate _value);
	/**
	 *	@generated 
	 */
	void addCoordinate(CoordinateSequence self, final int _idx, final Coordinate _value);
	/**
	 *	@generated 
	 */
	void removeCoordinate(CoordinateSequence self, final int _idx);
	/**
	 *	@generated 
	 */
	void removeCoordinate(CoordinateSequence self, final Coordinate _coord);
	/**
	 returns the boundingbox containing all coordinates within this sequence 
	 * @generated 
	 */
	Envelope getEnvelope(CoordinateSequence self);
	/**
	 *	@generated 
	 */
	int getIndexOf(CoordinateSequence self, final Coordinate _coord);
}
