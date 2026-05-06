package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.sf.MultiGeometry;
import de.emir.model.universal.spatial.sf.impl.MultiGeometryImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import org.locationtech.jts.geom.Geometry;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.delegate.IMultiGeometryDelegationInterface;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public class MultiGeometryOperations extends GeometryOperations implements IMultiGeometryDelegationInterface {

    @Override
    public Geometry createNativeGeometry(de.emir.model.universal.spatial.Geometry self) {
        assert self instanceof MultiGeometry;

        List<Geometry> geometries = new ArrayList<>();
        for (int i = 0; i < self.getNumGeometries(); i++) {

            de.emir.model.universal.spatial.Geometry geometry = self.getGeometry(i);
            geometries.add(
                    // explicitly call utility class, as this knows all SF geometry implementations
                    GeometryOperationUtil.getNativeGeometry(geometry)
            );
        }

        GeometryFactory factory = new GeometryFactory();
        return new GeometryCollection(geometries.toArray(new Geometry[0]), factory);
    }

    @Override
    public de.emir.model.universal.spatial.Geometry createUCoreGeometry(Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof GeometryCollection) {
            GeometryCollection nativeGeometry = (GeometryCollection) self;

            MultiGeometry multiGeometry = new MultiGeometryImpl();
            // map all internal Polygon objects
            for (int i = 0; i < nativeGeometry.getNumGeometries(); i++) {
                org.locationtech.jts.geom.Geometry geom = nativeGeometry.getGeometryN(i);
                // create a copy, this will call the polygon operations to create a new object
                de.emir.model.universal.spatial.Geometry newGeometry = GeometryOperationUtil.createUCoreGeometry(geom, crs);

                multiGeometry.getGeometries().add(
                        newGeometry
                );
            }

            return multiGeometry;
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

    @Override
    public int numCoordinates(de.emir.model.universal.spatial.Geometry self) {
        return createNativeGeometry(self).getNumPoints();
    }

    @Override
    public Coordinate getCoordinate(de.emir.model.universal.spatial.Geometry self, int index) {
        Geometry nativeGeometry = getNativeGeometry(self);
        org.locationtech.jts.geom.Coordinate nativeCoordinate = nativeGeometry.getCoordinates()[index];
        return new CoordinateImpl(
                nativeCoordinate.getX(),
                nativeCoordinate.getY(),
                nativeCoordinate.getZ(),
                self.getCRS()
        );
    }

    @Override
    public int getNumGeometries(de.emir.model.universal.spatial.Geometry self) {
        MultiGeometry geometry = (MultiGeometry) self;
        return geometry.getNumGeometries();
    }

    @Override
    public de.emir.model.universal.spatial.Geometry getGeometry(de.emir.model.universal.spatial.Geometry self, int idx) {
        MultiGeometry geometry = (MultiGeometry) self;
        return geometry.getGeometry(idx);
    }

	@Override
	public CoordinateSequence getCoordinates(de.emir.model.universal.spatial.Geometry self) {
		throw new UnsupportedOperationException("getCoordinates is not implemented for MultiGeometryOperations. Use getGeometry to work with the contained objects.");
	}
}
