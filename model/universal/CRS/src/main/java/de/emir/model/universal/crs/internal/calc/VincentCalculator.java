package de.emir.model.universal.crs.internal.calc;

import net.jafama.FastMath;

public class VincentCalculator implements IGeodeticCalculator {

	@Override
	public double getAzimuth(double lat1, double lon1, double lat2, double lon2) {
		return vincentyFormula(lat1, lon1, lat2, lon2, VincentyCalculationType.FINAL_BEARING)[0];
	}

	@Override
	public double getDistance(double lat1, double lon1, double lat2, double lon2) {
		if (lat1 == lat2 && lon1 == lon2) return 0; //vincentyFormula throws an exception in this case
		return vincentyFormula(lat1, lon1, lat2, lon2, VincentyCalculationType.DISTANCE)[0];
	}

	@Override
	public double[] getDistanceAndAzimuth(double lat1, double lon1, double lat2, double lon2) {
		return vincentyFormula(lat1, lon1, lat2, lon2, VincentyCalculationType.DISTANCE_BEARING);
	}
	
	
	
	static double[] vincentyFormula(double latitude1, double longitude1, double latitude2, double longitude2,
            VincentyCalculationType type) {
		if (latitude1 == latitude2 && longitude1 == longitude2)
			return new double[]{0,0};
        double a = 6378137.0;
        double b = 6356752.3142;
        double f = 1.0 / 298.257223563; // WGS-84 ellipsiod
        double L = Math.toRadians(longitude2 - longitude1);
        double U1 = FastMath.atan((1 - f) * Math.tan(Math.toRadians(latitude1)));
        double U2 = FastMath.atan((1 - f) * Math.tan(Math.toRadians(latitude2)));
        double sinU1 = FastMath.sin(U1), cosU1 = FastMath.cos(U1);
        double sinU2 = FastMath.sin(U2), cosU2 = FastMath.cos(U2);

        double lambda = L;
        double lambdaP = 2 * Math.PI;
        double iterLimit = 20;
        double sinLambda = 0;
        double cosLambda = 0;
        double sinSigma = 0;
        double cosSigma = 0;
        double sigma = 0;
        double sinAlpha = 0;
        double cosSqAlpha = 0;
        double cos2SigmaM = 0;
        double C;
        while (FastMath.abs(lambda - lambdaP) > 1e-12 && --iterLimit > 0) {
            sinLambda = FastMath.sin(lambda);
            cosLambda = FastMath.cos(lambda);
            sinSigma = FastMath.sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda)
                    + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
            if (sinSigma == 0) {
                return new double[]{0,0}; //0; // co-incident points
            }
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = FastMath.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0; // equatorial line
            }
            C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
            lambdaP = lambda;
            lambda = L + (1 - C) * f * sinAlpha
                    * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
        }
        if (iterLimit == 0) {
            return null; //Double.NaN; // formula failed to converge
        }

        double distance = Double.NaN;
        if (type == VincentyCalculationType.DISTANCE || type == VincentyCalculationType.DISTANCE_BEARING) {
        	double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
            double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
            double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        	double deltaSigma = B
                    * sinSigma
                    * (cos2SigmaM + B
                            / 4
                            * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM
                                    * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
        	distance = b * A * (sigma - deltaSigma);
        	if (type == VincentyCalculationType.DISTANCE)
        		return new double[]{distance};
        }
        // initial bearing
        if (type == VincentyCalculationType.INITIAL_BEARING) {
        	double fwdAz =FastMath.atan2(cosU2 * sinLambda, cosU1 * sinU2 - sinU1 * cosU2 * cosLambda);
            return new double[]{fwdAz};
        }
        // final bearing
        double bearing = FastMath.atan2(cosU1 * sinLambda, -sinU1 * cosU2 + cosU1 * sinU2 * cosLambda);
        if (type == VincentyCalculationType.FINAL_BEARING)
        	return new double[]{bearing};
        return new double[]{distance, bearing};
    }

    static enum VincentyCalculationType {
        DISTANCE,
        FINAL_BEARING,
        INITIAL_BEARING,
        DISTANCE_BEARING
    }


}
