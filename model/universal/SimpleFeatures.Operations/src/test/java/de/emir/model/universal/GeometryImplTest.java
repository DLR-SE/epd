package de.emir.model.universal;
import static org.testng.Assert.*;

import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.sf.*;
import de.emir.model.universal.spatial.sf.impl.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * Simple geometric operation test that calls all methods for all SF geometry types. This does not ensure that
 * operations are correctly implemented, but that the method does not throw errors and it somehow implemented.
 */
class GeometryImplTest {

    static Point point;
    static LineString lineString;
    static Polygon polygon;

    static MultiGeometry multiGeometry;
    static MultiLineString multiLineString;
    static MultiPolygon multiPolygon;

    static {
        SpatialDelegateProviders.register();
        SfDelegateProviders.register();

        List<Coordinate> polygonCoordinates = new ArrayList<>();
        polygonCoordinates.add(new CoordinateImpl(-0.5, -0.5, CRSUtils.ENGINEERING_2D));
        polygonCoordinates.add(new CoordinateImpl(0.5, -0.5, CRSUtils.ENGINEERING_2D));
        polygonCoordinates.add(new CoordinateImpl(0.5, 0.5, CRSUtils.ENGINEERING_2D));
        polygonCoordinates.add(new CoordinateImpl(-0.5, 0.5, CRSUtils.ENGINEERING_2D));

        CoordinateSequence polygonCoordinateSequence = new CoordinateSequenceImpl(polygonCoordinates);
        polygonCoordinateSequence.setCrs(CRSUtils.ENGINEERING_2D);
        LinearRing polygonLinearRing = new LinearRingImpl(polygonCoordinateSequence);
        polygon = new PolygonImpl(polygonLinearRing, new ArrayList<>());

        List<Coordinate> lineStringCoordinates = new ArrayList<>();
        lineStringCoordinates.add(new CoordinateImpl(-0.5, -0.5, CRSUtils.ENGINEERING_2D));
        lineStringCoordinates.add(new CoordinateImpl(0.5, -0.5, CRSUtils.ENGINEERING_2D));
        lineStringCoordinates.add(new CoordinateImpl(0.5, 0.5, CRSUtils.ENGINEERING_2D));
        lineStringCoordinates.add(new CoordinateImpl(-0.5, 0.5, CRSUtils.ENGINEERING_2D));

        CoordinateSequence lineStringCoordinateSequence = new CoordinateSequenceImpl(lineStringCoordinates);
        lineStringCoordinateSequence.setCrs(CRSUtils.ENGINEERING_2D);
        lineString = new LineStringImpl(lineStringCoordinateSequence);

        point = new PointImpl(new CoordinateImpl(0.0, 0.0, CRSUtils.ENGINEERING_2D));

        multiGeometry = new MultiGeometryImpl();
        multiGeometry.getGeometries().add(point);
        multiGeometry.getGeometries().add(lineString);
        multiGeometry.getGeometries().add(polygon);

        multiLineString = new MultiLineStringImpl();
        multiLineString.getLines().add(lineString);

        multiPolygon = new MultiPolygonImpl();
        multiPolygon.getPolygons().add(polygon);

    }

    @Test
    public void test_contains() {
        assertFalse(point.contains(polygon));

        assertFalse(lineString.contains(polygon));

        assertTrue(polygon.contains(point));

        assertTrue(multiPolygon.contains(point));

        assertFalse(multiLineString.contains(point));

        assertTrue(multiGeometry.contains(point));
    }

    @Test
    public void test_crosses() {
        assertFalse(point.crosses(point));

        assertFalse(polygon.crosses(point));

        assertFalse(lineString.crosses(polygon));

        assertFalse(multiLineString.crosses(polygon));

        assertFalse(multiPolygon.crosses(polygon));

        // JTS: Operation does not support GeometryCollection arguments
//        assertFalse(multiGeometry.crosses(polygon));
    }

    @Test
    public void test_distance() {
        assertEquals(0.0, point.distance(polygon), 0.01);

        assertEquals(0.5, lineString.distance(point), 0.01);

        assertEquals(0.0, polygon.distance(point), 0.01);

        assertEquals(0.0, multiGeometry.distance(point), 0.01);

        assertEquals(0.5, multiLineString.distance(point), 0.01);

        assertEquals(0.0, multiPolygon.distance(point), 0.01);
    }

