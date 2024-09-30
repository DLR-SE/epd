package de.emir.model.universal.spatial.ops;

import org.geotools.api.referencing.FactoryException;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import de.emir.model.universal.spatial.CoordinateSequence;

import org.locationtech.jts.geom.GeometryFactory;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.NativeCRSImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.delegate.IGeometryDelegationInterface;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;

/**
 *	@generated 
 */
public abstract class GeometryOperations  implements IGeometryDelegationInterface{
	
	
	protected static GeometryFactory sGeometryFactory = new GeometryFactory();
	private static org.geotools.api.referencing.crs.CoordinateReferenceSystem sNativeWGS;

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public CoordinateSequence getCoordinates(Geometry self)
	{
		//TODO: 
//		throw new UnsupportedOperationException("getCoordinates not yet implemented");
		return self.getCoordinates();
	}
	
	public abstract org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self);

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public void setCoordinate(Geometry self, final int index, final Coordinate coord)
	{
		//TODO: 
//		throw new UnsupportedOperationException("setCoordinate not yet implemented");
		self.setCoordinate(index, coord);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public void removeCoordinate(Geometry self, final int index)
	{
		//TODO: 
//		throw new UnsupportedOperationException("removeCoordinate not yet implemented");
		self.removeCoordinate(index);
	}
	
	private org.locationtech.jts.geom.Geometry mNative = null;
	private Envelope mEnvelope;
	
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
	public org.locationtech.jts.geom.Geometry getNativeGeometry(Geometry self){
		if (mNative == null)
			mNative = createNativeGeometry(self);
		return mNative;
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public int getDimension(Geometry self)
	{
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
	public CoordinateReferenceSystem getCRS(Geometry self)
	{
		//TODO: 
//		throw new UnsupportedOperationException("getCRS not yet implemented");
		return self.getCRS();
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void recursiveSetCRS(Geometry self, final CoordinateReferenceSystem crs)
	{
		self.applyCRS(crs);
		for (int i = 0; i < numCoordinates(self); i++)
			getCoordinate(self, i).setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void applyCRS(Geometry self, final CoordinateReferenceSystem crs)
	{
		for (int i = 0; i < numCoordinates(self); i++){
			Coordinate c = getCoordinate(self, i);
			c.set(c.get(crs));
		}
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
	@Override
	public abstract int getNumGeometries(Geometry self);
	
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
	public Envelope getEnvelope(Geometry self)
	{
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
			Coordinate min = new CoordinateImpl(CRSUtils.transform(bounds.getMinX(), bounds.getMinY(), bounds_crs, CRSUtils.WGS84_2D), CRSUtils.WGS84_2D);
			Coordinate max = new CoordinateImpl(CRSUtils.transform(bounds.getMaxX(), bounds.getMaxY(), bounds_crs, CRSUtils.WGS84_2D), CRSUtils.WGS84_2D);
			out.setMinPoint(min);
			out.setMaxPoint(max);
		}else{
			out.setLatLon(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY()); //TODO: This methods assumes, that the CRS is WGS84
		}
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean intersects(Geometry self, final Geometry geom)
	{
		//TODO: 
//		throw new UnsupportedOperationException("intersects not yet implemented");
		return self.intersects(geom);
	}

	/**
	 * @inheritDoc
	 * @generated not
	*/
	public boolean isConvex(Geometry self)
	{
		//TODO: 
//		throw new UnsupportedOperationException("isConvex not yet implemented");
		return self.isConvex();
	}

}
