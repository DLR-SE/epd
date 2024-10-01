package de.emir.model.universal.crs.internal.calc;

import net.jafama.FastMath;

import static java.lang.Math.min;

public class VincentCalculator3D extends VincentCalculator{
    public double getDistance(double latA, double lonA, double latB, double lonB, double hight) {
        // Calculate via vincenty Formula at depth blow sea level
        return variableHightVincentyFormula(latA, lonA, latB, lonB, hight, VincentyCalculationType.DISTANCE) [0];
    }

    public double getDistance(double latA, double lonA, double hightA, double latB, double lonB,
                              double hightB) {
        // Assuming a constant pitch in depth between two points this method is underestimating the distance.
        // This will ensure the triangle inequality as well as symmetry condition for being a metric space.
        // None negativity will be guaranteed by using the positive square root
        // https://de.wikipedia.org/wiki/Metrischer_Raum
        double hight = min(hightA, hightB); // use lower hight for shorter 2D distance.
        double twoDimensionalDistance = variableHightVincentyFormula(latA, lonA, latB, lonB, hight, VincentyCalculationType.DISTANCE) [0];
        return Math.sqrt(Math.pow(twoDimensionalDistance, 2) + Math.pow((hightA - hightB), 2));
    }

    private final static double VINCENTY_A = 6378137.0; // used in vincenty formula
    private final static double VINCENTY_F = 1.0 / 298.257223563; // // used in vincenty formula | WGS-84 ellipsoid

    /**
     * Use Vincenty Formulae for Calculation of distance and/or bearing between point A and point B
     * @param latitudeA Latitude coordinate of point A
     * @param longitudeA Longitude coordinate of point A
     * @param latitudeB Latitude coordinate of point B
     * @param longitudeB longitude coordinate of point B
     * @param hightOffset hight offset from sea level in meters
     * @param type Type of resulting calculation
     * @return [distance, bearing] may be NaN b´depending on Type of calculation
     */
    static double[] variableHightVincentyFormula(double latitudeA, double longitudeA, double latitudeB, double longitudeB,
                                                 double hightOffset, VincentyCalculationType type) {
        // mostly copied from VincentyCalculator.java
        if (FastMath.abs(latitudeA - latitudeB) < 1e-12 && FastMath.abs(longitudeA - longitudeB) < 1e-12)
            return new double[]{0,0}; // both points are the same so distance is 0

        double L = Math.toRadians(longitudeB - longitudeA);
        double U_A = FastMath.atan((1 - VINCENTY_F) * Math.tan(Math.toRadians(latitudeA)));
        double U_B = FastMath.atan((1 - VINCENTY_F) * Math.tan(Math.toRadians(latitudeB)));
        double sinU_A = FastMath.sin(U_A), cosU_A = FastMath.cos(U_A);
        double sinU_B = FastMath.sin(U_B), cosU_B = FastMath.cos(U_B);

        double lambda = L;
        double lambdaP = 2*Math.PI;
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
            sinSigma = FastMath.sqrt((cosU_B * sinLambda) * (cosU_B * sinLambda)
                    + (cosU_A * sinU_B - sinU_A * cosU_B * cosLambda) * (cosU_A * sinU_B - sinU_A * cosU_B * cosLambda));
            cosSigma = sinU_A * sinU_B + cosU_A * cosU_B * cosLambda;
            sigma = FastMath.atan2(sinSigma, cosSigma);
            sinAlpha = cosU_A * cosU_B * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;
            cos2SigmaM = cosSigma - 2 * sinU_A * sinU_B / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0; // equatorial line
            }
            C = VINCENTY_F / 16 * cosSqAlpha * (4 + VINCENTY_F * (4 - 3 * cosSqAlpha));
            lambdaP = lambda;
            lambda = L + (1 - C) * VINCENTY_F * sinAlpha
                    * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
        }
        if (iterLimit == 0) {
            return null; //Double.NaN; // formula failed to converge
        }

        double distance = Double.NaN;
        double a = VINCENTY_A + hightOffset;
        double b = a * (1 - VINCENTY_F);

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
            double fwdAz =FastMath.atan2(cosU_B * sinLambda, cosU_A * sinU_B - sinU_A * cosU_B * cosLambda);
            return new double[]{fwdAz};
        }
        // final bearing
        double bearing = FastMath.atan2(cosU_A * sinLambda, -sinU_A * cosU_B + cosU_A * sinU_B * cosLambda);
        if (type == VincentyCalculationType.FINAL_BEARING)
            return new double[]{bearing};
        return new double[]{distance, bearing};
    }
}
