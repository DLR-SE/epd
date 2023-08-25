package de.emir.rcp.views.test;

import de.emir.model.universal.units.*;
import de.emir.rcp.util.LatexRender;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LatexRenderTest {
    public static void main(String[] args) {
        testUnits();
    }

    public static void test(String symbol) {
        try {
            BufferedImage result = LatexRender.createImage(
                    symbol
            );

            assertTrue(result != null);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public static void testAll(List<String> symbols) {
        for (String symbol : symbols) {
            test(symbol);
        }
    }

    public static void testAll(String... symbols) {
        for (String symbol : symbols) {
            test(symbol);
        }
    }

    public static void testUnits() {

        //=============================
        // Testing enums
        //=============================


        System.out.println("Testing AngleUnit");
        testAll(
                Arrays.stream(AngleUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        System.out.println("Testing MassUnit");
        testAll(
                Arrays.stream(MassUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        System.out.println("Testing TimeUnit");
        testAll(
                Arrays.stream(TimeUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        System.out.println("Testing TemperatureUnit");
        testAll(
                Arrays.stream(TemperatureUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        System.out.println("Testing DistanceUnit");
        testAll(
                Arrays.stream(DistanceUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        System.out.println("Testing VolumeUnit");
        testAll(
                Arrays.stream(VolumeUnit.values())
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        //=============================
        // Testing compound types
        //=============================

        System.out.println("Testing VolumeUnit");
        testAll(
                Arrays.stream(new SpeedUnit[]{
                                SpeedUnit.METER_PER_SECOND,
                                SpeedUnit.METER_PER_MINUTE,
                                SpeedUnit.METER_PER_HOUR,
                                SpeedUnit.KNOTS,
                                SpeedUnit.KMH,
                                SpeedUnit.KM_PER_MINUTE,
                                SpeedUnit.KM_PER_SECOND,
                                SpeedUnit.NAUTICALMILES_PER_SECOND,
                                SpeedUnit.NAUTICALMILES_PER_MINUTE,
                        })
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        testAll(
                Arrays.stream(new AngularSpeedUnit[]{
                                AngularSpeedUnit.DEGREES_PER_SECOND,
                                AngularSpeedUnit.DEGREES_PER_MINUTE,
                                AngularSpeedUnit.DEGREES_PER_HOUR,
                                AngularSpeedUnit.RADIANS_PER_SECOND,
                                AngularSpeedUnit.RADIANS_PER_MINUTE,
                                AngularSpeedUnit.RADIANS_PER_HOUR,
                                AngularSpeedUnit.ROUNDS_PER_SECOND,
                                AngularSpeedUnit.ROUNDS_PER_MINUTE,
                                AngularSpeedUnit.ROUNDS_PER_HOUR,
                                AngularSpeedUnit.RPM,
                        })
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );


        testAll(
                Arrays.stream(new AccelerationUnit[]{
                                AccelerationUnit.METER_PER_SQUARE_SECOND,
                                AccelerationUnit.METER_PER_SQUARE_MINUTE,
                                AccelerationUnit.METER_PER_SQUARE_HOUR,
                                AccelerationUnit.KM_PER_SQUARE_SECOND,
                                AccelerationUnit.KM_PER_SQUARE_MINUTE,
                                AccelerationUnit.KM_PER_SQUARE_HOUR,
                                AccelerationUnit.NAUTICALMILES_PER_SQUARE_SECOND,
                                AccelerationUnit.NAUTICALMILES_PER_SQUARE_MINUTE,
                                AccelerationUnit.NAUTICALMILES_PER_SQUARE_HOUR,
                        })
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );

        testAll(
                Arrays.stream(new AngularAccelerationUnit[]{
                                AngularAccelerationUnit.DEGREE_PER_SQUARE_SECOND,
                                AngularAccelerationUnit.DEGREE_PER_SQUARE_MINUTE,
                                AngularAccelerationUnit.RADIANS_PER_SQUARE_SECOND,
                                AngularAccelerationUnit.RADIANS_PER_SQUARE_MINUTE,
                                AngularAccelerationUnit.ROUNDS_PER_SQUARE_SECOND,
                                AngularAccelerationUnit.ROUNDS_PER_SQUARE_MINUTE,
                        })
                        .map(UnitSymbolUtil::getSymbol)
                        .collect(Collectors.toList())
        );
    }
}
