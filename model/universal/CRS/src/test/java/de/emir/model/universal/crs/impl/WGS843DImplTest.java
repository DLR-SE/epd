package de.emir.model.universal.crs.impl;

import de.emir.model.universal.CoreModel;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.internal.ICoordinateTransform;
import de.emir.model.universal.crs.internal.TransformFactory;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.algorithm.Distance;

import static org.junit.jupiter.api.Assertions.*;

class WGS843DImplTest {
    WGS84CRS wgs84;
    @BeforeEach
    public void setup() {
        this.wgs84 = new WGS843DImpl();
    }
    @Test
    public void test_execution3D() {
        Vector3D vec_a = new Vector3DImpl();
        vec_a.set(0,1,2);
        Vector3D vec_b = new Vector3DImpl();
        vec_b.set(0,2,3);
        this.wgs84.getDistance(vec_a, vec_b);
    }

    @Test
    public void given3DVectorsAtSurface_expectSameResultAs2DVectors() {
        Vector2D vec_a = new Vector2DImpl();
        vec_a.setX(0);
        vec_a.setY(1);
        Vector2D vec_b = new Vector2DImpl();
        vec_b.setX(1);
        vec_b.setY(2);
        double dist_2d = this.wgs84.getDistance(vec_a, vec_b);

        Vector3D vec_a3D = new Vector3DImpl();
        vec_a3D.set(0,1,0);
        Vector3D vec_b3D = new Vector3DImpl();
        vec_b3D.set(1,2,0);
        double dist_3d = this.wgs84.getDistance(vec_a3D, vec_b3D);
        Assertions.assertTrue(Math.abs(dist_2d - dist_3d) < 1e-12);
    }
    @Test
    public void WGS842DAndWGS843DSameResultOn2DCalculations() {
        WGS84CRS wgs842d = new WGS842DImpl();
        Vector2D vec_a = new Vector2DImpl();
        vec_a.setX(0);
        vec_a.setY(1);
        Vector2D vec_b = new Vector2DImpl();
        vec_b.setX(1);
        vec_b.setY(2);

        double dist_2d = wgs842d.getDistance(vec_a, vec_b);
        double dist_3d = this.wgs84.getDistance(vec_a, vec_b);
        Assertions.assertTrue(Math.abs(dist_2d - dist_3d) < 1e-12);
    }
    @Test
    public void lowerDistanceBelowSeaLevel() {
        Vector3D vec_a = new Vector3DImpl();
        vec_a.set(0,1,0);
        Vector3D vec_b = new Vector3DImpl();
        vec_b.set(10,5,0);
        double distSeaLevel = this.wgs84.getDistance(vec_a, vec_b);

        Vector3D vec_c = new Vector3DImpl();
        vec_c.set(0,1,-1000);
        Vector3D vec_d = new Vector3DImpl();
        vec_d.set(10,5,-1000);
        double distSubSurface = this.wgs84.getDistance(vec_c, vec_d);

        Assertions.assertTrue((distSeaLevel - distSubSurface) > 1e-12);
    }
    @Test
    public void higherDistanceAboveSeaLevel() {
        Vector3D vec_a = new Vector3DImpl();
        vec_a.set(0,1,0);
        Vector3D vec_b = new Vector3DImpl();
        vec_b.set(10,5,0);
        double distSeaLevel = this.wgs84.getDistance(vec_a, vec_b);

        Vector3D vec_c = new Vector3DImpl();
        vec_c.set(0,1,1000);
        Vector3D vec_d = new Vector3DImpl();
        vec_d.set(10,5,1000);
        double distSubSurface = this.wgs84.getDistance(vec_c, vec_d);

        Assertions.assertTrue((distSubSurface - distSeaLevel) > 1e-12);
    }

    @Test
    public void verticalDistance() {
        Vector3D vec_a = new Vector3DImpl();
        vec_a.set(0,1,-100);
        Vector3D vec_b = new Vector3DImpl();
        vec_b.set(0,1,100);
        double dist = this.wgs84.getDistance(vec_a, vec_b);
        Assertions.assertTrue(Math.abs(dist - 200) < 1e-12);
    }

    @Test
    public void given2DVectorNotOnSurface() {
        Vector3D vec_a = new Vector3DImpl();
        vec_a.set(0,1,0);
        Vector3D vec_b = new Vector3DImpl();
        vec_b.set(0,1,100);
        double dist_a = this.wgs84.getDistance(vec_a, vec_b);

        Vector2D vec_c = new Vector2DImpl();
        vec_c.setX(0);
        vec_c.setY(1);
        Vector3D vec_d = new Vector3DImpl();
        vec_d.set(0,1,100);
        double dist_b = this.wgs84.getDistance(vec_c, vec_d);

        Assertions.assertTrue(dist_a > dist_b);
    }

    @Test
    public void transform3dTo2d() {
        WGS842D crs2d = new WGS842DImpl();
        WGS843D crs3d = new WGS843DImpl();
        ICoordinateTransform transformer = TransformFactory.getTransform(crs3d, crs2d);
        Assertions.assertNotNull(transformer);
        Vector3D vector3D_1 = new Vector3DImpl(1,1,1);
        Vector2D vector2D_1 = new Vector2DImpl(transformer.transform(vector3D_1.toArray(), crs3d, crs2d));
        Vector3D vector3D_2 = new Vector3DImpl(1,1,0);
        Vector2D vector2D_2 = new Vector2DImpl(transformer.transform(vector3D_2.toArray(), crs3d, crs2d));
        double dist = crs2d.getDistance(vector2D_1, vector2D_2);
        Assertions.assertTrue(Math.abs(dist) < 1e-9);
    }

    @Test
    public void transform2dTo3d() {
        WGS842D crs2d = new WGS842DImpl();
        WGS843D crs3d = new WGS843DImpl();
        ICoordinateTransform transformer = TransformFactory.getTransform(crs2d, crs3d);
        Assertions.assertNotNull(transformer);
        Vector2D vector2D_1 = new Vector2DImpl(1,1);
        Vector3D vector3D_1 = new Vector3DImpl(transformer.transform(vector2D_1.toArray(), crs3d, crs2d));
        Vector2D vector2D_2 = new Vector2DImpl(1, 1);
        Vector3D vector3D_2 = new Vector3DImpl(transformer.transform(vector2D_2.toArray(), crs3d, crs2d));
        double dist = crs3d.getDistance(vector3D_1, vector3D_2);
        Assertions.assertTrue(Math.abs(dist) < 1e-9);
    }
}