package de.emir.model.universal.physics.util;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Transformer using linearization at a point. It is intended as a quick transformer where Poses in a RelativeEngineering CRS are to be transformed to a WGS84 CRS.
 * It shall only be used with multiple transformations with a single generator and when performance is way more important than accuracy.
 * Since it uses linearization the father away the coordinate is from the initial step size, the inaccuracy grows.
 * @implNote It is only considering transformations in 2D space.
 */
public class LinearizedTransformedPositionGenerator implements TransformedPositionGenerator {
    // sine of angle of z-rotation of reference Pose
    private final double sin;
    // cosine of angle of z-rotation of reference Pose
    private final double cos;
    // x latitude scaling factor
    private final double xScale;
    // y scaling factor
    private final double yScale;
    // x offset
    private final double xOffset;
    // y offset
    private final double yOffset;
    // coordinate system of the reference
    private final CoordinateReferenceSystem referenceCRS;
    // reference Pose
    private final Pose referencePose;
    // Flag that tracks for viability of this object
    private final boolean thisOk;

    /**
     * Constructor method with reference Coordinate parametrisation. Linearization step size is 1 degree in each direction.
     * A default orientation towards north is assumed.
     * @param referenceCoordinate reference coordinate
     */
    public LinearizedTransformedPositionGenerator(final Coordinate referenceCoordinate) {
        this(referenceCoordinate, 0.1, 0.1);
    }

    /**
     * Constructor method with reference Coordinate parametrisation.
     * A default orientation towards north is assumed.
     * @param referenceCoordinate reference coordinate
     * @param xStep step size in x direction for linearization
     * @param yStep step size in y direction for linearization
     */
    public LinearizedTransformedPositionGenerator(final Coordinate referenceCoordinate, final double xStep, final double yStep) {
        this(new PoseImpl(referenceCoordinate, new EulerImpl(0,0,0, AngleUnit.DEGREE)), xStep, yStep);
    }

    /**
     * Constructor method with reference Pose parametrisation.
     * @param referencePose reference Pose
     * @param xStep step size in x direction for linearization in deg
     * @param yStep step size in y direction for linearization in deg
     */
    public LinearizedTransformedPositionGenerator(final Pose referencePose, final double xStep, final double yStep) {
        this.referencePose = referencePose;
        // check pose
        thisOk = SimpleTransformedPositionGenerator.poseViable2D(referencePose);
        if (!thisOk) {
            ULog.error("reference Pose is not viable. TransformedPositionGenerator cannot be used.");
            sin = Double.NaN;
            cos = Double.NaN;
            xScale = Double.NaN;
            yScale = Double.NaN;
            xOffset = Double.NaN;
            yOffset = Double.NaN;
            referenceCRS = null;
            return;
        }

        // precompute coefficients
        //rotation
        double angle = referencePose.getOrientation().toEuler().getZ().getAs(AngleUnit.RADIAN) - Math.PI/2;
        sin = sin(angle);
        cos = cos(angle);
        //scale
        Coordinate xShift = new CoordinateImpl(referencePose.getCoordinate());
        xShift.setX(xShift.getX() + xStep);
        Coordinate yShift = new CoordinateImpl(referencePose.getCoordinate());
        yShift.setY(xShift.getY() + yStep);
        xScale = xStep / referencePose.getCoordinate().getDistance(xShift).getAs(DistanceUnit.METER);
        yScale = yStep / referencePose.getCoordinate().getDistance(yShift).getAs(DistanceUnit.METER);
        //translation
        xOffset = referencePose.getCoordinate().getX();
        yOffset = referencePose.getCoordinate().getY();
        // crs
        referenceCRS = referencePose.getCoordinate().getCrs();
    }

