import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var word = scanner.nextLine();
        var res = "";

        int count = 1;
        char alph = word.length() > 0 ? word.charAt(0) : ' ';
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == alph) {
                count++;
            } else {
                res += String.valueOf(alph) + count;
                alph = word.charAt(i);
                count = 1;
            }
        }
        if (count >= 1) {
            res += String.valueOf(alph) + count;
        }
        System.out.println(res);
    }
}