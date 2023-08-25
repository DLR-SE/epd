package de.emir.epd.nmeasensor.verify;

import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackCharacteristic;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;

import java.util.List;

/**
 * Check AIS values based on this website
 * https://www.navcen.uscg.gov/?pageName=AISMessagesA
 */
public class VesselVerifier {

    private static final double DEFAULT_DISTANCE_TOLERANCE = 10;

    public static void verify(Vessel vessel) {
        Speed sog = PhysicalObjectUtils.getSOG(vessel);
        if (sog != null) {
            boolean sogCheck = checkSog(sog);
            vessel.setPropertyValue(VesselErrorCode.SOG_CHECK.name(), sogCheck);
        }
        Angle cog = PhysicalObjectUtils.getCOG(vessel);
        if (cog != null) {
            boolean cogCheck = checkCog(cog);
            vessel.setPropertyValue(VesselErrorCode.COG_CHECK.name(), cogCheck);
        }
        Angle heading = PhysicalObjectUtils.getHeading(vessel);
        if (heading != null) {
            boolean headingCheck = checkHeading(heading);
            vessel.setPropertyValue(VesselErrorCode.HEADING_CHECK.name(), headingCheck);
        }

        AngularSpeed rot = PhysicalObjectUtils.getRateOfTurn(vessel);
        if (rot != null) {
            boolean rotCheck = checkRot(rot);
            vessel.setPropertyValue(VesselErrorCode.ROT_CHECK.name(), rotCheck);
        }

        Pose pose = vessel.getPose();
        if (pose != null) {
            Coordinate coordinate = pose.getCoordinate();
            if (coordinate != null) {
                boolean coordinateCheck = checkCoordinate(coordinate);
                vessel.setPropertyValue(VesselErrorCode.POSITION_CHECK.name(), coordinateCheck);
            }
        }

        TrackCharacteristic trackCharacteristic = vessel.getFirstCharacteristic(TrackCharacteristic.class);
        if (trackCharacteristic != null) {
            Track track = trackCharacteristic.getTrack();
            if (track != null) {
                List<TrackPoint> trackPointList = track.getElements();
                if (trackPointList != null && trackPointList.size() > 1) {
                    TrackPoint current = trackPointList.get(trackPointList.size() - 1);
                    TrackPoint last = trackPointList.get(trackPointList.size() - 2);

                    if (current != null && last != null) {
                        boolean trackCheck = checkTrackPoints(current, last);
                        vessel.setPropertyValue(VesselErrorCode.TRACKPOINT_CHECK.name(), trackCheck);
                    }
                }
            }
        }


    }

