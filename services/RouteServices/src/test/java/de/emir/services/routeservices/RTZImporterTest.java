package de.emir.services.routeservices;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;

import com.google.common.io.CharStreams;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.service.RouteServicesModel;
import de.emir.service.routeservices.impl.RTZRouteExportImpl;
import de.emir.service.routeservices.impl.RTZRouteImportImpl;

public class RTZImporterTest {

	
	static {
		RouteServicesModel.init(); //initialize the data model and its dependencies
		
		SpatialDelegateProviders.register(); //register operations for coordinate handling
	}

	public static void main(String[] args) throws IOException{
        RTZImporterTest tester = new RTZImporterTest();

        tester.testImport("tests/routeImport/Barcelona - Las Palmas.rtz");
        tester.testExport("tests/routeImport/Barcelona - Las Palmas.rtz", "testdata/Route.rtz");
	}

    private void testImport(String importFile) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
		RTZRouteImportImpl rtz = new RTZRouteImportImpl();
		String str = CharStreams.toString(new InputStreamReader(is));
		Route r = rtz.importRoute(str, CRSUtils.WGS84_2D);
		Assert.assertNotNull(r);
		Assert.assertEquals(38, r.getWaypoints().getWaypoints().size());
		Coordinate lastCoordinate = r.getWaypoints().getWaypoints().getLast().getPosition();
		Assert.assertEquals(28.128315646098, lastCoordinate.getLatitude(), 0.00001);
		Assert.assertEquals(-15.4204179039724, lastCoordinate.getLongitude(), 0.00001);
	}

    private void testExport(String importFile, String exportFile) throws IOException {
        // import test file, which gets exported later
        InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
        RTZRouteImportImpl rtz = new RTZRouteImportImpl();
        String str = CharStreams.toString(new InputStreamReader(is));
        Route r = rtz.importRoute(str, CRSUtils.WGS84_2D);
        Assert.assertNotNull(r);

        // export previously imported route file
        RTZRouteExportImpl exp = new RTZRouteExportImpl();
        System.out.println(exp.exportToString(r));
        File f = new File(exportFile);
        if (f.getParentFile().exists() == false) {
            boolean success = f.getParentFile().mkdirs();
            Assert.assertTrue(success);
        }
        exp.exportToFile(r, f);
        Assert.assertTrue(f.exists());

        // we imported and exported the same file. Thus, we repeat the same test
        // case as import to check if everything is correct.
        this.testImport(exportFile);
    }
}
