//package de.emir.model.universal.crs.internal.calc;
//
//import org.geotools.referencing.GeodeticCalculator;
//
//public class GeoToolsCalculator implements IGeodeticCalculator {
//
//	GeodeticCalculator 	mCalculator = new GeodeticCalculator();
//	
//	public double getAzimuth(double lat1, double lon1, double lat2, double lon2) {
//		mCalculator.setStartingGeographicPoint(lon1, lat1);
//		mCalculator.setDestinationGeographicPoint(lon2, lat2);
//		return Math.toRadians(mCalculator.getAzimuth());
//	}
//
//	public double getDistance(double lat1, double lon1, double lat2, double lon2) {
//		mCalculator.setStartingGeographicPoint(lon1, lat1);
//		mCalculator.setDestinationGeographicPoint(lon2, lat2);
//		return mCalculator.getOrthodromicDistance();
//	}
//
//	public double[] getDistanceAndAzimuth(double lat1, double lon1, double lat2, double lon2) {
//		mCalculator.setStartingGeographicPoint(lon1, lat1);
//		mCalculator.setDestinationGeographicPoint(lon2, lat2);
//		return new double[]{mCalculator.getOrthodromicDistance(), Math.toRadians(mCalculator.getAzimuth()) };
//	}
//
//}
