package Controller;

import View.Menu;
import View.MyInput;
import Model.Ship;

import java.util.Random;

public class Gameplay {

    static Random random = new Random();

    static MyInput input = new MyInput();
    public static int aiShipsSunk = 0;
    public static int playerShipsSunk = 0;

    public static Ship[] ships = new Ship[]{
            new Ship("Carrier", 'C', 5),
            new Ship("Battleship", 'B', 4),
            new Ship("Cruiser", 'R', 3),
            new Ship("Submarine", 'S', 3),
            new Ship("Destroyer", 'D', 2)
    };

    public static void PlaceShips() {

        boolean shipBuilt;

        //For Testing
//        BuildShip(new int[]{2, 2}, false, 5, 'C', Menu.aiBoard); //C3, H
//        BuildShip(new int[]{9, 5}, false, 4, 'B', Menu.aiBoard); //J6, H
//        BuildShip(new int[]{4, 6}, true, 3, 'R', Menu.aiBoard); //E7, V
//        BuildShip(new int[]{4, 8}, true, 3, 'S', Menu.aiBoard); //E9, V
//        BuildShip(new int[]{5, 4}, false, 2, 'D', Menu.aiBoard); //F5, H

        for (int i = 0; i < ships.length; i++) { //Loop through each ship
            do {
                Menu.DisplayBoard();

                //Take in coordinate
                System.out.println("Where would you like to place your " + ships[i].getShipName() + "? Enter a coordinate (e.g. D5):");
                int[] inputCoordinate = input.GetCoords();
//                System.out.println(Arrays.toString(inputCoordinate)); //For testing

                String inputDirectionStr;
                boolean vertical = false;
                do {
                    inputDirectionStr = input.GetUserStr("Would you like to place that ship (V)ertically down or (H)orizontally right from that position?", true).toLowerCase();
//                    System.out.println(inputDirectionStr); //For testing
                } while (!(inputDirectionStr.equals("v") || inputDirectionStr.equals("h"))); //Validate "V" or "H"

                if (inputDirectionStr.equals("v")) {
                    vertical = true;
                } //Else false is implied

                //Take in 'V' or 'H', build ship accordingly
                //"V" = true, "H" = false

                shipBuilt = BuildShip(inputCoordinate, vertical, ships[i].getShipLength(), ships[i].getShipLetter(), Menu.playerBoard);
                if (!shipBuilt) {
                    System.out.println("That ship won't fit there! Try a different coordinate or direction.");
                }
            } while (!shipBuilt);
        }
    }

    public static boolean BuildShip(int[] coordinate, boolean vertical, int shipLength, char shipChar, char[][] board) {
//        System.out.println("coordinate: (" + coordinate[0] + ", " + coordinate[1] + ") vertical: " + vertical);
        if (vertical) {
            if (coordinate[0] + shipLength <= 10) {
                for (int i = 0; i < shipLength; i++) {
                    if (board[coordinate[0] + i][coordinate[1]] != '~') {
//                        System.out.println("Ship already there");
                        return false;
                    }
                }
                for (int i = 0; i < shipLength; i++) {
                    board[coordinate[0] + i][coordinate[1]] = shipChar;
                }
//                System.out.println("Ship built");
                return true;
            }
        } else {
            if (coordinate[1] + shipLength <= 10) {
                for (int i = 0; i < shipLength; i++) { //Check for other ships in the way
                    if (board[coordinate[0]][coordinate[1] + i] != '~') {
//                        System.out.println("Ship already there");
                        return false;
                    }
                }
                for (int i = 0; i < shipLength; i++) { //Build the ships
                    board[coordinate[0]][coordinate[1] + i] = shipChar;
                }
//                System.out.println("ship built");
                return true;
            }
        }
//        System.out.println("Out of Bounds");
        return false;
    }

    public static boolean HitShip(char[][] board, int[] coords) {
        if (/*the coordinate hits something that isn't a tilde it has hit a ship*/ board[coords[0]][coords[1]] != '~') { //if the board index at the coordinate is anything other than a tilde
            return true;
        }
        return false;
    }

