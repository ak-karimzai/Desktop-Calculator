import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        // implement this method
        while (true) {
            int number = scanner.nextInt();
            if (number == 0) break;

            if (number % 2 == 0) {
                System.out.println(number);
            }
        }
    }
}