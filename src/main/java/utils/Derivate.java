package utils;

/**
 * Pochodne do zadania 4
 */

public class Derivate
{

    public static double derivateFirstX (double[] vec) {
        return 4.0*vec[0] - 2.0* vec[1] - 2.0;
    }

    public static double derviateFirstY (double[] vec) {
        return 4.0*vec[1] - 2.0* vec[0] - 2.0*vec[2];
    }

    public static double derviateFirstZ (double[] vec) {
        return 2.0* vec[2] - 2.0*vec[1];
    }

    public static double derviateSecondX (double[] vec) {
        return 12 * Math.pow(vec[0], 3.0) + 12 * Math.pow(vec[0], 2.0) - 24 * vec[0];
    }

    public static double derviateSecondY (double[]  vec) {
        return 24 * vec[1] - 24;
    }

}
