package misc;

import model.Neuron;
import model.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Kohonen {
    Scanner scanner = new Scanner(System.in);
    public void start() {
        List<Neuron> neurons = new ArrayList<>();
        System.out.println("Número de entradas");
        int number_inputs = scanner.nextInt();
        System.out.println("Número de neuronas");
        int number_neurons = scanner.nextInt();
        for(int i = 0; i < number_neurons; i++)
            neurons.add(new Neuron());
        System.out.println("Coeficiente de aprendizaje");
        Double alpha = scanner.nextDouble();
        setUpNetwork(neurons, number_inputs);
        Neuron n1 = establishCategories(neurons, 0);
        Neuron n2 = establishCategories(neurons, 1);
        List<Neuron> w1 = new ArrayList<>();
        w1.add(n1);
        List<Neuron> w2 = new ArrayList<>();
        w1.add(n2);

    }

    private void calculate(Neuron n1, Neuron n2, int iterations) {

    }

    private Double euclidianDistance(List<Neuron> neurons) {
        Double euclidian_distance = neurons.stream()
                .mapToDouble(neuron -> neuron.getTuples().stream()
                        .mapToDouble(tuple -> Math.sqrt(Math.pow(tuple.getInput() - tuple.getWeight(), 2)))
                        .sum())
                .sum();
        return euclidian_distance;
    }

    private void setUpNetwork(List<Neuron> neurons, int n_inputs) {
        int n_neuron = 1;
        neurons.stream().forEach(neuron -> {
            Tuple tuple = new Tuple();
            List<Tuple> tuples = new ArrayList<>();
            for(int i = 1; i <= n_inputs; i++) {
                System.out.println("Entrada " + i + " de la neurona " + n_neuron);
                Double input = scanner.nextDouble();
                System.out.println("Peso " + i + " de la neurona " + n_neuron);
                Double weight = scanner.nextDouble();
                tuple.setInput(input);
                tuple.setWeight(weight);
                tuples.add(tuple);
            }
            neuron.setTuples(tuples);
        });
    }

    private Neuron establishCategories(List<Neuron> neurons, int condition) {
        Neuron w = neurons.stream().filter(neuron -> neuron.getInputs().get(0) == condition && neuron.getInputs().get(neuron.getInputs().size() - condition) == 0).findFirst().orElse(null);
        return w;
    }
}
