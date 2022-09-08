import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        var a = scanner.nextDouble();
        var b = scanner.nextDouble();
        var c = scanner.nextDouble();

        var p = (a + b + c) / 2;
        var s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println(s);
    }
}