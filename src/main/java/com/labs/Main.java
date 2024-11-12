package com.labs;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println("Результат: " + parser.parse("x"));
    }
}
