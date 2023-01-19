package Controller;

import View.Menu;

    public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.GameBoard();
        Menu.welcome();
        menu.PlaceShips();

    }
}
