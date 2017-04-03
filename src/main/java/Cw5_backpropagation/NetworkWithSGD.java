package Cw5_backpropagation;

import utils.Vector;

import java.util.Arrays;

public class NetworkWithSGD
{

    private static final double C = 0.5;
    private static final double EPS = 0.000001;
    private static final double BETA = 3.00;

    private double[][] x = new double[4][3];

    private double[][] w = {
            {0, 1, 2},
            {0, 1, 2}
    };

    private double[] s = {0, 1, 2};

    private double[] z = {0, 1, 1, 0};
    private double[][] u = {
            {0, 0, 1},
            {1, 0, 1},
            {0, 1, 1},
            {1, 1, 1}
    };

    public static void main(String[] args)
    {
        NetworkWithSGD network = new NetworkWithSGD();
        network.train();
    }

    public void train()
    {
        double sNorm;
        double wMax;

        int epoch = 0;

        do
        {
            ++epoch;

            x = calculateNewX();
            double[] sNew = calculateNewS();
            double[][] wNew = calculateNewW();

            sNorm = Vector.normalize(Vector.vectorsDifference(s, sNew));
            wMax = Vector.normalize(Vector.vectorsDifference(w, wNew));

            s = sNew;
            w = wNew;
        } while(Math.max(sNorm, wMax) >= EPS);


        System.out.println("#Iterations: " + epoch);
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("w[0] = " + Arrays.toString(w[0]));
        System.out.println("w[1] = " + Arrays.toString(w[1]));

        for(int p = 0; p < 4; ++p)
        {
            System.out.printf("y[%d] = %.5f%n", p,  y(p));
        }

    }

    private double[][] calculateNewW()
    {

        double[][] wNew = new double[w.length][3];


        for(int i = 0; i < 2; ++i)
        {
            for(int j = 0; j < 3; ++j)
            {
                wNew[i][j] = w[i][j] - C * derivativeW(i, j);
            }
        }

        return wNew;
    }

    private double derivativeW(int i, int j)
    {
        double sum = 0.0;

        for(int p = 0; p < 4; ++p)
        {

            sum += (y(p) - z[p]) * derivativeF(s[0] * x[p][0] + s[1] * x[p][1] + s[2] * x[p][2])
                    * s[i] * derivativeF(w[i][0] * u[p][0] + w[i][1] * u[p][1] + w[i][2] * u[p][2]) * u[p][j];

        }

        return sum;
    }

    private double[] calculateNewS()
    {
        double[] sNew = new double[s.length];

        for(int i = 0; i < s.length; ++i)
        {
            sNew[i] = s[i] - C * derivativeS(i);
        }

        return sNew;
    }

    private double derivativeS(int i)
    {
        double sum = 0.0;

        for(int p = 0; p < 4; ++p)
        {
            sum += (y(p) - z[p]) * derivativeF(s[0] * x[p][0] + s[1] * x[p][1] + s[2] * x[p][2]) * x[p][i];
        }

        return sum;
    }

    private double f(double u)
    {
        return 1.0 / (1.0 + Math.exp(-u * BETA));
    }

    private double y(int p)
    {
        return f(s[0] * x[p][0] + s[1] * x[p][1] + s[2] * x[p][2]);
    }

    private double derivativeF(double u)
    {
        return BETA * f(u) * (1 - f(u));
    }

    private double[][] calculateNewX()
    {
        double[][] newX = new double[4][3];

        for(int p = 0; p < 4; ++p)
        {
            newX[p][0] = f(w[0][0] * u[p][0] + w[0][1] * u[p][1] + w[0][2] * u[p][2]);
            newX[p][1] = f(w[1][0] * u[p][0] + w[1][1] * u[p][1] + w[1][2] * u[p][2]);
            newX[p][2] = 1;
        }

        return newX;
    }

}
