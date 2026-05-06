package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.delegate.IPolygonDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;

/**
 *	@generated 
 */
public class PolygonOperations extends GeometryOperations implements IPolygonDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
        assert self instanceof Polygon;
		Polygon poly = (Polygon)self;
		
		if (poly.getShell() != null && poly.getShell().getPoints().numCoordinates() > 0) {
			while (poly.getShell().getPoints().numCoordinates() < 3) {
				// This ugly hack makes a polygon from a point...
				Coordinate c0 = poly.getShell().getPoints().getCoordinate(0);
				poly.getShell().getPoints().addCoordinate(c0);
			}
			if (poly.getShell().getPoints().numCoordinates() > 2) {
				org.locationtech.jts.geom.Polygon nat = null;
				LinearRingOperations del = new LinearRingOperations();
				org.locationtech.jts.geom.LinearRing nat_shell = (org.locationtech.jts.geom.LinearRing) del
						.getNativeGeometry(poly.getShell());
				org.locationtech.jts.geom.LinearRing[] nat_holes = null;
				if (poly.getHoles().isEmpty() == false) {
					nat_holes = new org.locationtech.jts.geom.LinearRing[poly.getHoles().size()];
					for (int i = 0; i < poly.getHoles().size(); i++) {
						del = poly.getHoles().get(i).getDelegate();
						nat_holes[i] = (org.locationtech.jts.geom.LinearRing) del
								.getNativeGeometry(poly.getHoles().get(i));
					}
				}
				nat = new org.locationtech.jts.geom.Polygon(nat_shell, nat_holes, sGeometryFactory);
				return nat;
			} 
		}
		return null;
	}

    @Override
    public Geometry createUCoreGeometry(org.locationtech.jts.geom.Geometry self, CoordinateReferenceSystem crs) {

        // cannot create an object from an empty geometry
        if (self.isEmpty()){
            return null;
        }

        if (self instanceof org.locationtech.jts.geom.Polygon) {

            org.locationtech.jts.geom.Polygon nativePolygon = (org.locationtech.jts.geom.Polygon) self;
            Polygon polygon = new PolygonImpl();
            polygon.setShell(new LinearRingImpl());

            // map the exterior ring
            for (org.locationtech.jts.geom.Coordinate coord : nativePolygon.getExteriorRing().getCoordinates()) {
                polygon.getShell().getPoints().addCoordinate(
                        new CoordinateImpl(
                                coord.getX(),
                                coord.getY(),
                                coord.getZ(),
                                crs
                        )
                );
            }

            // map all interior holes
            for (int i = 0; i < nativePolygon.getNumInteriorRing(); i++) {
                LinearRing ring = new LinearRingImpl();

                for (org.locationtech.jts.geom.Coordinate coord : nativePolygon.getInteriorRingN(i).getCoordinates()) {
                    ring.getPoints().addCoordinate(
                            new CoordinateImpl(
                                    coord.getX(),
                                    coord.getY(),
                                    coord.getZ(),
                                    crs
                            )
                    );
                }

                polygon.getHoles().add(ring);
            }

            return polygon;
        } else {
            return GeometryOperationUtil.createUCoreGeometry(self, crs);
        }
    }

    @Override
	public int numCoordinates(Geometry self) {
		Polygon p = (Polygon)self;
		int n = p.getShell() != null ? p.getShell().numCoordinates() : 0;
		for (LinearRing h : p.getHoles())
			n += h.numCoordinates();
		return n;
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		Polygon p = (Polygon)self;
		int max = p.getShell().numCoordinates();
		if (index < max)
			return p.getShell().getCoordinate(index);
		int min = max;
		for (int i = 0; i < p.getHoles().size(); i++){
			max += p.getHoles().get(i).numCoordinates();
			if (index > min && index < max)
				return p.getHoles().get(i).getCoordinate(index-min);
			min = max;
		}
		return null;
	}

	@Override
	public int getNumGeometries(Geometry self) {
		return 1;
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		return self;
	}

	@Override
	public CoordinateSequence getCoordinates(Geometry self) {
		CoordinateSequence result = new CoordinateSequenceImpl();
		for (int i = 0; i < self.numCoordinates(); i++) {
			result.addCoordinate(getCoordinate(self, i));
		}
		return result;
	}
	
}
