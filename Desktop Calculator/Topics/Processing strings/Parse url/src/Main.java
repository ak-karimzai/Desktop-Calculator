import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var urlLink = scanner.nextLine();

        var infoPart = urlLink.split("\\?")[1];
        var infoArr = infoPart.split("&");

        int passIdx = -1;
        for (int i = 0; i < infoArr.length; i++) {
            var keyAndValue = infoArr[i].split("=");

            if (keyAndValue[0].equals("pass")) {
                passIdx = i;
            }

            printKeyAndValueInfo(keyAndValue);
        }
        if (passIdx != -1) {
            String[] arr = infoArr[passIdx].split("=");
            arr[0] = String.valueOf("password");
            printKeyAndValueInfo(arr);
        }
    }

    private static void printKeyAndValueInfo(String[] keyAndValue) {
        if (keyAndValue.length == 2) {
            System.out.println(keyAndValue[0] + " : " + keyAndValue[1]);
        } else {
            System.out.println(keyAndValue[0] + " : " + "not found");
        }
    }
}