    /**
     * Generate a Coordinate at the relative position provided.
     * If the reference is looking towards north (z-Angle = 0°) distance ahead is distance towards north and distance to starboard is distance towards east.
     * @param distanceAhead relative distance ahead in meters
     * @param distanceStarboard relative distance provided to starboard in meters
     * @return new Coordinate in WGS84 2D Coordinate System
     */
    @Override
    public Coordinate generateTransformed(double distanceAhead, double distanceStarboard) {
        Coordinate relativeCoordinate = new CoordinateImpl(distanceStarboard, distanceAhead, null); // CRS is not needed later on
        return generateTransformed(relativeCoordinate);
    }

    /**
     * Transform the Coordinate to the CRS of the Reference
     * @param relativeCoordinate relative Coordinate to transform
     * @return transformed Coordinate
     */
    @Override
    public Coordinate generateTransformed(Coordinate relativeCoordinate) {
        return generateTransformed(relativeCoordinate, referenceCRS);

    }

    /**
     * Transform the Coordinate to the provided CRS
     * @param relativeCoordinate relative Coordinate to transform
     * @param targetCRS target CRS
     * @return transformed Coordinate
     */
    @Override
    public Coordinate generateTransformed(Coordinate relativeCoordinate, CoordinateReferenceSystem targetCRS) {
        if (!thisOk) {
            return null;
        }
        if (!(relativeCoordinate.getCrs() instanceof EngineeringCRS)) {
            ULog.warn("Relative Coordinate is not an Engineering crs. If the axis are not scaled in meters this transformation might not work");
        }
        double originalX = -relativeCoordinate.getX();
        double originalY = relativeCoordinate.getY();
        // rotate using rotation matrix
        double transformedX = originalX * cos - originalY * sin; // cos - sin
        double transformedY = originalX * sin + originalY * cos; // sin + cos
        // scale
        transformedX = transformedX * xScale;
        transformedY = transformedY * yScale;
        // translate
        transformedX = transformedX + xOffset;
        transformedY = transformedY + yOffset;

        if (targetCRS instanceof WGS84CRS) {
            // ellipsoid warp around correction
            while (transformedX > 90 || transformedX < -90) { // over North Pole
                if (transformedX > 90) {
                    transformedX = 180 - transformedX;
                } else {
                    transformedX = -180 - transformedX;
                }
                transformedY = 180 + transformedY;
            }
            while (transformedY > 180 || transformedY < -180) {
                if (transformedY > 180) {
                    transformedY = -360 + transformedY;
                } else {
                    transformedY = 360 + transformedY;
                }
            }
        }
        return new CoordinateImpl(transformedX, transformedY, targetCRS);
    }

    /**
     * Transform the Pose to the CRS of the Reference
     * @param relativePose relative Pose to transform
     * @return transformed Pose
     * @implNote Only perform rotation in 2D space
     */
    @Override
    public Pose generateTransformed(Pose relativePose) {
        return generateTransformed(relativePose, referenceCRS);
    }

    /**
     * Transform the Pose to the provided CRS
     * @param relativePose relative Pose to transform
     * @param targetCRS target CRS
     * @return transformed Pose
     * @implNote Only perform rotation in 2D space
     */
    @Override
    public Pose generateTransformed(Pose relativePose, CoordinateReferenceSystem targetCRS) {
        if(!SimpleTransformedPositionGenerator.poseViable2D(relativePose)) {
            ULog.error("Provided Pose cannot be transformed since it is not viable");
            return null;
        }
        // transform coordinate
        Coordinate transformedCoordinate = generateTransformed(relativePose.getCoordinate(), targetCRS);
        // transform Angle -  z-Angle Only
        Euler relativePoseOrientation = relativePose.getOrientation().toEuler();
        Angle transformedAngle = new AngleImpl(relativePoseOrientation.getZ());
        transformedAngle.add(referencePose.getOrientation().toEuler().getZ());
        return new PoseImpl(transformedCoordinate,
                new EulerImpl(
                        new AngleImpl(relativePoseOrientation.getX()), // copy from relative - only 2D rotation
                        new AngleImpl(relativePoseOrientation.getY()), // copy from relative - only 2D rotation
                        transformedAngle
                )
        );
    }

}





