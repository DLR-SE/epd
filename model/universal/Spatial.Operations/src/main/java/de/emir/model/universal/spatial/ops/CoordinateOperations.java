package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.spatial.delegate.ICoordinateDelegationInterface;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;

/**
 * @generated
 */
public class CoordinateOperations implements ICoordinateDelegationInterface {

	double mLat = Double.NaN;
	double mLon = Double.NaN;

	/**
	 * @inheritDoc
	 * @generated
	*/
	public Coordinate getTarget(Coordinate self, final Distance distance, final Angle azimuth)
	{
		//TODO: 
		throw new UnsupportedOperationException("getTarget not yet implemented");
	}

	double mCartX = Double.NaN;
	double mCartY = Double.NaN;

	double mCopyX = Double.NaN;
	double mCopyY = Double.NaN;
	double mCopyZ = Double.NaN;

	boolean hasChanges(Coordinate c) {
		boolean res = false;
		if (mCopyX != mCopyX) {
			res = true;
		} else {
			if (mCopyX != c.getX() || mCopyY != c.getY())
				res = true;
		}
		if (res) {
			mCopyX = c.getX();
			mCopyY = c.getY();
			mCopyZ = c.getZ();
		}
		return res;
	}

	CoordinateReferenceSystem internalGetCRS(Coordinate coordinate) {
		CoordinateReferenceSystem crs = coordinate.getCrs();
		if (crs == null)
			if (coordinate.getZ() == coordinate.getZ()) {
				return CRSUtils.ENGINEERING_3D;
			} else {
				return CRSUtils.ENGINEERING_2D;
			}
		return crs;
	}

	void updateLatLon(Coordinate coordinate) {
		CoordinateReferenceSystem crs = internalGetCRS(coordinate);
		CoordinateReferenceSystem dst_crs = CRSUtils.WGS84_2D;
		if (dimension(coordinate) == 3)
			dst_crs = CRSUtils.WGS84_3D;
		double[] v = CRSUtils.transform(coordinate.getX(), coordinate.getY(), crs, dst_crs);
		// since we know the target CRS, we know the Axis Order
		mLat = v[0];
		mLon = v[1];
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public Angle getAzimuth(Coordinate self, final Coordinate other)
	{
		CoordinateReferenceSystem crs = internalGetCRS(self);
		Coordinate o = other.get(crs);
		double angle = crs.getDistanceAndAzimuth(toVector(self), toVector(o)).get(1); //0 is always the distance
		return new AngleImpl(angle, AngleUnit.RADIAN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Distance getDistance(Coordinate self, final Coordinate other) {
		CoordinateReferenceSystem crs = internalGetCRS(self);
		Coordinate oc = get(other, crs);
		return new DistanceImpl(crs.getDistance(toVector(self), toVector(oc)), DistanceUnit.METER);
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public Vector2D toVector2D(Coordinate self)
	{
		//TODO: 
		//  returns a 2D vector, skips the z value, if not NaN 
		throw new UnsupportedOperationException("toVector2D not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public Vector3D toVector3D(Coordinate self)
	{
		//TODO: 
		//  returns a 3D vector, fills the z value with 0 if dimension() == 2 
		throw new UnsupportedOperationException("toVector3D not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int dimension(Coordinate self) {
		double z = self.getZ();
		if (z != z) // isNaN
			return 2;
		return 3;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLatitude(Coordinate self) {
		if (mLat != mLat || hasChanges(self))
			updateLatLon(self);
		return mLat;
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void set(Coordinate self, final Coordinate value)
	{
		self.setX(value.getX());
		self.setY(value.getY());
		self.setZ(value.getZ());
		self.setCrs(value.getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void set(Coordinate self, final double x, final double y, final double z, final CoordinateReferenceSystem crs)
	{
		//TODO: 
		//  utility method to set all coordinate values and the crs 
		//  * @note this method calls the corresponding setter and thus produces IValueChange events
		//  
		throw new UnsupportedOperationException("set not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getLongitude(Coordinate self) {
		if (mLon != mLon || hasChanges(self))
			updateLatLon(self);
		return mLon;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Coordinate get(Coordinate self, final CoordinateReferenceSystem dst) {
		CoordinateReferenceSystem src = self.getCrs();
		int d = dimension(self);
		if (src == null) {
			if (d == 2) {
				src = CRSUtils.WGS84_2D;
			} else if (d == 3)
				src = CRSUtils.WGS84_3D;
			// if both fail, the following exception is intended.
		}

		// first check if we allready got the right crs
		if (src.equals(dst))
			return self;

		// if not, transform and return a copy
		if (d == 2)
			return new CoordinateImpl(CRSUtils.transform(self.getX(), self.getY(), src, dst), dst);
		return new CoordinateImpl(CRSUtils.transform(self.getX(), self.getY(), self.getZ(), src, dst), dst);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setLatLon(Coordinate self, final double lat, final double lon) {
		setLatLonAlt(self, lat, lon, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setLatLonAlt(Coordinate self, final double lat, final double lon, final double alt) {
		self.setX(mCopyX = lat);
		self.setY(mCopyY = lon);
		self.setZ(mCopyZ = alt);

		mLat = lat;
		mLon = lon;
		mCartX = Double.NaN;
		mCartY = Double.NaN;
		if (alt != alt) { // NaN => WGS84 2D
			self.setCrs(CRSUtils.WGS84_2D);
		} else
			self.setCrs(CRSUtils.WGS84_3D);
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void setXY(Coordinate self, final double x, final double y)
	{
		//TODO: 
		throw new UnsupportedOperationException("setXY not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void setXYZ(Coordinate self, final double x, final double y, final double z)
	{
		//TODO: 
		throw new UnsupportedOperationException("setXYZ not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public Coordinate copy(Coordinate self)
	{
		//TODO: 
		throw new UnsupportedOperationException("copy not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not 
	 */
	@Override
	public Vector toVector(Coordinate self) {
		if (self.dimension() == 2)
			return new Vector2DImpl(self.getX(), self.getY());
		else
			return new Vector3DImpl(self.getX(), self.getY(), self.getZ());
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void fromVector(Coordinate self, final Vector value, final CoordinateReferenceSystem crs)
	{
		//TODO: 
		throw new UnsupportedOperationException("fromVector not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public void fromVector(Coordinate self, final Vector value)
	{
		//TODO: 
		throw new UnsupportedOperationException("fromVector not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	*/
	public String readableString(Coordinate self)
	{
		//TODO: 
		throw new UnsupportedOperationException("readableString not yet implemented");
	}
}
