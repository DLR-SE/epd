package de.emir.model.universal.units.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;

public class UnitParserTest {

    @Test
    public void testParseSpeedUnit_validValues() {
        assertEquals(SpeedUnit.KNOTS, UnitParser.parseSpeedUnit("knots"));
        assertEquals(SpeedUnit.NAUTICALMILES_PER_MINUTE, UnitParser.parseSpeedUnit("nm/min"));
        assertEquals(SpeedUnit.KMH, UnitParser.parseSpeedUnit("km/h"));
        assertEquals(SpeedUnit.METER_PER_SECOND, UnitParser.parseSpeedUnit("m/s"));
    }

    @Test
    public void testParseSpeedUnit_invalidValueThrows() {
        assertThrows(IllegalArgumentException.class, () -> UnitParser.parseSpeedUnit("invalid"));
    }

    @Test
    public void testParseDistanceUnit_validValues() {
        assertEquals(DistanceUnit.METER, UnitParser.parseDistanceUnit("m"));
        assertEquals(DistanceUnit.KILOMETER, UnitParser.parseDistanceUnit("km"));
        assertEquals(DistanceUnit.NAUTICAL_MILES, UnitParser.parseDistanceUnit("nm"));
    }

    @Test
    public void testParseDistanceUnit_invalidValueThrows() {
        assertThrows(IllegalArgumentException.class, () -> UnitParser.parseDistanceUnit("invalid"));
    }
}