package de.emir.model.universal.crs.internal.calc;


/**
 * 
 * @author sschweigert
 * @deprecated This Implementation will most likly produce wrong results, use the VincentCalculator instead
 */
@Deprecated
public class CircularCalculator implements IGeodeticCalculator 
{
	
	 	static final double _eQuatorialEarthRadius = 6378.1370D;
	    static final double _d2r = (Math.PI / 180D);

	    public static double HaversineInM(double lat1, double long1, double lat2, double long2) {
	        return (1000D * HaversineInKM(lat1, long1, lat2, long2));
	    }

	    public static double HaversineInKM(double lat1, double long1, double lat2, double long2) {
	        double dlong = (long2 - long1) * _d2r;
	        double dlat = (lat2 - lat1) * _d2r;
	        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
	                * Math.pow(Math.sin(dlong / 2D), 2D);
	        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));

            return _eQuatorialEarthRadius * c;
	    }

	public static double mod(double x, double y){
		if (x == 0) 
			return x;
		double mod=y - x * (int)y/x;
		if ( mod < 0) mod = mod + x;
		return mod;
	}
	@Override
	public double getAzimuth(double _lat1, double _lon1, double _lat2, double _lon2) {
		double lat1 = _lat1 * _d2r;
		double lat2 = _lat2 * _d2r;
		double lon1 = _lon1 * _d2r;
		double lon2 = _lon2 * _d2r;
        return mod(Math.atan2(Math.sin(lon1-lon2)*Math.cos(lat2),Math.cos(lat1)*Math.sin(lat2)-Math.sin(lat1)*Math.cos(lat2)*Math.cos(lon1-lon2)), 2*Math.PI);
	}

	@Override
	public double getDistance(double lat1, double lon1, double lat2, double lon2) {
		return HaversineInM(lat1, lon1, lat2, lon2);
	}

	@Override
	public double[] getDistanceAndAzimuth(double lat1, double lon1, double lat2, double lon2) {
		return new double[]{getDistance(lat1, lon1, lat2, lon2), getAzimuth(lat1, lon1, lat2, lon2)};
	}

}
