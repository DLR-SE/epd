package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.MultiPolygon;
import de.emir.model.universal.spatial.sf.delegate.IMultiPolygonDelegationInterface;

/**
 *	@generated 
 */
public class MultiPolygonOperations extends GeometryOperations implements IMultiPolygonDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		MultiPolygon mpoly = (MultiPolygon) self;
		return new org.locationtech.jts.geom.MultiPolygon(getNativePolygons(mpoly), sGeometryFactory);
	}

	protected  org.locationtech.jts.geom.Polygon[] getNativePolygons(MultiPolygon self) {
		org.locationtech.jts.geom.Polygon[] result = new org.locationtech.jts.geom.Polygon[self.getNumGeometries()];
		for (int i = 0; i < self.getNumGeometries(); i++) {
			PolygonOperations geoOp = new PolygonOperations(); 
//			GeometryOperations geoOp = self.getGeometry(i).getDelegate();
			result[i] = (org.locationtech.jts.geom.Polygon) geoOp.createNativeGeometry(self.getGeometry(i));
		}
		return result;
	}

	@Override
	public int numCoordinates(Geometry self) {
		// TODO
		return 0;
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		// TODO
		return null;
	}

	@Override
	public int getNumGeometries(Geometry self) {
		MultiPolygon mpoly = (MultiPolygon) self;
		return mpoly.getNumGeometries();
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		MultiPolygon mpoly = (MultiPolygon) self;
		return mpoly.getGeometry(idx);
	}

}
