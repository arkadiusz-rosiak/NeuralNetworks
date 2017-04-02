package Cw5_backpropagation;

import java.util.Arrays;

public class Backpropagation
{

    public static void main(String[] args)
    {
        Network network = new Network(3, 3, 1);

        double[][] inputs = {
            {0, 0, 1},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        double[][] expected = {{0},{1},{1},{0}};

        network.train(inputs, expected, 10000);

        System.out.println(network);


        for(int p = 0; p < inputs.length; ++p){
            System.out.println("y_"+p+" = "+Arrays.toString(network.calculateResultFor(inputs[p])));
        }

        System.out.println(Arrays.toString(network.calculateResultFor(new double[]{0.2, 0.9, 1})));

    }

}
