package View;

public class Menu {
    MyInput input = new MyInput();
    public static char[][] board;
    private int rowSize;
    private int columnSize;
    
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
//        BuildShip(new int[]{2, 2}, false, 5, 'C');
//        BuildShip(new int[]{9, 5}, false, 4, 'B');
//        BuildShip(new int[]{4, 6}, true, 3, 'R');
//        BuildShip(new int[]{4, 8}, true, 3, 'S');
//        BuildShip(new int[]{5, 4}, false, 2, 'D');

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
                shipBuilt = BuildShip(coordinate, vertical, shipLength, shipChar);
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

    public boolean BuildShip(int[] coordinate, boolean vertical, int shipLength, char shipChar) {
        System.out.println("coordinate: (" + coordinate[0] + ", " + coordinate[1] + ") vertical: " + vertical);
        //Check for OOB
        if (vertical) {
            if (coordinate[0] + shipLength <= 10) {
                for (int i = 0; i < shipLength; i++) {
                    if (board[coordinate[0] + i][coordinate[1]] != '~') {
                        System.out.println("Ship already there");
                        return false;
                    }
                }
                for (int i = 0; i < shipLength; i++) {
                    board[coordinate[0] + i][coordinate[1]] = shipChar;
                }
                System.out.println("Ship built");
                return true;
            }
        } else {
            if (coordinate[1] + shipLength <= 10) {
                for (int i = 0; i < shipLength; i++) { //Check for other ships in the way
                    if (board[coordinate[0]][coordinate[1] + i] != '~') {
                        System.out.println("Ship already there");
                        return false;
                    }
                }
                for (int i = 0; i < shipLength; i++) { //Build the ships
                    board[coordinate[0]][coordinate[1] + i] = shipChar;
                }
                System.out.println("ship built");
                return true;
            }
        }
        System.out.println("Oob");
        return false;
    }

    public void GameBoard(int columnSize, int rowSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        board = new char[rowSize][columnSize];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = '~';
            }
        }
    }

    public void DisplayBoard() {
       for (int row = 0; row < board[0].length; row++) {
            System.out.print("| ");
            for(int column = 0; column < board[1].length; column++){
//                board[row][column] = '~';
                System.out.print(board[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

}
