package View;

public class Menu {
    MyInput input = new MyInput();
    
    public static void welcome(){
        System.out.println(" Welcome to BattleShips");

        System.out.println("                                  )___(");
        System.out.println("                           _______/__/_");
        System.out.println("                  ___     /===========|   ___");
        System.out.println(" ____       __   [\\\\\\]___/____________|__[///]   __");
        System.out.println(" \\   \\_____[\\\\]__/___________________________\\__[//]___");
        System.out.println("  \\ 40A                  U.S.S Maine                   |");
        System.out.println("   \\                                                  /");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void PlaceShips() {

        String[] ships = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"}; //This might need to be a hashmap instead -- Mod 4
        boolean shipBuilt;
        char[][] board = new char[10][10];

        DisplayBoard(board); //Will show an empty board

        for (int i = 0; i < ships.length; i++) { //Loop through each ship
            do {
                //Take in coordinate
                do {
                    String inputCoordinate = input.GetUserStr("Where would you like to place your " + ships[i] + "? Enter a coordinate (e.g. D5):", true);
                    System.out.println(inputCoordinate);
                } while (!ValidCoordinate());

                //parse to something like [4, 5]  -- Mod2b
                int[] coordinate = {4, 5};

                String inputDirectionStr;
                boolean vertical = false;
                do {
                    inputDirectionStr = input.GetUserStr("Would you like to place that ship (V)ertically down or (H)orizontally right from that position?", true);
                    System.out.println(inputDirectionStr);
                } while (!(inputDirectionStr.equals("V") || inputDirectionStr.equals("H"))); //Validate "V" or "H"

                if (inputDirectionStr.equals("V")) {
                    vertical = true;
                } //Else false is implied

                //Take in 'V' or 'H', build ship accordingly
                //"V" = true, "H" = false

                shipBuilt = BuildShips(board, coordinate, vertical);
                if (!shipBuilt) {
                    System.out.println("That ship won't fit there! Try a different coordinate or direction.");
                } else {
                    DisplayBoard(board); //Shows the player the board after placing the ship
                }
            } while (!shipBuilt);
        }
    }

    public boolean ValidCoordinate() {
        //Insert coordinate parsing/validation
        return true;
    }

    public boolean BuildShips(char[][] board, int[] coordinate, boolean vertical) {
        System.out.println("coordinate: (" + coordinate[0] + ", " + coordinate[1] + ") vertical: " + vertical);
        //Do checks to make sure ship can fit -- G1a
            //If so
                //Go through and replace the board positions with corresponding ship letter, etc. -- G1b/Mod 4
            //If not
                //Return false
        return true;
    }

    public void DisplayBoard(char[][] board) {
        // -- Mod 2
    }

}
