package ph.edu.dlsu.advstat.zscore;

/**
 * Created by ryana on 9/16/2016.
 */
public class ChiSquare {
    private static double gamma = 0;

    public static double computeP(double xsq,double df) {
        if( Math.abs(df - 1) < 0.0001) {
            double h = 0.001;
            double area = 0;
            for(double i = xsq; i + h - 100 <= 0.00001; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x4 = i + h;
                double dist = (x4 - x0) / 4.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double x3 = x2 + dist;
                double temp = 2 * dist / 45 *
                        (7 * computeDensity(x0,df) + 32 * computeDensity(x1,df) +
                                12 * computeDensity(x2,df) + 32 * computeDensity(x3,df)
                                + 7 * computeDensity(x4,df));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        }
        gamma = 0;
        int partitions = (int)(xsq / (Math.abs(df - 1) < 0.0001 ? 0.000001 : 0.001));
        double h = xsq / partitions;
        double area = 1.0;
        for(double i = 0; i + h - xsq <= 0.00001; i += h) {
            // System.out.println("x0 = " + i);
            double x0 = i;
            double x4 = i + h;
            double dist = (x4 - x0) / 4.0;
            // System.out.println("dist = " + dist);
            double x1 = x0 + dist;
            double x2 = x1 + dist;
            double x3 = x2 + dist;
            double temp = 2 * dist / 45 *
                    (7 * computeDensity(x0,df) + 32 * computeDensity(x1,df) +
                            12 * computeDensity(x2,df) + 32 * computeDensity(x3,df)
                            + 7 * computeDensity(x4,df));
            // System.out.println(temp);
            area -= temp;
        }
        return area;
    }

    public static double computeX2(double pValue,double df) {
        if( Math.abs(df - 1) < 0.0001) {
            double h = 0.001;
            double area = 0;
            double runningDist = Math.abs(pValue - area);
            for(double i = 100 - h; i >= -0.00001; i -= h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x3 = i + h;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3 * dist / 8 *
                        (computeDensity(x0,df) + 3 * computeDensity(x1,df) +
                                3 * computeDensity(x2,df) + computeDensity(x3,df));
                // System.out.println(temp);
                area += temp;
                double currDist = Math.abs(area - pValue);
                if(currDist > runningDist) {
                    return x3;
                } else {
                    runningDist = currDist;
                }
            }
            return area;
        }
        gamma = 0;
        double h = Math.abs(df - 1) < 0.0001 ? 0.000001 : 0.001;
        double area = 1.0;
        double runningDist = Math.abs(pValue - area);
        for(double i = 0; true; i += h) {
            // System.out.println("x0 = " + i);
            double x0 = i;
            double x3 = i + h;
            double dist = (x3 - x0) / 3.0;
            // System.out.println("dist = " + dist);
            double x1 = x0 + dist;
            double x2 = x1 + dist;
            double temp = 0.375 * dist *
                    (computeDensity(x0,df) + 3 * (computeDensity(x1,df) +
                            computeDensity(x2,df)) + computeDensity(x3,df));
            // System.out.println(temp);
            area -= temp;
            if( Math.abs(pValue - area) > runningDist) {
                return x0;
            } else {
                runningDist = Math.abs(pValue - area);
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
        if(t > 0.00001 ) {
            if( Math.abs(df - 1) < 0.0001) {
                // System.out.println("Returning p(" + t + ";" + df + ") = " +
                // 					(Math.exp(-t / 2.0) / Math.sqrt(2.0) / Math.sqrt(t) / Math.sqrt(Math.PI)));
                return Math.exp(-t / 2.0) / Math.sqrt(2.0 * t * Math.PI);
            } else if ( Math.abs(df - 2) < 0.0001) {
                // if ( Math.abs(df - 2) < 0.0001) {
                return Math.exp(-t / 2.0) / 2.0;
            }

            if( Math.abs(gamma) < 0.0001 ) {
                gamma = gamma(df / 2.0);
            }

            return (Math.pow(t,df / 2.0 - 1) * Math.exp(-t / 2.0)) /
                    (Math.pow(2.0,df / 2) * gamma);
        } else {
            return 0;
        }
    }
}
