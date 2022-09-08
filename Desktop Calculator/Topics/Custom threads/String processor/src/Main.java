import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    private boolean hasLowerCase(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLowerCase(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        // implement this method
        while (true) {
            var word = scanner.nextLine();
            if (hasLowerCase(word)) {
                System.out.println(word.toUpperCase());
            } else {
                break;
            }
        }
        System.out.println("FINISHED");
    }
}