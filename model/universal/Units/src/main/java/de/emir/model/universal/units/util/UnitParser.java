package de.emir.model.universal.units.util;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;

/**
 * Class that reads the speed and distance units from a String format and converts them into real {@link SpeedUnit} and
 * {@link DistanceUnit} objects.
 */
public class UnitParser {

    /**
     * Transforms a string into its corresponding SpeedUnit.
     * @param speedUnit speed unit as a String.
     * @return SpeedUnit object corresponding to the input string.
     * @throws IllegalArgumentException if the string does not match any known speed unit
     */
    public static SpeedUnit parseSpeedUnit(String speedUnit) {
        return switch (speedUnit.toLowerCase()) {
            case "knots" -> SpeedUnit.KNOTS;
            case "nm/min" -> SpeedUnit.NAUTICALMILES_PER_MINUTE;
            case "km/h" -> SpeedUnit.KMH;
            case "m/s" -> SpeedUnit.METER_PER_SECOND;
            default -> throw new IllegalArgumentException("Unknown speed unit String: " + speedUnit);
        };
    }

    /**
     * Transforms a string into its corresponding DistanceUnit.
     * @param distanceUnit distance unit as a String.
     * @return DistanceUnit object corresponding to the input string.
     * @throws IllegalArgumentException if the string does not match any known distance unit
     */
    public static DistanceUnit parseDistanceUnit(String distanceUnit) {
        return switch (distanceUnit.toLowerCase()) {
            case "m" -> DistanceUnit.METER;
            case "km" -> DistanceUnit.KILOMETER;
            case "nm" -> DistanceUnit.NAUTICAL_MILES;
            default -> throw new IllegalArgumentException("Unknown distance unit String: " + distanceUnit);
        };
    }
}
