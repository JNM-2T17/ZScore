package ph.edu.dlsu.advstat.zscore;

/**
 * Created by ryana on 9/16/2016.
 */
public class ZScore {
    public static double computeP(double z) {
        if( Math.abs(z) < 0.0001) {
            return 0.5;
        } else if( z < 0 ) {
            int partitions = (int)(-z / 0.01);
            double h = z / partitions;
            double area = 0.5;
            for(double i = h; i - z >= -0.00001; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i - h;
                double x3 = i;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3.0 * dist / 8.0 *
                        (computeDensity(x0) + 3 * computeDensity(x1) +
                                3 * computeDensity(x2) + computeDensity(x3));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        } else {
            int partitions = (int)(z / 0.01);
            double h = z / partitions;
            double area = 0.5;
            for(double i = 0; i - z + h <= 0.00001; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x3 = i + h;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3.0 * dist / 8.0 *
                        (computeDensity(x0) + 3 * computeDensity(x1) +
                                3 * computeDensity(x2) + computeDensity(x3));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        }
    }

    public static double computeZ(double pValue) {
        if( Math.abs(pValue - 0.5) < 0.00001) {
            return 0;
        } else if( pValue < 0.5) {
            double h = -0.0001;
            double area = 0.5;
            double runningDist = Math.abs(area - pValue);
            for(double i = h; true; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i - h;
                double x3 = i;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3.0 * dist / 8.0 *
                        (computeDensity(x0) + 3 * computeDensity(x1) +
                                3 * computeDensity(x2) + computeDensity(x3));
                // System.out.println(temp);
                area += temp;
                if( Math.abs(area - pValue) > runningDist ) {
                    return x0;
                } else {
                    runningDist = Math.abs(area - pValue);
                }
            }
        } else {
            double h = 0.0001;
            double area = 0.5;
            double runningDist = Math.abs(area - pValue);
            for(double i = 0; true; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x3 = i + h;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3.0 * dist / 8.0 *
                        (computeDensity(x0) + 3 * computeDensity(x1) +
                                3 * computeDensity(x2) + computeDensity(x3));
                // System.out.println(temp);
                area += temp;
                if( Math.abs(area - pValue) > runningDist ) {
                    return x0;
                } else {
                    runningDist = Math.abs(area - pValue);
                }
            }
        }
    }

    private static double computeDensity(double z) {
        double cons = 1/Math.sqrt(2 * Math.PI);
        double arg = -0.5 * Math.pow(z,2);
        return cons * Math.exp(arg);
    }
}
