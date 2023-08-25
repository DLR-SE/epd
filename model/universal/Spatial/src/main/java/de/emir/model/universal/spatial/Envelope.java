package de.emir.model.universal.spatial;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Envelope")	
public interface Envelope extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minPoint", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setMinPoint(Coordinate _minPoint);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minPoint", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public Coordinate getMinPoint();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maxPoint", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setMaxPoint(Coordinate _maxPoint);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maxPoint", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public Coordinate getMaxPoint();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Coordinate getCenter();
	
	/**
	 *	@generated 
	 */
	Vector getSize(final DistanceUnit unit);
	/**
	 *	@generated 
	 */
	void correct();
	
	/**
	 *	@generated 
	 */
	Envelope copy();
	
	/**
	 *	@generated 
	 */
	void setLatLon(final double min_lat, final double min_lon, final double max_lat, final double max_lon);
	/**
	 *	@generated 
	 */
	void setXY(final double min_x, final double min_y, final double max_x, final double max_y);
	/**
	 *	@generated 
	 */
	void setXYZ(final double min_x, final double min_y, final double min_z, final double max_x, final double max_y, final double max_z);
	/**
	 set the crs to min and max point 
	 * @generated 
	 */
	void setCRS(final CoordinateReferenceSystem crs);
	/**
	 Applys the CRS to min and max point, by changing their x,y, and z values but without changing the instances 
	 * @generated 
	 */
	void applyCRS(final CoordinateReferenceSystem crs);
	/**
	
	 * Checks weather both envelopes intersect with each other. 
	 * Fails if one envelope is totally contained inside the other
	 * @generated 
	 */
	boolean intersects(final Envelope other);
	/**
	
	 * Checks if the other envelope is totally or partially inside this envelope
	 * @generated 
	 */
	boolean containsOrIntersects(final Envelope other);
	/**
	 *	@generated 
	 */
	boolean contains(final Coordinate coord);
	/**
	
	 * Expand the current instance to also covers the other envelope
	 * @generated 
	 */
	void expandLocal(final Envelope other);
	/**
	
	 * Checks if the other envelope is inside this envelope, 
	 * fails if they do intersect but the other envelope is not totally within this
	 * @generated 
	 */
	boolean contains(final Envelope other);
	/**
	
	 * returns a new envelope that covers the current instance as well as the other envelope
	 * @generated 
	 */
	Envelope expand(final Envelope other);
	/**
	
	 * expands the current envelope to also cover the given coordinate
	 * @generated 
	 */
	void expandLocal(final Coordinate other);
	/**
	
	 * creates a new envelope that covers the current envelope as well as the given coordinate
	 * @generated 
	 */
	Envelope expand(final Coordinate other);
	/**
	 *	@generated 
	 */
	void tranformLocal(final Coordinate translate, final Rotation rotation);
	/**
	 *	@generated 
	 */
	Envelope transform(final Coordinate translate, final Rotation rotation);
	/**
	
	 * returns the four corners of the bounding box
	 * - vertices[0] = mMinPoint;
	 * - vertices[1] = Vec2d(mMaxPoint.X, mMinPoint.Y);
	 * - vertices[2] = mMaxPoint;
	 * - vertices[3] = Vec2d(mMinPoint.X, mMaxPoint.Y);
	 * @generated 
	 */
	List<Coordinate> getVertices();
	
}
