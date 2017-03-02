package utils;

import java.util.Arrays;

public class Vector {

    public static double[] ones(int length) {
        double[] array = new double[length];
        Arrays.fill(array, 1.0);
        return array;
    }

    public static double[][] concat(double[][] a, double[][] b) {
        int aLen = a.length;
        int bLen = b.length;
        double[][] c = new double[aLen+bLen][a[0].length];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}
