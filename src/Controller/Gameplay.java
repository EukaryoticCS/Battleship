package Controller;

import View.Menu;
import View.MyInput;
import View.Ship;

import java.util.Arrays;

public class Gameplay {

    MyInput input = new MyInput();

    boolean win = false;

    public void Attack(char[][] board, int[] coordinate)
    {

//        do{
//            if(!win){
//                input.GetUserInt(){
//
//                }
//
//            }
//
//        }while(!win);
    }

    public void PlaceShips() {

        Ship[] ships = new Ship[] {
                new Ship("Carrier", 'C', 5),
                new Ship("Battleship", 'B', 4),
                new Ship("Cruiser", 'R', 3),
                new Ship("Submarine", 'S', 3),
                new Ship("Destroyer", 'D', 2)
        };

        boolean shipBuilt;

        //For Testing
//        gameplay.BuildShip(new int[]{2, 2}, false, 5, 'C',board); //C3, H
//        gameplay.BuildShip(new int[]{9, 5}, false, 4, 'B',board); //J6, H
//        gameplay.BuildShip(new int[]{4, 6}, true, 3, 'R',board); //E7, V
//        gameplay.BuildShip(new int[]{4, 8}, true, 3, 'S',board); //E9, V
//        gameplay.BuildShip(new int[]{5, 4}, false, 2, 'D',board); //F5, H

        Menu.DisplayBoard();
        for (int i = 0; i < ships.length; i++) { //Loop through each ship
            do {
                //Take in coordinate
                System.out.println("Where would you like to place your " + ships[i].getShipName() + "? Enter a coordinate (e.g. D5):");
                int[] inputCoordinate = input.GetCoords();
                System.out.println(Arrays.toString(inputCoordinate)); //For testing

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

                shipBuilt = BuildShip(inputCoordinate, vertical, ships[i].getShipLength(), ships[i].getShipLetter(), Menu.board);
                if (!shipBuilt) {
                    System.out.println("That ship won't fit there! Try a different coordinate or direction.");
                } else {
                    Menu.DisplayBoard(); //Shows the player the board after placing the ship
                }
            } while (!shipBuilt);
        }
    }

    public boolean BuildShip(int[] coordinate, boolean vertical, int shipLength, char shipChar,char[][] board) {
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
}
