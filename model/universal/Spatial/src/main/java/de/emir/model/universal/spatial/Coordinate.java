package de.emir.model.universal.spatial;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Coordinate extends UObject 
{
	/**
	 references the current coordinate system, and therefore how x,y,z has to be read
	 * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed
	 * @generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY)
	public void setCrs(CoordinateReferenceSystem _crs);
	/**
	 references the current coordinate system, and therefore how x,y,z has to be read
	 * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed
	 * @generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY)
	public CoordinateReferenceSystem getCrs();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public void setX(double _x);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "x", associationType = AssociationType.PROPERTY)
	public double getX();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public void setY(double _y);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "y", associationType = AssociationType.PROPERTY)
	public double getY();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public void setZ(double _z);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "z", associationType = AssociationType.PROPERTY)
	public double getZ();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Angle getAzimuth(final Coordinate other);
	
	/**
	 *	@generated 
	 */
	Distance getDistance(final Coordinate other);
	/**
	 *	@generated 
	 */
	Coordinate getTarget(final Distance distance, final Angle azimuth);
	/**
	 *	@generated 
	 */
	int dimension();
	
	/**
	 
	 * returns the longitude (WGS84) of this coordinate.
	 * depending on the current crs, this includes a coordinate transformation 
	 * @generated 
	 */
	double getLatitude();
	
	/**
	 
	 * returns the longitude (WGS84) of this coordinate.
	 * depending on the current crs, this includes a coordinate transformation 
	 * @generated 
	 */
	double getLongitude();
	
	/**
	 returns a copy of this coordinate, that has been transformed into the given crs. 
	 * if parameter crs is the same as the member crs, a copy is returned. 
	 * \note if the member crs is set to null, the coordinate is assumed to be defined as WGS84 coordinate 
	 * @generated 
	 */
	Coordinate get(final CoordinateReferenceSystem crs);
	
	/**
	 *	@generated 
	 */
	void setLatLon(final double lat, final double lon);
	
	/**
	 *	@generated 
	 */
	void setLatLonAlt(final double lat, final double lon, final double alt);
	
	/**
	 *	@generated 
	 */
	void setXY(final double x, final double y);
	
	/**
	 *	@generated 
	 */
	void setXYZ(final double x, final double y, final double z);
	
	/**
	 *	@generated 
	 */
	Coordinate copy();
	
	/**
	
	 * Returns either an Vector2D or Vector3D, depending on the value of dimension()
	 * @generated 
	 */
	Vector toVector();
	/**
	 returns a 2D vector, skips the z value, if not NaN 
	 * @generated 
	 */
	Vector2D toVector2D();
	/**
	 returns a 3D vector, fills the z value with 0 if dimension() == 2 
	 * @generated 
	 */
	Vector3D toVector3D();
	/**
	 *	@generated 
	 */
	void fromVector(final Vector value, final CoordinateReferenceSystem crs);
	
	/**
	 *	@generated 
	 */
	void fromVector(final Vector value);
	/**
	 takes over the values from <code>value</code> 
	 * @generated 
	 */
	void set(final Coordinate value);
	/**
	 utility method to set all coordinate values and the crs 
	 * @note this method calls the corresponding setter and thus produces IValueChange events
	 * @generated 
	 */
	void set(final double x, final double y, final double z, final CoordinateReferenceSystem crs);
	/**
	 *	@generated 
	 */
	String readableString();
	
	void set(double x, double y, double z);
	void setXY(double x2, double y2, CoordinateReferenceSystem crs);
	public double[] toArray();
	
}
