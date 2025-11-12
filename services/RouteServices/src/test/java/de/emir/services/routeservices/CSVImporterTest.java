package de.emir.services.routeservices;

import com.google.common.io.CharStreams;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.service.RouteServicesModel;
import de.emir.service.routeservices.impl.CSVRouteFileImpl;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVImporterTest {

	
	static {
		RouteServicesModel.init(); //initialize the data model and its dependencies
		
		SpatialDelegateProviders.register(); //register operations for coordinate handling
	}

	public static void main(String[] args) throws IOException{
        CSVImporterTest tester = new CSVImporterTest();

        tester.testImport("tests/routeImport/route.csv");
        tester.testImportBadHeader("tests/routeImport/route_bad_header.csv");
	}

    private void testImport(String importFile) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
		CSVRouteFileImpl rtz = new CSVRouteFileImpl();
		String str = CharStreams.toString(new InputStreamReader(is));
		Route r = rtz.importRoute(str, CRSUtils.WGS84_2D);
		Assert.assertNotNull(r);
		Assert.assertEquals(3, r.getWaypoints().getWaypoints().size());

        // wp 0 has all available fields
        Waypoint wp0 = r.getWaypoints().getWaypoints().get(0);
        Assert.assertEquals(54.0, wp0.getPosition().getLatitude(), 0.00001);
        Assert.assertEquals(8.0, wp0.getPosition().getLongitude(), 0.00001);
        Assert.assertNotNull(wp0.getLeg());
        Leg leg = wp0.getLeg();
        Assert.assertEquals(0.0, leg.getPlanSpeedMin().getAs(SpeedUnit.KNOTS), 0.00001);
        Assert.assertEquals(1.0, leg.getPlanSpeedMax().getAs(SpeedUnit.KNOTS), 0.00001);
        Assert.assertEquals(0.1, leg.getPortsideXTD().getAs(DistanceUnit.NAUTICAL_MILES), 0.00001);
        Assert.assertEquals(0.2, leg.getStarboardXTD().getAs(DistanceUnit.NAUTICAL_MILES), 0.00001);

        // wp 1 does not have all fields
        Waypoint wp1 = r.getWaypoints().getWaypoints().get(1);
        Assert.assertEquals(54.1, wp1.getPosition().getLatitude(), 0.00001);
        Assert.assertEquals(8.1, wp1.getPosition().getLongitude(), 0.00001);
        Assert.assertNull(wp1.getLeg());

        // wp 2 has empty fields
        Waypoint wp2 = r.getWaypoints().getWaypoints().get(2);
        Assert.assertEquals(54.2, wp2.getPosition().getLatitude(), 0.00001);
        Assert.assertEquals(8.2, wp2.getPosition().getLongitude(), 0.00001);
        Assert.assertNull(wp2.getLeg());
	}

    /**
     * Test that CSV filed with a wrong header (e.g. misordered fields or missing fields) results in an
     * exception -> the importer should return null.
     * @param importFile
     * @throws IOException
     */
    private void testImportBadHeader(String importFile) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
        CSVRouteFileImpl rtz = new CSVRouteFileImpl();
        String str = CharStreams.toString(new InputStreamReader(is));
        Route r = rtz.importRoute(str, CRSUtils.WGS84_2D);
        Assert.assertNull(r);
    }

}
