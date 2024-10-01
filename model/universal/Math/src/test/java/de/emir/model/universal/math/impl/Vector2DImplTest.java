package de.emir.model.universal.math.impl;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import org.junit.Test;

import org.junit.Assert;

import java.util.Objects;

public class Vector2DImplTest {
    public static final double DOUBLE_EQUAL_THRESHOLD = 1e-9;

    @Test
    public void instantiation() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d = new Vector2DImpl(vec2d); // copy constructor
        vec2d = new Vector2DImpl(0.0, 0.0);
        vec2d = new Vector2DImpl(new double[]{0.0, 0.0});
    }

    @Test
    public void illegalInstantiation() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Vector2DImpl(Double.NaN, 0));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Vector2DImpl(0, Double.NaN));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Vector2DImpl(Double.POSITIVE_INFINITY, 0));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Vector2DImpl(0, Double.POSITIVE_INFINITY));
    }
    @Test
    public void setX() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setX(1);
    }

    @Test
    public void setX0() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setX(0.0);
    }

    @Test
    public void setX10() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setX(1);
        vec2d.setX(0.0);
    }
    @Test
    public void setXNaN() {
        Vector2D vec2d = new Vector2DImpl();
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.setX(Double.NaN));
    }

    @Test
    public void setXInf() {
        Vector2D vec2d = new Vector2DImpl();
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.setX(Double.POSITIVE_INFINITY));
    }

    @Test
    public void setY() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setY(1);
    }

    @Test
    public void setY0() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setY(0.0);
    }

    @Test
    public void setY10() {
        Vector2D vec2d = new Vector2DImpl();
        vec2d.setY(1);
        vec2d.setY(0.0);
    }

    @Test
    public void setYNaN() {
        Vector2D vec2d = new Vector2DImpl();
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.setY(Double.NaN));
    }
    @Test
    public void setYInf() {
        Vector2D vec2d = new Vector2DImpl();
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.setY(Double.POSITIVE_INFINITY));
    }

    @Test
    public void add() {
        Vector2D vec2d = new Vector2DImpl(-1, -2);
        vec2d = vec2d.add(new Vector2DImpl(1,2));
        assert vec2d.getX() == 0 && vec2d.getY() == 0;
        vec2d = vec2d.add(new Vector2DImpl(1,2));
        assert vec2d.getX() == 1 && vec2d.getY() == 2;
    }

    @Test
    public void addLocal() {
        Vector2D vec2d = new Vector2DImpl(-1, -2);
        vec2d.addLocal(new Vector2DImpl(1,2));
        assert vec2d.getX() == 0 && vec2d.getY() == 0;
        vec2d.addLocal(new Vector2DImpl(1,2));
        assert vec2d.getX() == 1 && vec2d.getY() == 2;
    }

    @Test
    public void sub() {
        Vector2D vec2d = new Vector2DImpl(1, 2);
        vec2d = vec2d.sub(new Vector2DImpl(1,2));
        assert vec2d.getX() == 0 && vec2d.getY() == 0;
        vec2d = vec2d.sub(new Vector2DImpl(1,2));
        assert vec2d.getX() == -1 && vec2d.getY() == -2;
    }

    @Test
    public void subLocal() {
        Vector2D vec2d = new Vector2DImpl(1, 2);
        vec2d.subLocal(new Vector2DImpl(1,2));
        assert vec2d.getX() == 0 && vec2d.getY() == 0;
        vec2d.subLocal(new Vector2DImpl(1,2));
        assert vec2d.getX() == -1 && vec2d.getY() == -2;
    }

    @Test
    public void mult() {
        Vector2D vec2d = new Vector2DImpl(1, 2);
        vec2d = vec2d.mult(2);
        assert vec2d.getX() == 2 && vec2d.getY() == 4;
    }

    @Test
    public void multLocal() {
        Vector2D vec2d = new Vector2DImpl(1, 2);
        vec2d.multLocal(2);
        assert vec2d.getX() == 2 && vec2d.getY() == 4;
    }

    @Test
    public void multLocalRotationAtLength0RetainsAngle() {
        Vector2D vec2d = new Vector2DImpl(1, 0);
        vec2d.multLocal(0);
        vec2d.rotateLocalCCW(Math.PI/2);
        vec2d = vec2d.normalize();
        assert Math.abs(vec2d.getX() + 0) < DOUBLE_EQUAL_THRESHOLD; // should be 0
        assert Math.abs(vec2d.getY() - 1) < DOUBLE_EQUAL_THRESHOLD; // should be 1
    }

    @Test
    public void div() {
        Vector2D vec2d = new Vector2DImpl(2, 4);
        vec2d = vec2d.div(2);
        assert vec2d.getX() == 1 && vec2d.getY() == 2;
    }

    @Test
    public void divNaN() {
        Vector2D vec2d = new Vector2DImpl(2, 4);
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.div(Double.NaN));
    }

    @Test
    public void divLocal() {
        Vector2D vec2d = new Vector2DImpl(2, 4);
        vec2d.divLocal(2);
        assert vec2d.getX() == 1 && vec2d.getY() == 2;
    }

    @Test
    public void divLocal0() {
        Vector2D vec2d = new Vector2DImpl(2, 4);
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.divLocal(0));
    }

    @Test
    public void divLocalNaN() {
        Vector2D vec2d = new Vector2DImpl(2, 4);
        Assert.assertThrows(IllegalArgumentException.class, () -> vec2d.divLocal(Double.NaN));
    }

    @Test
    public void rotateLocalCW() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D.rotateLocalCW(Math.PI); // turn 180°
        assert Math.abs(vec2D.getX() + 1) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() - 0) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateLocal0LenRotation() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D.setX(0);
        vec2D.rotateLocalCW(Math.PI/2); // turn 90°
        vec2D.normalizeLocal();
        assert Math.abs(vec2D.getX() - 0) < DOUBLE_EQUAL_THRESHOLD; // should be 0
        assert Math.abs(vec2D.getY() + 1) < DOUBLE_EQUAL_THRESHOLD; // should bd 1
    }

    @Test
    public void rotateLocalCW90() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D.rotateLocalCW(Math.PI/2); // turn 90°
        assert Math.abs(vec2D.getX()) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() + 1) < DOUBLE_EQUAL_THRESHOLD;
    }
    @Test
    public void rotateLocalCW_90() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D.rotateLocalCW(-1* (Math.PI/2)); // turn -90°
        assert Math.abs(vec2D.getX()) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() - 1) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCW() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D = vec2D.rotateCW((Math.PI/4)); // turn 45°
        assert Math.abs(vec2D.getX() - 1/Math.sqrt(2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() + 1/Math.sqrt(2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateLocalCCW() {
        Vector2D vec2D = new Vector2DImpl(1, 0);
        vec2D.rotateLocalCCW(-1* (Math.PI/4)); // turn -45° ccw
        assert Math.abs(vec2D.getX() - 1/Math.sqrt(2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() + 1/Math.sqrt(2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCW() {
        Vector2D vec2D = new Vector2DImpl(2, 0);
        vec2D = vec2D.rotateCCW((Math.PI/2*3)); // turn 270° ccw
        assert Math.abs(vec2D.getX()) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY() + 2) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCW0() {
        Vector2D vec2D = new Vector2DImpl(2, 0);
        vec2D = vec2D.rotateCCW((0));
        assert Math.abs(vec2D.getX() - 2) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY()) < DOUBLE_EQUAL_THRESHOLD;
    }
    @Test
    public void rotateCCW2pi() {
        Vector2D vec2D = new Vector2DImpl(2, 0);
        vec2D = vec2D.rotateCCW((2*Math.PI));
        assert Math.abs(vec2D.getX() - 2) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY()) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void rotateCCWYChange() {
        Vector2D vec2D = new Vector2DImpl(0, 2);
        vec2D = vec2D.rotateCCW((Math.PI/2*3)); // turn 270° ccw
        assert Math.abs(vec2D.getX() - 2) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec2D.getY()) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void dot() {
        Vector2D vec1 = new Vector2DImpl(1,2);
        Vector2D vec2 = new Vector2DImpl(2,1);
        assert vec1.dot(vec2) == 4;
        assert vec2.dot(vec1) == 4;
    }

    @Test
    public void normalize() {
        Vector2D vec = new Vector2DImpl(0, 1);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - 0) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - 1) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalize_0_by_default() {
        Vector2D vec = new Vector2DImpl(0, 0);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - 1) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - 0) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalize_0_changed_to() {
        Vector2D vec = new Vector2DImpl(1, 1);
        vec.setX(0);
        vec.setY(0);
        vec =  vec.normalize();
        assert Math.abs(vec.getX() - 0) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - 1) < DOUBLE_EQUAL_THRESHOLD;

        vec.setY(0);
        vec.setX(1);
        vec.setX(0);
        vec = vec.normalize();
        assert Math.abs(vec.getX() - 1) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - 0) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalize_from_smaller() {
        Vector2D vec = new Vector2DImpl(0.1, 0.1);
        vec.setX(0);
        vec.setY(0);
        vec =  vec.normalize();
        assert Math.abs(vec.getX() - 0) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() - 1) < DOUBLE_EQUAL_THRESHOLD;

    }

    @Test
    public void normalizeLocal() {
        Vector2D vec = new Vector2DImpl(-0.1, -0.1);
        vec.normalizeLocal();
        assert Math.abs(vec.getX() + Math.sqrt(1.0/2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() + Math.sqrt(1.0/2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeLocalNormalized () {
        Vector2D vec = new Vector2DImpl(-0.1, -0.1);
        vec.normalizeLocal();
        vec.normalizeLocal();
        assert Math.abs(vec.getX() + Math.sqrt(1.0/2)) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() + Math.sqrt(1.0/2)) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void normalizeLocal_270_via_0() {
        Vector2D vec = new Vector2DImpl(0, -1);
        vec.setY(0.0);
        vec.normalizeLocal();
        assert Math.abs(vec.getX() + 0) < DOUBLE_EQUAL_THRESHOLD;
        assert Math.abs(vec.getY() + 1) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void cross() {
        Vector2D vec1 = new Vector2DImpl(1,2);
        Vector2D vec2 = new Vector2DImpl(3,4);
        double cross = vec1.cross(vec2);
        assert cross == -2;
    }

    @Test
    public void dimensions() {
        assert new Vector2DImpl().dimensions() == 2;
    }

    @Test
    public void get() {
        Vector2D vec = new Vector2DImpl(1,2);
        assert vec.get(0) == 1;
        assert vec.get(1) == 2;
    }

    @Test
    public void set() {
        Vector2D vec = new Vector2DImpl(0,0);
        vec.set(0, 1); // set x to 1
        assert vec.getX() == 1;
        vec.set(1, 1); // set y to 1
        assert vec.getY() == 1;
    }
    @Test
    public void setException() {
        Vector2D vec = new Vector2DImpl(0,0);
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> vec.set(4,1));
    }

    @Test
    public void copy() {
        Vector2D vec = new Vector2DImpl(1,1);
        vec.rotateLocalCW(1);
        Vector vec2 = vec.copy();
        assert vec2.get(0) == vec.get(0);
        assert vec2.get(1) == vec.get(1);
    }

    @Test
    public void readableString() {
        Vector2D vec = new Vector2DImpl(1,2);
        String outStr = vec.toString();
        String refStr = "Vector2DImpl{" + " x = " + 1.0 + " y = " + 2.0 + "}";
        assert Objects.equals(outStr, refStr);
    }

    @Test
    public void getLength() {
        double ref = Math.sqrt(2*2+4*4);
        double length = new Vector2DImpl(2, -4).getLength();
        assert Math.abs(ref - length) < DOUBLE_EQUAL_THRESHOLD;
    }

    @Test
    public void getSquareLength() {
        double ref = 3*3+4*4;
        double length = new Vector2DImpl(-3, -4).getSquareLength();
        assert Math.abs(ref - length) < DOUBLE_EQUAL_THRESHOLD;
    }
}