    /**
     * Checks if the vessel contains any error code properties
     *
     * @param vessel vessel
     * @return everything is ok or not
     */
    public static boolean isCorrect(Vessel vessel) {
        for (VesselErrorCode vesselErrorCode : VesselErrorCode.values()) {
            boolean check = isCorrect(vessel, vesselErrorCode);
            if (check == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a specified error code is in the vessel properties
     *
     * @param vessel          vessel
     * @param vesselErrorCode error code
     * @return vessel contains property value
     */
    public static boolean isCorrect(Vessel vessel, VesselErrorCode vesselErrorCode) {
    	try{
    		return vessel.getPropertyValueAsBoolean(vesselErrorCode.name());
    	}catch (Exception e) {
			return true;
		}
    }

    /**
     * Checks if the speed value is within ais value bounds
     *
     * @param speed sog value
     * @return ok or not ok
     */
    public static boolean checkSog(Speed speed) {
        if (speed == null) return false;
        return checkSog(speed.getAs(SpeedUnit.KNOTS));
    }

    /**
     * Checks if the speed value is within ais value bounds
     *
     * @param value sog in knots
     * @return ok or not ok
     */
    public static boolean checkSog(double value) {
        if (value < 0) return false;
        if (value > 102) return false;

        return true;
    }

    /**
     * Checks if the cog value is within ais value bounds
     *
     * @param angle cog value
     * @return ok or not ok
     */
    public static boolean checkCog(Angle angle) {
        if (angle == null) return false;
        return checkCog(angle.getAs(AngleUnit.DEGREE));
    }

    /**
     * Checks if the cog value is within ais value bounds
     *
     * @param value cog value in degrees
     * @return ok or not ok
     */
    public static boolean checkCog(double value) {
        if (value < 0) return false;
        if (value >= 360) return false;

        return true;
    }

    /**
     * Checks if the heading value is within ais value bounds
     *
     * @param angle heading value in degrees
     * @return ok or not ok
     */
    public static boolean checkHeading(Angle angle) {
        if (angle == null) return false;
        return checkHeading(angle.getAs(AngleUnit.DEGREE));
    }

    /**
     * Checks if the heading value is within ais value bounds
     *
     * @param value heading value in degrees
     * @return ok or not ok
     */
    public static boolean checkHeading(double value) {
        return checkCog(value);
    }

    /**
     * Checks if the rot value is within ais value bounds
     *
     * @param rot rot value in degrees per minute
     * @return ok or not ok
     */
    public static boolean checkRot(AngularSpeed rot) {
        if (rot == null) return false;
        return checkRot(rot.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE));
    }

    /**
     * Checks if the rot value is within ais value bounds
     *
     * @param value rot value in degrees per minute
     * @return ok or not ok
     */
    public static boolean checkRot(double value) {
        if (value < 0) return false;
        if (value > 708) return false;

        return true;
    }

    /**
     * Checks if the coordinate is within ais value bounds
     *
     * @param coordinate lat lon coordinate
     * @return ok or not ok
     */
    public static boolean checkCoordinate(Coordinate coordinate) {
        if (coordinate == null) return false;
        return checkCoordinate(coordinate.getLatitude(), coordinate.getLongitude());
    }

    /**
     * Checks if the coordinate is within ais value bounds
     *
     * @param lat lat coordinate
     * @param lon lon coordinate
     * @return ok or not ok
     */
    public static boolean checkCoordinate(double lat, double lon) {
        //some of our data is broken, so 0,0 is a default value which shouldn't be reached in real data (hopefully)
        if (lat == 0 && lon == 0) return false;
        if (lat > 90) return false;
        if (lon > 180) return false;
        if (lat < -90) return false;
        if (lon < -180) return false;
        return true;
    }

    /**
     * Checks if time is within ais value bounds e.g. != null
     *
     * @param time value
     * @return ok or not ok
     */
    public static boolean checkTime(Time time) {
        return time != null;
    }

    /**
     * Checks if a trackpoint is within ais bounds
     * checks: Coordinate, SOG, COG, Heading, Time
     *
     * @param trackPoint trackpoint
     * @return ok or not ok
     */
    public static boolean checkTrackPoint(TrackPoint trackPoint) {
        boolean check = true;
        Coordinate coordinate = trackPoint.getCoordinate();
        check = checkCoordinate(coordinate);
        if (check == false) {
            return false;
        }
        Speed speed = trackPoint.getSpeed();
        check = checkSog(speed);
        if (check == false) {
            return false;
        }
        Angle cog = trackPoint.getCourse();
        check = checkCog(cog);
        if (check == false) {
            return false;
        }
        Angle heading = trackPoint.getHeading();
        check = checkHeading(heading);
        if (check == false) {
            return false;
        }
        Time time = trackPoint.getTime();
        check = checkTime(time);
        return check;
    }

    /**
     * Checks if current trackpoint can be reached based on a last trackpoint
     * this uses the default tolerance
     *
     * @param current current
     * @param last    last
     * @return ok or not ok
     */
    public static boolean checkTrackPoints(TrackPoint current, TrackPoint last) {
        return checkTrackPoints(current, last, DEFAULT_DISTANCE_TOLERANCE);
    }

    /**
     * Checks if current trackpoint can be reached based on a last trackpoint with an additional tolerance in meters
     *
     * @param current   current
     * @param last      last
     * @param tolerance tolerance in meters
     * @return ok or not ok
     */
    public static boolean checkTrackPoints(TrackPoint current, TrackPoint last, double tolerance) {
        if (checkTrackPoint(current) == false) return false;
        if (checkTrackPoint(last) == false) return false;

        double acceleration = calculateAcceleration(current.getSpeed(), last.getSpeed(), current.getTime(), last.getTime());
        double seconds = current.getTime().sub(last.getTime()).getAs(TimeUnit.SECOND);
        double possibleAccelerationDistance = (acceleration * (seconds * seconds)) / 2;
        double distance = current.getCoordinate().getDistance(last.getCoordinate()).getAs(DistanceUnit.METER);

        return possibleAccelerationDistance <= (distance - tolerance);
    }

    /**
     * Calculate acceleration between two sog values and its times
     *
     * @param first      first vessel speed
     * @param second     second vessel speed
     * @param firstTime  first vessel time
     * @param secondTime second vessel time
     * @return acceleration m/s
     */
    private static float calculateAcceleration(Speed first, Speed second, Time firstTime, Time secondTime) {
        double finalVelocity = first.getAs(SpeedUnit.METER_PER_SECOND);
        double initialVelocity = second.getAs(SpeedUnit.METER_PER_SECOND);
        double deltaVelocity = finalVelocity - initialVelocity;
        double deltaTime = firstTime.sub(secondTime).getAs(TimeUnit.SECOND);

        if (deltaTime != 0) {
            return (float) (deltaVelocity / deltaTime);
        } else {
            return 0;
        }
    }
}