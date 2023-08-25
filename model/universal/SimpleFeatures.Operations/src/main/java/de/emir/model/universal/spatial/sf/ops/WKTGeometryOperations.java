package de.emir.model.universal.spatial.sf.ops;

import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.WKTGeometry;
import de.emir.model.universal.spatial.sf.delegate.IWKTGeometryDelegationInterface;

/**
 The WKTGeometry is a helper geometry, where the geometry is specified using a WKT (Well known text) String
 * the internal geometry (getNativeGeometry()) is determinated at runtime
 * @generated 
 */
public class WKTGeometryOperations extends GeometryOperations implements IWKTGeometryDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		WKTReader reader = new WKTReader();
		try {
			return reader.read(((WKTGeometry)self).getWkt());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int numCoordinates(Geometry self) {
		org.locationtech.jts.geom.Geometry nat = getNativeGeometry(self);
		return nat.getNumPoints();
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		org.locationtech.jts.geom.Coordinate coord = getNativeGeometry(self).getCoordinates()[index];
		return new CoordinateImpl(coord.x, coord.y, coord.z, null);
	}

	@Override
	public int getNumGeometries(Geometry self) {
		return 1;
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		if (idx == 0) return self;
		return null;
	}
	
}
