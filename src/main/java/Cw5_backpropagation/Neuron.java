package Cw5_backpropagation;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class Neuron
{
    private static final double BETA = 2.0;
    private double[] weights;
    private double output;
    private double delta;

    Neuron(int nInputs)
    {
        this.weights = new double[nInputs];

        for(int i = 0; i < nInputs; ++i)
        {
            this.weights[i] = ThreadLocalRandom.current().nextDouble(0.6, 1);
        }
    }

    double calculateAndStoreOutput(double[] inputs){
        double activation = activate(inputs);
        output = transfer(activation);
        return output;
    }

    double getOutput()
    {
        return output;
    }

    private double transfer(double activation){
        return 1.0 / (1.0 + Math.exp(-activation * BETA));
    }

    private double activate(double[] inputs){
        double product = 0;

        for(int i = 0; i < weights.length; ++i){
            product += weights[i] * inputs[i];
        }

        return product;
    }

    double getDelta()
    {
        return delta;
    }

    void setDelta(double delta)
    {
        this.delta = delta;
    }

    double getWeight(int inputIndex){
        return this.weights[inputIndex];
    }

    void setWeight(int inputIndex, double weight){
        this.weights[inputIndex] = weight;
    }

    @Override
    public String toString()
    {
        return "{" +
                "weights: " + Arrays.toString(weights) + " delta: " + getDelta() +
                '}';
    }
}
