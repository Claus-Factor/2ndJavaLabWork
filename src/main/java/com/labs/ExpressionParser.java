package com.labs;

import java.util.HashMap;
import java.util.*;
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

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFunction(String token) {
        return FUNCTIONS.containsKey(token);
    }

    private boolean isOperator(String token) {
        return "+-*/^".contains(token);
    }

    private boolean isVariable(String token) {
        return Character.isLetter(token.charAt(0)) && !isFunction(token);
    }

    private int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    private List<String> splitExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) continue;

            if (Character.isDigit(c) || c == '.') {
                token.append(c);
            } else if (Character.isLetter(c)) {
                token.append(c);
            } else {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
                if ("+-*/^()".indexOf(c) >= 0) {
                    tokens.add(String.valueOf(c));
                }
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString());
        }

        return tokens;
    }


    public double parse(String expression) {
        try {
            // Простой пример проверки выражения
            if (expression.contains("++")) {
                throw new IllegalArgumentException("Некорректный оператор '++'");
            }
            return 0; // Пока ничего не вычисляем
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return Double.NaN;
        }
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
