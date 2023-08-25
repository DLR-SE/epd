package de.emir.model.universal.spatial.delegate;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface IEnvelopeDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	Coordinate getCenter(Envelope self);
	/**
	 *	@generated 
	 */
	Vector getSize(Envelope self, final DistanceUnit _unit);
	/**
	 *	@generated 
	 */
	void correct(Envelope self);
	/**
	 *	@generated 
	 */
	Envelope copy(Envelope self);
	/**
	 *	@generated 
	 */
	void setLatLon(Envelope self, final double _min_lat, final double _min_lon, final double _max_lat, final double _max_lon);
	/**
	 *	@generated 
	 */
	void setXY(Envelope self, final double _min_x, final double _min_y, final double _max_x, final double _max_y);
	/**
	 *	@generated 
	 */
	void setXYZ(Envelope self, final double _min_x, final double _min_y, final double _min_z, final double _max_x, final double _max_y, final double _max_z);
	/**
	 set the crs to min and max point 
	 * @generated 
	 */
	void setCRS(Envelope self, final CoordinateReferenceSystem _crs);
	/**
	 Applys the CRS to min and max point, by changing their x,y, and z values but without changing the instances 
	 * @generated 
	 */
	void applyCRS(Envelope self, final CoordinateReferenceSystem _crs);
	/**
	
	 * Checks weather both envelopes intersect with each other. 
	 * Fails if one envelope is totally contained inside the other
	 * @generated 
	 */
	boolean intersects(Envelope self, final Envelope _other);
	/**
	
	 * Checks if the other envelope is totally or partially inside this envelope
	 * @generated 
	 */
	boolean containsOrIntersects(Envelope self, final Envelope _other);
	/**
	 *	@generated 
	 */
	boolean contains(Envelope self, final Coordinate _coord);
	/**
	
	 * Expand the current instance to also covers the other envelope
	 * @generated 
	 */
	void expandLocal(Envelope self, final Envelope _other);
	/**
	
	 * Checks if the other envelope is inside this envelope, 
	 * fails if they do intersect but the other envelope is not totally within this
	 * @generated 
	 */
	boolean contains(Envelope self, final Envelope _other);
	/**
	
	 * returns a new envelope that covers the current instance as well as the other envelope
	 * @generated 
	 */
	Envelope expand(Envelope self, final Envelope _other);
	/**
	
	 * expands the current envelope to also cover the given coordinate
	 * @generated 
	 */
	void expandLocal(Envelope self, final Coordinate _other);
	/**
	
	 * creates a new envelope that covers the current envelope as well as the given coordinate
	 * @generated 
	 */
	Envelope expand(Envelope self, final Coordinate _other);
	/**
	 *	@generated 
	 */
	void tranformLocal(Envelope self, final Coordinate _translate, final Rotation _rotation);
	/**
	 *	@generated 
	 */
	Envelope transform(Envelope self, final Coordinate _translate, final Rotation _rotation);
	/**
	
	 * returns the four corners of the bounding box
	 * - vertices[0] = mMinPoint;
	 * - vertices[1] = Vec2d(mMaxPoint.X, mMinPoint.Y);
	 * - vertices[2] = mMaxPoint;
	 * - vertices[3] = Vec2d(mMinPoint.X, mMaxPoint.Y);
	 * @generated 
	 */
	List<Coordinate> getVertices(Envelope self);
}
