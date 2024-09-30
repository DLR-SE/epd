package de.emir.model.universal.spatial.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.delegate.IEnvelopeDelegationInterface;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Rotation;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.model.universal.units.impl.QuaternionImpl;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Envelope.class)
public class EnvelopeImpl extends UObjectImpl implements Envelope  
{
	
	
	/**
	 *	@generated 
	 */
	private Coordinate mMinPoint = new CoordinateImpl();
	/**
	 *	@generated 
	 */
	private Coordinate mMaxPoint = new CoordinateImpl();
			
	/**
	 *	Default constructor
	 * @note using this constructor creates a valid envelope with min and max point at location [0,0,NaN]
	 *	@generated not
	 */
	public EnvelopeImpl(){
		super();
		//set the default values and assign them to this instance
		setMinPoint(mMinPoint);
		setMaxPoint(mMaxPoint);
	}
		
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EnvelopeImpl(final Envelope _copy) {
		mMinPoint = new CoordinateImpl(_copy.getMinPoint());
		mMaxPoint = new CoordinateImpl(_copy.getMaxPoint());
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EnvelopeImpl(Coordinate _minPoint, Coordinate _maxPoint) {
		mMinPoint = new CoordinateImpl(_minPoint); 
		mMaxPoint = new CoordinateImpl(_maxPoint); 
	}
	
	public EnvelopeImpl(double x, double y, double x2, double y2, CoordinateReferenceSystem crs) {
		getMinPoint().setXY(x, y, crs);
		getMaxPoint().setXY(x2, y2, crs);
	}

	/**
	 * Initialize the envelope at one point (min and max point are the same)
	 * @param coord
	 */
	public EnvelopeImpl(Coordinate coord){
		this(new CoordinateImpl(coord), new CoordinateImpl(coord)); //if we use the same instance for min and max we may run into trouble
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.Envelope;
	}

	/**
	 *	@generated 
	 */
	public void setMinPoint(Coordinate _minPoint) {
		if (needNotification(SpatialPackage.Literals.Envelope_minPoint)){
			Coordinate _oldValue = mMinPoint;
			mMinPoint = _minPoint;
			notify(_oldValue, mMinPoint, SpatialPackage.Literals.Envelope_minPoint, NotificationType.SET);
		}else{
			mMinPoint = _minPoint;
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getMinPoint() {
		return mMinPoint;
	}

	/**
	 *	@generated 
	 */
	public void setMaxPoint(Coordinate _maxPoint) {
		if (needNotification(SpatialPackage.Literals.Envelope_maxPoint)){
			Coordinate _oldValue = mMaxPoint;
			mMaxPoint = _maxPoint;
			notify(_oldValue, mMaxPoint, SpatialPackage.Literals.Envelope_maxPoint, NotificationType.SET);
		}else{
			mMaxPoint = _maxPoint;
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getMaxPoint() {
		return mMaxPoint;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Coordinate getCenter()
	{
		double x = (getMinPoint().getX() + getMaxPoint().getX()) / 2.0;
		double y = (getMinPoint().getY() + getMaxPoint().getY()) / 2.0;
		double z = getMinPoint().dimension() == 2 ? (getMinPoint().getZ() + getMaxPoint().getZ()) / 2.0 : Double.NaN;

		return new CoordinateImpl(x, y, z, getMinPoint().getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector getSize(final DistanceUnit unit)
	{
		// For now, only the 2-dimensional is supported
		if (getMinPoint().dimension() != 2)
			throw new UnsupportedOperationException();
		Vector2DImpl size = new Vector2DImpl();
		// calc the size at the center, by creating vertices on all sides and
		// measure the distance
		Coordinate c = getCenter();
		Coordinate mi_x = new CoordinateImpl(getMinPoint().getX(), c.getY(), c.getCrs());
		Coordinate ma_x = new CoordinateImpl(getMaxPoint().getX(), c.getY(), c.getCrs());
		Distance distance_x = mi_x.getDistance(ma_x);
		Coordinate mi_y = new CoordinateImpl(c.getX(), getMinPoint().getY(), c.getCrs());
		Coordinate ma_y = new CoordinateImpl(c.getX(), getMaxPoint().getY(), c.getCrs());
		Distance distance_y = mi_y.getDistance(ma_y);
		size.setX(distance_x.getAs(unit));
		size.setY(distance_y.getAs(unit));
		return size;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void correct()
	{
		if (getMinPoint().getX() > getMaxPoint().getX()) {
			double mi = getMinPoint().getX();
			getMinPoint().setX(getMaxPoint().getX());
			getMaxPoint().setX(mi);
		}
		if (getMinPoint().getY() > getMaxPoint().getY()) {
			double mi = getMinPoint().getY();
			getMinPoint().setY(getMaxPoint().getY());
			getMaxPoint().setY(mi);
		}
		if (getMinPoint().dimension() != 2) {
			if (getMinPoint().getZ() > getMaxPoint().getZ()) {
				double mi = getMinPoint().getZ();
				getMinPoint().setZ(getMaxPoint().getZ());
				getMaxPoint().setZ(mi);
			}
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope copy()
	{
		return new EnvelopeImpl(this);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setXYZ(final double min_x, final double min_y, final double min_z, final double max_x, final double max_y, final double max_z)
	{
		getMinPoint().setXYZ(min_x, min_y, min_z);
		getMaxPoint().setXYZ(max_x, max_y, min_z);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setLatLon(final double min_lat, final double min_lon, final double max_lat, final double max_lon)
	{
		getMinPoint().setLatLon(min_lat, min_lon);
		getMaxPoint().setLatLon(max_lat, max_lon);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void applyCRS(final CoordinateReferenceSystem crs)
	{
		getMinPoint().set(getMinPoint().get(crs));
		getMaxPoint().set(getMaxPoint().get(crs));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setXY(final double min_x, final double min_y, final double max_x, final double max_y)
	{
		setXYZ(min_x, min_y, Double.NaN, max_x, max_y, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean containsOrIntersects(final Envelope other)
	{
		if (other == null)
			return false;
		int d = Math.min(getMinPoint().dimension(), other.getMinPoint().dimension());
		if (d == 2) {
			Coordinate smin = getMinPoint();
			CoordinateReferenceSystem crs = smin.getCrs(); 
			if (crs == null) {
				crs = CRSUtils.WGS84_2D;
				smin = smin.get(crs);
			}
			Coordinate smax = getMaxPoint().get(crs);
			Coordinate omin = other.getMinPoint().get(crs);
			Coordinate omax = other.getMaxPoint().get(crs);
			
			double minX = smin.getX();
			double minY = smin.getY();
			double oMinX =  omin.getX();
			double oMinY = omin.getY();
			
			double maxX = smax.getX();
			double maxY = smax.getY();
			double oMaxX =  omax.getX();
			double oMaxY = omax.getY();
			
			//check if they intersect
			boolean intersect = !(oMinX > maxX ||
			        oMaxX < minX ||
			        oMinY > maxY ||
			        oMaxY < minY);
			if (intersect)
				return true;
			
			if (contains(other))
				return true;
			return false;
		}
		throw new UnsupportedOperationException("3D intersection not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setCRS(final CoordinateReferenceSystem crs)
	{
		getMinPoint().setCrs(crs);
		getMaxPoint().setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean contains(final Envelope other)
	{
		//check all vertices of the other envelope
		List<Coordinate> vertices = other.getVertices();
		for (Coordinate c : vertices)
			if (!contains(c))
				return false;
		return true;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean intersects(final Envelope other)
	{
		if (other == null)
			return false;
		if (getMinPoint().dimension() == 2) {
			Coordinate smin = getMinPoint();
			CoordinateReferenceSystem crs = smin.getCrs(); 
			if (crs == null) {
				crs = CRSUtils.WGS84_2D;
				smin = smin.get(crs);
			}
			Coordinate smax = getMaxPoint().get(crs);
			Coordinate omin = other.getMinPoint().get(crs);
			Coordinate omax = other.getMaxPoint().get(crs);
			
			double minX = smin.getX();
			double minY = smin.getY();
			double oMinX =  omin.getX();
			double oMinY = omin.getY();
			
			double maxX = smax.getX();
			double maxY = smax.getY();
			double oMaxX =  omax.getX();
			double oMaxY = omax.getY();
			
			return !(oMinX > maxX ||
			        oMaxX < minX ||
			        oMinY > maxY ||
			        oMaxY < minY);
		}
		throw new UnsupportedOperationException("3D intersection not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean contains(final Coordinate _coord)
	{
		if (_coord == null)
			return false;
		Coordinate coord = _coord.get(getMinPoint().getCrs());
		double x = coord.getX();
		double y = coord.getY();
		return x >= getMinPoint().getX() &&
		        x <= getMaxPoint().getX() &&
		        y >= getMinPoint().getY() &&
		        y <= getMaxPoint().getY();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void expandLocal(final Envelope other)
	{
		if (getMaxPoint() == null) {
			setMinPoint(new CoordinateImpl(other.getMinPoint()));
			setMaxPoint(new CoordinateImpl(other.getMaxPoint()));
			return;
		}
		if (getMinPoint().getCrs() == null) {
			CoordinateReferenceSystem crs = other.getMinPoint().getCrs();
			if (crs == null)
				crs = other.getMinPoint().dimension() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
			getMinPoint().setCrs(crs);
			getMaxPoint().setCrs(crs);
		}
		Coordinate omi = other.getMinPoint().get(getMinPoint().getCrs());
		Coordinate oma = other.getMaxPoint().get(getMinPoint().getCrs());

		double minX = Math.min(getMinPoint().getX(), omi.getX());
		double minY = Math.min(getMinPoint().getY(), omi.getY());
		double maxX = Math.max(getMaxPoint().getX(), oma.getX());
		double maxY = Math.max(getMaxPoint().getY(), oma.getY());
		int d = getMinPoint().dimension();
		double maxZ = d > 2 ? Math.max(getMaxPoint().getZ(), oma.getZ()) : Double.NaN;
		double minZ = d > 2 ? Math.min(getMinPoint().getZ(), omi.getZ()) : Double.NaN;
		getMinPoint().set(minX, minY, minZ);
		getMaxPoint().set(maxX, maxY, maxZ);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope expand(final Envelope other)
	{
		Envelope out = copy();
		out.expandLocal(other);
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void expandLocal(final Coordinate other)
	{
		if (getMaxPoint() == null) {
			setMinPoint(new CoordinateImpl(other));
			setMaxPoint(new CoordinateImpl(other));
			return;
		}
		if (getMinPoint().getCrs() == null) {
			CoordinateReferenceSystem crs = other.getCrs();
			if (crs == null)
				crs = other.dimension() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
			getMinPoint().setCrs(crs);
			getMaxPoint().setCrs(crs);
		}
		Coordinate o = other.get(getMinPoint().getCrs());
		double minX = Math.min(getMinPoint().getX(), o.getX());
		double minY = Math.min(getMinPoint().getY(), o.getY());
		double maxX = Math.max(getMaxPoint().getX(), o.getX());
		double maxY = Math.max(getMaxPoint().getY(), o.getY());
		int d = getMinPoint().dimension();
		double maxZ = d > 2 ? Math.max(getMaxPoint().getZ(), o.getZ()) : Double.NaN;
		double minZ = d > 2 ? Math.min(getMinPoint().getZ(), o.getZ()) : Double.NaN;
		getMinPoint().set(minX, minY, minZ);
		getMaxPoint().set(maxX, maxY, maxZ);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope expand(final Coordinate other)
	{
		Envelope copy = copy();
		copy.expandLocal(other);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void tranformLocal(final Coordinate translate, final Rotation _rotation)
	{
		CoordinateReferenceSystem crs = getMinPoint().getCrs();
		int dim = translate.dimension();
		if (crs == null)
			crs = dim == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
		Coordinate mi = getMinPoint().get(crs);
		Coordinate ma = getMaxPoint().get(crs);
		Coordinate trans_trans = translate.get(crs);
		Vector2D t = new Vector2DImpl(trans_trans.getX(), trans_trans.getY());
		if (dim == 2){
			final Rotation rotation = _rotation == null ? new QuaternionImpl(0, 0, 0, 1) : _rotation;
			Vector2D miV = rotation.transform2D(new Vector2DImpl(mi.getX(), mi.getY()));
			Vector2D maV = rotation.transform2D(new Vector2DImpl(ma.getX(), ma.getY()));
			Vector2D transl = new Vector2DImpl(trans_trans.getX(), trans_trans.getY());
			miV.addLocal(t);
			maV.addLocal(t);
			getMinPoint().set(new CoordinateImpl(miV, getMinPoint().getCrs()));
			getMaxPoint().set(new CoordinateImpl(maV, getMinPoint().getCrs()));
		}
		
		throw new UnsupportedOperationException();  
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope transform(final Coordinate translate, final Rotation rotation)
	{
		Envelope copy = copy();
		copy.tranformLocal(translate, rotation);
		return copy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public List<Coordinate> getVertices()
	{
		List<Coordinate> out = new ArrayList<Coordinate>();
		out.add(getMinPoint().copy());
		Coordinate c = getMinPoint().copy(); //copy to hold the crs
		c.setX(getMaxPoint().getX());
		out.add(c);
		out.add(getMaxPoint().copy());
		c = getMaxPoint().copy();
		c.setX(getMinPoint().getX());
		out.add(c);
		return out;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EnvelopeImpl{" +
		"}";
	}

	/**
	 * Create an envelope around the given coordinate (center) and the given length and width
	 * @param sensorWGSLoc
	 * @param length
	 * @param width
	 * @return
	 */
	public static Envelope create(Coordinate center, LengthImpl length, LengthImpl width) {
		Engineering2DImpl e2d = new Engineering2DImpl(); e2d.setOrigin(center.toVector());
		double l2 = length.getAs(DistanceUnit.METER) / 2.0;
		double w2 = width.getAs(DistanceUnit.METER) / 2.0;
		Coordinate min = new CoordinateImpl(-l2, -w2, e2d);
		Coordinate max = new CoordinateImpl( l2,  w2, e2d);
		return new EnvelopeImpl(min, max);
	}

	public static Envelope create(Coordinate center, Length length, Length width, Length height) {
		EngineeringCRS crs = null;
		if (center.getCrs() instanceof EngineeringCRS)
			crs = (EngineeringCRS)center.getCrs();
		else
			crs = new Engineering3DImpl(center.toVector());
		
		double l2 = length.getAs(DistanceUnit.METER) / 2.0;
		double w2 = width.getAs(DistanceUnit.METER) / 2.0;
		double h2 = height.getAs(DistanceUnit.METER) / 2.0;
		Coordinate c = center.get(crs); 
		double z = c.getZ();
		if (z != z) z = 0;
		Coordinate min = new CoordinateImpl(c.getX() + -l2, c.getY() + -w2, z + -h2, crs);
		Coordinate max = new CoordinateImpl(c.getX() +  l2, c.getY() +  w2, z +  h2, crs);
		return new EnvelopeImpl(min, max);
	}
}
