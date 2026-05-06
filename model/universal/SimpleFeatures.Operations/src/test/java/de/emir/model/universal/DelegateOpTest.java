package de.emir.model.universal;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.SfDelegateProviders;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.PointImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.tuml.ucore.runtime.DelegateFactory;

/**
 * Test the geoemetric operations run directly on the JTS geometry after transforming the eMIR geometry using the
 * DelegateFactory and getNativeGeometry method.
 */
class DelegateOpTest {
	private PolygonImpl polygon;
	private PointImpl pointIn;
	private PointImpl pointOut;
	private LineStringImpl lineIn;
	private LineStringImpl lineOut;
	private org.locationtech.jts.geom.Geometry native1;
	private org.locationtech.jts.geom.Geometry native2;
	private org.locationtech.jts.geom.Geometry native3;
	private org.locationtech.jts.geom.Geometry native4;
	private org.locationtech.jts.geom.Geometry native5;
	
	@BeforeMethod
    public void setUp() {
		SfDelegateProviders delegateProviders = new SfDelegateProviders();
		delegateProviders.initializePlugin();
        
        polygon = new PolygonImpl();
		polygon.setShell(new LinearRingImpl(new CoordinateSequenceImpl(Arrays.asList(
				new CoordinateImpl(0, 0, 0, CRSUtils.WGS84_3D), new CoordinateImpl(0, 2, 0, CRSUtils.WGS84_3D),
				new CoordinateImpl(2, 2, 0, CRSUtils.WGS84_3D), new CoordinateImpl(2, 0, 0, CRSUtils.WGS84_3D)))));

        pointIn = new PointImpl();
        pointIn.setCoordinate(new CoordinateImpl(1, 1, 0, CRSUtils.WGS84_3D));
        pointOut = new PointImpl();
        pointOut.setCoordinate(new CoordinateImpl(3, 3, 0, CRSUtils.WGS84_3D));
        
		lineIn = new LineStringImpl(new CoordinateSequenceImpl(Arrays.asList(
				new CoordinateImpl(1, 1, -1, CRSUtils.WGS84_3D), new CoordinateImpl(1, 1, 1, CRSUtils.WGS84_3D))));
		lineOut = new LineStringImpl(new CoordinateSequenceImpl(Arrays.asList(
				new CoordinateImpl(3, 3, -1, CRSUtils.WGS84_3D), new CoordinateImpl(3, 3, 1, CRSUtils.WGS84_3D))));

        native1 = ((GeometryOperations) DelegateFactory.createDelegate(new PolygonImpl())).getNativeGeometry(polygon);
		native2 = ((GeometryOperations) DelegateFactory.createDelegate(new PointImpl())).getNativeGeometry(pointIn);
		native3 = ((GeometryOperations) DelegateFactory.createDelegate(new PointImpl())).getNativeGeometry(pointOut);
		native4 = ((GeometryOperations) DelegateFactory.createDelegate(new LineStringImpl())).getNativeGeometry(lineIn);
		native5 = ((GeometryOperations) DelegateFactory.createDelegate(new LineStringImpl())).getNativeGeometry(lineOut);
    }

	@Test
	void testIntersects() {
		assertTrue(native1.intersects(native2));
		assertFalse(native1.intersects(native3));
		
		assertTrue(native1.intersects(native4));
		assertFalse(native1.intersects(native5));
	}
	
	@Test
	void testContains() {
		assertTrue(native1.contains(native2));
		assertFalse(native1.contains(native3));
	}
	
	@Test
	void testDisjoint() {
		assertFalse(native1.disjoint(native2));
		assertTrue(native1.disjoint(native3));
		
		assertFalse(native1.disjoint(native4));
		assertTrue(native1.disjoint(native5));
	}

	@Test
	void testCovers() {
		assertTrue(native1.covers(native2));
		assertFalse(native1.covers(native3));
	}
	
	@Test
	void testCoveredBy() {
		assertTrue(native2.coveredBy(native1));
		assertFalse(native3.coveredBy(native1));
	}
}
