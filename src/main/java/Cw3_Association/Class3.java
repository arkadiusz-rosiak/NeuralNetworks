package Cw3_Association;

import java.util.Arrays;

public class Class3 {

    private static double[] sgn(double x[]){

        double out[] = new double[x.length];

        for(int i = 0; i < out.length; ++i){
            out[i] = (x[i] >= 0) ? +1 : -1;
        }

        return out;
    }


    private static double[] getClearVector(){
        double[] out = new double[25];
        Arrays.fill(out, -1.0);
        return out;
    }

    private static double[][] calculateW(double[] w, double[] u){

        double[][] out = new double[25][25];

        for(int i =0; i<25; ++i){
            out[i] = getClearVector();
        }

        for(int i =0; i<25; ++i){
            for(int j=0; j<25; j++){
                out[i][j] = 1.0/25.0 * (w[i] * w[j] + u[i] * u[j]);
            }
        }


        return out;
    }

    public static void printW(double[][] W){
        for(double[] v : W){
            System.out.println(Arrays.toString(v));
        }
    }

    private static double[] calculateF(double[][] W, double[] u){
        double[] out = new double[25];

        for(int i=0; i<25; ++i){
            double tmp = 0;

            for(int j=0; j<25; ++j){
                tmp += W[i][j] * u[j];
            }

            out[i] = tmp;
        }

        return sgn(out);
    }

    public static void main(String[] args){


        double[] z0 = {
                -1, -1, -1, -1, -1,
                -1,  1,  1,  1, -1,
                -1,  1, -1,  1, -1,
                -1,  1,  1,  1, -1,
                -1, -1, -1, -1, -1
        };

        double[] z1 = {
                -1, -1, -1, -1, -1,
                -1,  1,  1, -1, -1,
                -1, -1,  1, -1, -1,
                -1, -1,  1, -1, -1,
                -1, -1, -1, -1, -1
        };

        double[] z0prim = {
                -1,  1,  1,  1, -1,
                -1,  1, -1,  1, -1,
                -1,  1, -1,  1, -1,
                -1,  1,  1,  1, -1,
                -1, -1, -1, -1, -1
        };

        double[] z1prim = {
                -1, -1,  1, -1, -1,
                -1, -1,  1, -1, -1,
                -1, -1,  1, -1, -1,
                -1, -1,  1, -1, -1,
                -1, -1,  1, -1, -1
        };

        double[][] W = calculateW(z0, z1);

        double[] f = calculateF(W, z0);
        interfacePrint(f);

        System.out.print("\n");

        f = calculateF(W, z1);
        interfacePrint(f);

        System.out.print("\n");

        f = calculateF(W, z0prim);
        interfacePrint(f);

        System.out.print("\n");

        f = calculateF(W, z1prim);
        interfacePrint(f);

    }

    private static void interfacePrint(double[] vec){

        for (int i=0; i<25; i++) {
            if (vec[i] == -1.0) {
                System.out.print(" ");
            } else {
                System.out.print("*");
            }

            if (i%5 == 4) {
                System.out.print("\n");
            }
        }

    }

}

