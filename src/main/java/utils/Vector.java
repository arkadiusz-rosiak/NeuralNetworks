package utils;

import java.util.Arrays;

public class Vector
{


    public static double[] ones(int length)
    {
        double[] array = new double[length];
        Arrays.fill(array, 1.0);
        return array;
    }

    public static double[][] concat(double[][] a, double[][] b)
    {
        int aLen = a.length;
        int bLen = b.length;
        double[][] c = new double[aLen + bLen][a[0].length];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public static double normalize(double[] vector)
    {
        double sum = 0;

        for(double el : vector)
        {
            sum += el * el;
        }

        return Math.sqrt(sum);
    }

    public static double normalize(double[][] vector)
    {
        double sum = 0;

        for(double[] vector2 : vector)
        {
            for(double el : vector2)
            {
                sum += el * el;
            }
        }

        return Math.sqrt(sum);
    }

    public static double[] vectorsDifference(double[] xNew, double[] xOld)
    {
        double[] result = new double[xNew.length];

        for(int i = 0; i < xNew.length; ++i)
        {
            result[i] =  Math.abs(xOld[i] - xNew[i]);
        }

        return result;
    }

    public static double[][] vectorsDifference(double[][] xNew, double[][] xOld)
    {
        double[][] result = new double[xNew.length][xNew[0].length];

        for(int i = 0; i < xNew.length; ++i)
        {
            for(int j = 0; j < xNew[i].length; ++j)
            {
                result[i][j] = Math.abs(xOld[i][j] - xNew[i][j]);
            }
        }

        return result;
    }

    public static double getMaxElem(double[] vector)
    {
        double max = vector[0];

        for(double el : vector){
            max = (el > max) ? el : max;
        }

        return max;
    }

    public static double getMaxElem(double[][] vector)
    {
        double max = vector[0][0];

        for(double[] insideVector : vector){
            for(double el : insideVector)
            {
                max = (el > max) ? el : max;
            }
        }

        return max;
    }

    public static double[] bias(int length){
        double[] vec = new double[length];
        vec[length-1] = 1;
        return vec;
    }
}
