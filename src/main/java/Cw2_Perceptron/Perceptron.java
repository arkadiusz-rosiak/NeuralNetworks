package Cw2_Perceptron;

import Cw1_McCullochPitts.Neuron;
import utils.Vector;

@SuppressWarnings("WeakerAccess")
public class Perceptron extends Neuron {

    private final double[][] uVector;
    private final double stepSize;

    private final int positiveSize;
    private final int negativeSize;

    private int iterations = 0;

    public Perceptron(double[][] positiveVectors, double[][] negativeVectors) {
        this(positiveVectors, negativeVectors, 1.0);
    }

    public Perceptron(double[][] positiveVectors, double[][] negativeVectors, double stepSize){
        super(Vector.ones(positiveVectors[0].length));

        this.uVector = Vector.concat(positiveVectors, negativeVectors);
        checkVectorsSize(uVector);

        this.stepSize = stepSize;
        this.positiveSize = positiveVectors.length;
        this.negativeSize = negativeVectors.length;
    }

    private void checkVectorsSize(double[][] vectors) {
        int firstSize = vectors[0].length;

        for (double[] vector : vectors) {
            if(vector.length != firstSize){
                throw new IllegalArgumentException("All training vectors should be same size!");
            }
        }
    }

    public double getStepSize() {
        return this.stepSize;
    }

    public int getIterations() {
        return iterations;
    }

    public void train(){
        iterations = 1;
        int counter = 0;

        while(counter < getTrainingVectorsCount()){
            double neuronOutput = getResults(getCurrentVector());
            double teacherOutput = getTeacherOutput(iterations);

            for (int i = 0; i < getCurrentVector().length; i++) {
                weights[i] = weights[i] + stepSize * (teacherOutput - neuronOutput) * getCurrentVector()[i];
            }

            counter = (teacherOutput == neuronOutput) ? counter+1 : 0;
            ++iterations;
        }
    }

    private double[] getCurrentVector(){
        return uVector[iterations % getTrainingVectorsCount()];
    }

    private double getTeacherOutput(int t) {
        if(t % getTrainingVectorsCount() < positiveSize){
            return 1.0;
        }
        else{
            return 0.0;
        }
    }

    private int getTrainingVectorsCount(){
        return positiveSize + negativeSize;
    }
}