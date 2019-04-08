package misc;

import model.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class BackPropagation {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        List<Neuron> neurons = new ArrayList<>();
        System.out.println("Número de neuronas");
        int neuron_numbers = scanner.nextInt();
        for(int j = 0 ; j < neuron_numbers ; j++)
            neurons.add(new Neuron());
        setupNetwork(neurons);
        System.out.println("Establecer umbral de activación");
        Double threshold = scanner.nextDouble();
        System.out.println("Establecer coeficiente de aprendizaje");
        Double alpha = scanner.nextDouble();
        System.out.println("Número de salidas");
        int number_outputs = scanner.nextInt();
    }

    private void setupNetwork(List<Neuron> neurons) {
        int neuron_number = 1;
        for(Neuron neuron : neurons) {
            System.out.println("Entrada de la neurona " + neuron_number);
            Double input = scanner.nextDouble();
            System.out.println("Peso de la neurona " + neuron_number);
            Double weight = scanner.nextDouble();
            neuron_number++;
            neuron.setInput(input);
            neuron.setWeight(weight);
        }
    }

    private void hidenLayers(int inputs, int outputs) {

    }
}
