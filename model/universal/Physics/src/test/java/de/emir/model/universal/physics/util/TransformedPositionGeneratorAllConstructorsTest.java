package de.emir.model.universal.physics.util;

import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.impl.EulerImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TransformedPositionGeneratorAllConstructorsTest {
    // Allowed maximum absolute error in degree
    private static final double ALLOWED_ERROR_DEGREE = 0.01; // in °
    // Allowed maximum absolute error in meters
    private static final double ALLOWED_ERROR_METERS = 1; // in m
    // Method that calls the constructor for a TransformedPositionGenerator.
    private final Function<Pose, TransformedPositionGenerator> generatorConstructor;

    public TransformedPositionGeneratorAllConstructorsTest(Function<Pose, TransformedPositionGenerator> generatorConstructor) {
        this.generatorConstructor = generatorConstructor;
    }

    /**
     * Factory to test different implementations of TransformedPositionGenerator with different constructors
     * @return test class instances
     */
    @Factory
    public static Object[] testClassInstanceGenerator() {
        List<TransformedPositionGeneratorAllConstructorsTest> testClassInstanceList = new ArrayList<>();
        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new SimpleTransformedPositionGenerator(p))); // simple - pose
        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new SimpleTransformedPositionGenerator(p.getCoordinate()))); // simple - coordinate
        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new SimpleTransformedPositionGenerator(p.getCoordinate().getX(), p.getCoordinate().getY()))); // simple - x,y

        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new LinearizedTransformedPositionGenerator(p.getCoordinate()))); // linearized - coordinate
        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new LinearizedTransformedPositionGenerator(p.getCoordinate(), 0.1, 0.1))); // linearized - coordinate, xStep, yStep
        testClassInstanceList.add(new TransformedPositionGeneratorAllConstructorsTest(p -> new LinearizedTransformedPositionGenerator(p, 0.1, 0.1))); // linearized - pose, xStep, yStep

        return testClassInstanceList.toArray(new Object[0]);
    }

    /**
     * Test setup container
     * @param referencePose reference Pose
     * @param relativePose relative Pose
     * @param expectedTransformedPose transformed Pose
     */
    public record TestInOut(Pose referencePose, Pose relativePose, Pose expectedTransformedPose){}

    /**
     * Create input-output combinations
     */
    @DataProvider(name = "generateInputOutput")
    public static TestInOut[] generateInputOutput() {
        List<TestInOut> testInOutList = new ArrayList<>();

        // #1
        Coordinate referenceCoordinate = new CoordinateImpl(53.140557, 8.219967, CRSUtils.WGS84_2D);
        Orientation referenceOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        Pose referencePose = new PoseImpl(referenceCoordinate, referenceOrientation);
        LocatableObject dummyObj = new PhysicalObjectImpl();
        dummyObj.setPose(referencePose);

        Coordinate relativeCoordinate = new CoordinateImpl(1638.348*sin(308.092351 / 180 * Math.PI), 1638.348*cos(308.092351 / 180 * Math.PI), dummyObj.getOwnedCoordinateSystem());
        Orientation relativeOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        Pose relativePose = new PoseImpl(relativeCoordinate, relativeOrientation);

        Coordinate expectedCoordinate = new CoordinateImpl(53.149624, 8.200677, CRSUtils.WGS84_2D);
        Orientation expectecOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        Pose expectedPose = new PoseImpl(expectedCoordinate, expectecOrientation);
        testInOutList.add(new TestInOut(referencePose, relativePose, expectedPose));

        // #2 (#1 backwards)
        referenceCoordinate = new CoordinateImpl(53.149624, 8.200677, CRSUtils.WGS84_2D);
        referenceOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        referencePose = new PoseImpl(referenceCoordinate, referenceOrientation);
        dummyObj = new PhysicalObjectImpl();
        dummyObj.setPose(referencePose);

        relativeCoordinate = new CoordinateImpl(1638.348*sin(128.076916 / 180 * Math.PI), 1638.348*cos(128.076916 / 180 * Math.PI), dummyObj.getOwnedCoordinateSystem());
        relativeOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        relativePose = new PoseImpl(relativeCoordinate, relativeOrientation);

        expectedCoordinate = new CoordinateImpl(53.140557, 8.219967, CRSUtils.WGS84_2D);
        expectecOrientation = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
        expectedPose = new PoseImpl(expectedCoordinate, expectecOrientation);
        testInOutList.add(new TestInOut(referencePose, relativePose, expectedPose));

        return testInOutList.toArray(new TestInOut[0]);
    }

    /**
     * Test whether the transformation runs without errors and creates non-null results
     * @param testInOut test input amd output
     */
    @Test(dataProvider = "generateInputOutput")
    public void testRunWithoutErrors(TestInOut testInOut) {
        TransformedPositionGenerator generator = generatorConstructor.apply(testInOut.referencePose); // execute constructor
        // pose
        Pose poseResult = generator.generateTransformed(testInOut.relativePose);
        assert poseResult != null;
        // coordinate
        Coordinate coordinateResult = generator.generateTransformed(testInOut.relativePose.getCoordinate());
        assert coordinateResult != null;
        // distances
        Coordinate coordinateDistances = generator.generateTransformed(testInOut.relativePose.getCoordinate().getY(), -testInOut.relativePose.getCoordinate().getX());
        assert coordinateDistances != null;
    }

    /**
     * Test result equality between different overloaded method calls
     */
    @Test(dataProvider = "generateInputOutput")
    public void testOverloadedMethodEquality(TestInOut testInOut) {
        TransformedPositionGenerator generator = generatorConstructor.apply(testInOut.referencePose); // execute constructor
        // generate results
        Pose poseResult = generator.generateTransformed(testInOut.relativePose);
        Coordinate coordinateResult = generator.generateTransformed(testInOut.relativePose.getCoordinate());
        Coordinate distancesResult = generator.generateTransformed(testInOut.relativePose.getCoordinate().getY(), testInOut.relativePose.getCoordinate().getX());
        // check pose argument against coordinate argument
        assert poseResult.getCoordinate().getX() == coordinateResult.getX(); // should be exactly equal
        assert poseResult.getCoordinate().getY() == coordinateResult.getY(); // should be exactly equal
        // check distance arguments against coordinate argument
        assert distancesResult.getX() == coordinateResult.getX(); // should be exactly equal
        assert distancesResult.getY() == coordinateResult.getY(); // should be exactly equal
    }

    /**
     * Test whether the transformation was done correctly
     * @param testInOut test input amd output
     */
    @Test(dataProvider = "generateInputOutput")
    public void testCorrectResult(TestInOut testInOut) {
        TransformedPositionGenerator generator = generatorConstructor.apply(testInOut.referencePose); // execute constructor
        // calculate actual distance
        VincentCalculator calculator = new VincentCalculator();
        double expectedDistance = calculator.getDistance(
                testInOut.referencePose.getCoordinate().getLatitude(),
                testInOut.referencePose.getCoordinate().getLongitude(),
                testInOut.expectedTransformedPose.getCoordinate().getLatitude(),
                testInOut.expectedTransformedPose.getCoordinate().getLongitude()
        ); // in m
        // sanity check for expected distance using pythagoras theorem. If something is wrong here the test setup will not work.
        assert Math.abs(expectedDistance - Math.sqrt(Math.pow(testInOut.relativePose.getCoordinate().getX(), 2) + Math.pow(testInOut.relativePose.getCoordinate().getY(), 2) )) < ALLOWED_ERROR_METERS;

        // pose
        Pose poseResult = generator.generateTransformed(testInOut.relativePose);
        Pose expectedResultPose = testInOut.expectedTransformedPose();
        // check degree deviation
        assert Math.abs(poseResult.getCoordinate().getX() - expectedResultPose.getCoordinate().getX()) < ALLOWED_ERROR_DEGREE;
        assert Math.abs(poseResult.getCoordinate().getY() - expectedResultPose.getCoordinate().getY()) < ALLOWED_ERROR_DEGREE;
        // check meter deviation
        double resultingDistance = calculator.getDistance(
                testInOut.referencePose.getCoordinate().getLatitude(),
                testInOut.referencePose.getCoordinate().getLongitude(),
                poseResult.getCoordinate().getLatitude(),
                poseResult.getCoordinate().getLongitude()
        ); // in m
        assert Math.abs(expectedDistance - resultingDistance) < ALLOWED_ERROR_METERS;

        // coordinate
        Coordinate coordinateResult = generator.generateTransformed(testInOut.relativePose.getCoordinate());
        Coordinate expectedResultCoordinate = testInOut.expectedTransformedPose().getCoordinate();
        // check degree deviation
        assert Math.abs(coordinateResult.getX() - expectedResultCoordinate.getX()) < ALLOWED_ERROR_DEGREE;
        assert Math.abs(coordinateResult.getY() - expectedResultCoordinate.getY()) < ALLOWED_ERROR_DEGREE;
        // check meter deviation
        resultingDistance = calculator.getDistance(
                testInOut.referencePose.getCoordinate().getLatitude(),
                testInOut.referencePose.getCoordinate().getLongitude(),
                coordinateResult.getLatitude(),
                coordinateResult.getLongitude()
        ); // in m
        assert Math.abs(expectedDistance - resultingDistance) < ALLOWED_ERROR_METERS;

        // distances
        // From the Relative CRS Y is ahead and x is to starboard side
        Coordinate distancesResult = generator.generateTransformed(testInOut.relativePose.getCoordinate().getY(), testInOut.relativePose.getCoordinate().getX());
        // check degree deviation
        assert Math.abs(distancesResult.getX() - expectedResultCoordinate.getX()) < ALLOWED_ERROR_DEGREE;
        assert Math.abs(distancesResult.getY() - expectedResultCoordinate.getY()) < ALLOWED_ERROR_DEGREE;
        // check meter deviation
        resultingDistance = calculator.getDistance(
                testInOut.referencePose.getCoordinate().getLatitude(),
                testInOut.referencePose.getCoordinate().getLongitude(),
                distancesResult.getLatitude(),
                distancesResult.getLongitude()
        ); // in m
        assert Math.abs(expectedDistance - resultingDistance) < ALLOWED_ERROR_METERS;
    }

}