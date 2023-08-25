package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.delegate.IPolygonDelegationInterface;

/**
 *	@generated 
 */
public class PolygonOperations extends GeometryOperations implements IPolygonDelegationInterface{

	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		Polygon poly = (Polygon)self;
		
		if (poly.getShell() != null && poly.getShell().getPoints().numCoordinates() > 0) {
			while (poly.getShell().getPoints().numCoordinates() < 3) {
				// This ugly hack makes a polygon from a point...
				Coordinate c0 = poly.getShell().getPoints().getCoordinate(0);
				poly.getShell().getPoints().addCoordinate(c0);
			}
			if (poly.getShell().getPoints().numCoordinates() > 2) {
				org.locationtech.jts.geom.Polygon nat = null;
//			GeometryOperations del = poly.getShell().getDelegate();
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
	
}
