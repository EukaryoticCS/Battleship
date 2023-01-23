package View;

import Controller.Gameplay;

public class Menu {
    static MyInput input = new MyInput();
    Gameplay gameplay = new Gameplay();
    public static char[][] board;
    public static char[][] aiBoard;



    public static void welcome() {
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

    public void InitializeBoard() {
        board = new char[10][10];
        aiBoard = new char[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = '~';
                aiBoard[row][col] = '~';
            }
        }
    }


    public static void DisplayBoard() {
        // make a temp board that replaces all the ship letters with ~s and display the boards side by side with players hits and misses on them

        char[] rowChars = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; //For row labels

        System.out.print("   1   2   3   4   5   6   7   8   9   10");//Column labels
        System.out.print("     ");
        System.out.println("    1   2   3   4   5   6   7   8   9   10");//Column labels

        for (int row = 0; row < board.length; row++) {
            System.out.print(rowChars[row] + "| ");


            for (int column = 0; column < board[1].length; column++) {
                System.out.print(board[row][column]);
                System.out.print(" | ");
            }


            System.out.print("    ");
            System.out.print(rowChars[row] + "| ");
            for (int column = 0; column < board[1].length; column++) {

                System.out.print(aiBoard[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
    public static boolean AttackingMenu(){ //Will return true if the player won this turn, false if not
        System.out.println("Attack what Coords?(Column,Row)");

        int[] coords;
        char shipChar;

        do{
        coords = input.GetCoords();
        shipChar = board[coords[0]][coords[1]];

        if(shipChar == 'X' || shipChar == 'O'){
            System.out.println("That coordinate has already been attacked! Try a different spot!");
        }

        } while (shipChar == 'X' || shipChar == 'O');

        boolean HitShip = Gameplay.HitShip(coords);

        if (HitShip) {
            System.out.println("Hit!");
            board[coords[0]][coords[1]] = 'X';
            if (Gameplay.SunkShip(shipChar)) { //Check for sink
                System.out.println("You sunk their " + Gameplay.getShipNameFromChar(shipChar) + "!"); //"You sunk their Battleship!"
                Gameplay.aiShipsSunk++;

                if (Gameplay.aiShipsSunk == 5) { //Check for win
                    System.out.println("You win!");
                    return true;
                }
            }
        } else {
            System.out.println("Miss");
            board[coords[0]][coords[1]] = 'O';
        }
        return false;
    }
}
