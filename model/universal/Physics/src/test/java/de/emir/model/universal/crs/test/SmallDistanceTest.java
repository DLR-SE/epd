package de.emir.model.universal.crs.test;

import static org.junit.Assert.fail;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.geotools.referencing.GeodeticCalculator;
import org.opengis.referencing.operation.TransformException;

import com.google.common.io.Files;

import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculatorSlowMath;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.DistanceImpl;

public class SmallDistanceTest {

	
	public static void main(String[] args) {
		testInverse();
//		new SmallDistanceTest().test(new File("src/test/resources/EnablesSOG2.csv"));
		foo();
	}
	
	public static void foo() {
		Coordinate ll1 = new CoordinateImpl(53.1487781, 8.2010379, CRSUtils.WGS84_2D); //schuler building
		Coordinate ll2 = new CoordinateImpl(53.1488183, 8.2014501, CRSUtils.WGS84_2D);
		Coordinate ll3 = new CoordinateImpl(53.1486710, 8.2014901, CRSUtils.WGS84_2D);
		Coordinate ll4 = new CoordinateImpl(53.1486308, 8.2010779, CRSUtils.WGS84_2D);
		EnvelopeImpl env = new EnvelopeImpl(ll1);
		env.expandLocal(ll3);env.expandLocal(ll4);env.expandLocal(ll2);
		System.out.println("Center: " + env.getCenter());
		Engineering2D crs = new Engineering2DImpl(env.getCenter().toVector());
		System.out.println(ll1.get(crs));
		System.out.println(ll2.get(crs));
		System.out.println(ll3.get(crs));
		System.out.println(ll4.get(crs));
		
		Coordinate o = new CoordinateImpl(53.149625, 8.201264, CRSUtils.WGS84_2D);
		System.out.println("Distance: " + env.getCenter().getDistance(o).getAs(DistanceUnit.METER));
		
		System.out.println("\n");
		ll1 = new CoordinateImpl(-33.8988712, 18.5281386, CRSUtils.WGS84_2D); //some building in south africa
		ll2 = new CoordinateImpl(-33.8988801, 18.5285282, CRSUtils.WGS84_2D);
		ll3 = new CoordinateImpl(-33.8994135, 18.5285105, CRSUtils.WGS84_2D);
		ll4 = new CoordinateImpl(-33.8994046, 18.5281209, CRSUtils.WGS84_2D);
		env = new EnvelopeImpl(ll1);
		env.expandLocal(ll3);env.expandLocal(ll4);env.expandLocal(ll2);
		System.out.println("Center: " + env.getCenter());
		crs = new Engineering2DImpl(env.getCenter().toVector());
		System.out.println(ll1.get(crs));
		System.out.println(ll2.get(crs));
		System.out.println(ll3.get(crs));
		System.out.println(ll4.get(crs));
	}
	
