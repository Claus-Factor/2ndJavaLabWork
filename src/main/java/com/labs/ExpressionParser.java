package com.labs;

import java.util.*;
import java.util.function.Function;

/**
 * Класс для парсинга и вычисления математических выражений, поддерживающий переменные и функции.
 * <p>
 * Поддерживаемые операции:
 * <ul>
 *     <li>Арифметические операции: +, -, *, /, ^</li>
 *     <li>Функции: sin, cos, sqrt</li>
 *     <li>Переменные: могут быть заданы в виде Map с их значениями</li>
 * </ul>
 * @author Николай Вольхин (8 группа, 3 курс)
 */
public class ExpressionParser {

    /** Словарь функций, доступных для использования в выражениях. */
    private static final Map<String, Function<Double, Double>> FUNCTIONS = Map.of(
            "sin", Math::sin,
            "cos", Math::cos,
            "sqrt", Math::sqrt
    );

    /** Словарь значений переменных, используемых в выражении. */
    private final Map<String, Double> variables;

    /**
     * Конструктор, инициализирующий переменные.
     *
     * @param variables значения переменных, используемых в выражении
     */
    public ExpressionParser(Map<String, Double> variables) {
        this.variables = variables;
    }

    /**
     * Основной метод для парсинга и вычисления выражения.
     *
     * @param expression математическое выражение в виде строки
     * @return результат вычисления
     * @throws IllegalArgumentException если выражение некорректно
     */
    public double parse(String expression) {
        try {
            List<String> tokens = splitExpression(expression);
            List<String> postfix = toPostfix(tokens);
            return parsePostfix(postfix);
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректное выражение: " + expression);
        }
    }

    /**
     * Разделяет выражение на токены.
     * В контексте проекта токен - это минимальная смысловая единица, на основе которой происходит разбор математического выражения.
     *
     * @param expression строка выражения
     * @return список токенов
     */
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

    /**
     * Преобразует список токенов в обратную польскую нотацию (ОПН).
     *
     * @param tokens список токенов
     * @return список токенов в ОПН
     */
    private List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isFunction(token)) {
                stack.push(token);
            } else if (isVariable(token)) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); // Удаляем "("
                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    output.add(stack.pop());
                }
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    /**
     * Вычисляет значение выражения в ОПН.
     *
     * @param postfix список токенов в ОПН
     * @return результат вычисления
     */
    private double parsePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isVariable(token)) {
                stack.push(variables.getOrDefault(token, 0.0));
            } else if (isFunction(token)) {
                double value = stack.pop();
                stack.push(FUNCTIONS.get(token).apply(value));
            } else if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(token, a, b));
            }
        }

        return stack.pop();
    }

    /**
     * Проверяет, является ли токен числом.
     *
     * @param token токен
     * @return true, если токен — число
     */
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли токен функцией.
     *
     * @param token токен
     * @return true, если токен — функция
     */
    private boolean isFunction(String token) {
        return FUNCTIONS.containsKey(token);
    }

    /**
     * Проверяет, является ли токен оператором.
     *
     * @param token токен
     * @return true, если токен — оператор
     */
    private boolean isOperator(String token) {
        return "+-*/^".contains(token);
    }

    /**
     * Проверяет, является ли токен переменной.
     *
     * @param token токен
     * @return true, если токен — переменная
     */
    private boolean isVariable(String token) {
        return Character.isLetter(token.charAt(0)) && !isFunction(token);
    }

    /**
     * Применяет оператор к двум значениям.
     *
     * @param operator оператор
     * @param a первое значение
     * @param b второе значение
     * @return результат операции
     */
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

    /**
     * Определяет приоритет оператора.
     *
     * @param operator оператор
     * @return приоритет оператора
     */
    private int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}
