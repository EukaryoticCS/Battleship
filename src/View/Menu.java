package View;

import Controller.Gameplay;

public class Menu {
    MyInput input = new MyInput();
    Gameplay gameplay = new Gameplay();
    public static char[][] board;

    
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

        DisplayBoard(); //Will show an empty board

        //For Testing
        gameplay.BuildShip(new int[]{2, 2}, false, 5, 'C',board);
        gameplay.BuildShip(new int[]{9, 5}, false, 4, 'B',board);
        gameplay.BuildShip(new int[]{4, 6}, true, 3, 'R',board);
        gameplay.BuildShip(new int[]{4, 8}, true, 3, 'S',board);
        gameplay.BuildShip(new int[]{5, 4}, false, 2, 'D',board);

        DisplayBoard();
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

                int shipLength = 5; //For testing
                char shipChar = 'C'; //For testing
                shipBuilt = gameplay.BuildShip(coordinate, vertical, shipLength, shipChar, board);
                if (!shipBuilt) {
                    System.out.println("That ship won't fit there! Try a different coordinate or direction.");
                } else {
                    DisplayBoard(); //Shows the player the board after placing the ship
                }
            } while (!shipBuilt);
        }
    }

    public boolean ValidCoordinate() {
        //Insert coordinate parsing/validation
        return true;
    }


    public void GameBoard() {
        board = new char[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = '~';
            }
        }
    }

    public void DisplayBoard() {
       for (int row = 0; row < board.length; row++) {
            System.out.print("| ");
            for(int column = 0; column < board[1].length; column++){
                System.out.print(board[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

}
