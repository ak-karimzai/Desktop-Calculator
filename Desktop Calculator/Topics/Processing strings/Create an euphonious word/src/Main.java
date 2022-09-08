import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var word = scanner.nextLine();

        int count = 0, wordCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (!isVowel(word.charAt(i))) {
                wordCount++;
            } else {
                if (wordCount >= 3) {
                    count++;
                }
                wordCount = 0;
            }
        }
        if (wordCount >= 3) {
            count++;
        }
        System.out.println(count);
    }

    private static boolean isVowel(char chr) {
        final char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
        for (int i = 0; i < vowels.length; i++) {
            if (chr == vowels[i]) {
                return true;
            }
        }
        return false;
    }
}