import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        int numberOfElems = scanner.nextInt();

        while (numberOfElems > 0) {
            stack.push(scanner.nextInt());
            numberOfElems--;
        }

        while (stack.peek() != null) {
            System.out.println(stack.pop());
        }
    }
}
