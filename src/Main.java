import org.json.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String line = scan.nextLine().trim();
            if (line.endsWith("\"Spades\" }")) {
                break;
            }
            stringBuilder.append(line).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
