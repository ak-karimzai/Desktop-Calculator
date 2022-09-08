import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        var line = scanner.nextLine();
        var subString = scanner.nextLine().trim();

        int count = 0;
        for (int i = 0; i < line.length(); ) {
            int k = 0;
            if (line.charAt(i) == subString.charAt(k)) {
                while (i < line.length() && k < subString.length()) {
                    if (line.charAt(i) != subString.charAt(k)) {
                        break;
                    }
                    i++;
                    k++;
                }
                if (k == subString.length()) {
                    count++;
                }
            } else {
                i++;
            }
        }
        System.out.println(count);
    }
}