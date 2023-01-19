package Controller;

import View.Menu;

public class Gameplay {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.GameBoard(10,10);
        Menu.welcome();
        menu.PlaceShips();

    }
}