    public static boolean SunkShip(char[][] board, char shipChar) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == shipChar)
                    return false; //If it found any of the hit character
            }
        }
        return true; //If it did not find any, it will return true: the ship was sunk
    }

    public static String getShipNameFromChar(char shipChar) {
        for (int i = 0; i < ships.length; i++) {
            if (ships[i].getShipLetter() == shipChar) {
                return ships[i].getShipName();
            }
        }
        return null;
    }

    public static boolean AITurn() { //Very similar to PlayerTurn()
        char[] rows = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        int[] coords;
        char shipChar;

        do {
            int row = random.nextInt(0, 9);
            int col = random.nextInt(0, 9);

            coords = new int[]{row, col};

            shipChar = Menu.playerBoard[coords[0]][coords[1]];

        } while (shipChar == 'X' || shipChar == 'O');

        System.out.printf("The AI attacks %c%d\n", rows[coords[0]], coords[1] + 1); //Magic


        if (HitShip(Menu.playerBoard, coords)) {
            System.out.println("The AI hit your " + getShipNameFromChar(shipChar) + "!");
            Menu.playerBoard[coords[0]][coords[1]] = 'X';

            if (SunkShip(Menu.playerBoard, shipChar)) { //Check for sink
                System.out.println("The AI sunk your " + getShipNameFromChar(shipChar) + "!");
                playerShipsSunk++;

                if (playerShipsSunk == 5) { //Check for win
                    System.out.println("How could you have lost, the AI was picking random spots!");
                    return true;
                }
            }
        } else {
            System.out.println("The AI Missed!");
            Menu.playerBoard[coords[0]][coords[1]] = 'O';
        }
        return false;
    }


    public static void AIShipPlacing() {
        int boatPlacement = random.nextInt(1, 6);
//        System.out.println(boatPlacement); //For testing
        switch (boatPlacement) {
            case 1:
                Gameplay.BuildShip(new int[]{2, 2}, false, 5, 'C', Menu.aiBoard); //C3, H
                Gameplay.BuildShip(new int[]{9, 5}, false, 4, 'B', Menu.aiBoard); //J6, H
                Gameplay.BuildShip(new int[]{4, 6}, true, 3, 'R', Menu.aiBoard); //E7, V
                Gameplay.BuildShip(new int[]{4, 8}, true, 3, 'S', Menu.aiBoard); //E9, V
                Gameplay.BuildShip(new int[]{5, 4}, false, 2, 'D', Menu.aiBoard); //F5, H
                break;

            case 2:
                Gameplay.BuildShip(new int[]{2, 2}, true, 5, 'C', Menu.aiBoard); //C3, V
                Gameplay.BuildShip(new int[]{6, 5}, true, 4, 'B', Menu.aiBoard); //G6, V
                Gameplay.BuildShip(new int[]{4, 6}, false, 3, 'R', Menu.aiBoard); //E7, H
                Gameplay.BuildShip(new int[]{6, 7}, true, 3, 'S', Menu.aiBoard); //G8, V
                Gameplay.BuildShip(new int[]{5, 4}, true, 2, 'D', Menu.aiBoard); //F5, V
                break;

            case 3:
                Gameplay.BuildShip(new int[]{1, 1}, true, 5, 'C', Menu.aiBoard); //B2, V
                Gameplay.BuildShip(new int[]{1, 2}, true, 4, 'B', Menu.aiBoard); //B3, V
                Gameplay.BuildShip(new int[]{1, 3}, true, 3, 'R', Menu.aiBoard); //B4, V
                Gameplay.BuildShip(new int[]{1, 4}, true, 3, 'S', Menu.aiBoard); //B5, V
                Gameplay.BuildShip(new int[]{1, 5}, true, 2, 'D', Menu.aiBoard); //B6, V
                break;

            case 4:
                Gameplay.BuildShip(new int[]{3, 0}, false, 5, 'C', Menu.aiBoard); //D1, H
                Gameplay.BuildShip(new int[]{4, 1}, false, 4, 'B', Menu.aiBoard); //E2, H
                Gameplay.BuildShip(new int[]{5, 2}, false, 3, 'R', Menu.aiBoard); //F3, H
                Gameplay.BuildShip(new int[]{6, 3}, false, 3, 'S', Menu.aiBoard); //G4, H
                Gameplay.BuildShip(new int[]{8, 4}, false, 2, 'D', Menu.aiBoard); //H5, H
                break;

            case 5:
                Gameplay.BuildShip(new int[]{2, 0}, true, 5, 'C', Menu.aiBoard); //C1, V
                Gameplay.BuildShip(new int[]{6, 1}, false, 4, 'B', Menu.aiBoard); //F2, H
                Gameplay.BuildShip(new int[]{2, 5}, false, 3, 'R', Menu.aiBoard); //C6, H
                Gameplay.BuildShip(new int[]{9, 7}, false, 3, 'S', Menu.aiBoard); //J8, H
                Gameplay.BuildShip(new int[]{5, 5}, true, 2, 'D', Menu.aiBoard); //F6, V
                break;
        }
    }


    public static void GamePlayLoop() {
        boolean quit;
        do {
            //Reset the game on restart
            aiShipsSunk = 0;
            playerShipsSunk = 0;
            Menu.InitializeBoards();

            AIShipPlacing(); //Place AI ships and player ships
            PlaceShips();

            boolean validInput;
            while (true) {
                Menu.DisplayBoard();
                if (Menu.PlayerTurn()) { //Returns true if player won
                    break;
                }
                if (AITurn()) { //Returns true if AI won
                    break;
                }
            }
            do {
                String playAgain = input.GetUserStr("Play again? (Y/N)", true).toLowerCase();
                validInput = playAgain.equals("y") || playAgain.equals("n");
                if (playAgain.equals("y")) {
                    //returns false to replay the game
                    quit = false;

                } else {
                    //quit
                    quit = true;
                }

            } while (!validInput);
        } while (!quit);
    }
}
