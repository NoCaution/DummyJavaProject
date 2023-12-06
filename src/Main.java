import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "src/resources/english-turkish.csv";
    private static final String[] lastFoundResult = new String[2];
    private static final HashMap<String, String> dictionary = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean userExited;
        System.out.println("To exit the program: press 'e'");
        System.out.println("Please type a word or expression to find turkish translation");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH, StandardCharsets.UTF_8));
            copyDictionary(bufferedReader);
            do {
                userInput = scanner.nextLine().toLowerCase().trim();
                userExited = userInput.equals("e");

                if (!userExited) {
                    searchForUserInput(userInput);
                }
            } while (!userExited);

        } catch (IOException e) {
            System.out.println("something went wrong.. please try again.");
        }
        System.out.println("you exited the program");
    }

    public static void searchForUserInput(String userInput) {
        String searchResult;

        if (userInput.equals(lastFoundResult[0])) {
            System.out.println(userInput + ": " + lastFoundResult[1]);
        }
        searchResult = dictionary.get(userInput);
        boolean isMatch = searchResult != null;

        if (isMatch) {
            System.out.println(userInput + ": " + searchResult);
            lastFoundResult[0] = userInput;
            lastFoundResult[1] = searchResult;
        } else {
            System.out.println("no result for: " + userInput);
        }
    }

    public static void copyDictionary(BufferedReader bufferedReader) {
        String line;
        String[] splitLine;
        String result;
        int keyLength;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                splitLine = line.split(",");
                keyLength = splitLine[0].length() + 1; //+1 is because of comma included;
                result = line.substring(keyLength).replaceAll(",*$", "");

                dictionary.put(splitLine[0], result);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("something went wrong..");
        }
    }
}