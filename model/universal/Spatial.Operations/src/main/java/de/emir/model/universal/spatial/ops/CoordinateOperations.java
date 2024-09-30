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
	 * @generated_not
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
	 * @generated_not
	 */
	@Override
	public Distance getDistance(Coordinate self, final Coordinate other) {
		CoordinateReferenceSystem crs = internalGetCRS(self);
		Coordinate oc = get(other, crs);
		return new DistanceImpl(crs.getDistance(toVector(self), toVector(oc)), DistanceUnit.METER);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public Vector2D toVector2D(Coordinate self)
	{
		return new Vector2DImpl(self.getX(), self.getY());
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public Vector3D toVector3D(Coordinate self)
	{
		if (dimension(self) == 2) {
            return new Vector3DImpl(self.getX(), self.getY(), 0);
        }
        return new Vector3DImpl(self.getX(), self.getY(), self.getZ());
	}

	/**
	 * @inheritDoc
	 * @generated_not
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
	 * @generated_not
	 */
	@Override
	public double getLatitude(Coordinate self) {
		if (mLat != mLat || hasChanges(self))
			updateLatLon(self);
		return mLat;
	}

	/**
	 * @inheritDoc
	 * @generated_not
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
	 * @generated_not
	*/
	public void set(Coordinate self, final double x, final double y, final double z, final CoordinateReferenceSystem crs)
	{
		self.setX(x);
		self.setY(y);
		self.setZ(z);
		self.setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	@Override
	public double getLongitude(Coordinate self) {
		if (mLon != mLon || hasChanges(self))
			updateLatLon(self);
		return mLon;
	}

	/**
	 * @inheritDoc
	 * @generated_not
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
	 * @generated_not
	 */
	@Override
	public void setLatLon(Coordinate self, final double lat, final double lon) {
		setLatLonAlt(self, lat, lon, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated_not
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
	 * @generated_not
	*/
	public void setXY(Coordinate self, final double x, final double y)
	{
		self.setX(x);
		self.setY(y);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void setXYZ(Coordinate self, final double x, final double y, final double z)
	{
		self.setX(x);
		self.setY(y);
		self.setZ(z);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public Coordinate copy(Coordinate self)
	{
		return new CoordinateImpl(self);
	}

	/**
	 * @inheritDoc
	 * @generated_not 
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
	 * @generated_not
	*/
	public void fromVector(Coordinate self, final Vector value, final CoordinateReferenceSystem crs)
	{
		fromVector(self, value);
        self.setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public void fromVector(Coordinate self, final Vector value)
	{
		self.setX(value.get(0));
        self.setY(value.get(1));
        if (value.dimensions() == 3) {
            self.setZ(value.get(2));
        }
	}

	/**
	 * @inheritDoc
	 * @generated_not
	*/
	public String readableString(Coordinate self)
	{
		if (self.dimension() == 3) {
            return String.format("%.6f %.6f %.6f", self.getX(), self.getY(), self.getZ());
        }
        return String.format("%.6f %.6f", self.getX(), self.getY());
	}
}
