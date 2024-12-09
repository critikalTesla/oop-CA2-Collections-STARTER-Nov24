package org.example;

import java.util.*;

public class ArithmeticCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression (supports +, -, *, /, and parentheses):");
        String expression = scanner.nextLine();

        try {
            double result = evaluateExpression(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Evaluates an arithmetic expression
    public static double evaluateExpression(String expression) throws Exception {
        List<String> rpn = convertToRPN(expression);
        return evaluateRPN(rpn);
    }

    // Converts an infix expression to Reverse Polish Notation (RPN)
    public static List<String> convertToRPN(String expression) throws Exception {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumeric(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (operators.isEmpty()) {
                    throw new Exception("Mismatched parentheses");
                }
                operators.pop();
            } else {
                throw new Exception("Invalid token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            String op = operators.pop();
            if (op.equals("(") || op.equals(")")) {
                throw new Exception("Mismatched parentheses");
            }
            output.add(op);
        }

        return output;
    }

    // Evaluates an RPN expression
    public static double evaluateRPN(List<String> rpn) throws Exception {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new Exception("Invalid expression");
                }
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            } else {
                throw new Exception("Invalid token in RPN: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new Exception("Invalid expression");
        }

        return stack.pop();
    }

    // Checks if a token is numeric
    public static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Checks if a token is an operator
    public static boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    // Returns the precedence of an operator
    public static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    // Applies an operator to two operands
    public static double applyOperator(double a, double b, String operator) throws Exception {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new Exception("Division by zero");
                return a / b;
            default:
                throw new Exception("Unknown operator: " + operator);
        }
    }
}
