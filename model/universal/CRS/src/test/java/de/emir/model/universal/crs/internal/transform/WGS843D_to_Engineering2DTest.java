package de.emir.model.universal.crs.internal.transform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;

class WGS843D_to_Engineering2DTest {

    WGS843D_to_Engineering2D transformer;

    /**
     * Setup new transformer before each test
     */
    @BeforeEach
    void setUp() {
        transformer = new WGS843D_to_Engineering2D();
    }

    /**
     * Test basic transformation of WGS843D.
     */
    @Test
    void transformBasicWGS843D() {
        WGS84CRS source = new WGS843DImpl();
        Engineering2D target = new Engineering2DImpl();
        double[] originalCoordinate = new double[]{0, 0, 10};

        double[] transformedCoordinate = transformer.transform(originalCoordinate, source, target);
        assert transformedCoordinate[0] == 0;
        assert transformedCoordinate[1] == 0;
        assert transformedCoordinate.length == 2;
    }

}