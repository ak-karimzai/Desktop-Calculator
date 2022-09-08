import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        var a = scanner.nextDouble();
        var b = scanner.nextDouble();

        System.out.println(Math.pow(a, b));
    }
}