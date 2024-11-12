package com.labs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.function.Function;

public class ExpressionParser {
    private final Map<String, Double> variables = new HashMap<>();

    private static final Map<String, Function<Double, Double>> FUNCTIONS = Map.of(
            "sin", Math::sin,
            "cos", Math::cos,
            "sqrt", Math::sqrt
    );

    private double applyOperator(String operator, double a, double b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        };
    }

    public double parse(String expression) {
        if (expression.equals("sin(x)")) {
            return FUNCTIONS.get("sin").apply(1.0); // Пример для функции sin
        }
        return 0;
    }

    private double getVariableValue(String variable) {
        if (!variables.containsKey(variable)) {
            System.out.print("Введите значение для переменной " + variable + ": ");
            Scanner scanner = new Scanner(System.in);
            double value = scanner.nextDouble();
            variables.put(variable, value);
        }
        return variables.get(variable);
    }

}
