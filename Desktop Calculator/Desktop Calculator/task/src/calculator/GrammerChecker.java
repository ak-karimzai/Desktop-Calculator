package calculator;

public class GrammerChecker {
    private static Evaluate evaluate = new Evaluate();
    public static boolean isOperator(char ch) {
        final char[] operators = { '^', '\u00F7', '\u00D7', '\u2212', '\u002B',
                '+', '-', '/', 'x' };
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == ch) {
                return true;
            }
        }
        return false;
    }

    public static Pair<Integer, Integer> countParenthesis(String equation) {
        int leftParentheses, rightParentheses;
        leftParentheses = rightParentheses = 0;

        for (int idx = 0; idx < equation.length(); idx++) {
            if (equation.charAt(idx) == '(') {
                leftParentheses++;
            } else if (equation.charAt(idx) == ')') {
                rightParentheses++;
            }
        }
        return new Pair<>(leftParentheses, rightParentheses);
    }

    public static boolean isValidEquation(String equation) {
        if (isOperator(equation.charAt(0))) {
            return true;
        }
        return false;
    }

    public static boolean isUnaryEquation(String equation) {
        int operators = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (isOperator(equation.charAt(i))) {
                operators++;
            }
        }
        if (operators <= 1 && equation.length() > 0 && isOperator(equation.charAt(equation.length() - 1)))
            return true;
        return false;
    }

    public static String getValidEquation(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '.') {
                if (i == 0) {
                    equation = "0".concat(equation);
                } else if (isOperator(equation.charAt(i - 1))) {
                    equation = equation.substring(0, i).
                            concat("0.").
                            concat(equation.substring(i + 1));
                    i--;
                } else if ((i + 1 != equation.length()) && isOperator(equation.charAt(i + 1))) {
                    equation = equation.substring(0, i + 1).
                            concat("0").
                            concat(equation.substring(i + 1));
                    i++;
                }
            }
        }
        if (equation.length() == 1 && isOperator(equation.charAt(0)))
            return "";
        return equation;
    }
}
