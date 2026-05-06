package de.emir.model.universal.spatial.ops;

import org.geotools.api.referencing.FactoryException;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.GeometryFactory;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.NativeCRSImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.delegate.IGeometryDelegationInterface;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 *	@generated 
 */
public abstract class GeometryOperations implements IGeometryDelegationInterface{

	protected static GeometryFactory sGeometryFactory = new GeometryFactory();
	private static org.geotools.api.referencing.crs.CoordinateReferenceSystem sNativeWGS;

    private org.locationtech.jts.geom.Geometry mNative = null;
    private Envelope mEnvelope;

    /**
     * Based on the UCore geometry, create a native JTS geometry object.
     * @param self UCore geometry object
     * @return jts geometry object
     */
	public abstract org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self);

    /**
     * Based on the JTS geometry, create a UCore geometry object.
     * @param self UCore geometry object
     * @param crs coordinate reference system to use
     * @return jts geometry object
     */
    public abstract Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs);

    /**
     * Get native JTS geometry. This involves caching -> if the cached type does not exist, it
     * will be created.
     * @param self "this" geometry
     * @return cached or newly created native JTS geometry
     */
    public org.locationtech.jts.geom.Geometry getNativeGeometry(Geometry self){
        if (mNative == null)
            mNative = createNativeGeometry(self);
        return mNative;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    public abstract CoordinateSequence getCoordinates(Geometry self);

    /**
	 * @inheritDoc
	 * @generated not
	*/
	public void setCoordinate(Geometry self, final int index, final Coordinate coord) {
		org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self);
        nativeGeometry.getCoordinates()[index] = new org.locationtech.jts.geom.Coordinate(
                coord.getX(), coord.getY(), coord.getZ()
        );
    }

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public void removeCoordinate(Geometry self, final int index) {
		self.removeCoordinate(index);
	}
	
	/**
	 * Invalidate stored values, after changes on the geometry
	 */
	public void invalidate() {
		mNative = null;
		mEnvelope = null;
	}
	
	public void clearNative() {
		mNative = null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean coveredBy(Geometry self, final Geometry g) {
		// note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).coveredBy(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean covers(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).covers(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean crosses(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).crosses(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean equalsExact(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).equalsExact(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean contains(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        return getNativeGeometry(self).contains(delegate.getNativeGeometry(geom));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry difference(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).difference(delegate.getNativeGeometry(g));
	    return createUCoreGeometry(nativeGeometry, self.getCRS());
    }

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean disjoint(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).disjoint(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public double distance(Geometry self, final Geometry g) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = g.getDelegate();
        return getNativeGeometry(self).distance(delegate.getNativeGeometry(g));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean within(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        return getNativeGeometry(self).within(delegate.getNativeGeometry(geom));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public double getLength(Geometry self) {
		return getNativeGeometry(self).getLength();
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry intersection(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).intersection(delegate.getNativeGeometry(geom));
        return createUCoreGeometry(nativeGeometry, self.getCRS());
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public double getArea(Geometry self) {
		return getNativeGeometry(self).getArea();
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public int getDimension(Geometry self) {
		return getCoordinate(self, 0).dimension();
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public abstract int numCoordinates(Geometry self);

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public CoordinateReferenceSystem getCRS(Geometry self) {
        return self.getCRS();
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void recursiveSetCRS(Geometry self, final CoordinateReferenceSystem crs) {
		self.applyCRS(crs);
		for (int i = 0; i < numCoordinates(self); i++)
			getCoordinate(self, i).setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void applyCRS(Geometry self, final CoordinateReferenceSystem crs) {
		for (int i = 0; i < numCoordinates(self); i++){
			Coordinate c = getCoordinate(self, i);
			c.set(c.get(crs));
		}
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry normalized(Geometry self) {
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).norm();
        return createUCoreGeometry(nativeGeometry, self.getCRS());
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public void normalize(Geometry self) {
		getNativeGeometry(self).normalize();
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean overlaps(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        return getNativeGeometry(self).overlaps(delegate.getNativeGeometry(geom));
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public abstract Coordinate getCoordinate(Geometry self, final int index);

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry union(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).union(delegate.getNativeGeometry(geom));
	    return createUCoreGeometry(nativeGeometry, self.getCRS());
    }
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public abstract int getNumGeometries(Geometry self);

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry reversed(Geometry self) {
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).reverse();
        return createUCoreGeometry(nativeGeometry, self.getCRS());
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public Geometry symDifference(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        org.locationtech.jts.geom.Geometry nativeGeometry = getNativeGeometry(self).symDifference(delegate.getNativeGeometry(geom));
        return createUCoreGeometry(nativeGeometry, self.getCRS());
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public abstract Geometry getGeometry(Geometry self, final int idx);
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public Envelope getEnvelope(Geometry self) {
		if (mEnvelope == null){
			mEnvelope = createEnvelope(self);
		}
		return mEnvelope;
	}
	
	private Envelope createEnvelope(Geometry self) {
		if (numCoordinates(self) <= 0)
			return new EnvelopeImpl();
		Envelope env = new EnvelopeImpl(getCoordinate(self, 0).copy());
		for (int i = 1; i < numCoordinates(self); i++)
			env.expandLocal(getCoordinate(self, i));
		return env;		
	}

	public static org.geotools.api.referencing.crs.CoordinateReferenceSystem getNativeWGS842D() {
		try {
			if (sNativeWGS == null)
				sNativeWGS = CRS.decode("urn:ogc:def:crs:EPSG:6.6:4326");
		} catch (FactoryException e) {
			e.printStackTrace();
		}
		return sNativeWGS;
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean touches(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        return getNativeGeometry(self).touches(delegate.getNativeGeometry(geom));
	}
	
	public static CoordinateReferenceSystem getCRS(org.geotools.api.referencing.crs.CoordinateReferenceSystem nat){
		if (nat == getNativeWGS842D() || nat == null)
			return CRSUtils.WGS84_2D;
		NativeCRSImpl crs = new NativeCRSImpl();
		crs.setWkt(nat.toWKT());
		return crs;
	}
	
	protected Envelope createEnvelope(org.locationtech.jts.geom.Envelope bounds) {
		EnvelopeImpl out = new EnvelopeImpl();
		if (bounds instanceof ReferencedEnvelope){
			CoordinateReferenceSystem bounds_crs = getCRS(((ReferencedEnvelope)bounds).getCoordinateReferenceSystem());
			Coordinate min = new CoordinateImpl(
                    CRSUtils.transform(
                            bounds.getMinX(),
                            bounds.getMinY(),
                            bounds_crs,
                            CRSUtils.WGS84_2D
                    ),
                    CRSUtils.WGS84_2D
            );
			Coordinate max = new CoordinateImpl(
                    CRSUtils.transform(
                            bounds.getMaxX(),
                            bounds.getMaxY(),
                            bounds_crs,
                            CRSUtils.WGS84_2D
                    ),
                    CRSUtils.WGS84_2D
            );
			out.setMinPoint(min);
			out.setMaxPoint(max);
		} else {
            // TODO: This methods assumes, that the CRS is WGS84
			out.setLatLon(
                    bounds.getMinX(),
                    bounds.getMinY(),
                    bounds.getMaxX(),
                    bounds.getMaxY()
            );
		}
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean intersects(Geometry self, final Geometry geom) {
        // note cannot use getNativeGeometry as that would return "self"s geometry
        GeometryOperations delegate = geom.getDelegate();
        return getNativeGeometry(self).intersects(delegate.getNativeGeometry(geom));
	}

}
