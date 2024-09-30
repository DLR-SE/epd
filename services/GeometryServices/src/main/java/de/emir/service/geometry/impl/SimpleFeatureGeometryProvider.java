package de.emir.service.geometry.impl;

import java.util.ArrayList;
import java.util.List;

import org.geotools.api.geometry.Position;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geometry.Position2D;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.PointImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.IGeometryProvider;
import de.emir.service.geometry.ISimpleFeatureGeometryProvider;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ISimpleFeatureGeometryProvider.class)
public class SimpleFeatureGeometryProvider extends UObjectImpl implements ISimpleFeatureGeometryProvider , IGeometryProvider 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SimpleFeatureGeometryProvider(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SimpleFeatureGeometryProvider(final ISimpleFeatureGeometryProvider _copy) {
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.SimpleFeatureGeometryProvider;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Geometry createGeometry(final Object native_geom, final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem dstCRS)
	{
		if(native_geom instanceof org.locationtech.jts.geom.Geometry == false)
			return null;
		org.locationtech.jts.geom.Geometry geom = (org.locationtech.jts.geom.Geometry)native_geom;
		if (geom instanceof GeometryCollection) {
			if(geom.getNumGeometries() == 1) 
				return createSingleGeometry(geom.getGeometryN(0), sourceCRS, dstCRS);
			//TODO: implement multiple 
		} else {
			return createSingleGeometry(geom, sourceCRS, dstCRS);
		}
		return null;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SimpleFeatureGeometryProvider{" +
		"}";
	}
	
	
	Geometry createSingleGeometry(org.locationtech.jts.geom.Geometry geom, CoordinateReferenceSystem srcCRS, CoordinateReferenceSystem dstCRS) {
		CoordinateSequenceImpl seq;
		if (geom instanceof Point) {
			Vector2DImpl value = new Vector2DImpl(((Point)geom).getX(), ((Point)geom ).getY());
			return new PointImpl(new CoordinateImpl(CRSUtils.transform(value, srcCRS, dstCRS), dstCRS));
		} else if (geom instanceof LinearRing) {
			LinearRing lr = (LinearRing)geom;
			seq = new CoordinateSequenceImpl(coordinateList(lr.getCoordinateSequence(), srcCRS, dstCRS));
			return new LinearRingImpl(seq);
		} else if (geom instanceof LineString) {
			LineString ls = (LineString)geom;
			seq = new CoordinateSequenceImpl(coordinateList(ls.getCoordinateSequence(), srcCRS, dstCRS));
			return new LineStringImpl(seq);
		} else if (geom instanceof Polygon) {
			Polygon p = (Polygon)geom;
			PolygonImpl out = new PolygonImpl();
			out.setShell(new LinearRingImpl());
			out.getShell().setPoints(new CoordinateSequenceImpl());
			for (org.locationtech.jts.geom.Coordinate c : p.getExteriorRing().getCoordinates()){
				out.getShell().getPoints().addCoordinate(convert(c, srcCRS, dstCRS));
			}
			for (int i = 0; i < p.getNumInteriorRing(); i++){
				LineString inter = p.getInteriorRingN(i);
				LinearRingImpl hole = new LinearRingImpl();
				hole.setPoints(new CoordinateSequenceImpl());
				for (org.locationtech.jts.geom.Coordinate c : inter.getCoordinates()){
					hole.getPoints().addCoordinate(convert(c, srcCRS, dstCRS));
				}
				out.getHoles().add(hole);
			}
			return out;
//			CoordinateSequenceImpl ex_seq = new CoordinateSequenceImpl(coordinateList(p.getExteriorRing().getCoordinateSequence(), srcCRS, dstCRS));
//			LinearRingImpl exterior = new LinearRingImpl(dstCRS, ex_seq);
//			ArrayList<de.emir.model.universal.spatial.sf.LinearRing> interior = new ArrayList<de.emir.model.universal.spatial.sf.LinearRing>();
//			for (int i = 0; i < p.getNumInteriorRing(); i++) {
//				CoordinateSequenceImpl in_seq = new CoordinateSequenceImpl(coordinateList(p.getInteriorRingN(i).getCoordinateSequence(), srcCRS, dstCRS));
//				LinearRingImpl inter = new LinearRingImpl(dstCRS, in_seq); 
//				interior.add(inter);
//			}
//			return new PolygonImpl(dstCRS, exterior, interior);
		}
		return null;
	}
	
	private Coordinate convert(org.locationtech.jts.geom.Coordinate p, CoordinateReferenceSystem src, CoordinateReferenceSystem dst) {
		if (src != null){
			Coordinate nc = new CoordinateImpl(p.x, p.y, p.z, src);
			if (dst != null)
				return nc.get(dst);
			else
				return nc;
		}else{
			//assume the default geotools wgs, so we have to do a transformation
			try {
				Position dp = getTransform().transform(new Position2D(p.x, p.y), null);
				Coordinate nc = new CoordinateImpl(dp.getOrdinate(0), dp.getOrdinate(1), CRSUtils.WGS84_2D);
				if (dst != null)
					return nc.get(dst);
				else
					return nc;
			} catch (TransformException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	static DefaultGeographicCRS native_emir_wgs = createDefaultEMIRWGS842D();
	static DefaultGeographicCRS native_wgs = DefaultGeographicCRS.WGS84;
	
	private static DefaultGeographicCRS createDefaultEMIRWGS842D() {
		try {
			return (DefaultGeographicCRS) CRS.decode("urn:ogc:def:crs:EPSG:6.6:4326");
		} catch (FactoryException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static MathTransform transform = null;
	private static MathTransform getTransform() {
		if (transform == null)
			try {
				transform = CRS.findMathTransform(native_wgs, native_emir_wgs);
			} catch (FactoryException e) {
				e.printStackTrace();
			}
		return transform;
	}
	
	List<Coordinate> coordinateList(org.locationtech.jts.geom.CoordinateSequence sequence, CoordinateReferenceSystem src, CoordinateReferenceSystem dst){
		ArrayList<Coordinate> out = new ArrayList<Coordinate>();
		for (int i = 0; i < sequence.size(); i++){
			org.locationtech.jts.geom.Coordinate p = sequence.getCoordinate(i);
			if (src != null){
				Coordinate nc = new CoordinateImpl(p.x, p.y, p.z, src);
				if (dst != null)
					out.add(new CoordinateImpl(p.x, p.y, p.z, src).get(dst));
				else
					out.add(nc);
			}else{
				//assume the default geotools wgs, so we have to do a transformation
				try {
					Position dp = getTransform().transform(new Position2D(p.x, p.y), null);
					Coordinate nc = new CoordinateImpl(dp.getOrdinate(0), dp.getOrdinate(1), CRSUtils.WGS84_2D);
					if (dst != null)
						out.add(nc.get(dst));
					else
						out.add(nc);
				} catch (TransformException e) {
                    throw new RuntimeException(e);
                }
            }
		}
		return out;
	}
}
