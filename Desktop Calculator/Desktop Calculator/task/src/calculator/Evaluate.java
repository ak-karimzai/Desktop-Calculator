package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class Evaluate {
    final private Map<Character, BiFunction<Double, Double, Double>> operatorMap = Map.of(
            '\u002B', CalculatorOperations::add,
            '\u2212', CalculatorOperations::subtract,
            '-', CalculatorOperations::subtract,
            '\u00D7', CalculatorOperations::multiply,
            'x', CalculatorOperations::multiply,
            '\u00F7', CalculatorOperations::divide,
            '/', CalculatorOperations::divide,
            '^', CalculatorOperations::pow,
            '\u221a', CalculatorOperations::sqrt
    );

    public int operatorPrecedence(char ch) {
        int res;
        if (ch == '^') {
            res = 5;
        } else if (ch == '\u00F7' || ch == '\u00D7' || ch == 'x' || ch == '/') {
            res = 4;
        } else if (ch == '\u221a') {
            res = 3;
        } else if (ch == '\u2212' || ch == '-') {
            res = 2;
        } else if (ch == '\u002B' || ch == '+') {
            res = 1;
        } else {
            res = 0;
        }
        return res;
    }
    public String changeToPostfix(String expression) {
        String res = "";
        Deque<Character> stack = new ArrayDeque<>();

        for (int idx = 0; idx < expression.length(); idx++) {
            char ch = expression.charAt(idx);

            if (Character.isDigit(ch) || ch == '.') {
                res += ch;
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    res += stack.pop();
                }
                stack.pop();
            } else {
                res += ' ';
                while (!stack.isEmpty() &&
                        operatorPrecedence(ch) <= operatorPrecedence(stack.peek())) {
                    res += stack.pop();
                }
                stack.push(ch);
//                }
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public String evaluatePostfix(String postfixExpression) throws NoSuchElementException,
                                                                   ArithmeticException,
                                                                   NumberFormatException {
        Deque<Double> stack = new ArrayDeque<>();
        
        for (int idx = 0; idx < postfixExpression.length(); idx++) {
            char ch = postfixExpression.charAt(idx);

            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch) || ch == '.') {
                String num = "";
                while (idx < postfixExpression.length() &&
                        (Character.isDigit(ch = postfixExpression.charAt(idx)) ||
                                ch == '.')) {
                    num += ch;
                    idx++;
                }
                idx--;
                stack.push(Double.valueOf(num));
            } else {

                double rhs = stack.pop(), lhs;
                if (ch == '-' && stack.size() == 0) {
                    lhs = 0;
                } else if (ch == '\u221a') {
                    lhs = rhs;
                } else {
                    lhs = stack.pop();
                }
                var operator = operatorMap.get(ch);
                stack.push(operator.apply(lhs, rhs));
            }
        }
        var res = stack.pop();
        var intValue = res.intValue();
        if ((res - intValue) == 0.0) {
            return String.valueOf(intValue);
        }
        return String.valueOf(res);
    }
    public String eval(String equation) throws EquationInvalidException {
        var res = "";
        try {
            var postfixEquation = changeToPostfix(equation);
            res = evaluatePostfix(postfixEquation);
        } catch (Exception e) {
            throw new EquationInvalidException(equation);
        }
        return res;
    }
}

class CalculatorOperations {
    public static Double add(Double lhs, Double rhs) {
        return lhs + rhs;
    }

    public static Double subtract(Double lhs, Double rhs) {
        return lhs - rhs;
    }

    public static Double multiply(Double lhs, Double rhs) {
        return lhs * rhs;
    }

    public static Double divide(Double lhs, Double rhs) {
        var res = lhs / rhs;
        if (res == Double.POSITIVE_INFINITY ||
            res == Double.NEGATIVE_INFINITY)
            throw new ArithmeticException("Not finite");
        return res;
    }

    public static Double pow(Double lhs, Double rhs) {
        return Math.pow(lhs, rhs);
    }
    public static Double sqrt(Double lhs, Double rhs) {
        if (lhs < 0)
            throw new ArithmeticException("square root not defined for negative value: " + lhs);
        return Math.sqrt(lhs);
    }
}

