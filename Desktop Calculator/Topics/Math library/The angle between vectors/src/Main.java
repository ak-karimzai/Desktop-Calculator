import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        var x1 = scanner.nextDouble();
        var y1 = scanner.nextDouble();
        var x2 = scanner.nextDouble();
        var y2 = scanner.nextDouble();

        var ab = x1 * x2 + y1 * y2;
        var notA = x1 * x1 + y1 * y1;
        var notB = x2 * x2 + y2 * y2;
        var theta = Math.acos(ab / Math.sqrt(notA * notB));
        System.out.println(Math.toDegrees(theta));
    }
}