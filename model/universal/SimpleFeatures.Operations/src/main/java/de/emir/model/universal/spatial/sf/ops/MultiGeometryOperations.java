package de.emir.model.universal.spatial.sf.ops;

import org.locationtech.jts.geom.Geometry;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.delegate.IMultiGeometryDelegationInterface;

/**
 *	@generated 
 */
public class MultiGeometryOperations extends GeometryOperations implements IMultiGeometryDelegationInterface{

	@Override
	public Geometry createNativeGeometry(de.emir.model.universal.spatial.Geometry self) {
		// TODO
		return null;
	}

	@Override
	public int numCoordinates(de.emir.model.universal.spatial.Geometry self) {
		// TODO
		return 0;
	}

	@Override
	public Coordinate getCoordinate(de.emir.model.universal.spatial.Geometry self, int index) {
		// TODO
		return null;
	}

	@Override
	public int getNumGeometries(de.emir.model.universal.spatial.Geometry self) {
		// TODO
		return 0;
	}

	@Override
	public de.emir.model.universal.spatial.Geometry getGeometry(de.emir.model.universal.spatial.Geometry self,
			int idx) {
		// TODO
		return null;
	}
}
