package Controller;

import View.Menu;
import View.MyInput;

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


    public boolean BuildShip(int[] coordinate, boolean vertical, int shipLength, char shipChar,char[][] board) {
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
}
