package Cw6_HopfieldNet;

import utils.Vector;

import java.util.Scanner;

public class Hopfield
{

    private static final int N = 25;

    private final double[] xs = {
            0, 0, 0, 0, 0,
            0, 1, 1, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0
    };

    private double[] x = Vector.randomZeroOneVector(N);

    private double[][] c = new double[N][N];

    private double[] theta = new double[N];

    private double[][] w = new double[N][N];

    public Hopfield()
    {
        initializeC();
        initializeTheta();
        initializeW();
    }

    private void initializeW()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                w[i][j] = 2 * c[i][j];
            }
        }
    }

    private void initializeTheta()
    {
        for(int i = 0; i < N; i++)
        {
            theta[i] = 0.0;
            for(int j = 0; j < N; j++)
            {
                theta[i] += c[i][j];
            }
        }
    }

    private void initializeC()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(i == j)
                {
                    c[i][j] = 0;
                }
                else
                {
                    c[i][j] = (xs[i] - 0.5) * (xs[j] - 0.5);
                }
            }
        }
    }

    private double u(int i)
    {
        double ui = 0;

        for(int j = 0; j < N; j++)
        {
            ui = w[i][j] * x[j];
        }

        return ui - theta[i];
    }

    private void calculateNewX()
    {
        for(int i = 0; i < N; i++)
        {
            if(u(i) > 0)
            {
                x[i] = 1;
            }
            else if(u(i) < 0)
            {
                x[i] = 0;
            }
        }
    }

    private void printX()
    {

        for(int i = 0; i < N; i++)
        {

            if(x[i] == 1)
            {
                System.out.print('■');
            }
            else
            {
                System.out.print('□');
            }

            if(i % 5 == 4)
            {
                System.out.println();
            }
        }
    }

    public static void main(String[] args)
    {

        Hopfield app = new Hopfield();

        Scanner scanner = new Scanner(System.in);

        app.printX();

        while(true)
        {
            scanner.nextLine();
            app.calculateNewX();
            app.printX();
        }


    }
}
