package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test functionality of CoordinateSequenceImpl and its inner Class LinkedCoordinate
 * Tested:
 * - Getter, Setter, Adder and Remover variations
 * - Inner class LinkedCoordinate
 *  - Setter
 *  - dimension
 *  - CRS
 * Untested:
 * - Envelope
 * - toString
 * - variations using 2D Coordinates for most methods
 * - Errors on faulty inputs
 */
public class CoordinateSequenceImplTest {

    CoordinateSequence coordinateSequence;
    @BeforeMethod
    public void setUp() {
        coordinateSequence = new CoordinateSequenceImpl();

    }

    @Test
    public void testSetGetCrs() {
        CoordinateReferenceSystem referenceCrs = new WGS843DImpl();
        coordinateSequence.setCrs(referenceCrs);

        CoordinateReferenceSystem crsFromSequence = coordinateSequence.getCrs();
        assertEquals(referenceCrs, crsFromSequence);
    }

    @Test
    public void testSetGet2DCoordinatesIn2DCRS() {
        CoordinateReferenceSystem crs = new WGS842DImpl();
        coordinateSequence.setCrs(crs);
        Coordinate coordinate1 = new CoordinateImpl(1,2,crs);
        Coordinate coordinate2 = new CoordinateImpl(3,4,crs);
        coordinateSequence.addCoordinate(coordinate1);
        coordinateSequence.addCoordinate(coordinate2);

        assertEquals(coordinateSequence.getCoordinate(0).getX(), coordinate1.getX());
        assertEquals(coordinateSequence.getCoordinate(1).getX(), coordinate2.getX());

        assertEquals(coordinateSequence.getCoordinate(0).getY(), coordinate1.getY());
        assertEquals(coordinateSequence.getCoordinate(1).getY(), coordinate2.getY());


        assertEquals(coordinateSequence.getCoordinate(0).getZ(), coordinate1.getZ()); // should be Double.NaN for Both
        assertEquals(coordinateSequence.getCoordinate(1).getZ(), coordinate2.getZ()); // should be Double.NaN for Both
    }

    @Test
    public void testSetGet2DCoordinatesIn3DCrs() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.setCrs(crs);
        Coordinate coordinate1 = new CoordinateImpl(1,2,crs);
        Coordinate coordinate2 = new CoordinateImpl(3,4,crs);
        coordinateSequence.addCoordinate(coordinate1);
        coordinateSequence.addCoordinate(coordinate2);

        assertEquals(coordinateSequence.getCoordinate(0).getX(), coordinate1.getX());
        assertEquals(coordinateSequence.getCoordinate(1).getX(), coordinate2.getX());

        assertEquals(coordinateSequence.getCoordinate(0).getY(), coordinate1.getY());
        assertEquals(coordinateSequence.getCoordinate(1).getY(), coordinate2.getY());


