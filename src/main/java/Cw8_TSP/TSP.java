package Cw8_TSP;

import utils.Vector;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class TSP
{

    private static final double T0 = 10;
    private static final int L = 3;
    private static final int M = 10;
    private final double[][] citiesA = new double[10][10];
    private final double[][] citiesB = new double[10][10];


    private double[][] d;
    private int[] S;
    private double T;


    private TSP(){
        generateCitiesA();
        generateCitiesB();
    }

    private void generateCitiesA()
    {
        for(int a = 0; a < 10; a++)
        {
            for(int b = 0; b < 10; b++)
            {
                if((a == 0 && b == 9) || (a == 9 && b == 0)){
                    citiesA[a][b] = 1;
                }
                else{
                    citiesA[a][b] = Math.abs(a-b);
                }
            }
        }
    }

    private void generateCitiesB()
    {
        for(int a = 0; a < 10; a++)
        {
            for(int b = 0; b < 10; b++)
            {
                if((a == 0 && b == 1) || (a == 8 && b == 9)){
                    citiesB[a][b] = 1;
                }
                else if(a < b){
                    citiesB[a][b] = pow(a, 3) + pow(b, 3)
                            - pow(a, 2)*(b+1) - pow(b, 2)*(a+1)
                            + 4 * pow(a, 2) - pow(b, 2)
                            + 4*a+ 4*b;
                }
                else if(a > b){
                    citiesB[a][b] = citiesB[b][a];
                }
            }
        }
    }

    public void solve(double[][] cities){
        d = cities;
        S = Vector.randomVectorOfDifferentInts(10);
        T = T0;

        int t = 0;
        int acceptedSolutions;

        do
        {
            acceptedSolutions = 0;

            for(int i = 0; i < M; i++)
            {
                int[] Sprim = generateNewS();
                double dE = calculateE(Sprim) - calculateE(S);

                if(isAcceptable(dE)){
                    S = Sprim;
                    acceptedSolutions++;
                }
            }

            T = T0 / (1 + Math.log(++t));

        }while(acceptedSolutions > L);

        System.out.println("S=" + Arrays.toString(S));
        System.out.println("E=" + calculateE(S));
    }

    private boolean isAcceptable(double dE)
    {
        double random = ThreadLocalRandom.current().nextDouble(0,1);
        return dE < 0 || random < pow(Math.E, -dE/T);
    }

    private int[] generateNewS()
    {
        int[] Sprim = Arrays.copyOf(S, S.length);

        int k = ThreadLocalRandom.current().nextInt(10);
        int l = ThreadLocalRandom.current().nextInt(10);

        while(k == l){
            k = ThreadLocalRandom.current().nextInt(10);
        }

        Sprim[k] = S[l];
        Sprim[l] = S[k];

        return Sprim;
    }

    private double calculateE(int[] S){
        double E = 0;
        for(int i = 0; i < d.length-1; i++)
        {
            E += d[S[i]][S[i+1]];
        }
        E += d[S[0]][S[d.length-1]];
        return E;
    }

    public static void main(String[] args)
    {

        TSP app = new TSP();
        System.out.println("Zadanie 1:");
        app.solve(app.citiesA);

        app = new TSP();
        System.out.println("Zadanie 2:");
        app.solve(app.citiesB);
    }

}
