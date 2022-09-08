import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static boolean isOpenBracket(char chr) {
        final char[] openBrackets = {'{', '[', '('};
        for (var bracket : openBrackets) {
            if (bracket == chr) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        var brackets = scanner.nextLine();
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> bracketMap = Map.of(
                '}', '{',
                ']', '[',
                ')', '('
        );
        boolean res = true;

        for (int idx = 0; idx < brackets.length() && res; idx++) {
            if (isOpenBracket(brackets.charAt(idx))) {
                stack.push(brackets.charAt(idx));
            } else {
                if (stack.peek() == null ||
                        !stack.pop().equals(bracketMap.get(brackets.charAt(idx)))) {
                    res = false;
                }
            }
        }
        if (stack.peek() != null) {
            res = false;
        }
        System.out.println(res);
    }
}