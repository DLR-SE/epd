package de.emir.model.universal.spatial.sf.delegate;

import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.delegate.IGeometryDelegationInterface;

/**
*	delegation interface
*	@generated
*/
public interface IPointDelegationInterface extends IGeometryDelegationInterface{
	/**
	 *	@generated 
	 */
	CoordinateSequence getCoordinates(Geometry self);
}
