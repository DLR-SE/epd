package de.emir.model.universal.crs.internal.calc;

public interface IGeodeticCalculator {

	
	double getAzimuth(double lat1, double lon1, double lat2, double lon2);
	double getDistance(double lat1, double lon1, double lat2, double lon2);
	
	/**
	 * calculates the distance and azimuth in one run and return both in an array
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return array, containing distance and azimuth [distance, azimuth]
	 */
	double[] getDistanceAndAzimuth(double lat1, double lon1, double lat2, double lon2);
	
}
