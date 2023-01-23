package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyInput {
    private final BufferedReader bread = new BufferedReader(new InputStreamReader(System.in)); //Get the user string

    public String GetUserStr(String prompt, boolean required) { //Overridden method allows for hard-coded prompts
        if (prompt != null) {
            System.out.println(prompt); //Prompts user with an input
        }
        while (true) {
            try { //Try/Catch statement
                return bread.readLine(); //Get user input as string, return it
            } catch (Exception ex) {
                if (!required) {
                    return "";
                }
                System.out.println("Please input a valid string"); //If it fails, loop unless it doesn't matter
            }
        }

    }

    public int[] GetCoords() {
        while (true) {
            try {
                String coordinateInput = bread.readLine().trim().toLowerCase(); // taking user input
                if (coordinateInput.length() <= 4 && coordinateInput.length() >= 1) {

                    char letter = coordinateInput.charAt(0);
                    if (letter <= 'j' && letter >= 'a') {

                        int col = letter - 'a'; // 'a' is an ascii value (97)

                        String number = coordinateInput.substring(1);
                        int row = Integer.parseInt(number);
                        if (row <= 10 && row >= 1) {

                            return new int[]{col, row - 1};
                        }
                    }
                }
                System.out.println("Invalid coordinate!");
            } catch (Exception ex) {
                System.out.println("Invalid coordinate!");
            }
        }
    }
}
