package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.geometry.jts.ReferencedEnvelope3D;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.delegate.IEnvelopeDelegationInterface;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Rotation;
import de.emir.model.universal.units.impl.QuaternionImpl;

/**
 * @generated
 */
public class EnvelopeOperations implements IEnvelopeDelegationInterface {

	public org.locationtech.jts.geom.Envelope getNativeEnvelope(Envelope self) {
		if (self.getMaxPoint() == null || self.getMinPoint() == null)
			return null;
		int dim = self.getMaxPoint().dimension();
		CoordinateReferenceSystem crs = self.getMaxPoint().getCrs();
		if (crs == null)
			crs = self.getMinPoint().getCrs();
		org.opengis.referencing.crs.CoordinateReferenceSystem ncrs = null;
		try {
			ncrs = crs != null ? CRS.parseWKT(crs.toWKT()) : null;
		} catch (FactoryException e) {
			e.printStackTrace();
		}
		if (dim == 2) {
			return new ReferencedEnvelope(self.getMinPoint().getX(), self.getMaxPoint().getX(),
					self.getMinPoint().getY(), self.getMaxPoint().getY(), ncrs);
		} else
			return new ReferencedEnvelope3D(self.getMinPoint().getX(), self.getMaxPoint().getX(),
					self.getMinPoint().getY(), self.getMaxPoint().getY(), self.getMinPoint().getZ(),
					self.getMaxPoint().getZ(), ncrs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Coordinate getCenter(Envelope self) {
		double x = (self.getMinPoint().getX() + self.getMaxPoint().getX()) / 2.0;
		double y = (self.getMinPoint().getY() + self.getMaxPoint().getY()) / 2.0;
		double z = self.getMinPoint().dimension() == 2 ? (self.getMinPoint().getZ() + self.getMaxPoint().getZ()) / 2.0
				: Double.NaN;

		return new CoordinateImpl(x, y, z, self.getMinPoint().getCrs());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Vector getSize(Envelope self, final DistanceUnit unit) {
		// For now, only the 2-dimensional is supported
		if (self.getMinPoint().dimension() != 2)
			throw new UnsupportedOperationException();
		Vector2DImpl size = new Vector2DImpl();
		// calc the size at the center, by creating vertices on all sides and
		// measure the distance
		Coordinate c = getCenter(self);
		Coordinate mi_x = new CoordinateImpl(self.getMinPoint().getX(), c.getY(), c.getCrs());
		Coordinate ma_x = new CoordinateImpl(self.getMaxPoint().getX(), c.getY(), c.getCrs());
		Distance distance_x = mi_x.getDistance(ma_x);
		Coordinate mi_y = new CoordinateImpl(c.getX(), self.getMinPoint().getY(), c.getCrs());
		Coordinate ma_y = new CoordinateImpl(c.getX(), self.getMaxPoint().getY(), c.getCrs());
		Distance distance_y = mi_y.getDistance(ma_y);
		size.setX(distance_x.getAs(unit));
		size.setY(distance_y.getAs(unit));
		return size;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void correct(Envelope self) {
		if (self.getMinPoint().getX() > self.getMaxPoint().getX()) {
			double mi = self.getMinPoint().getX();
			self.getMinPoint().setX(self.getMaxPoint().getX());
			self.getMaxPoint().setX(mi);
		}
		if (self.getMinPoint().getY() > self.getMaxPoint().getY()) {
			double mi = self.getMinPoint().getY();
			self.getMinPoint().setY(self.getMaxPoint().getY());
			self.getMaxPoint().setY(mi);
		}
		if (self.getMinPoint().dimension() != 2) {
			if (self.getMinPoint().getZ() > self.getMaxPoint().getZ()) {
				double mi = self.getMinPoint().getZ();
				self.getMinPoint().setZ(self.getMaxPoint().getZ());
				self.getMaxPoint().setZ(mi);
			}
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope copy(Envelope self) {
		return new EnvelopeImpl(self);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setLatLon(Envelope self, final double min_lat, final double min_lon, final double max_lat,
			final double max_lon) {
		Coordinate mip = self.getMinPoint();
		mip.setLatLon(min_lat, min_lon);
		Coordinate map = self.getMaxPoint();
		map.setLatLon(max_lat, max_lon);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void setXYZ(Envelope self, final double min_x, final double min_y, final double min_z, final double max_x, final double max_y, final double max_z)
	{
		Coordinate mip = self.getMinPoint();
		mip.setXYZ(min_x, min_y, min_z);
		Coordinate map = self.getMaxPoint();
		map.setXYZ(max_x, max_y, min_z);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setXY(Envelope self, final double min_x, final double min_y, final double max_x, final double max_y) {
		Coordinate mip = self.getMinPoint();
		mip.setXY(min_x, min_y);
		Coordinate map = self.getMaxPoint();
		map.setXY(max_x, max_y);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void applyCRS(Envelope self, final CoordinateReferenceSystem crs)
	{
		self.getMinPoint().set(self.getMinPoint().get(crs));
		self.getMaxPoint().set(self.getMaxPoint().get(crs));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setCRS(Envelope self, final CoordinateReferenceSystem crs) {
		self.getMinPoint().setCrs(crs);
		self.getMaxPoint().setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public boolean containsOrIntersects(Envelope self, final Envelope other)
	{
		if (self == null || other == null)
			return false;
		int d = self.getMinPoint().dimension();
		if (true || d == 2) {
			Coordinate smin = self.getMinPoint();
			CoordinateReferenceSystem crs = smin.getCrs(); 
			if (crs == null) {
				crs = CRSUtils.WGS84_2D;
				smin = smin.get(crs);
			}
			Coordinate smax = self.getMaxPoint().get(crs);
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
			
			if (contains(self, other))
				return true;
			return false;
//			if (!contains(self, omax))
//				return false;
//			if (!contains(self, omin))
//				return false;
//			if (!contains(self, new CoordinateImpl(omax.getX(), omin.getY(), omin.getCrs())))
//				return false;
//			if (!contains(self, new CoordinateImpl(omin.getX(), omax.getY(), omin.getCrs())))
//				return false;
//			return true;
		}
		throw new UnsupportedOperationException("3D intersection not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean intersects(Envelope self, final Envelope other) {
		if (self == null || other == null)
			return false;
		int d = self.getMinPoint().dimension();
		if (d == 2) {
			Coordinate smin = self.getMinPoint();
			CoordinateReferenceSystem crs = smin.getCrs(); 
			if (crs == null) {
				crs = CRSUtils.WGS84_2D;
				smin = smin.get(crs);
			}
			Coordinate smax = self.getMaxPoint().get(crs);
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
	@Override
	public boolean contains(Envelope self, final Envelope other)
	{
		//check all vertices of the other envelope
		List<Coordinate> vertices = getVertices(other);
		for (Coordinate c : vertices)
			if (!contains(self, c))
				return false;
		return true;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean contains(Envelope self, final Coordinate _coord) {
		if (_coord == null)
			return false;
		Coordinate coord = _coord.get(self.getMinPoint().getCrs());
		double x = coord.getX();
		double y = coord.getY();
		double minx = self.getMinPoint().getX();
		double maxx = self.getMaxPoint().getX();
		double miny = self.getMinPoint().getY();
		double maxy = self.getMaxPoint().getY();

		return x >= minx &&
		        x <= maxx &&
		        y >= miny &&
		        y <= maxy;
		
//		return x >= minx && x <= maxx && y >= miny && y <= maxy;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void expandLocal(Envelope self, final Envelope other) {
		if (self.getMaxPoint() == null) {
			self.setMinPoint(new CoordinateImpl(other.getMinPoint()));
			self.setMaxPoint(new CoordinateImpl(other.getMaxPoint()));
			return;
		}
		if (self.getMinPoint().getCrs() == null) {
			CoordinateReferenceSystem crs = other.getMinPoint().getCrs();
			if (crs == null)
				crs = other.getMinPoint().dimension() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
			self.getMinPoint().setCrs(crs);
			self.getMaxPoint().setCrs(crs);
		}
		Coordinate omi = other.getMinPoint().get(self.getMinPoint().getCrs());
		Coordinate oma = other.getMaxPoint().get(self.getMinPoint().getCrs());

		double minX = Math.min(self.getMinPoint().getX(), omi.getX());
		double minY = Math.min(self.getMinPoint().getY(), omi.getY());
		double maxX = Math.max(self.getMaxPoint().getX(), oma.getX());
		double maxY = Math.max(self.getMaxPoint().getY(), oma.getY());
		self.getMinPoint().set(minX, minY, Double.NaN);
		self.getMaxPoint().set(maxX, maxY, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope expand(Envelope self, final Envelope other) {
		Envelope out = copy(self);
		expandLocal(out, other);
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void expandLocal(Envelope self, final Coordinate other) {
		if (self.getMaxPoint() == null) {
			self.setMinPoint(new CoordinateImpl(other));
			self.setMaxPoint(new CoordinateImpl(other));
			return;
		}
		if (self.getMinPoint().getCrs() == null) {
			CoordinateReferenceSystem crs = other.getCrs();
			if (crs == null)
				crs = other.dimension() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
			self.getMinPoint().setCrs(crs);
			self.getMaxPoint().setCrs(crs);
		}
		Coordinate o = other.get(self.getMinPoint().getCrs());
		double minX = Math.min(self.getMinPoint().getX(), o.getX());
		double minY = Math.min(self.getMinPoint().getY(), o.getY());
		double maxX = Math.max(self.getMaxPoint().getX(), o.getX());
		double maxY = Math.max(self.getMaxPoint().getY(), o.getY());
		self.getMinPoint().set(minX, minY, Double.NaN);
		self.getMaxPoint().set(maxX, maxY, Double.NaN);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope expand(Envelope self, final Coordinate other) {
		Envelope out = copy(self);
		expandLocal(out, other);
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void tranformLocal(Envelope self, final Coordinate translate, final Rotation rotation) {
		// TODO:
		throw new UnsupportedOperationException("tranformLocal not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope transform(Envelope self, final Coordinate translate, final Rotation _rotation) {
		CoordinateReferenceSystem crs = self.getMinPoint().getCrs();
		int dim = translate.dimension();
		if (crs == null)
			crs = dim == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
		Coordinate mi = self.getMinPoint().get(crs);
		Coordinate ma = self.getMaxPoint().get(crs);
		Coordinate trans_trans = translate.get(crs);
		Vector2D t = new Vector2DImpl(trans_trans.getX(), trans_trans.getY());
		if (dim == 2){
			final Rotation rotation = _rotation == null ? new QuaternionImpl(0, 0, 0, 1) : _rotation;
			Vector2D miV = rotation.transform2D(new Vector2DImpl(mi.getX(), mi.getY()));
			Vector2D maV = rotation.transform2D(new Vector2DImpl(ma.getX(), ma.getY()));
			Vector2D transl = new Vector2DImpl(trans_trans.getX(), trans_trans.getY());
			miV.addLocal(t);
			maV.addLocal(t);
			return new EnvelopeImpl(miV.getX(), miV.getY(), maV.getX(), maV.getY(), crs);
		}
		
		throw new UnsupportedOperationException();  
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public List<Coordinate> getVertices(Envelope self) {
		List<Coordinate> out = new ArrayList<Coordinate>();
		out.add(self.getMinPoint().copy());
		Coordinate c = self.getMinPoint().copy(); //copy to hold the crs
		c.setX(self.getMaxPoint().getX());
		out.add(c);
		out.add(self.getMaxPoint().copy());
		c = self.getMaxPoint().copy();
		c.setX(self.getMinPoint().getX());
		out.add(c);
		return out;
	}
}
