package misc;

import model.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perceptron {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Número de salidas");
        int n = scanner.nextInt();
        List<Double> outputs = new ArrayList<>();
        List<Neuron> neurons = new ArrayList<>();
        for(int i = 1 ; i <= n; i++) {
            System.out.println("Valor de la salida " + i);
            Double output = scanner.nextDouble();
            outputs.add(output);
        }
        System.out.println("Número de neuronas");
        int neuron_numbers = scanner.nextInt();
        for(int j = 0 ; j < neuron_numbers ; j++)
            neurons.add(new Neuron());
        setupNetwork(neurons);
        System.out.println("Establecer umbral de activación");
        Double threshold = scanner.nextDouble();
        System.out.println("Establecer coeficiente de aprendizaje");
        Double alpha = scanner.nextDouble();
        train_network(neurons, threshold, outputs, alpha);
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

    private void train_network(List<Neuron> neurons, Double threshold, List<Double> outputs, Double alpha) {
        List<Double> real_outputs = new ArrayList<>();
            do {
                calculate(neurons, threshold, outputs, alpha, real_outputs);
            }while(!outputs.equals(real_outputs));
    }

    private void update_inputs(List<Neuron> neurons) {
        neurons.stream()
                .forEach(neuron -> {
                    System.out.println("Nueva entrada");
                    neuron.setInput(scanner.nextDouble());
        });
    }

    private Double inputFunction(List<Neuron> neurons, Double threshold) {
        Double input_value = neurons.stream()
                .mapToDouble(value -> value.getWeight() * value.getInput())
                .sum() - threshold;
        return input_value;
    }

    private void updateWights(List<Neuron> neurons, Double alpha, Double error) {
        neurons.stream()
                .forEach(neuron -> neuron.setWeight(neuron.getWeight() + (alpha * neuron.getInput() * error)));
    }

    private void calculate(List<Neuron> neurons, Double threshold, List<Double> outputs, Double alpha, List<Double> real_outputs) {
        real_outputs.clear();
        for(Double output : outputs) {
            System.out.println("Valor de la salida: " + output);
            Double input_value = inputFunction(neurons, threshold);
            System.out.println("Valor de la función de entrada: " + input_value);
            Double activation_value = activationFunction(input_value);
            System.out.println("Valor de la función de activación " + activation_value);
            real_outputs.add(activation_value);
            Double error = error(output, activation_value);
            System.out.println("Valor del error: " + error);
            updateWights(neurons, alpha, error);
            System.out.println("Valor de los pesos actualizados");
            for(Neuron neuron : neurons)
                System.out.println("Entrada: + " + neuron.getInput() + "\t Peso: " + neuron.getWeight());
            System.out.println("Actualizando entradas");
            update_inputs(neurons);
        }
        for(Double n : real_outputs)
            System.out.println(n);
    }

    private Double error(Double desired_output, Double real_output) {
        Double error_value = desired_output - real_output;
        return error_value;
    }

    private Double activationFunction(Double input_value) {
        return input_value < 0 ? 0.0: 1.0;
    }
}