	public static void testInverse() {
		CoordinateImpl c1 = new CoordinateImpl(54.6, 12.4, CRSUtils.WGS84_2D);
		GeodeticCalculator gc = new GeodeticCalculator();
		gc.setStartingGeographicPoint(12.4, 54.6);
		gc.setDirection(30, new DistanceImpl(9, DistanceUnit.NAUTICAL_MILES).getAs(DistanceUnit.METER));
		Point2D dst = gc.getDestinationGeographicPoint();
		CoordinateImpl c2 = new CoordinateImpl(dst.getY(), dst.getX(), CRSUtils.WGS84_2D);
		
		gc = new GeodeticCalculator();
		gc.setStartingGeographicPoint(c1.getLongitude(), c1.getLatitude());
		gc.setDestinationGeographicPoint(c2.getLongitude(), c2.getLatitude());
		
		System.out.println("Coordinate: " + c2);
		System.out.println("Azimuth[Deg]: " + c1.getAzimuth(c2).getAs(AngleUnit.DEGREE));
		System.out.println("Distance[NM]:" + c1.getDistance(c2).getAs(DistanceUnit.METER));
		System.out.println("GeodedicCalculator");
		System.out.println(gc.getAzimuth());
		System.out.println(gc.getOrthodromicDistance());
		try {
			System.out.println(gc.getDestinationPosition());
		} catch (TransformException e) {
			e.printStackTrace();
		}
	}
	public void test(File file) {
		try {
			List<String> lines = Files.readLines(file, Charset.defaultCharset());
			List<Coordinate> coordinates = new ArrayList<>();
			List<Double> times = new ArrayList<>();
			for (String line : lines) {
				if (line.startsWith("#")) continue;
				String[] sp = line.split(";");
				String t_str = sp[0].split("\\{")[1].split("\\}")[0].split("=")[1].trim();
				String lat_str = sp[5].replaceAll(",", ".");
				String lon_str = sp[6].replaceAll(",", ".");
				Coordinate c = new CoordinateImpl(Double.parseDouble(lat_str), Double.parseDouble(lon_str), CRSUtils.WGS84_2D);
				double t = Double.parseDouble(t_str);
				coordinates.add(c); times.add(t);
			}
			
			VincentCalculatorSlowMath 	slowMath = new VincentCalculatorSlowMath();
			VincentCalculator			fastMath = new VincentCalculator();
			GeodeticCalculator 			geodedic = new GeodeticCalculator();
			double expected = 5.1444399949;
			double expected_ang = 130;
			//calculate the distance between two coordinates
			if (times.size() != coordinates.size())
				fail("Failed to read input document");
			double fast_sum = 0;
			double slow_sum = 0;
			double geod_sum = 0;
			double fast_sum_ar = 0;
			double slow_sum_ar = 0;
			double geod_sum_ar = 0;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < coordinates.size()-1; i++) {
				Coordinate c1 = coordinates.get(i);
				Coordinate c2 = coordinates.get(i+1);
				geodedic.setStartingGeographicPoint(c1.getLongitude(), c1.getLatitude());
				geodedic.setDestinationGeographicPoint(c2.getLongitude(), c2.getLatitude());
				double[] slow = slowMath.getDistanceAndAzimuth(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
				double[] fast = fastMath.getDistanceAndAzimuth(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
				
				double slow_dist = slow[0];
				double fast_dist = fast[0];
				double geod_dist = geodedic.getOrthodromicDistance();
				
				double slow_ang = Math.toDegrees(slow[1]);
				double fast_ang = Math.toDegrees(fast[1]);
				double geod_ang = geodedic.getAzimuth();
				
				double slow_err = Math.abs(slow_dist-expected);
				double fast_err = Math.abs(fast_dist-expected);
				double geod_err = Math.abs(geod_dist-expected);
				
				double slow_ar 	= (slow_ang - expected_ang);
				double fast_ar 	= (fast_ang - expected_ang);
				double geod_ar 	= (geod_ang - expected_ang);
				System.out.println(i+": " + slow_err + " ---- " + fast_err + "----" + geod_err);
				System.out.println(i+": " + slow_ar + " ---- " + fast_ar + "----" + geod_ar + "\n");
				fast_sum += fast_err;
				slow_sum += slow_err;
				geod_sum += geod_err;
				
				fast_sum_ar += fast_ar;
				slow_sum_ar += slow_ar;
				geod_sum_ar += geod_ar;
				
				sb.append(String.format("%1.8f", slow_err) + ";" + String.format("%1.8f", fast_err) + ";" + String.format("%1.8f", geod_err) + ";" + String.format("%1.8f", slow_ar) + ";" + String.format("%1.8f", fast_ar) + ";" + String.format("%1.8f", geod_ar) + "\n");
			}
			System.out.println("Fast Error: " + fast_sum);
			System.out.println("Slow Error: " + slow_sum);
			System.out.println("Geod Error: " + geod_sum);
			System.out.println("Fast Angle Error: " + fast_sum_ar);
			System.out.println("Slow Angle Error: " + slow_sum_ar);
			System.out.println("Geod Angle Error: " + geod_sum_ar);
			System.out.println("\n\n\n");
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
