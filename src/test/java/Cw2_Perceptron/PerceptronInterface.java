package Cw2_Perceptron;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PerceptronInterface {

    private double[][] positiveVectors = new double[][]{
        {
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0, 1.0
        },{
            0.0, 0.0, 1.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 1.0
        },{
            0.0, 0.0, 0.0, 0.0, 0.0,
            1.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0, 0.0, 1.0
        }
    };

    private double[][] negativeVectors = new double[][] {
        {
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 1.0, 1.0, 0.0,
            0.0, 1.0, 0.0, 1.0, 0.0,
            0.0, 1.0, 1.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 1.0
        },{
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0,
            1.0, 1.0, 1.0, 0.0, 0.0,
            1.0, 0.0, 1.0, 0.0, 0.0,
            1.0, 1.0, 1.0, 0.0, 0.0, 1.0
        }
    };

    @Test
    public void trainingWithBigStep() throws Exception {
        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 1.0);
        perceptron.train();

        System.out.println("Iterations = " + perceptron.getIterations());
        System.out.println("Weights = " + Arrays.toString(perceptron.getWeights()));

        assertEquals(28, perceptron.getIterations());
    }

    @Test
    public void trainingWithMediumStep() throws Exception {
        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 0.1);
        perceptron.train();

        System.out.println("Iterations = " + perceptron.getIterations());
        System.out.println("Weights = " + Arrays.toString(perceptron.getWeights()));

        assertEquals(46, perceptron.getIterations());
    }

    @Test
    public void trainingWithSmallStep() throws Exception {
        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 0.01);
        perceptron.train();

        System.out.println("Iterations = " + perceptron.getIterations());
        System.out.println("Weights = " + Arrays.toString(perceptron.getWeights()));

        assertEquals(386, perceptron.getIterations());
    }

}