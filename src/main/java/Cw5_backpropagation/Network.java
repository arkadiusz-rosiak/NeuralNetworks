package Cw5_backpropagation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network
{
    private static final double C = 0.4;
    private static final double EPS = 0.000001;

    private List<Neuron> hiddenLayer = new ArrayList<>();
    private List<Neuron> outputLayer = new ArrayList<>();

    private double[] s = {0, 1, 2};

    public Network(int nInputs, int nHidden, int nOutputs)
    {
        for(int i = 0; i < nHidden; ++i)
        {
            hiddenLayer.add(new Neuron(nInputs));
        }

        for(int i = 0; i < nOutputs; ++i)
        {
            outputLayer.add(new Neuron(nHidden));
        }
    }


    public void train(double[][] trainSet, double[][] expected, int nEpoch){
        for(int epoch = 1; epoch <= nEpoch; ++epoch){
            double totalError = 0;

            for(int currentSet = 0; currentSet < trainSet.length; ++currentSet){
                double[] row = trainSet[currentSet];
                double[] output = calculateResultFor(row);
                totalError += calculateTotalError(expected[currentSet], output);
                backwardPropagateError(expected[currentSet]);
                updateWeights(row);
            }

            if(epoch % 10000 == 0){
                System.out.println("Total error: " + totalError);
            }

        }
    }

    private double calculateTotalError(double[] expected, double[] output){
        double sum = 0.0;

        for(int i = 0; i < expected.length; ++i){
            sum += Math.pow(expected[i] - output[i], 2);
        }

        return sum;
    }

    public double[] calculateResultFor(double[] input)
    {

        double[] hiddenResults = new double[hiddenLayer.size()];
        for(int i = 0; i < hiddenLayer.size(); ++i)
        {
            Neuron neuron = hiddenLayer.get(i);
            hiddenResults[i] = neuron.calculateAndStoreOutput(input);
        }

        double[] outputResults = new double[outputLayer.size()];
        for(int i = 0; i < outputLayer.size(); ++i)
        {
            Neuron neuron = outputLayer.get(i);
            outputResults[i] = neuron.calculateAndStoreOutput(hiddenResults);
        }

        return outputResults;
    }

    private void backwardPropagateError(double[] expected)
    {
        backwardPropagateErrorInOutputLayer(expected);
        backwardPropagateErrorInHiddenLayer();
    }

    private void updateWeights(double[] row){

        // hidden layer
        for(Neuron neuron : hiddenLayer){
            for(int i = 0; i < row.length; ++i){
                neuron.setWeight(i, neuron.getWeight(i) + C*neuron.getDelta()*row[i]);
            }
        }

        // output layer
        double[] hiddenOutput = new double[hiddenLayer.size()];
        for(int i = 0; i < hiddenOutput.length; ++i){
            hiddenOutput[i] = hiddenLayer.get(i).getOutput();
        }

        for(Neuron neuron : outputLayer){
            for(int i = 0; i < hiddenOutput.length; ++i){
                neuron.setWeight(i, neuron.getWeight(i) + C*neuron.getDelta()*hiddenOutput[i]);
            }
        }

    }




    private void backwardPropagateErrorInHiddenLayer()
    {
        double[] errors = new double[hiddenLayer.size()];

        for(int j = 0; j < hiddenLayer.size(); ++j)
        {
            double error = 0.0;
            for(Neuron neuron : outputLayer)
            {
                error += neuron.getWeight(j) * neuron.getDelta();
            }
            errors[j] = error;
        }

        setNeuronDeltas(hiddenLayer, errors);
    }

    private void backwardPropagateErrorInOutputLayer(double[] expected)
    {
        double[] errors = new double[outputLayer.size()];

        for(int i = 0; i < outputLayer.size(); ++i)
        {
            for(Neuron neuron : outputLayer)
            {
                errors[i] = expected[i] - neuron.getOutput();
            }
        }

        setNeuronDeltas(outputLayer, errors);
    }

    private void setNeuronDeltas(List<Neuron> layer, double[] errors)
    {
        for(int i = 0; i < layer.size(); ++i)
        {
            Neuron neuron = layer.get(i);
            neuron.setDelta(errors[i] * transferDerivative(neuron.getOutput()));
        }
    }

    private double transferDerivative(double output)
    {
        return output * (1.0 - output);
    }

    @Override
    public String toString()
    {
        return "Hidden layer: " + hiddenLayer + '\n' + "Output layer: " + outputLayer + '\n'
                + "S: " + Arrays.toString(s);
    }
}
