package Cw1_McCullochPitts;

// Neuron McCullochaPittsa
class Neuron {

    private double[] weights;
    private double[] inputs;

    Neuron(double[] weights) {
        this.weights = weights;
    }

    double getResults(double[] input){

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
}
