package ph.edu.dlsu.advstat.zscore;

/**
 * Created by ryana on 9/16/2016.
 */
public class TScore {
    private static double gamma1 = 0;
    private static double gamma2 = 0;

    public static double computeP(double z,double df) {
        gamma1 = gamma2 = 0;
        if( Math.abs(z) < 0.0001) {
            return 0.5;
        } else if( z < 0 ) {
            int partitions = (int)(-z / 0.001);
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
                        (computeDensity(x0,df) + 3 * computeDensity(x1,df) +
                                3 * computeDensity(x2,df) + computeDensity(x3,df));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        } else {
            int partitions = (int)(z / 0.001);
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
                        (computeDensity(x0,df) + 3 * computeDensity(x1,df) +
                                3 * computeDensity(x2,df) + computeDensity(x3,df));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        }
    }

    public static double computeT(double pValue,double df) {
        gamma1 = gamma2 = 0;
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
                        (computeDensity(x0,df) + 3 * computeDensity(x1,df) +
                                3 * computeDensity(x2,df) + computeDensity(x3,df));
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
                        (computeDensity(x0,df) + 3 * computeDensity(x1,df) +
                                3 * computeDensity(x2,df) + computeDensity(x3,df));
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

    private static double gammaPrime(double x,double t) {
        if( Math.abs(x) < 0.00001 ) {
            return 0;
        }
        return Math.pow(x,t - 1) * Math.exp(-x);
    }

    private static double gamma(double z) {
        double h = 0.01;
        double area = 0;
        for(double i = 0; i + h <= 100.00001; i += h) {
            // System.out.println("x0 = " + i);
            double x0 = i;
            double x3 = i + h;
            double dist = (x3 - x0) / 3.0;
            // System.out.println("dist = " + dist);
            double x1 = x0 + dist;
            double x2 = x1 + dist;
            double temp = 3.0 * dist / 8.0 *
                    (gammaPrime(x0,z) + 3 * gammaPrime(x1,z) +
                            3 * gammaPrime(x2,z) + gammaPrime(x3,z));
            area += temp;
            // System.out.println(temp + "; total = " + area);

        }
        // System.out.println("Gamma(" + z + ") = " + area);
        return area;
    }

    private static double computeDensity(double t,double df) {
        if( Math.abs(df - 1) < 0.0001) {
            return 1.0 / (Math.PI * (1 + t * t));
        } else if( Math.abs(df - 2) < 0.0001) {
            return 1.0 / Math.pow(2 + t * t, 1.5);
        } else if( Math.abs(df - 3) < 0.0001) {
            return Math.sqrt(108.0) / (Math.PI * Math.pow(3 + t * t,2));
        }

        if( Math.abs(gamma1) < 0.0001 ) {
            gamma1 = gamma((df + 1.0) / 2.0);
        }
        if( Math.abs(gamma2) < 0.0001) {
            gamma2 = gamma(df / 2.0);
        }

        return gamma1/(Math.sqrt(df * Math.PI) * gamma2) *
                Math.pow(1.0 + Math.pow(t,2.0)/df,-(df + 1.0)/2.0);
    }
}
