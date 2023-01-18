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

    public int GetUserInt(String prompt) {
        int iReturn = 0;
        boolean keepLooping = true;

        while (keepLooping) {
            try {
                iReturn = Integer.parseInt(GetUserStr(prompt, true));
                keepLooping = false;
            } catch (Exception ex) {
                System.out.println("Please input a valid integer");
            }
        }
        return iReturn;
    }
}
