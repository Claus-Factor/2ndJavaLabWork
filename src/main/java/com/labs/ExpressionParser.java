package com.labs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpressionParser {
    private final Map<String, Double> variables = new HashMap<>();

    public double parse(String expression) {
        if (expression.equals("x")) {
            return getVariableValue("x"); // Пример для переменной
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
