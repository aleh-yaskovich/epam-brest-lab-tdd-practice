package com.epam.brest.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceMenu {

    public void menu() {
        System.out.println("SIMPLE CONSOLE CALCULATOR\n" +
                "Use '+' to add, '-' to subtract, '*' to multiply, ':' to divide.\n" +
                "You can enter a fractional number separated by a point or comma. For example: 2.67 or 2,67.\n" +
                "Negative numbers are entered according to the rule: -1 + (-3).\n" +
                "To complete enter 'x'.");
        String expression = getExpression();
        while(!expression.equals("x")) {
            if(checkExpressionForMatch(expression)) {
                String[] expressionElements = splitExpression(expression);
                System.out.println("Answer: "+expression+"=" + getResult(expressionElements));
            } else {
                System.out.println("Your expression is wrong :(");
            }
            expression = getExpression();
        }
    }

    /**
     * define a math operation and return the result
     * */
    public String getResult(String[] expressionElements) {
        Double a = Double.parseDouble(expressionElements[0]);
        Double b = Double.parseDouble(expressionElements[1]);
        switch(expressionElements[2]) {
            case "+": return String.format("%.4f",(a + b));
            case "-": return String.format("%.4f",(a - b));
            case "*": return String.format("%.4f",(a * b));
            case ":": return String.format("%.4f",(a / b));
            default:
                System.out.println("Something wrong...");
                return null;
        }
    }

    /**
     * return array with three elements: "number", "math operation sign", "number"
     * */
    public String[] splitExpression(String expression) {
        String[] expElements = new String[3];
        Pattern pattern = Pattern.compile("(\\(-)?\\d{1,}[,.]?\\d{0,}\\)?");
        Matcher matcher = pattern.matcher(expression);
        int i = 0;
        while(matcher.find()) {
            expElements[i] = matcher.group();
            i++;
        }
        expElements[2] = expression.substring(expElements[0].length(), expElements[0].length()+1);
        return expElements;
    }


    /**
     * check expression for match -> "number"+"math operation sign"+"number"
     * numbers can be positive and negative, integer and fractional
     * */
    public boolean checkExpressionForMatch(String expression) {
        return expression.matches("-?\\d{1,}[,.]?\\d{0,}[*:+-](\\(-)?\\d{1,}[,.]?\\d{0,}\\)?");
    }

    /**
     * get expression
     * */
    private String getExpression() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your expression: ");
        String result = scan.nextLine();
        result = result.replaceAll(" ", "");
        result = result.replaceAll(",", ".");
        return result;
    }

}
