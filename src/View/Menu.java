package View;

import Controller.Gameplay;

public class Menu {
    static MyInput input = new MyInput();
    public static char[][] playerBoard;
    public static char[][] aiBoard;

    public static char[][] hiddenBoard;

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

    public static void InitializeBoards() {
        playerBoard = new char[10][10];
        aiBoard = new char[10][10];
        hiddenBoard = new char[10][10];
        for (int row = 0; row < playerBoard.length; row++) {
            for (int col = 0; col < playerBoard[0].length; col++) {
                playerBoard[row][col] = '~';
                aiBoard[row][col] = '~';
                hiddenBoard[row][col] = '~';
            }
        }
    }

    public static void DisplayBoard() {
        // make a temp board that replaces all the ship letters with ~s and display the boards side by side with players hits and misses on them

        char[] rowChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; //For row labels

        System.out.print("   1   2   3   4   5   6   7   8   9   10");//Column labels
        System.out.print("     ");
        System.out.println("    1   2   3   4   5   6   7   8   9   10");//Column labels

        for (int row = 0; row < playerBoard.length; row++) {
            System.out.print(rowChars[row] + "| ");


            for (int column = 0; column < playerBoard[1].length; column++) {
                System.out.print(playerBoard[row][column]);
                System.out.print(" | ");
            }


            System.out.print("    ");
            System.out.print(rowChars[row] + "| ");
            for (int column = 0; column < playerBoard[1].length; column++) {

                System.out.print(hiddenBoard[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public static boolean PlayerTurn() { //Will return true if the player won this turn, false if not
        System.out.println("Attack what coordinates? (Row, Column, e.g. C6)");

        int[] coords;
        char shipChar;

        do {
            coords = input.GetCoords();
            shipChar = aiBoard[coords[0]][coords[1]];

            if (shipChar == 'X' || shipChar == 'O') {
                System.out.println("That coordinate has already been attacked! Try a different spot!");
            }

        } while (shipChar == 'X' || shipChar == 'O');

        if (Gameplay.HitShip(aiBoard, coords)) {
            System.out.println("Hit!");
            aiBoard[coords[0]][coords[1]] = 'X';
            hiddenBoard[coords[0]][coords[1]] = 'X';

            if (Gameplay.SunkShip(aiBoard, shipChar)) { //Check for sink
                System.out.println("You sunk their " + Gameplay.getShipNameFromChar(shipChar) + "!"); //"You sunk their Battleship!"
                Gameplay.aiShipsSunk++;

                if (Gameplay.aiShipsSunk == 5) { //Check for win
                    System.out.println("Congratulations! You beat your opponent in Battleship!");
                    return true;
                }
            }
        } else {
            System.out.println("Miss");
            aiBoard[coords[0]][coords[1]] = 'O';
            hiddenBoard[coords[0]][coords[1]] = 'O';
        }
        return false;
    }
}
