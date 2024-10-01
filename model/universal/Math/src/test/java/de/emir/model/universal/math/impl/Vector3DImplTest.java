package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3DImplTest {
    public static final double DOUBLE_EQUAL_THRESHOLD = Vector2DImplTest.DOUBLE_EQUAL_THRESHOLD;
    @Test
    public void setX() {
        Vector3D vec = new Vector3DImpl(0,0,0);
        vec.setX(1.0);
        assert vec.getX() == 1.0;
    }

    @Test
    public void getX() {
        Vector3D vec = new Vector3DImpl(1,0,0);
        assert vec.getX() == 1.0;
    }

    @Test
    public void setY() {
        Vector3D vec = new Vector3DImpl(0,0,0);
        vec.setY(1.0);
        assert vec.getY() == 1.0;
    }

    @Test
    public void getY() {
        Vector3D vec = new Vector3DImpl(0,1,0);
        assert vec.getY() == 1.0;
    }

    @Test
    public void setZ() {
        Vector3D vec = new Vector3DImpl(0,0,0);
        vec.setZ(1.0);
        assert vec.getZ() == 1.0;
    }

    @Test
    public void getZ() {
        Vector3D vec = new Vector3DImpl(0,0, 1);
        assert vec.getZ() == 1.0;
    }

    @Test
    public void set() {
        Vector3D vec = new Vector3DImpl(0,0,0);
        vec.set(1.0, 2.0, 1.0);
        assert vec.getZ() == 1.0;
        assert vec.getY() == 2.0;
        assert vec.getX() == 1.0;
    }

    @Test
    public void getAnglePhi() {
        Vector3DImpl vec = new Vector3DImpl(1,0,0);
        double phi = vec.getAnglePhi();
        boolean statement = Math.abs(phi - 0.0) < DOUBLE_EQUAL_THRESHOLD || Math.abs(phi - 2*Math.PI) < DOUBLE_EQUAL_THRESHOLD;
        assert statement;
    }
    @Test
    public void getAnglePhi90deg() {
        Vector3DImpl vec = new Vector3DImpl(0,1,0);
        double phi = vec.getAnglePhi();
        assert Math.abs(phi - (Math.PI/2)) < DOUBLE_EQUAL_THRESHOLD;
    }
    @Test
    public void getAnglePhi270deg() {
        Vector3DImpl vec = new Vector3DImpl(0,-1,0);
        double phi = vec.getAnglePhi();
        double comp = Math.PI/2*3;
        assert Math.abs(phi - (Math.PI/2*3)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getAnglePhi180deg() {
        Vector3DImpl vec = new Vector3DImpl(-2,0,0);
        double phi = vec.getAnglePhi();
        assert Math.abs(phi - (Math.PI)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getAngleTheta() {
        Vector3DImpl vec = new Vector3DImpl(3,0,0);
        double theta = vec.getAngleTheta();
        assert Math.abs(theta - (Math.PI / 2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getAngleThetaY() {
        Vector3DImpl vec = new Vector3DImpl(0,3,0);
        double theta = vec.getAngleTheta();
        assert Math.abs(theta - (Math.PI / 2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getAngleThetaZ() {
        Vector3DImpl vec = new Vector3DImpl(0,0,60.4);
        double theta = vec.getAngleTheta();
        assert Math.abs(theta - (0.0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getAngleTheta180() {
        Vector3DImpl vec = new Vector3DImpl(0,0,-60.4);
        double theta = vec.getAngleTheta();
        assert Math.abs(theta - (Math.PI)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void add() {
        Vector3D vec1 = new Vector3DImpl(0, 0, -1);
        Vector3D vec2 = new Vector3DImpl(1, 2, 0);
        Vector3D vecRes = vec1.add(vec2);
        assert Math.abs(vecRes.getX() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void addLocal() {
        Vector3D vec1 = new Vector3DImpl(0, 0, -1);
        Vector3D vec2 = new Vector3DImpl(1, 2, 0);
        vec1.addLocal(vec2);
        Vector3D vecRes = vec1;
        assert Math.abs(vecRes.getX() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void sub() {
        Vector3D vec1 = new Vector3DImpl(0, 0, 3);
        Vector3D vec2 = new Vector3DImpl(1, 0, 1);
        Vector3D vecRes = vec1.sub(vec2);
        assert Math.abs(vecRes.getX() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (2)) < DOUBLE_EQUAL_THRESHOLD;

    }

    @Test
    public void subLocal() {
        Vector3D vec1 = new Vector3DImpl(4, -3, 1);
        Vector3D vec2 = new Vector3DImpl(1, 2, 0);
        vec1.subLocal(vec2);
        Vector3D vecRes = vec1;
        assert Math.abs(vecRes.getX() - (3)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (-5)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (1)) < DOUBLE_EQUAL_THRESHOLD;

    }

    @Test
    public void mult() {
        Vector3D vec1 = new Vector3DImpl(1,2,3);
        Vector3D vec2 = new Vector3DImpl(0, 1, -2);
        Vector3D vecRes = vec1.mult(vec2);
        assert Math.abs(vecRes.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-6)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void multLocal() {
        Vector3D vec1 = new Vector3DImpl(4,5,6);
        Vector3D vec2 = new Vector3DImpl(-3, 0, -2);
        vec1.multLocal(vec2);
        Vector3D vecRes = vec1;
        assert Math.abs(vecRes.getX() - (-12)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-12)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void div() {
        Vector3D vec1 = new Vector3DImpl(4,5,6);
        Vector3D vec2 = new Vector3DImpl(-3, 1, -2);
        Vector3D vecRes = vec1.div(vec2);
        assert Math.abs(vecRes.getX() - (4.0/-3)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (5)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-3)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void divLocal() {
        Vector3D vec1 = new Vector3DImpl(0,1,6);
        Vector3D vec2 = new Vector3DImpl(-3, 1, -3);
        vec1.divLocal(vec2);
        Vector3D vecRes = vec1;
        assert Math.abs(vecRes.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (-2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void cross() { // example from wikipedia : https://de.wikipedia.org/wiki/Kreuzprodukt
        Vector3D vec1 = new Vector3DImpl(1,2,3);
        Vector3D vec2 = new Vector3DImpl(-7, 8, 9);
        Vector3D vecRes = vec1.cross(vec2);
        assert Math.abs(vecRes.getX() - (-6)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (-30)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (22)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void crossLocal() { // example from https://www.studysmarter.de/schule/mathe/geometrie/kreuzprodukt/
        Vector3D vec1 = new Vector3DImpl(2,-4,4);
        Vector3D vec2 = new Vector3DImpl(5, 3, -1);
        Vector3D vecRes = vec1.cross(vec2);
        assert Math.abs(vecRes.getX() - (-8)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getY() - (22)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vecRes.getZ() - (26)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeSimple() {
        Vector3D vec = new Vector3DImpl(1,1,1);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeVia0() {
        Vector3D vec = new Vector3DImpl(1,1,1);
        vec.set(0,0,0);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeFromSmaller() {
        Vector3D vec = new Vector3DImpl(0.1,0.1,0.1);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeZeros() {
        Vector3D vec = new Vector3DImpl(0,0, 0);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - (Math.sqrt(0))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(0))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeBackFrom0() {
        Vector3D vec = new Vector3DImpl(1,1, 1);
        vec.set(0,0,0);
        vec.set(1,1,1);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeLocal() {
        Vector3D vec = new Vector3DImpl(1,1,1);
        vec.normalizeLocal();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeLocalNormalized() {
        Vector3D vec = new Vector3DImpl(1,1,1);
        vec.normalizeLocal();
        vec.normalizeLocal();
        assert Math.abs(vec.getX() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (Math.sqrt(1.0/3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCWAroundSelf() {
        Vector3D vec = new Vector3DImpl(0,0,1);
        vec = vec.rotateCW(0, 0, 100);
        assert Math.abs(vec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (1)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCWYAxis() {
        Vector3D vec = new Vector3DImpl(0,0,1);
        vec = vec.rotateCCW(0, Math.PI/2, 0);
        assert Math.abs(vec.getX() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCWXAxis() {
        Vector3D vec = new Vector3DImpl(0,0,1);
        vec = vec.rotateCCW(Math.PI, 0, 0);
        assert Math.abs(vec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCWZAxis() {
        Vector3D vec = new Vector3DImpl(1,0,0);
        vec = vec.rotateCCW(0, 0, Math.PI*3/2);
        assert Math.abs(vec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateLocalCCW_no_rotation() {
        Vector3D vec = new Vector3DImpl(0, 1, 0);
        vec.rotateLocalCCW(0, 0, 0);
        assert Math.abs(vec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateLocalCW_rotation_of_0_vector() {
        Vector3D vec = new Vector3DImpl(0, 0, 1);
        vec.set(0, 0, 0); // set vector to 0 -> normalization would still result in (0, 0, 1)
        vec.rotateLocalCW(Math.PI/2, 0, 0); // normalization should result in (0, 1, 0)
        vec.normalizeLocal();
        assert Math.abs(vec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getRotationToDirection() {
        Vector3D angleVec = new Vector3DImpl(0, 0, Math.PI);
        Vector3D rotatedVec = angleVec.getRotationToDirection(new Vector3DImpl(1, 0, 0));
        assert Math.abs(rotatedVec.getX() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getRotationToDirection2() {
        Vector3D angleVec = new Vector3DImpl(0, 0, Math.PI/2);
        Vector3D rotatedVec = angleVec.getRotationToDirection(new Vector3DImpl(0, 1, 0));
        assert Math.abs(rotatedVec.getX() - (-1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getRotationToDirection0VectorBy0() {
        Vector3D angleVec = new Vector3DImpl(0, 0, 0);
        Vector3D rotatedVec = angleVec.getRotationToDirection(new Vector3DImpl(0, 0, 0));
        rotatedVec.normalizeLocal(); // should be default vector
        assert Math.abs(rotatedVec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getY() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getZ() - (1)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getRotationToDirection0VectorToY() {
        Vector3D angleVec = new Vector3DImpl(Math.PI*3/2, 0, 0);
        Vector3D rotatedVec = angleVec.getRotationToDirection(new Vector3DImpl(0, 0, 0));
        rotatedVec.normalizeLocal();
        assert Math.abs(rotatedVec.getX() - (0)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getY() - (1)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(rotatedVec.getZ() - (0)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void dot() {
    }

    @Test
    public void dimensions() {
        assert new Vector3DImpl(0, 0, 0).dimensions() == 3;
    }

    @Test
    public void getLength() {
        Vector3D vec = new Vector3DImpl(1,2,3);
        assert Math.abs(vec.getLength() - (Math.sqrt(1+2*2+3*3))) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getSquareLength() {
        Vector3D vec = new Vector3DImpl(4,5,6);
        assert Math.abs(vec.getSquareLength() - (4*4+5*5+6*6)) < DOUBLE_EQUAL_THRESHOLD;
    }
}