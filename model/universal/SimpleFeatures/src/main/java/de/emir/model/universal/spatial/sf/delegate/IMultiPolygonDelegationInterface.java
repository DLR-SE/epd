package de.emir.model.universal.spatial.sf.delegate;

import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.delegate.IMultiGeometryDelegationInterface;

/**
*	delegation interface
*	@generated
*/
public interface IMultiPolygonDelegationInterface extends IMultiGeometryDelegationInterface{
	/**
	 *	@generated 
	 */
	CoordinateSequence getCoordinates(Geometry self);
}
