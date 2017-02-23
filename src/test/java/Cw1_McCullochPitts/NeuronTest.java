package Cw1_McCullochPitts;

import org.junit.Test;

public class NeuronTest {


    @Test
    public void notGate() throws Exception {

        double[] weights = {-1, 0};
        Neuron neuron = new Neuron(weights);

        double[] u = new double[]{0, 1};
        double result = neuron.getResults(u);

        System.out.println("NOT: " + result);
    }

    @Test
    public void andGate() throws Exception {

        double[] weights = {0.333, 0.333, -0.5};
        Neuron neuron = new Neuron(weights);

        double[] u = new double[]{0, 0, 1};
        double result = neuron.getResults(u);

        System.out.println("AND:" + result);
    }

    @Test
    public void nandGate() throws Exception {

        double[] weights = {-0.333, -0.333, 0.5};
        Neuron neuron = new Neuron(weights);

        double[] u = new double[]{0, 0, 1};
        double result = neuron.getResults(u);

        System.out.println("NAND: " + result);
    }


    @Test
    public void orGate() throws Exception {

        double[] weights = {0.5, 0.5, -0.5};
        Neuron neuron = new Neuron(weights);

        double[] u = new double[]{1, 0, 1};
        double result = neuron.getResults(u);

        System.out.println("OR: " + result);
    }
}