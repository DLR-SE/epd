package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.delegate.IGeometryCollectionDelegationInterface;
import org.locationtech.jts.geom.Geometry;

/**
 Base class for collections of geometries 
 * @generated 
 */
public class GeometryCollectionOperations extends GeometryOperations implements IGeometryCollectionDelegationInterface{
    @Override
    public Geometry createNativeGeometry(de.emir.model.universal.spatial.Geometry self) {
        return null;
    }

    @Override
    public de.emir.model.universal.spatial.Geometry createUCoreGeometry(Geometry self, CoordinateReferenceSystem crs) {
        return null;
    }

    @Override
    public int numCoordinates(de.emir.model.universal.spatial.Geometry self) {
        return 0;
    }

    @Override
    public Coordinate getCoordinate(de.emir.model.universal.spatial.Geometry self, int index) {
        return null;
    }

    @Override
    public int getNumGeometries(de.emir.model.universal.spatial.Geometry self) {
        return 0;
    }

    @Override
    public de.emir.model.universal.spatial.Geometry getGeometry(de.emir.model.universal.spatial.Geometry self, int idx) {
        return null;
    }

    @Override
	public CoordinateSequence getCoordinates(de.emir.model.universal.spatial.Geometry self) {
		throw new UnsupportedOperationException("getCoordinates is not implemented for GeometryCollectionOperations.");
	}
}
