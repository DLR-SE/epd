package de.emir.model.universal.spatial.delegate;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.model.universal.units.Length;

/**
*	delegation interface
*	@generated
*/
public interface ICoordinateDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	Angle getAzimuth(Coordinate self, final Coordinate _other);
	/**
	 *	@generated 
	 */
	Distance getDistance(Coordinate self, final Coordinate _other);
	/**
	 *	@generated 
	 */
	Coordinate getTarget(Coordinate self, final Distance _distance, final Angle _azimuth);
	/**
	 *	@generated 
	 */
	int dimension(Coordinate self);
	/**
	 
	 * returns the longitude (WGS84) of this coordinate.
	 * depending on the current crs, this includes a coordinate transformation 
	 * @generated 
	 */
	double getLatitude(Coordinate self);
	/**
	 
	 * returns the longitude (WGS84) of this coordinate.
	 * depending on the current crs, this includes a coordinate transformation 
	 * @generated 
	 */
	double getLongitude(Coordinate self);
	/**
	 returns a copy of this coordinate, that has been transformed into the given crs. 
	 * if parameter crs is the same as the member crs, a copy is returned. 
	 * \note if the member crs is set to null, the coordinate is assumed to be defined as WGS84 coordinate 
	 * @generated 
	 */
	Coordinate get(Coordinate self, final CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	void setLatLon(Coordinate self, final double _lat, final double _lon);
	/**
	 *	@generated 
	 */
	void setLatLonAlt(Coordinate self, final double _lat, final double _lon, final double _alt);
	/**
	 *	@generated 
	 */
	void setXY(Coordinate self, final double _x, final double _y);
	/**
	 *	@generated 
	 */
	void setXYZ(Coordinate self, final double _x, final double _y, final double _z);
	/**
	 *	@generated 
	 */
	Coordinate copy(Coordinate self);
	/**
	
	 * Returns either an Vector2D or Vector3D, depending on the value of dimension()
	 * @generated 
	 */
	Vector toVector(Coordinate self);
	/**
	 returns a 2D vector, skips the z value, if not NaN 
	 * @generated 
	 */
	Vector2D toVector2D(Coordinate self);
	/**
	 returns a 3D vector, fills the z value with 0 if dimension() == 2 
	 * @generated 
	 */
	Vector3D toVector3D(Coordinate self);
	/**
	 *	@generated 
	 */
	void fromVector(Coordinate self, final Vector _value, final CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	void fromVector(Coordinate self, final Vector _value);
	/**
	 takes over the values from <code>value</code> 
	 * @generated 
	 */
	void set(Coordinate self, final Coordinate _value);
	/**
	 utility method to set all coordinate values and the crs 
	 * @note this method calls the corresponding setter and thus produces IValueChange events
	 * @generated 
	 */
	void set(Coordinate self, final double _x, final double _y, final double _z, final CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	String readableString(Coordinate self);
}
