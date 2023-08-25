package de.emir.service.geometry.impl;

import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.IWKTUtil;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IWKTUtil.class)
public class WKTUtil extends UObjectImpl implements IWKTUtil  
{
	
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WKTUtil(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WKTUtil(final IWKTUtil _copy) {
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.WKTUtil;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Geometry loadWKT(String wkt)
	{
		WKTReader reader = new WKTReader();
		try {
			org.locationtech.jts.geom.Geometry nat = reader.read(wkt);
			SimpleFeatureGeometryProvider sfgp = new SimpleFeatureGeometryProvider();
            return sfgp.createGeometry(nat, CRSUtils.WGS84_2D, CRSUtils.WGS84_2D);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Polygon createBox() {
		Polygon poly = new PolygonImpl();
		poly.setShell(new LinearRingImpl());
		poly.getShell().setPoints(new CoordinateSequenceImpl());
		
		de.emir.model.universal.crs.CoordinateReferenceSystem crs = CRSUtils.WGS84_2D;
//		((Engineering2DImpl)crs).setOrigin(new Vector2DImpl());
		
//		crs = CRSUtils.WGS84_2D;
		
		CoordinateSequence cs = poly.getShell().getPoints();
		cs.addCoordinate(new CoordinateImpl(-100, -100, crs));
		cs.addCoordinate(new CoordinateImpl(-100,  100, crs));
		cs.addCoordinate(new CoordinateImpl( 100,  100, crs));
		cs.addCoordinate(new CoordinateImpl( 100, -100, crs));
		cs.addCoordinate(new CoordinateImpl(-100, -100, crs));
		
		return poly;
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String exportWKT(final Geometry geom)
	{
		if (geom == null) return null;
		GeometryOperations delegate = geom.getDelegate();
		if (delegate == null)
			return null;
		delegate.clearNative();
		org.locationtech.jts.geom.Geometry nat = delegate.getNativeGeometry(geom);
		return nat.toText();		
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WKTUtil{" +
		"}";
	}
}
