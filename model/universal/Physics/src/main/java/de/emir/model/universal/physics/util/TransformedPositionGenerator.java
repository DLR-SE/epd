package de.emir.model.universal.physics.util;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;

/**
 * Helper that allows quick transformation of Poses and coordinates relative to another CRS.
 */
public interface TransformedPositionGenerator {
    /**
     * Generate a Coordinate at the relative position provided.
     * If the reference is looking towards north (z-Angle = 0°) distance ahead is distance towards north and distance to starboard is distance towards east.
     * @param distanceAhead relative distance ahead in meters
     * @param distanceStarboard relative distance provided to starboard in meters
     * @return new Coordinate in WGS84 2D Coordinate System
     */
    Coordinate generateTransformed(final double distanceAhead, final double distanceStarboard);
    /**
     * Get Transformed coordinate from relative coordinate.
     * @param relativeCoordinate relative coordinate
     * @return transformed coordinate
     */
    Coordinate generateTransformed(final Coordinate relativeCoordinate);
    /**
     * Get Transformed coordinate from relative coordinate.
     * @param relativeCoordinate relative coordinate
     * @param targetCRS target crs
     * @return transformed coordinate
     */
    Coordinate generateTransformed(final Coordinate relativeCoordinate, final CoordinateReferenceSystem targetCRS);
    /**
     * Get Transformed Pose from relative Pose.
     * @param relativePose relative Pose
     * @return transformed Pose
     */
    Pose generateTransformed(final Pose relativePose);
    /**
     * Get Transformed pose from relative pose.
     * @param relativePose relative pose
     * @param targetCRS target crs
     * @return pose coordinate
     */
    Pose generateTransformed(final Pose relativePose, final CoordinateReferenceSystem targetCRS);
}
