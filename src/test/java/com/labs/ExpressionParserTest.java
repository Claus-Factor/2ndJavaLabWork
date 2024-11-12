package com.labs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class ExpressionParserTest {

    @Test
    void testAddition() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(5.0, parser.parse("2 + 3"), 0.0001));
    }

    @Test
    void testSubtraction() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(1.0, parser.parse("3 - 2"), 0.0001));
    }

    @Test
    void testMultiplication() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(6.0, parser.parse("2 * 3"), 0.0001));
    }

    @Test
    void testDivision() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(2.0, parser.parse("6 / 3"), 0.0001));
    }



    @Test
    void testExponentiation() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(8.0, parser.parse("2 ^ 3"), 0.0001));
    }

    @Test
    void testFunctionSine() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", Math.PI / 2);
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(1.0, parser.parse("sin(x)"), 0.0001));
    }

    @Test
    void testExpressionWithWhitespace() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("a", 2.0);
        variables.put("b", 3.0);
        ExpressionParser parser = new ExpressionParser(variables);
        assertDoesNotThrow(() -> assertEquals(5.0, parser.parse(" a + b "), 0.0001));
    }

    @Test
    void testInvalidExpression() {
        Map<String, Double> variables = new HashMap<>();
        ExpressionParser parser = new ExpressionParser(variables);
        assertThrows(IllegalArgumentException.class, () -> parser.parse("2 + * 3"));
    }
}
