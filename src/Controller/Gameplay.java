package Controller;

import View.Menu;

public class Gameplay {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Menu.welcome();
        menu.PlaceShips();
    }
}
