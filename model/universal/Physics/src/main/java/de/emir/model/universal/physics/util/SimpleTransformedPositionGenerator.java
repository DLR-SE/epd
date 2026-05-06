package de.emir.model.universal.physics.util;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Simple transformer that uses a dummy LocatableObject in to compute absolute Poses and Coordinates relative to the reference Pose or Coordinate.
 * The CRS of the output will be the CRS of the reference.
 * @implNote It is only considering transformations in 2D space.
 */
public class SimpleTransformedPositionGenerator implements TransformedPositionGenerator{
    // static default orientation when non is provided
    private static final Orientation DEFAULT_ORIENTATION = new EulerImpl(0.0, 0.0, 0.0, AngleUnit.DEGREE);
    // Dummy LocatableObject to do transformations
    private final LocatableObject referenceLocatableObject;
    // CRS of the reference
    private final CoordinateReferenceSystem referenceCRS;
    // Flag that tracks for viability of this object
    private final boolean thisOkay;

    /**
     * Constructor method with reference Pose parametrisation.
     * @param referencePose reference pose
     */
    public SimpleTransformedPositionGenerator(final Pose referencePose) {
        referenceLocatableObject = new PhysicalObjectImpl();
        thisOkay = poseViable2D(referencePose);
        if (!thisOkay) {
            ULog.error("reference Pose is not viable. TransformedPositionGenerator cannot be used.");
        }
        referenceLocatableObject.setPose(referencePose);
        referenceCRS = referencePose.getCoordinate().getCrs();
    }

    /**
     * Constructor method with reference Coordinate parametrisation.
     * A default orientation towards north is assumed.
     * @param referenceCoordinate reference Coordinate for future calculations.
     */
    public SimpleTransformedPositionGenerator(final Coordinate referenceCoordinate) {
        this(new PoseImpl(referenceCoordinate, DEFAULT_ORIENTATION));
    }

    /**
     * Constructor method with reference Coordinate parametrisation.
     * @param lat Latitude.
     * @param lon Longitude.
     * @implNote a default orientation towards north and WGS84 2D is assumed.
     */
    public SimpleTransformedPositionGenerator(final double lat, final  double lon) {
        this(new CoordinateImpl(lat, lon, CRSUtils.WGS84_2D));
    }


    /**
     * Generate a Coordinate at the relative position provided.
     * If the reference is looking towards north (z-Angle = 0°) distance ahead is distance towards north and distance to starboard is distance towards east.
     * @param distanceAhead relative distance ahead in meters
     * @param distanceStarboard relative distance provided to starboard in meters
     * @return new Coordinate in WGS84 2D Coordinate System
     */
    @Override
    public Coordinate generateTransformed(final double distanceAhead,final double distanceStarboard) {
        Coordinate relativeCoordinate = new CoordinateImpl(distanceStarboard, distanceAhead, referenceLocatableObject.getOwnedCoordinateSystem());
        return generateTransformed(relativeCoordinate);
    }

    /**
     * Transform the Coordinate to the CRS of the Reference
     * @param relativeCoordinate relative Coordinate to transform
     * @return transformed Coordinate
     */
    @Override
    public Coordinate generateTransformed(final Coordinate relativeCoordinate) {
        return generateTransformed(relativeCoordinate, referenceCRS);
    }

    /**
     * Transform the Coordinate to the provided CRS
     * @param relativeCoordinate relative Coordinate to transform
     * @param targetCRS target CRS
     * @return transformed Coordinate
     */
    @Override
    public Coordinate generateTransformed(final Coordinate relativeCoordinate, final CoordinateReferenceSystem targetCRS) {
        if (!thisOkay) {
            return null;
        }
        // prepare
        Coordinate copiedReferenceCoordinate = new CoordinateImpl(relativeCoordinate);
        copiedReferenceCoordinate.setCrs(referenceLocatableObject.getOwnedCoordinateSystem());
        // do transform
        return copiedReferenceCoordinate.get(targetCRS);
    }

    /**
     * Transform the Pose to the CRS of the Reference
     * @param relativePose relative Pose to transform
     * @return transformed Pose
     * @implNote Only perform rotation in 2D space
     */
    @Override
    public Pose generateTransformed(final Pose relativePose) {
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
    public Pose generateTransformed(final Pose relativePose, final CoordinateReferenceSystem targetCRS) {
        if (!thisOkay) {
            return null;
        }
        if (!poseViable2D(relativePose)) {
            ULog.error("Provided Pose cannot be transformed since it is not viable");
            return null;
        }
        Coordinate transformedCoordinate = generateTransformed(relativePose.getCoordinate(), targetCRS);
        // transform Angle -  z-Angle Only
        Euler relativePoseOrientation = relativePose.getOrientation().toEuler();
        Angle transformedAngle = new AngleImpl(relativePoseOrientation.getZ());
        transformedAngle.add(referenceLocatableObject.getPose().getOrientation().toEuler().getZ());
        return new PoseImpl(transformedCoordinate,
                new EulerImpl(
                        new AngleImpl(relativePoseOrientation.getX()), // copy from relative - only 2D rotation
                        new AngleImpl(relativePoseOrientation.getY()), // copy from relative - only 2D rotation
                        transformedAngle
                )
        );
    }

    /**
     * Checks whether a Pose is viable in 2D space
     * checks are:
     * - CRS exists
     * - Pose is not null
     * - Coordinate exists
     * - Longitude and Latitude are numbers
     * - Orientation exists
     * - Orientation has a z-Angle
     * - Value of z-Angle is a number
     * @param poseToCheck Pose that is checked
     * @return true if the Pose is okay
     */
    public static boolean poseViable2D(Pose poseToCheck) {
        if (poseToCheck == null) {
            ULog.error("Pose is cannot be null");
            return false;
        }
        // check coordinate
        if (poseToCheck.getCoordinate() == null) {
            ULog.error("Pose has no coordinate");
            return false;
        }
        if (Double.isNaN(poseToCheck.getCoordinate().getLatitude())) {
            ULog.error("Latitude is not a number");
            return false;
        }
        if (Double.isNaN(poseToCheck.getCoordinate().getLongitude())) {
            ULog.error("Longitude is not a number");
            return false;
        }
        if (poseToCheck.getCoordinate().getCrs() == null) {
            ULog.error("Pose has no CRS");
            return false;
        }
        // check orientation
        if (poseToCheck.getOrientation() == null) {
            ULog.error("Pose has no Orientation");
            return false;
        }
        if (poseToCheck.getOrientation().toEuler().getZ() == null) {
            ULog.error("Orientation has no Z-Angle");
            return false;
        }
        if (Double.isNaN(poseToCheck.getOrientation().toEuler().getZ().getValue())) {
            ULog.error("Value of Angle of Orientation is not a number");
            return false;
        }
        return true; // Pose is ok
    }
}
