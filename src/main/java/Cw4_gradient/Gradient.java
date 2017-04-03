package Cw4_gradient;

import utils.Derivate;

import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public class Gradient {

    private static final double C = 0.01;
    private static final double EPS = 0.000001;

    private double[] calculateXNewFirst(double[] xOld, double[] input){

        double[] xNew = new double[xOld.length];

        xNew[0] = xOld[0] - C * Derivate.derivateFirstX(input);
        xNew[1] = xOld[1] - C * Derivate.derviateFirstY(input);
        xNew[2] = xOld[2] - C * Derivate.derviateFirstZ(input);
        return xNew;
    }


    public static void main(String[] args){

        Gradient app = new Gradient();

        double[] input1 = new double[] { 0.5, 0.5, 0.5 };
        double[] result = app.calculateFirst(input1);
        System.out.println("x new = " + Arrays.toString(result) + " f(x,y,z) = " + app.valueFirst(result));



    }

    public double[] calculateFirst(double[] input){
        double[] xOld = new double[]{ 0, 0, 0};
        double[] xNew = calculateXNewFirst(xOld, input);

        while(normalize(vectorsDifference(xNew, xOld)) >= EPS){
            xOld = xNew;
            xNew = calculateXNewFirst(xOld, xNew);
        }

        return xNew;
    }



    private double[] vectorsDifference(double[] xNew, double[] xOld)
    {
        double[] result = new double[xNew.length];

        for(int i = 0; i < xNew.length; ++i){
            result[i] = xOld[i] - xNew[i];
        }

        return result;
    }

    private double normalize(double[] vector)
    {
        double sum = 0;

        for(double el : vector){
            sum += el*el;
        }

        return Math.sqrt(sum);
    }

    public double valueFirst(double[] input){
        return 2 * Math.pow(input[0],2) + 2* Math.pow(input[1],2) + Math.pow(input[2],2)
                - 2*input[0]*input[1] - 2*input[1]*input[2] - 2*input[0] + 3;
    }


}
