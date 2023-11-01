import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "src/resources/english-turkish.csv";
    private static final String[] lastFoundResult = new String[2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean userExited;
        System.out.println("To exit the program, press 'e'");
        System.out.println("Please type a word or expression to find turkish translation");
        try {
            do {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH, StandardCharsets.UTF_8));
                userInput = scanner.nextLine().toLowerCase().trim();
                userExited = userInput.equals("e");
                if (!userExited) {
                    searchForUserInput(bufferedReader, userInput);
                }
            } while (!userExited);

        } catch (IOException e) {
            System.out.println("something went wrong.. please try again.");
        }
        System.out.println("you exited the program");
    }

    public static void searchForUserInput(BufferedReader bufferedReader, String userInput) {
        String[] searchResult;
        String line;
        boolean isMatch = false;

        if (userInput.equals(lastFoundResult[0])) {
            isMatch = true;
            System.out.println(userInput + ": " + lastFoundResult[1]);
        }
        try {
            while ((line = bufferedReader.readLine()) != null && !isMatch) {
                searchResult = line.split(",");
                if (userInput.equals(searchResult[0])) {
                    lastFoundResult[0] = userInput;
                    lastFoundResult[1] = searchResult[1];
                    isMatch = true;
                    System.out.println(userInput + ": " + searchResult[1]);
                }
            }
            bufferedReader.close();
            if (!isMatch) {
                System.out.println("there is no result for: " + userInput);
            }
        } catch (IOException e) {
            System.out.println("something went wrong..");
        }
    }
}