    @Test
    public void test_within() {
        assertTrue(point.within(polygon));

        assertFalse(lineString.within(polygon));

        assertFalse(polygon.within(lineString));

        assertFalse(multiGeometry.within(lineString));

        assertTrue(multiLineString.within(lineString));

        assertFalse(multiPolygon.within(lineString));
    }

    @Test
    public void test_disjoint() {
        assertFalse(point.disjoint(polygon));

        assertFalse(lineString.disjoint(polygon));

        assertFalse(polygon.disjoint(lineString));

        assertFalse(multiGeometry.disjoint(lineString));

        assertFalse(multiLineString.disjoint(lineString));

        assertFalse(multiPolygon.disjoint(lineString));
    }

    @Test
    public void test_overlaps() {
        assertFalse(point.overlaps(polygon));

        assertFalse(lineString.overlaps(polygon));

        assertFalse(polygon.overlaps(lineString));

        assertFalse(multiGeometry.overlaps(lineString));

        assertFalse(multiLineString.overlaps(lineString));

        assertFalse(multiPolygon.overlaps(lineString));
    }

    @Test
    public void test_covers() {
        assertFalse(point.covers(polygon));

        assertFalse(lineString.covers(polygon));

        assertTrue(polygon.covers(lineString));

        assertTrue(multiGeometry.covers(lineString));

        assertTrue(multiLineString.covers(lineString));

        assertTrue(multiPolygon.covers(lineString));
    }

    @Test
    public void test_coversBy() {
        assertTrue(point.coveredBy(polygon));

        assertTrue(lineString.coveredBy(polygon));

        assertFalse(polygon.coveredBy(lineString));

        assertFalse(multiGeometry.coveredBy(lineString));

        assertTrue(multiLineString.coveredBy(lineString));

        assertFalse(multiPolygon.coveredBy(lineString));
    }

    @Test
    public void test_touches() {
        assertFalse(point.touches(polygon));

        assertTrue(lineString.touches(polygon));

        assertTrue(polygon.touches(lineString));

        assertTrue(multiGeometry.touches(lineString));

        assertFalse(multiLineString.touches(lineString));

        assertTrue(multiPolygon.touches(lineString));
    }

    @Test
    public void test_intersects() {
        assertTrue(point.intersects(polygon));

        assertTrue(lineString.intersects(polygon));

        assertTrue(polygon.intersects(lineString));

        assertTrue(multiGeometry.intersects(lineString));

        assertTrue(multiLineString.intersects(lineString));

        assertTrue(multiPolygon.intersects(lineString));
    }

    @Test
    public void test_equals_exact() {
        assertFalse(point.equalsExact(polygon));

        assertFalse(polygon.equalsExact(point));

        assertFalse(lineString.equalsExact(point));

        assertFalse(multiGeometry.equalsExact(point));

        assertFalse(multiLineString.equalsExact(point));

        assertFalse(multiPolygon.equalsExact(point));

        assertTrue(point.equalsExact(point));

        assertTrue(polygon.equalsExact(polygon));
    }

    @Test
    public void test_length() {
        assertEquals(4.0, polygon.getLength(), 0.01);
        assertEquals(0.0, point.getLength(), 0.01);
        assertEquals(3.0, lineString.getLength(), 0.01);
        assertEquals(7.0, multiGeometry.getLength(), 0.01);
        assertEquals(3.0, multiLineString.getLength(), 0.01);
        assertEquals(4.0, multiPolygon.getLength(), 0.01);
    }

    @Test
    public void test_area() {
        assertEquals(1.0, polygon.getArea(), 0.01);
        assertEquals(0.0, point.getArea(), 0.01);
        assertEquals(0.0, lineString.getArea(), 0.01);
        assertEquals(1.0, multiGeometry.getArea(), 0.01);
        assertEquals(0.0, multiLineString.getArea(), 0.01);
        assertEquals(1.0, multiPolygon.getArea(), 0.01);
    }

    //TODO

