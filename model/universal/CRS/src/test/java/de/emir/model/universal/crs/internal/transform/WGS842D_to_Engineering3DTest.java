package de.emir.model.universal.crs.internal.transform;

import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGS842D_to_Engineering3DTest {

    WGS842D_to_Engineering3D transformer;

    /**
     * Setup new transformer before each test
     */
    @BeforeEach
    void setUp() {
        transformer = new WGS842D_to_Engineering3D();
    }

    /**
     * Test basic transformation of WGS842D.
     */
    @Test
    void transformBasicWGS842D() {
        WGS84CRS source = new WGS842DImpl();
        Engineering3D target = new Engineering3DImpl(); // origin is WGS842D CoordinateSystem by default;
        double[] originalCoordinate = new double[]{0, 0};

        double[] transformedCoordinate = transformer.transform(originalCoordinate, source, target);
        assert transformedCoordinate[0] == 0;
        assert transformedCoordinate[1] == 0;
    }

    /**
     * Test basic transformation of WGS843D
     */
    @Test
    void transformBasicWGS843D() {
        WGS84CRS source = new WGS843DImpl();
        Engineering3D target = new Engineering3DImpl(); // origin cs is WGS842D CoordinateSystem by default
        double[] originalCoordinate = new double[]{0, 0};

        double[] transformedCoordinate = transformer.transform(originalCoordinate, source, target);
        assert transformedCoordinate[0] == 0;
        assert transformedCoordinate[1] == 0;
    }

}