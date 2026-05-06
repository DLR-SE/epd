package de.emir.model.universal.spatial.sf.delegate;

import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;

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
