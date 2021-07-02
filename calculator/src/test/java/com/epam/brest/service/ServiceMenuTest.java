package com.epam.brest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceMenuTest {

    @Test
    void checkTrueExpressionForMatchAddTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("1+23"));
    }

    @Test
    void checkTrueExpressionForMatchSubtractTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("100-23"));
    }

    @Test
    void checkTrueExpressionForMatchMultiplyTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("-14*2"));
    }

    @Test
    void checkTrueExpressionForMatchDivideTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("10:(-5)"));
    }

    @Test
    void checkWrongExpressionForMatchTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertFalse(service.checkExpressionForMatch("a+b"));
    }

    @Test
    void checkExpressionWithNegativeNumberTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("-1+3"));
        Assertions.assertTrue(service.checkExpressionForMatch("1+(-3)"));
        Assertions.assertFalse(service.checkExpressionForMatch("1+-3"));
    }

    @Test
    void checkExpressionWithFractionalNumberTest() {
        ServiceMenu service = new ServiceMenu();
        Assertions.assertTrue(service.checkExpressionForMatch("-1.5+3.9"));
        Assertions.assertTrue(service.checkExpressionForMatch("1+(-3.9)"));
    }

    @Test
    void resultSplitExpressionTest() {
        ServiceMenu service = new ServiceMenu();
        String[] testArray = service.splitExpression("23.4+14");
        Assertions.assertEquals("23.4", testArray[0]);
        Assertions.assertEquals("14", testArray[1]);
        Assertions.assertEquals("+", testArray[2]);
    }

    @Test
    void addResultTest() {
        ServiceMenu service = new ServiceMenu();
        String[] testArray = service.splitExpression("23.4+14");
        Assertions.assertEquals("37.4000", service.getResult(testArray));
    }

    @Test
    void subtractResultTest() {
        ServiceMenu service = new ServiceMenu();
        String[] testArray = service.splitExpression("23.4-14");
        Assertions.assertEquals("9.4000", service.getResult(testArray));
    }

    @Test
    void multiplyResultTest() {
        ServiceMenu service = new ServiceMenu();
        String[] testArray = service.splitExpression("2.5*4");
        Assertions.assertEquals("10.0000", service.getResult(testArray));
    }

    @Test
    void divideResultTest() {
        ServiceMenu service = new ServiceMenu();
        String[] testArray = service.splitExpression("10:3");
        Assertions.assertEquals("3.3333", service.getResult(testArray));
    }
}