    @Test
    public void test_normalize() {
        {
            int numCoordinatesBefore = polygon.getCoordinates().numCoordinates();
            polygon.normalize();
            int numCoordinatesAfter = polygon.getCoordinates().numCoordinates();
            assertEquals(numCoordinatesBefore, numCoordinatesAfter);
        }

        {
            int numCoordinatesBefore = polygon.getCoordinates().numCoordinates();
            Geometry geom = polygon.normalized();
            int numCoordinatesAfter = geom.getCoordinates().numCoordinates();
            assertEquals(numCoordinatesBefore, numCoordinatesAfter);
        }
    }

    @Test
    public void test_difference() {
        assertEquals(1, point.difference(lineString).getCoordinates().numCoordinates());
        assertEquals(5, polygon.difference(lineString).getCoordinates().numCoordinates());
        assertNull(lineString.difference(polygon));
        // collections are not supported
//        assertEquals(5, multiGeometry.difference(polygon).getCoordinates().numCoordinates());
//        assertEquals(5, multiLineString.difference(polygon).getCoordinates().numCoordinates());
//        assertEquals(5, multiPolygon.difference(polygon).getCoordinates().numCoordinates());
    }

    @Test
    public void test_sym_difference() {
        assertEquals(1, point.symDifference(lineString).getGeometry(0).numCoordinates());
        assertEquals(5, polygon.symDifference(lineString).getGeometry(0).numCoordinates());
        assertEquals(5, lineString.symDifference(polygon).getGeometry(0).numCoordinates());
        // collections are not supported
//        assertEquals(5, multiGeometry.symDifference(polygon).getGeometry(0).numCoordinates());
//        assertEquals(5, multiLineString.symDifference(polygon).getGeometry(0).numCoordinates());
//        assertEquals(5, multiPolygon.symDifference(polygon).getGeometry(0).numCoordinates());
    }

    @Test
    public void test_union() {
        assertEquals(1, point.union(lineString).getGeometry(0).numCoordinates());
        assertEquals(5, polygon.union(lineString).getGeometry(0).numCoordinates());
        assertEquals(5, lineString.union(polygon).getGeometry(0).numCoordinates());
        // collections are not supported
//        assertEquals(5, multiGeometry.union(polygon).getGeometry(0).numCoordinates());
//        assertEquals(5, multiLineString.union(polygon).getGeometry(0).numCoordinates());
//        assertEquals(5, multiPolygon.union(polygon).getGeometry(0).numCoordinates());
    }

    @Test
    public void test_intersection() {
        assertNull(point.intersection(lineString));
        assertEquals(3, polygon.intersection(lineString).getNumGeometries());
        assertEquals(3, lineString.intersection(polygon).getNumGeometries());
        assertEquals(3, multiGeometry.intersection(polygon).getNumGeometries());
        assertEquals(3, multiLineString.intersection(polygon).getNumGeometries());
        assertEquals(1, multiPolygon.intersection(polygon).getNumGeometries());
    }

    @Test
    public void test_reverse() {
        assertEquals(point.reversed().getCoordinates().numCoordinates(), point.getCoordinates().numCoordinates());
        assertEquals(lineString.reversed().getCoordinates().numCoordinates(), lineString.getCoordinates().numCoordinates());
        assertEquals(polygon.reversed().getCoordinates().numCoordinates(), polygon.getCoordinates().numCoordinates());

//        assertEquals(multiPolygon.reversed().getCoordinates().numCoordinates(), multiPolygon.getCoordinates().numCoordinates());
//        assertEquals(multiGeometry.reversed().getCoordinates().numCoordinates(), multiGeometry.getCoordinates().numCoordinates());
//        assertEquals(multiLineString.reversed().getCoordinates().numCoordinates(), multiLineString.getCoordinates().numCoordinates());
    }

    @Test
    public void test_get_crs() {
        assertNotNull(point.getCRS());
        assertNotNull(lineString.getCRS());
        assertNotNull(polygon.getCRS());

        assertNotNull(multiPolygon.getGeometry(0).getCRS());
        assertNotNull(multiGeometry.getGeometry(0).getCRS());
        assertNotNull(multiLineString.getGeometry(0).getCRS());
    }

}