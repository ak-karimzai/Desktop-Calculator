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
        var _a = x1 * x1 + y1 * y1;
        var _b = x2 * x2 + y2 * y2;
        var theta = Math.acos(ab / Math.sqrt(_a * _b));
        System.out.println(theta);
    }
}