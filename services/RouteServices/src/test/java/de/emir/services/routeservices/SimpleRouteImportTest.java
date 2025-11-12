package de.emir.services.routeservices;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;

import com.google.common.io.CharStreams;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.service.RouteServicesModel;
import de.emir.service.routeservices.impl.SimpleRouteFileExportImpl;
import de.emir.service.routeservices.impl.SimpleRouteFileImpl;

public class SimpleRouteImportTest {

	
	static {
		RouteServicesModel.init(); //initialize the data model and its dependencies
		
		SpatialDelegateProviders.register(); //register operations for coordinate handling
		
	}

    public static void main(String[] args) throws IOException{
		SimpleRouteImportTest tester = new SimpleRouteImportTest();

        tester.testImport("tests/routeImport/OSLO-CPH VEN W.txt");
        tester.testImport("tests/routeImport/OSLO-CPH VEN W.txt", "testdata/Route.txt");
	}

	private void testImport(String importFile) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
		SimpleRouteFileImpl srfi = new SimpleRouteFileImpl();
		String str = CharStreams.toString(new InputStreamReader(is));
		Route r = srfi.importRoute(str, CRSUtils.WGS84_2D);
		Assert.assertNotNull(r);
		Assert.assertEquals(35, r.getWaypoints().getWaypoints().size());
	}

    private void testImport(String importFile, String exportFile) throws IOException {
        // import test file, which gets exported later
        InputStream is = getClass().getClassLoader().getResourceAsStream(importFile);
        SimpleRouteFileImpl srfi = new SimpleRouteFileImpl();
        String str = CharStreams.toString(new InputStreamReader(is));
        Route r = srfi.importRoute(str, CRSUtils.WGS84_2D);
        Assert.assertNotNull(r);
        Assert.assertEquals(35, r.getWaypoints().getWaypoints().size());

        // export previously imported route file
        SimpleRouteFileExportImpl exp = new SimpleRouteFileExportImpl();
        System.out.println(exp.exportToString(r));
        File f = new File(exportFile);
        if (f.getParentFile().exists() == false)
            f.getParentFile().mkdirs();
        exp.exportToFile(r, f);
        Assert.assertTrue(f.exists());

        // we imported and exported the same file. Thus, we repeat the same test
        // case as import to check if everything is correct.
        this.testImport(exportFile);
    }
}
