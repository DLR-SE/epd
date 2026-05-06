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
	
	 * Gets a Geometry representing the envelope (bounding box) of this Geometry.
	 * @generated 
	 */
	Envelope getEnvelope(Geometry self);
	/**
	
	 * Tests whether this geometry intersects the argument geometry.
	 * @generated 
	 */
	boolean intersects(Geometry self, final Geometry _geom);
	/**
	
	                             * Tests whether this geometry is covered by the argument geometry.
	 * @generated 
	 */
	boolean coveredBy(Geometry self, final Geometry _g);
	/**
	
	                             * Tests whether this geometry covers the argument geometry.
	 * @generated 
	 */
	boolean covers(Geometry self, final Geometry _g);
	/**
	
	                             * Tests whether this geometry crosses the argument geometry.
	 * @generated 
	 */
	boolean crosses(Geometry self, final Geometry _g);
	/**
	
	                             * Computes a Geometry representing the closure of the point-set of the points contained in this Geometry that are not contained in the other Geometry.
	 * @generated 
	 */
	Geometry difference(Geometry self, final Geometry _g);
	/**
	
	                             * Tests whether this geometry is disjoint from the argument geometry.
	 * @generated 
	 */
	boolean disjoint(Geometry self, final Geometry _g);
	/**
	
	                             * Returns true if the two Geometries are exactly equal, up to a specified distance tolerance.
	 * @generated 
	 */
	boolean equalsExact(Geometry self, final Geometry _g);
	/**
	
	 * Tests whether this geometry contains the argument geometry.
	 * @generated 
	 */
	boolean contains(Geometry self, final Geometry _geom);
	/**
	
	 * Tests whether this geometry is within the specified geometry.
	 * @generated 
	 */
	boolean within(Geometry self, final Geometry _geom);
	/**
	
	                             * Returns the minimum distance between this Geometry and another Geometry.
	 * @generated 
	 */
	double distance(Geometry self, final Geometry _g);
	/**
	
	                             * Returns the length of this Geometry.
	 * @generated 
	 */
	double getLength(Geometry self);
	/**
	
	 * Creates a new Geometry which is a normalized copy of this Geometry.
	 * @generated 
	 */
	Geometry normalized(Geometry self);
	/**
	 Apply the coordinate reference system to all coordinates in this geometry, without changing their instance
	 * e.g. change the values of the coordinate
	 * @generated 
	 */
	void applyCRS(Geometry self, final CoordinateReferenceSystem _crs);
	/**
	
	                             * Converts this Geometry to normal form (or canonical form ).
	 * @generated 
	 */
	void normalize(Geometry self);
	/**
	 sets the coordinate system for the geometrie and all contained coordinates 
	 * @generated 
	 */
	void recursiveSetCRS(Geometry self, final CoordinateReferenceSystem _crs);
	/**
	
	                             * Computes a new geometry which has all component coordinate sequences in reverse order
	                             * (opposite orientation) to this one.
	 * @generated 
	 */
	Geometry reversed(Geometry self);
	/**
	
	                             * Computes a Geometry representing the point-set which is common to both this Geometry and the other Geometry.
	 * @generated 
	 */
	Geometry intersection(Geometry self, final Geometry _geom);
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem getCRS(Geometry self);
	/**
	
	                             * Computes a Geometry representing the closure of the point-set which is the union
	                             * of the points in this Geometry which are not contained in the other Geometry, with
	                             * the points in the other Geometry not contained in this Geometry.
	 * @generated 
	 */
	Geometry symDifference(Geometry self, final Geometry _geom);
	/**
	
	                             * Returns the area of this Geometry.
	 * @generated 
	 */
	double getArea(Geometry self);
	/**
	
	                             * Tests whether this geometry overlaps the specified geometry.
	 * @generated 
	 */
	boolean overlaps(Geometry self, final Geometry _geom);
	/**
	
	                             * Computes the union of all the elements of this geometry.
	 * @generated 
	 */
	Geometry union(Geometry self, final Geometry _geom);
	/**
	
	                             * Tests whether this geometry touches the argument geometry.
	 * @generated 
	 */
	boolean touches(Geometry self, final Geometry _geom);
}
