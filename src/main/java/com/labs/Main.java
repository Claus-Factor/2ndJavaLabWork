package com.labs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        Map<String, Double> variables = new HashMap<>();
        // Ищем переменные в выражении и запрашиваем их значения у пользователя
        for (char c : expression.toCharArray()) {
            if (Character.isLetter(c) && !variables.containsKey(String.valueOf(c))) {
                System.out.print("Введите значение для переменной " + c + ": ");
                double value = scanner.nextDouble();
                variables.put(String.valueOf(c), value);
            }
        }

        ExpressionParser parser = new ExpressionParser(variables);

        try {
            double result = parser.parse(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
