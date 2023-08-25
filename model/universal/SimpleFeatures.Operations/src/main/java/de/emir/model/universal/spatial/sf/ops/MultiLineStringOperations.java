package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.MultiLineString;
import de.emir.model.universal.spatial.sf.delegate.IMultiLineStringDelegationInterface;

/**
 *	@generated 
 */
public class MultiLineStringOperations extends GeometryOperations implements IMultiLineStringDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		MultiLineString mls = (MultiLineString) self;
		return new org.locationtech.jts.geom.MultiLineString(getNativeLineStrings(mls), sGeometryFactory);
	}
	
	protected org.locationtech.jts.geom.LineString[] getNativeLineStrings(MultiLineString self) {
		org.locationtech.jts.geom.LineString[] result = new org.locationtech.jts.geom.LineString[self.getNumGeometries()];
		for (int i = 0; i < self.getNumGeometries(); i++) {
			LineStringOperations geoOp = new LineStringOperations(); 
//			GeometryOperations geoOp = self.getGeometry(i).getDelegate();
			result[i] = (org.locationtech.jts.geom.LineString) geoOp.createNativeGeometry(self.getGeometry(i));
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
		MultiLineString mls = (MultiLineString) self;
		return mls.getNumGeometries();
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		MultiLineString mls = (MultiLineString) self;
		return mls.getGeometry(idx);
	}

	
}
