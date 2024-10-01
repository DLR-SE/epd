package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.LocalCRS;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

/**
 * @warn this class contains manual changes
 * @generated
 */
@UMLImplementation(classifier = Coordinate.class)
public class CoordinateImpl extends UObjectImpl implements Coordinate {

	/**
	 *	@generated 
	 */
	private double mX = 0.0;
	/**
	 *	@generated 
	 */
	private double mY = 0.0;
	/**
	 * @generated not
	 */
	private double mZ = Double.NaN;
	/**
	 references the current coordinate system, and therefore how x,y,z has to be read
	 * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed
	 * @generated 
	 */
	private CoordinateReferenceSystem mCrs = null;

	//Shadow variables for fast access
	private double mLatitude = Double.NaN;
	private double mLongitude = Double.NaN;
	
	
	/** remembers the hash of the last source crs, that has been used to calculate latitude and longitude */
	private int mWGSCRSHash;
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CoordinateImpl(){
		super();
		//set the default values and assign them to this instance 
		setCrs(mCrs);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CoordinateImpl(final Coordinate _copy) {
		mX = _copy.getX();
		mY = _copy.getY();
		mZ = _copy.getZ();
		mCrs = _copy.getCrs();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CoordinateImpl(double _x, double _y, double _z, CoordinateReferenceSystem _crs) {
		mX = _x;
		mY = _y;
		mZ = _z;
		mCrs = _crs; 
	}

	public CoordinateImpl(double _x, double _y, CoordinateReferenceSystem _crs) {
		this(_x, _y, Double.NaN, _crs);
	}

	public CoordinateImpl(double[] v, CoordinateReferenceSystem crs) {
		mX = v[0];
		mY = v[1];
		if (v.length == 2) {
			mZ = Double.NaN;
		} else {
			mZ = v[2];
		}
		mCrs = crs;
	}

	public CoordinateImpl(double[] v) {
		this(v, null);
	}

	public CoordinateImpl(Vector vector, CoordinateReferenceSystem crs) {
		mX = vector.get(0);
		mY = vector.get(1);
		if (vector.dimensions() >= 3)
			mZ = vector.get(2);
		mCrs = crs;
	}

	public CoordinateImpl(Vector vector) {
		this(vector, null);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.Coordinate;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Coordinate getTarget(final Distance distance, final Angle azimuth)
	{
		CoordinateReferenceSystem crs = internalGetCRS(); //internalGetCRS() never returns null
		return new CoordinateImpl(crs.getTarget(new double[] {mX, mY, mZ}, distance.getAs(DistanceUnit.METER), azimuth.getAs(AngleUnit.RADIAN), Double.NaN), crs);
	}

	/**
	 *	@generated not
	 */
	@Override
	public void setX(double _x) {
		if (_x != _x)
			System.out.println();
		if (_x != mX){
			mLatitude = Double.NaN;//invalidate helper variables
			mLongitude = Double.NaN;
			if (needNotification(SpatialPackage.Literals.Coordinate_x)){
				double oldX = mX;
				mX = _x;
				notify(oldX, mX, SpatialPackage.Literals.Coordinate_x, NotificationType.SET);
			}else{
				mX = _x;
			}
		}
	}

	/**
	 *	@generated 
	 */
	public double getX() {
		return mX;
	}

	/**
	 *	@generated not
	 */
	@Override
	public void setY(double _y) {
		if (_y != mY){
			mLatitude = Double.NaN;//invalidate helper variables
			mLongitude = Double.NaN;
			
			if (needNotification(SpatialPackage.Literals.Coordinate_y)){
				double oldY = mY;
				mY = _y;
				notify(oldY, mY, SpatialPackage.Literals.Coordinate_y, NotificationType.SET);
			}else{
				mY = _y;
			}
		}
	}

	/**
	 *	@generated 
	 */
	public double getY() {
		return mY;
	}

	/**
	 *	@generated not
	 */
	@Override
	public void setZ(double _z) {
		//no need to disable shadow variables, since they do not apply to Z-Axis 
		//TODO: is that realy true?
		if (needNotification(SpatialPackage.Literals.Coordinate_z)){
			double oldZ = mZ;
			mZ = _z;
			notify(oldZ, mZ, SpatialPackage.Literals.Coordinate_z, NotificationType.SET);
		}else{
			mZ = _z;
		}
	}

	/**
	 *	@generated 
	 */
	public double getZ() {
		return mZ;
	}

	/**
	 references the current coordinate system, and therefore how x,y,z has to be read
	 * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed
	 * @generated not
	 */
	@Override
	public void setCrs(CoordinateReferenceSystem _crs) {
		if (_crs != mCrs){
			mLatitude = Double.NaN; //invalidate helper variables
			mLongitude = Double.NaN;
			
			Notification<CoordinateReferenceSystem> notification = basicSet(mCrs, _crs, SpatialPackage.Literals.Coordinate_crs);
			mCrs = _crs;
			if (notification != null){
				dispatchNotification(notification);
			}
		}
	}

	/**
	 references the current coordinate system, and therefore how x,y,z has to be read
	 * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed
	 * @generated 
	 */
	public CoordinateReferenceSystem getCrs() {
		return mCrs;
	}

	@Override
	public void set(double x, double y, double z) {
		setX(x); //use setter, to get the notification
		setY(y);
		setZ(z);
	}

	@Override
	public void setXY(double x2, double y2, CoordinateReferenceSystem crs) {
		setX(x2); //use setter, to get the notification
		setY(y2);
		setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector2D toVector2D()
	{
		return new Vector2DImpl(mX, mY);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector3D toVector3D()
	{
		double z = dimension() > 2 ? mZ : 0;
		return new Vector3DImpl(mX, mY, z);
	}

	//////////////////////////////////////////////////////////////////
	// Operations //
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Angle getAzimuth(final Coordinate other)
	{
		CoordinateReferenceSystem crs = internalGetCRS();
		Coordinate o = other.get(crs);
		int dim = Math.min(dimension(), other.dimension());
		double angle = 0;
		if (dim == 2)
			angle = crs.getDistanceAndAzimuth(toVector2D(), o.toVector2D()).get(1); //0 is always the distance
		else if (dim == 3)
			angle = crs.getDistanceAndAzimuth(toVector3D(), o.toVector3D()).get(1);
		else
			return null;
		return new AngleImpl(angle, AngleUnit.RADIAN);
	}


	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Distance getDistance(final Coordinate other)
	{
		CoordinateReferenceSystem crs = internalGetCRS();
		Coordinate oc = other.get(crs);
		return new DistanceImpl(crs.getDistance(toVector(), oc.toVector()), DistanceUnit.METER);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int dimension() {
		return mZ == mZ ? 3 : 2;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLatitude()
	{
		if (mLatitude != mLatitude ) //check for nan
			updateLatLon();
		else
			fastUpdateLatLon(); //see method doc
		return mLatitude;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLongitude()
	{
		if (mLongitude != mLongitude ) //check for nan
			updateLatLon();
		else
			fastUpdateLatLon(); //see method doc
		return mLongitude;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Coordinate value)
	{
		set(value.getX(), value.getY(), value.getZ(), value.getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Coordinate get(final CoordinateReferenceSystem dst)
	{
		if (dst == null)
			return this;
		
		CoordinateReferenceSystem src = internalGetCRS();

		// first check if we allready got the right crs
		if (src.equals(dst))
			return this;

		// if not, transform and return a copy
//		if (dimension() == 2)
//			return new CoordinateImpl(CRSUtils.transform(getX(), getY(), src, dst), dst);
		return new CoordinateImpl(CRSUtils.transform(getX(), getY(), getZ(), src, dst), dst);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setLatLon(final double lat, final double lon)
	{
		setLatLonAlt(lat, lon, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setLatLonAlt(final double lat, final double lon, final double alt)
	{
		set(lat, lon, alt, alt!=alt ? CRSUtils.WGS84_2D : CRSUtils.WGS84_3D);
		
		mLatitude = lat;
		mLongitude = lon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setXY(final double x, final double y)
	{
		setXYZ(x, y, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setXYZ(final double x, final double y, final double z)
	{
		set(x, y, z, z!=z ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D);
		
		mLatitude = Double.NaN;
		mLongitude = Double.NaN;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Coordinate copy()
	{
		return new CoordinateImpl(this);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector toVector() {
		if (dimension() == 2)
			return new Vector2DImpl(mX, mY);
		else
			return new Vector3DImpl(mX, mY, mZ);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void fromVector(final Vector value, final CoordinateReferenceSystem crs)
	{
		if (value.dimensions() == 2)
			set(value.get(0), value.get(1), crs);
		else
			set(value.get(0), value.get(1), value.get(2), crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void fromVector(final Vector value)
	{
		set(value.get(0), value.get(1), internalGetCRS());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		String str = "";
		if (internalGetCRS() instanceof WGS84CRS){
			str = String.format("%1.7f(Lat); %1.7f(Lon)", getLatitude(), getLongitude());//getLatitude()+"(Lat); " + getLongitude() + "(Lon)";
			if (dimension() > 2) str += getZ() + "(Alt)";
		}else{
			str = getX() + ";" + getY() + ";";
			if (dimension() > 2) str += getZ();
		}
		return str;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return readableString();
	}


	/** 
	 * Even if we got a latitude or longitude != NaN, we have to check if the coordinate has been changed
	 * this could happen, if the coordinate reference system we are currently using is a relative one. In such a case we need to 
	 * check if the CRS(.origin or .orientationOffset) has been changed and maybe recalculate lat and lon
	 * 
	 * @pre mLatitude and mLongitude != NaN
	 * @pos mLatitude and mLongitude have correct values, if required, they have been recalculated
	 */
	private void fastUpdateLatLon(){
		CoordinateReferenceSystem crs = internalGetCRS();
		if (crs instanceof LocalCRS == false)
			return ;  
		int crsHash = crs.getHash();
		if (crsHash != mWGSCRSHash)
			updateLatLon();//TODO: can we somehow improve performance here?
		//calling updateLatLon will result in recalculation of LatLon, without checking if the crs has been changed against the previous call to updateLatLon()
		//thus in many cases this recalculation will be not nessesary. 
	}
	private void updateLatLon() {
		CoordinateReferenceSystem crs = internalGetCRS();
		if (crs instanceof WGS84CRS){
			mLatitude = mX;
			mLongitude = mY;
			return ;
		}
		mWGSCRSHash = crs.getHash(); //remember the hash of the last transformation
		CoordinateReferenceSystem dst_crs = CRSUtils.WGS84_2D;
		double[] v = null;
		if (dimension() == 3){
			dst_crs = CRSUtils.WGS84_3D;
			v = CRSUtils.transform(mX, mY, mZ, crs, dst_crs);
		}else{
			v = CRSUtils.transform(mX, mY, mZ, crs, dst_crs);
		}
		// since we know the target CRS, we know the Axis Order
		if (v != null){
			mLatitude = v[0];
			mLongitude = v[1];
		}else{ //failed to get the transformation
			mLatitude = Double.NaN;
			mLongitude = Double.NaN; 
		}
	}
	
	//Ensures, that we always get a valid CRS
	private CoordinateReferenceSystem internalGetCRS() {
		CoordinateReferenceSystem crs = getCrs();
		if (crs == null)
			if (mZ == mZ) { //check for NaN
				return CRSUtils.ENGINEERING_3D;
			} else {
				return CRSUtils.ENGINEERING_2D;
			}
		return crs;
	}

	private void set(double x2, double y2, CoordinateReferenceSystem _crs) {
		set(x2, y2, Double.NaN, _crs);
	}
	
	/**
	 * @generated not
	 */
	@Override
	public void set(double x2, double y2, double z2, CoordinateReferenceSystem _crs) {
		if (x2 != x2)
			System.out.println();
 		setX(x2); setY(y2); setZ(z2); //need to use the setter to invalidate cartX/Y and lat/lon values
		setCrs(_crs);
	}

	@Override
	public double[] toArray() {
		if (mZ == mZ) //not NaN
			return new double[] {mX, mY, mZ};
		return new double[] {mX, mY};
	}
}
