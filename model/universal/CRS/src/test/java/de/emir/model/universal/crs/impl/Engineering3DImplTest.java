package de.emir.model.universal.crs.impl;

import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.impl.Vector3DImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Engineering3DImplTest {

    public Engineering3D engineering3D;

    @BeforeEach
    public void setup() {
        engineering3D = new Engineering3DImpl();
    }

    /**
     * Test getDistance method with a simple test.
     * Test whether the method can run without exceptions.
     * Test basic calculation.
     */
    @Test
    void testGetDistance() {
        Vector inputA = new Vector3DImpl(1,0,0);
        Vector inputB = new Vector3DImpl(0,0,0);

        double distance = engineering3D.getDistance(inputA, inputB);
        assert Math.abs(distance - 1) < 1e-6; // distance is 1
    }

    /**
     * Test getDistanceAndAzimuth method with a simple test.
     * Test whether the method can run without exceptions.
     * Test basic calculation.
     */
    @Test
    void testGetDistanceAndAzimuth() {
        Vector inputA = new Vector3DImpl(0,0,0);
        Vector inputB = new Vector3DImpl(1,0,0);

        Vector distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        double distance = distanceAndAzimuth.get(0);
        double azimuth = distanceAndAzimuth.get(1);

        assert Math.abs(distance - 1) < 1e-6; // distance is 1
        assert Math.abs(azimuth - Math.PI / 2) < 1e-6; // azimuth is 90°
    }

    /**
     * Test a variety of simple azimuth (45° steps) calculations.
     */
    @Test
    void testGetDistanceAndAzimuthVaryingDirections() {
        Vector inputA = new Vector3DImpl(0,0,0);
        Vector inputB = new Vector3DImpl(0,1,0);
        Vector distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - 0) < 1e-6; // azimuth is 0°

        inputB = new Vector3DImpl(1,1,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4) < 1e-6; // azimuth is 45°

        inputB = new Vector3DImpl(1,0,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4 * 2) < 1e-6; // azimuth is 90°

        inputB = new Vector3DImpl(1,-1,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4 * 3) < 1e-6; // azimuth is 135°

        inputB = new Vector3DImpl(0,-1,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI) < 1e-6; // azimuth is 180°

        inputB = new Vector3DImpl(-1,-1,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4 * 5) < 1e-6; // azimuth is 225°

        inputB = new Vector3DImpl(-1,0,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4 * 6) < 1e-6; // azimuth is 270°

        inputB = new Vector3DImpl(-1,1,0);
        distanceAndAzimuth = engineering3D.getDistanceAndAzimuth(inputA, inputB);
        assert Math.abs(distanceAndAzimuth.get(1) - Math.PI / 4 * 7) < 1e-6; // azimuth is 270°


    }
}