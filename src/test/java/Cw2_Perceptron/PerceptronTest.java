package Cw2_Perceptron;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PerceptronTest {

    private double[][] positiveVectors = new double[][]{
        {
            0, 1, 0,
            0, 1, 0,
            0, 1, 0, 1
        }
    };

    private double[][] negativeVectors = new double[][] {
        {
            0, 0, 0,
            1, 1, 1,
            0, 0, 0, 1
        }
    };

    @Test
    public void shouldInitializeWeightArraysWithOnes() throws Exception {

        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors);
        double[] weights = perceptron.getWeights();

        assertArrayEquals(new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, weights, 0.005);
    }

    @Test
    public void shouldAcceptTrainingStepAsConstructorParameter() throws Exception {
        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 1.0);
        assertEquals(1.0, perceptron.getStepSize(), 0.005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCheckIfAllVectorsAreTheSameSize() throws Exception {

        double[][] pos = new double[][]{
                {1}, {1, 2}, {1,2, 3}
        };

        double[][] neg = new double[][]{
                {4,5,6,7,8,9}
        };

        new Perceptron(pos, neg);
    }

    @Test
    public void shouldAcceptPositiveVectors() throws Exception {

        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 1);
        perceptron.train();

        for (double[] positiveVector : positiveVectors) {
            assertEquals(1.0, perceptron.getResults(positiveVector), 0.005);
        }
    }

    @Test
    public void shouldRejectNegativeVectors() throws Exception {

        Perceptron perceptron = new Perceptron(positiveVectors, negativeVectors, 1);
        perceptron.train();

        for (double[] negativeVector : negativeVectors) {
            assertEquals(0.0, perceptron.getResults(negativeVector), 0.005);
        }
    }
}