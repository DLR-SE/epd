package de.emir.model.universal.spatial;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "Geometry", isAbstract = true)	
public interface Geometry extends UObject 
{
	/**
	 *	@generated 
	 */
	CoordinateSequence getCoordinates();
	/**
	 *	@generated 
	 */
	int getDimension();
	/**
	 *	@generated 
	 */
	int numCoordinates();
	/**
	 *	@generated 
	 */
	Coordinate getCoordinate(final int index);
	/**
	 *	@generated 
	 */
	void setCoordinate(final int index, final Coordinate coord);
	/**
	 *	@generated 
	 */
	void removeCoordinate(final int index);
	/**
	 *	@generated 
	 */
	int getNumGeometries();
	/**
	 *	@generated 
	 */
	Geometry getGeometry(final int idx);
	/**
	
	 * Gets a Geometry representing the envelope (bounding box) of this Geometry.
	 * @generated 
	 */
	Envelope getEnvelope();
	/**
	
	 * Tests whether this geometry intersects the argument geometry.
	 * @generated 
	 */
	boolean intersects(final Geometry geom);
	/**
	
	                             * Tests whether this geometry is covered by the argument geometry.
	 * @generated 
	 */
	boolean coveredBy(final Geometry g);
	/**
	
	                             * Tests whether this geometry covers the argument geometry.
	 * @generated 
	 */
	boolean covers(final Geometry g);
	/**
	
	                             * Tests whether this geometry crosses the argument geometry.
	 * @generated 
	 */
	boolean crosses(final Geometry g);
	/**
	
	                             * Computes a Geometry representing the closure of the point-set of the points contained in this Geometry that are not contained in the other Geometry.
	 * @generated 
	 */
	Geometry difference(final Geometry g);
	/**
	
	                             * Tests whether this geometry is disjoint from the argument geometry.
	 * @generated 
	 */
	boolean disjoint(final Geometry g);
	/**
	
	                             * Computes a Geometry representing the point-set which is common to both this Geometry and the other Geometry.
	 * @generated 
	 */
	Geometry intersection(final Geometry geom);
	/**
	
	 * Tests whether this geometry contains the argument geometry.
	 * @generated 
	 */
	boolean contains(final Geometry geom);
	/**
	
	                             * Returns the area of this Geometry.
	 * @generated 
	 */
	double getArea();
	/**
	
	                             * Returns the minimum distance between this Geometry and another Geometry.
	 * @generated 
	 */
	double distance(final Geometry g);
	/**
	
	                             * Returns true if the two Geometries are exactly equal, up to a specified distance tolerance.
	 * @generated 
	 */
	boolean equalsExact(final Geometry g);
	/**
	
	 * Tests whether this geometry is within the specified geometry.
	 * @generated 
	 */
	boolean within(final Geometry geom);
	/**
	
	 * Creates a new Geometry which is a normalized copy of this Geometry.
	 * @generated 
	 */
	Geometry normalized();
	/**
	
	                             * Returns the length of this Geometry.
	 * @generated 
	 */
	double getLength();
	/**
	
	                             * Converts this Geometry to normal form (or canonical form ).
	 * @generated 
	 */
	void normalize();
	/**
	 Apply the coordinate reference system to all coordinates in this geometry, without changing their instance
	 * e.g. change the values of the coordinate
	 * @generated 
	 */
	void applyCRS(final CoordinateReferenceSystem crs);
	/**
	
	                             * Computes a new geometry which has all component coordinate sequences in reverse order
	                             * (opposite orientation) to this one.
	 * @generated 
	 */
	Geometry reversed();
	/**
	 sets the coordinate system for the geometrie and all contained coordinates 
	 * @generated 
	 */
	void recursiveSetCRS(final CoordinateReferenceSystem crs);
	/**
	
	                             * Tests whether this geometry overlaps the specified geometry.
	 * @generated 
	 */
	boolean overlaps(final Geometry geom);
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem getCRS();
	/**
	
	                             * Tests whether this geometry touches the argument geometry.
	 * @generated 
	 */
	boolean touches(final Geometry geom);
	/**
	
	                             * Computes the union of all the elements of this geometry.
	 * @generated 
	 */
	Geometry union(final Geometry geom);
	/**
	
	                             * Computes a Geometry representing the closure of the point-set which is the union
	                             * of the points in this Geometry which are not contained in the other Geometry, with
	                             * the points in the other Geometry not contained in this Geometry.
	 * @generated 
	 */
	Geometry symDifference(final Geometry geom);	
	
}