        assertEquals(coordinateSequence.getCoordinate(0).getZ(), coordinate1.getZ()); // should be Double.NaN for Both
        assertEquals(coordinateSequence.getCoordinate(1).getZ(), coordinate2.getZ()); // should be Double.NaN for Both
    }


    @Test
    public void testGetXCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));

        List<Double> result = coordinateSequence.getXCoordinates();
        for (Double number : result) {
            assertEquals(number, 1);
        }
    }

    @Test
    public void testGetYCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));

        List<Double> result = coordinateSequence.getYCoordinates();
        for (Double number : result) {
            assertEquals(number, 2);
        }
    }

    @Test
    public void testGetZCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));

        List<Double> result = coordinateSequence.getZCoordinates();
        for (Double number : result) {
            assertEquals(number, 3);
        }
    }

    @Test
    public void testGetCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));

        List<Double> result = coordinateSequence.getZCoordinates();
        for (Double number : result) {
            assertEquals(number, 3);
        }
    }

    @Test
    public void testSetCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.setCoordinate(0, new CoordinateImpl(4,5, 6, crs));

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 4);
        assertEquals(resultX.get(1), 1);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 5);
        assertEquals(resultY.get(1), 2);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 6);
        assertEquals(resultZ.get(1), 3);
    }

    @Test
    public void setZin2DCoordinateSequence() {
        CoordinateReferenceSystem crs = new WGS842DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, crs));

        coordinateSequence.getCoordinate(2).getZ();
        coordinateSequence.getCoordinate(2).setZ(0);
    }

    @Test
    public void testAddCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 1);
        assertEquals(resultX.get(1), 1);
        assertEquals(resultX.get(2), 4);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 2);
        assertEquals(resultY.get(1), 2);
        assertEquals(resultY.get(2), 5);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 3);
        assertEquals(resultZ.get(1), 3);
        assertEquals(resultZ.get(2), 6);
    }

    @Test
    public void testAddCoordinateAtPosition() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(0, new CoordinateImpl(4,5, 6, crs));

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 4);
        assertEquals(resultX.get(1), 1);
        assertEquals(resultX.get(2), 1);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 5);
        assertEquals(resultY.get(1), 2);
        assertEquals(resultY.get(2), 2);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 6);
        assertEquals(resultZ.get(1), 3);
        assertEquals(resultZ.get(2), 3);
    }

    @Test
    public void testRemoveCoordinateByIndex() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.removeCoordinate(0);

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 1);
        assertEquals(resultX.get(1), 4);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 2);
        assertEquals(resultY.get(1), 5);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 3);
        assertEquals(resultZ.get(1), 6);

        assertEquals(coordinateSequence.numCoordinates(), 2);
    }

    @Test
    public void testRemoveCoordinateByCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToRemove = new CoordinateImpl(4,5,6, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(coordinateToRemove);
        coordinateSequence.removeCoordinate(coordinateToRemove);

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 1);
        assertEquals(resultX.get(1), 1);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 2);
        assertEquals(resultY.get(1), 2);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 3);
        assertEquals(resultZ.get(1), 3);

        assertEquals(coordinateSequence.numCoordinates(), 2);
    }

    @Test
    public void testRemoveCoordinateByCoordinateIdenticalCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToRemove = new CoordinateImpl(1,2,3, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.removeCoordinate(coordinateToRemove);

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 4);
        assertEquals(resultX.get(1), 4);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 5);
        assertEquals(resultY.get(1), 5);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 6);
        assertEquals(resultZ.get(1), 6);

        assertEquals(coordinateSequence.numCoordinates(), 2);
    }
    @Test
    public void testRemoveMultipleCoordinateByCoordinateIdenticalCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToRemove = new CoordinateImpl(1,2,3, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.removeCoordinate(coordinateToRemove); // only removes first instance of coordinate with same values

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 4);
        assertEquals(resultX.get(1), 4);
        assertEquals(resultX.get(2), 1);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 5);
        assertEquals(resultY.get(1), 5);
        assertEquals(resultY.get(2), 2);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 6);
        assertEquals(resultZ.get(1), 6);
        assertEquals(resultZ.get(2), 3);

        assertEquals(coordinateSequence.numCoordinates(), 3);
    }

    @Test
    public void testRemoveCoordinateByCoordinateNonExistingCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToRemove = new CoordinateImpl(666,666,666, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.removeCoordinate(coordinateToRemove);

        List<Double> resultX = coordinateSequence.getXCoordinates();
        assertEquals(resultX.get(0), 1);
        assertEquals(resultX.get(1), 4);
        assertEquals(resultX.get(2), 4);

        List<Double> resultY = coordinateSequence.getYCoordinates();
        assertEquals(resultY.get(0), 2);
        assertEquals(resultY.get(1), 5);
        assertEquals(resultY.get(2), 5);

        List<Double> resultZ = coordinateSequence.getZCoordinates();
        assertEquals(resultZ.get(0), 3);
        assertEquals(resultZ.get(1), 6);
        assertEquals(resultZ.get(2), 6);

        assertEquals(coordinateSequence.numCoordinates(), 3);
    }

    @Test
    public void testGetIndexOf() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToFind = new CoordinateImpl(1,2,3, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));

        int idxOfCoordinateToFind = coordinateSequence.getIndexOf(coordinateToFind);

        assertEquals(idxOfCoordinateToFind, 0);
    }
    @Test
    public void testGetIndexOfFirstInstanceOfMultiple() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToFind = new CoordinateImpl(4,5,6, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));

        int idxOfCoordinateToFind = coordinateSequence.getIndexOf(coordinateToFind);

        assertEquals(idxOfCoordinateToFind, 1);
    }

    @Test
    public void testGetIndexOfNonExisting() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToFind = new CoordinateImpl(666,666,666, crs);
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));

        int idxOfCoordinateToFind = coordinateSequence.getIndexOf(coordinateToFind);

        assertEquals(idxOfCoordinateToFind, -1);
    }

    @Test
    public void testNumCoordinates() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(7,8, 9, crs));


        assertEquals(coordinateSequence.numCoordinates(), 3);
    }

    @Test
    public void testDimension3D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, 6, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(7,8, 9, crs));


        assertEquals(coordinateSequence.dimension(), 3);
    }

    @Test
    public void testDimension2D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(7,8, crs));


        assertEquals(coordinateSequence.dimension(), 2);
    }

    /**
     * This tests current behavior and does not follow any requirement. It may be deleted on failure.
     * If this test fails please note that current behavior is changed and may have unforeseen consequences in other places.
     */
    @Test
    public void testDimensionMixed2D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(7,8, 9, crs));


        assertEquals(coordinateSequence.dimension(), 2);
    }

    /**
     * This tests current behavior and does not follow any requirement. It may be deleted on failure.
     * If this test fails please note that current behavior is changed and may have unforeseen consequences in other places.
     */
    @Test
    public void testDimensionMixed3D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 9, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(4,5, crs));
        coordinateSequence.addCoordinate(new CoordinateImpl(7,8, crs));


        assertEquals(coordinateSequence.dimension(), 3);
    }

    @Test
    public void testLinkedCoordinateValueChanged() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToChange = new CoordinateImpl(1,2, crs);
        coordinateSequence.addCoordinate(coordinateToChange);
        Coordinate coordinateFromSequence = coordinateSequence.getCoordinate(0);
        coordinateSequence.getXCoordinates().set(0, 3.0);

        assertEquals(coordinateFromSequence.getX(), 3.0);
    }

    @Test
    public void testLinkedCoordinateValueChangedByCoordinate() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        Coordinate coordinateToChange = new CoordinateImpl(1,2, crs);
        coordinateSequence.addCoordinate(coordinateToChange);
        Coordinate coordinateFromSequence = coordinateSequence.getCoordinate(0);
        coordinateFromSequence.setX(3);

        assertEquals(coordinateSequence.getXCoordinates().get(0), 3.0);
    }

    @Test
    public void testLinkedCoordinateDimension2D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, crs));

        assertEquals(coordinateSequence.dimension(), 2);
        assertEquals(coordinateSequence.getCoordinate(0).dimension(), 2);
    }

    @Test
    public void testLinkedCoordinateDimension3D() {
        CoordinateReferenceSystem crs = new WGS843DImpl();
        coordinateSequence.addCoordinate(new CoordinateImpl(1,2, 3, crs));


        assertEquals(coordinateSequence.dimension(), 3);
        assertEquals(coordinateSequence.getCoordinate(0).dimension(), 3);
    }
    @Test
    public void testSetCrsApplyToLinkedCoordinate() {
        CoordinateReferenceSystem referenceCrs = new WGS843DImpl();
        coordinateSequence.setCrs(referenceCrs);
        Coordinate inputCoordinate = new CoordinateImpl();
        inputCoordinate.setCrs(new WGS842DImpl()); // explicitly a different crs

        coordinateSequence.addCoordinate(inputCoordinate);
        Coordinate coordinateToTest = coordinateSequence.getCoordinate(0);
        assertEquals(coordinateToTest.getCrs(), referenceCrs);
    }


}