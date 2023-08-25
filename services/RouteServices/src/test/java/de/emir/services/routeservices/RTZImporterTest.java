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
		new RTZImporterTest().testImport("tests/routeImport/Barcelona - Las Palmas.rtz");//OSLO-CPH VEN W.txt");
	}
	private void testImport(String url) throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(url);
		RTZRouteImportImpl rtz = new RTZRouteImportImpl();
		String str = CharStreams.toString(new InputStreamReader(is));
		Route r = rtz.importRoute(str, CRSUtils.WGS84_2D);
		Assert.assertNotNull(r);
		Assert.assertEquals(38, r.getWaypoints().getWaypoints().size());
		Coordinate lastCoord = r.getWaypoints().getWaypoints().get(37).getPosition();
		Assert.assertEquals(lastCoord.getLatitude(), 28.128315646098, 0.00001);
		Assert.assertEquals(lastCoord.getLongitude(), -15.4204179039724, 0.00001);
		
		
		RTZRouteExportImpl exp = new RTZRouteExportImpl();
		System.out.println(exp.exportToString(r));
		File f = new File("testdata/Route.rtz");
		if (f.getParentFile().exists() == false)
			f.getParentFile().mkdirs(); 
		exp.exportToFile(r, f);
		Assert.assertTrue(f.exists());
	}
}
