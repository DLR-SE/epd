package de.emir.model.universal.crs;

import java.util.List;
import de.emir.model.universal.crs.CoordinateSystem;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 
 * coordinate reference system is a coordinate system that is related to the real world by a datum. 
 * Generally, the real world will be the Earth although the principles are not restricted to the Earth.
 * \source S-100 Version 2 Part 6 - CRS 
 * @generated 
 */
@UMLClass(name = "CoordinateReferenceSystem", isAbstract = true, parent = IdentifiedObject.class)	
public interface CoordinateReferenceSystem extends IdentifiedObject 
{
	/**
	 the relevant coordinate system instance 
	 * this is defined by ISO 19111 ( for SingleCRS)  
	 *
	 * @generated 
	 */
	@UMLProperty(name = "cs", associationType = AssociationType.COMPOSITE)
	public void setCs(CoordinateSystem _cs);
	/**
	 the relevant coordinate system instance 
	 * this is defined by ISO 19111 ( for SingleCRS)  
	 *
	 * @generated 
	 */
	@UMLProperty(name = "cs", associationType = AssociationType.COMPOSITE)
	public CoordinateSystem getCs();
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 returns the dimension of this CRS 
	 * @generated 
	 */
	int dimension();
	
	/**
	 returns a direction vector (x,y[,z]) that points to north and has a length of 1 meter
	 * @generated 
	 */
	Vector getDirectionToNorth();
	
	/**
	 returns the bearing of a direction vector as set of angles (radian)
	 * @note the bearing refer to: the angle(s) away from North of a distant point as observed at the current point.
	 * @returns double[1] if z != z (NaN) or double[2] if z == z
	 *
	 * @generated 
	 */
	List<Double> directionToBearing(final double x, final double y, final double z);
	/**
	 creates a direction vector that is rotated around yaw (radian) and pitch (radian)
	 *  that points towards north, if both angles are 0 and rotates clockwise
	 *  @note pitch is ignored if this is an 2D coordinate reference system
	 * @param yaw rotation around the UP axis of the CRS (sometimes called azimuth) 
	 * @param pitch rotation around the RIGHT axis of the CRS
	 * @return a 2 or 3 dimensional vector that corresponds to the vector against north, rotated with yaw and pitch radians and has a length of 1 meter
	 * @generated 
	 */
	Vector bearingToDirection(final double yaw, final double pitch);
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem copy();
	/**
	 Converts the CoordinateReferenceSystem into a WKT (Well Known Text) String, that can be parsed from external tools / api's, like geotools.  
	 * @generated 
	 */
	String toWKT();
	/**
	 
	 * returns the distance between two coordinates, given in "this" CRS 
	 * @return the distance in meters between the given points 
	 *
	 * @generated 
	 */
	double getDistance(final Vector loc1, final Vector loc2);
	/**
	
	 * Returns the distance and azimuth between both coordinates and thus is a convinience method for 
	 * getDistance(...) and getAzimuth(...)
	 * @note this method should be used if both values are needed, since both calculations often perform almost the same steps
	 * @return double[2] with return[0] == Distance in meter and return[1] == azimuth in radians. 
	 * @generated 
	 */
	Vector getDistanceAndAzimuth(final Vector loc1, final Vector loc2);
	
	/**
	 * Returns a unique hash for this coordinate reference system used for equals checks
	 * @note the hash should change if the origin or the orientation of the crs changes
	 * @return
	 */
	public int getHash();
	
	/**
	 * Calculates the new position, based on one or two angles (2 or 3D) and a distance
	 * @param origin
	 * @param distance_meter
	 * @param azimuth_radian
	 * @param polar_radian
	 * @return
	 */
	public double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian);

	
}
