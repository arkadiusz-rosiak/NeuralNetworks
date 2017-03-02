package Cw1_McCullochPitts;

import java.util.Arrays;

// Neuron McCullochaPittsa
@SuppressWarnings("WeakerAccess")
public class Neuron {

    protected double[] weights;
    private double[] inputs;

    public Neuron(double[] weights) {
        this.weights = weights;
    }

    public double getResults(double[] input){

        if(input.length != weights.length){
            throw new IllegalArgumentException("Input size must be weights size (" + weights.length + ")");
        }

        this.inputs = input;

        double dotProduct = calculateDotProduct();
        return isNeuronActivated(dotProduct);
    }

    private double calculateDotProduct(){
        double product = 0;

        for(int i = 0; i < weights.length; ++i){
            product += weights[i] * inputs[i];
        }

        return product;
    }

    private double isNeuronActivated(double x){
        return (x >= 0) ? 1 : 0;
    }

    public double[] getWeights() {
        return Arrays.copyOf(weights, weights.length);
    }
}
