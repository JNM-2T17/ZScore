package ph.edu.dlsu.advstat.zscore;

/**
 * Created by ryana on 9/16/2016.
 */
public class FScore {
    private static boolean betaOK = false;
    private static double beta = 0;

    public static double computeP(double f,double df, double df2) {
        if( Math.abs(df - 1) < 0.0001 || Math.abs(df2 - 1) < 0.00001) {
            double h = 0.01;
            double area = 0;
            for(double i = f; i + h - 7000 <= 0.00001; i += h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x3 = i + h;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3 * dist / 8 *
                        (computeDensity(x0,df,df2) + 3 * computeDensity(x1,df,df2) +
                                3 * computeDensity(x2,df,df2) + computeDensity(x3,df,df2));
                // System.out.println(temp);
                area += temp;
            }
            return area;
        }
        betaOK = false;
        int partitions = (int)(f / 0.001);
        double h = f / partitions;
        double area = 1.0;
        for(double i = 0; i + h - f <= 0.00001; i += h) {
            System.out.println("x0 = " + i);
            double x0 = i;
            double x3 = i + h;
            double dist = (x3 - x0) / 3.0;
            // System.out.println("dist = " + dist);
            double x1 = x0 + dist;
            double x2 = x1 + dist;
            double temp = 3 * dist / 8 *
                    (computeDensity(x0,df,df2) + 3 * computeDensity(x1,df,df2) +
                            3 * computeDensity(x2,df,df2) + computeDensity(x3,df,df2));
            // System.out.println(temp);
            area -= temp;
        }
        return area;
    }

    public static double computeF(double pValue,double df,double df2) {
        if( Math.abs(df - 1) < 0.0001 || Math.abs(df2 - 1) < 0.00001) {
            double h = 0.01;
            double area = 0;
            double runningDist = Math.abs(pValue - area);
            for(double i = 7000 - h; i >= -0.00001; i -= h) {
                // System.out.println("x0 = " + i);
                double x0 = i;
                double x3 = i + h;
                double dist = (x3 - x0) / 3.0;
                // System.out.println("dist = " + dist);
                double x1 = x0 + dist;
                double x2 = x1 + dist;
                double temp = 3 * dist / 8 *
                        (computeDensity(x0,df,df2) + 3 * computeDensity(x1,df,df2) +
                                3 * computeDensity(x2,df,df2) + computeDensity(x3,df,df2));
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
        betaOK = false;
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
            double temp = 3.0 * dist / 8.0 *
                    (computeDensity(x0,df,df2) + 3 * (computeDensity(x1,df,df2) +
                            computeDensity(x2,df,df2)) + computeDensity(x3,df,df2));
            // System.out.println(temp);
            area -= temp;
            if( Math.abs(pValue - area) > runningDist) {
                return x0;
            } else {
                runningDist = Math.abs(pValue - area);
            }
        }
    }

    private static double betaPrime(double x,double a,double b) {
        if( Math.abs(x) < 0.00001) {
            x = 1e-5;
        }
        if( Math.abs(x - 1) < 0.0001 ) {
            x = 1 - 1e-5;
        }
        return Math.pow(x,a - 1) * Math.pow(1 - x, b - 1);
    }

    private static double beta(double a,double b) {
        double h = 1e-4;
        double area = 0;
        for(double i = 0; i + h - 1 <= 0.00001; i += h) {
            // System.out.println("x0 = " + i);
            double x0 = i;
            double x4 = i + h;
            double dist = (x4 - x0) / 4.0;
            // System.out.println("dist = " + dist);
            double x1 = x0 + dist;
            double x2 = x1 + dist;
            double x3 = x2 + dist;
            double temp = 2.0 * dist / 45.0 *
                    (7 * betaPrime(x0,a,b) + 32 * betaPrime(x1,a,b) +
                            12 * betaPrime(x2,a,b) + 32 * betaPrime(x3,a,b) +
                            7 * betaPrime(x4,a,b));
            area += temp;
            // System.out.println(temp + "; total = " + area);

        }
        // System.out.println("beta(" + z + ") = " + area);
        return area;
    }

    private static double computeDensity(double x,double d1,double d2) {
        if(x > 0.00001 ) {
            if(!betaOK) {
                beta = beta(d1/2.0,d2/2.0);
                betaOK = true;
            }
            return Math.sqrt(Math.pow(d1 * x,d1) * Math.pow(d2,d2) /
                    Math.pow(d1 * x + d2,d1 + d2)) /
                    (x * beta);
        } else {
            return 0;
        }
    }
}
