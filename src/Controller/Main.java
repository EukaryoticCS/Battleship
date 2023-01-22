package Controller;

import View.Menu;

    public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Gameplay gameplay = new Gameplay();

        menu.GameBoard(); //Initialize the gameboard
        Menu.welcome();
        gameplay.PlaceShips();

    }
}
