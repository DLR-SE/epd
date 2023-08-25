package de.emir.model.universal.spatial.delegate;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface IGeometryDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	int getDimension(Geometry self);
	/**
	 *	@generated 
	 */
	int numCoordinates(Geometry self);
	/**
	 *	@generated 
	 */
	CoordinateSequence getCoordinates(Geometry self);
	/**
	 *	@generated 
	 */
	Coordinate getCoordinate(Geometry self, final int _index);
	/**
	 *	@generated 
	 */
	void setCoordinate(Geometry self, final int _index, final Coordinate _coord);
	/**
	 *	@generated 
	 */
	void removeCoordinate(Geometry self, final int _index);
	/**
	 *	@generated 
	 */
	int getNumGeometries(Geometry self);
	/**
	 *	@generated 
	 */
	Geometry getGeometry(Geometry self, final int _idx);
	/**
	 *	@generated 
	 */
	Envelope getEnvelope(Geometry self);
	/**
	 *	@generated 
	 */
	boolean intersects(Geometry self, final Geometry _geom);
	/**
	 *	@generated 
	 */
	boolean isConvex(Geometry self);
	/**
	 Applys the coordinate reference system to all coordinates in this geometry, without changing their instance
	 * e.g. change the values of the coordinate
	 * @generated 
	 */
	void applyCRS(Geometry self, final CoordinateReferenceSystem _crs);
	/**
	 sets the coordinate system for the geometrie and all contained coordinates 
	 * @generated 
	 */
	void recursiveSetCRS(Geometry self, final CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem getCRS(Geometry self);
}
