package Cw6_Autoencoder;

public class Autoencoder
{
    private static final double C = 0.45;
    private static final double BETA = 1.3;
    private static final double EPS = 0.0001;

    private double[][] x = {
        {
            0, 0, 0, 0, 0,
            0, 1, 1, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 1, 0, 0,
        }, {
            0, 0, 1, 1, 0,
            0, 0, 0, 1, 0,
            0, 0, 0, 1, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
        }, {
            0, 0, 0, 0, 0,
            1, 1, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 1, 0, 0, 0,
        }
    };

    private double[][] xp = new double[3][25];

    private double[][] y = new double[3][16];

    private double[][] w = new double[16][25];

    private double[][] wp = new double[25][16];

    private double[] b = new double[16];

    private double[] bp = new double[25];

    public static void main(String[] args)
    {
        Autoencoder app = new Autoencoder();

        app.bp = app.calculateNewBP();
        app.wp = app.calculateNewWP();

        app.b = app.calculateNewB();
        app.w = app.calculateNewW();

        app.xp = app.calculateNewXP();
        app.y = app.calculateNewY();

        for(int eppoch = 0; eppoch < 50; eppoch++)
        {
            app.y = app.calculateNewY();
            app.b = app.calculateNewB();
            app.w = app.calculateNewW();
        }

        for(int eppoch = 0; eppoch < 50; eppoch++)
        {
            app.xp = app.calculateNewXP();
            app.bp = app.calculateNewBP();
            app.wp = app.calculateNewWP();
        }

        for(int eppoch = 0; eppoch < 50; eppoch++)
        {
            app.xp = app.calculateNewXP();
            app.bp = app.calculateNewBP();
            app.wp = app.calculateNewWP();

            app.y = app.calculateNewY();
            app.b = app.calculateNewB();
            app.w = app.calculateNewW();
        }



        double[][] xpp = app.calculateXPP();

        for(int a = 0; a < 3; a++)
        {
            System.out.println("x["+a+"] -------------------\n");
            for(int i = 0; i < xpp[a].length; i++)
            {
                if(xpp[a][i] == 1)
                {
                    System.out.print('X');
                }
                else
                {
                    System.out.print(' ');
                }

                if(i % 5 == 4)
                {
                    System.out.println();
                }
            }

            System.out.println("\n");
        }

    }

    private double[][] calculateXPP()
    {
        double[][] xpp = new double[3][25];

        for(int a = 0; a < 3; a++)
        {
            for(int k = 0; k < 25; k++)
            {
                double sum = 0.0;

                for(int i = 0; i < 16; i++)
                {
                    sum += wp[k][i] * y[a][i] + bp[k];
                }

                xpp[a][k] = f_1(sum);
            }
        }

        return xpp;
    }

    private double f_1(double u)
    {
        return (u >= 0) ? 1 : 0;
    }

    private double[][] calculateNewXP()
    {
        double[][] xpNew = new double[3][25];

        for(int a = 0; a < 3; a++)
        {
            for(int k = 0; k < 25; k++)
            {
                double sum = 0.0;

                for(int i = 0; i < 16; i++)
                {
                    sum += wp[k][i] * y[a][i] + bp[k];
                }

                xpNew[a][k] = f(sum);
            }
        }

        return xpNew;
    }

    private double[] calculateNewBP()
    {
        double[] bpNew = new double[25];

        for(int i = 0; i < 25; i++)
        {
            bpNew[i] = bp[i] - C * derivativeBP(i);
        }

        return bpNew;
    }

    private double[] calculateNewB()
    {
        double[] bNew = new double[16];

        for(int i = 0; i < 16; i++)
        {
            bNew[i] = b[i] - C * derivativeB(i);
        }

        return bNew;
    }

    private double derivativeBP(int p)
    {
        double sum = 0;

        for(int a = 0; a < 3; a++)
        {
            double subsum = 0.0;
            for(int i = 0; i < 16; i++)
            {
                subsum += wp[p][i] * y[a][i] + bp[p];
            }

            sum += (xp[a][p] - x[a][p]) * derivativeF(subsum);
        }

        return sum;
    }

    private double derivativeB(int p)
    {
        double sum = 0;

        for(int a = 0; a < 3; a++)
        {
            for(int k = 0; k < 25; k++)
            {
                double subsum1 = 0.0;
                for(int i = 0; i < 16; i++)
                {
                    subsum1 += wp[k][i] * y[a][i] + bp[k];
                }

                double subsum2 = 0.0;
                for(int j = 0; j < 25; j++)
                {
                    subsum2 += w[p][j] * x[a][j] + b[p];
                }

                sum += (xp[a][k] - x[a][k]) * derivativeF(subsum1) * wp[k][p] * derivativeF(subsum2);
            }
        }

        return sum;
    }

    private double[][] calculateNewWP()
    {
        double[][] wpNew = new double[25][16];

        for(int p = 0; p < 25; p++)
        {
            for(int q = 0; q < 16; q++)
            {
                wpNew[p][q] = wp[p][q] - C * derivativeWP(p, q);
            }
        }

        return wpNew;
    }

    private double derivativeWP(int p, int q)
    {
        double sum = 0;

        for(int a = 0; a < 3; a++)
        {
            double subsum = 0.0;
            for(int i = 0; i < 16; i++)
            {
                subsum += wp[p][i] * y[a][i] + bp[p];
            }

            sum += (xp[a][p] - x[a][p]) * derivativeF(subsum) * y[a][q];
        }


        return sum;
    }

    private double[][] calculateNewW()
    {
        double[][] wNew = new double[16][25];

        for(int p = 0; p < 16; ++p)
        {
            for(int q = 0; q < 25; ++q)
            {
                wNew[p][q] = w[p][q] - C * derivativeW(p, q);
            }
        }

        return wNew;
    }

    private double derivativeW(int p, int q)
    {
        double sum = 0.0;

        for(int a = 0; a < 3; a++)
        {
            for(int k = 0; k < 25; k++)
            {

                double subsum1 = 0.0;
                for(int i = 0; i < 16; i++)
                {
                    subsum1 += wp[k][i] * y[a][i] + bp[k];
                }

                double subsum2 = 0.0;
                for(int j = 0; j < 25; j++)
                {
                    subsum2 += w[p][j] * x[a][j] + b[p];
                }

                double diff = (xp[a][k] - x[a][k]);

                double Df1 = derivativeF(subsum1);

                double Df2 = derivativeF(subsum2);

                sum += diff * Df1 * wp[k][p] * Df2 * x[a][q];
            }
        }

        return sum;
    }

    private double f(double u)
    {
        return 1.0 / (1.0 + Math.exp(-BETA * u));
    }

    private double derivativeF(double u)
    {
        return BETA * f(u) * (1 - f(u));
    }

    private double[][] calculateNewY()
    {
        double[][] yNew = new double[3][16];

        for(int a = 0; a < 3; a++)
        {
            for(int i = 0; i < 16; i++)
            {
                double sum = 0;
                for(int j = 0; j < 25; j++)
                {
                    sum += w[i][j] * x[a][j] + b[i];
                }

                yNew[a][i] = f(sum);
            }
        }

        return yNew;
    }


}
