package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 
 * coordinate reference system is a coordinate system that is related to the real world by a datum. 
 * Generally, the real world will be the Earth although the principles are not restricted to the Earth.
 * \source S-100 Version 2 Part 6 - CRS 
 * @generated 
 */
@UMLImplementation(classifier = CoordinateReferenceSystem.class)
abstract public class CoordinateReferenceSystemImpl extends IdentifiedObjectImpl implements CoordinateReferenceSystem  
{
	
	
	/**
	 the relevant coordinate system instance 
	 * this is defined by ISO 19111 ( for SingleCRS)  
	 *
	 * @generated 
	 */
	private CoordinateSystem mCs = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CoordinateReferenceSystemImpl(){
		super();
		//set the default values and assign them to this instance 
		setCs(mCs);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CoordinateReferenceSystemImpl(final CoordinateReferenceSystem _copy) {
		super(_copy);
		mCs = _copy.getCs();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CoordinateReferenceSystemImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mCs = _cs; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.CoordinateReferenceSystem;
	}

	/**
	 the relevant coordinate system instance 
	 * this is defined by ISO 19111 ( for SingleCRS)  
	 *
	 * @generated 
	 */
	public void setCs(CoordinateSystem _cs) {
		Notification<CoordinateSystem> notification = basicSet(mCs, _cs, CrsPackage.Literals.CoordinateReferenceSystem_cs);
		mCs = _cs;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 the relevant coordinate system instance 
	 * this is defined by ISO 19111 ( for SingleCRS)  
	 *
	 * @generated 
	 */
	public CoordinateSystem getCs() {
		return mCs;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public int dimension()
	{
		//TODO: 
		//  returns the dimension of this CRS 
		throw new UnsupportedOperationException("dimension not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Vector getDirectionToNorth()
	{
		//TODO: 
		//  returns a direction vector (x,y[,z]) that points to north and has a length of 1 meter
		throw new UnsupportedOperationException("getDirectionToNorth not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public List<Double> directionToBearing(final double x, final double y, final double z)
	{
		//TODO: 
		//  returns the bearing of an direction vector as set of angles (radian) 
		//  * @note the bearing refer to: the angle(s) away from North of a distant point as observed at the current point.
		//  * @returns double[1] if z != z (NaN) or double[2] if z == z
		//  *
		throw new UnsupportedOperationException("directionToBearing not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Vector bearingToDirection(final double yaw, final double pitch)
	{
		//TODO: 
		//  creates a direction vector that is rotated around yaw (radian) and pitch (radian)
		//  *  that points towards north, if both angles are 0 and rotates clockwise
		//  *  @note pitch is ignored if this is an 2D coordinate reference system
		//  * @param yaw rotation around the UP axis of the CRS (sometimes called azimuth) 
		//  * @param pitch rotation around the RIGHT axis of the CRS
		//  * @return a 2 or 3 dimensional vector that corresponds to the vector against north, rotated with yaw and pitch radians and has a length of 1 meter
		//  
		throw new UnsupportedOperationException("bearingToDirection not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public CoordinateReferenceSystem copy()
	{
		//TODO: 
		throw new UnsupportedOperationException("copy not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public String toWKT()
	{
		//TODO: 
		//  Converts the CoordinateReferenceSystem into a WKT (Well Known Text) String, that can be parsed from external tools / api's, like geotools.  
		throw new UnsupportedOperationException("toWKT not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getDistance(final Vector loc1, final Vector loc2)
	{
		//TODO: 
		//  
		//  * returns the distance between two coordinates, given in "this" CRS 
		//  * @return the distance in meters between the given points 
		//  *
		throw new UnsupportedOperationException("getDistance not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Vector getDistanceAndAzimuth(final Vector loc1, final Vector loc2)
	{
		//TODO: 
		// 
		//  * Returns the distance and azimuth between both coordinates and thus is a convinience method for 
		//  * getDistance(...) and getAzimuth(...)
		//  * @note this method should be used if both values are needed, since both calculations often perform almost the same steps
		//  * @return double[2] with return[0] == Distance in meter and return[1] == azimuth in radians. 
		//  
		throw new UnsupportedOperationException("getDistanceAndAzimuth not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CoordinateReferenceSystemImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
