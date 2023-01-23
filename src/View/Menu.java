package View;

import Controller.Gameplay;

public class Menu {
    MyInput input = new MyInput();
    Gameplay gameplay = new Gameplay();
    public static char[][] board;

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

    public void GameBoard() {
        board = new char[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = '~';
            }
        }
    }

    public static void DisplayBoard() {
        char[] rowChars = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; //For row labels

        System.out.println("   1   2   3   4   5   6   7   8   9   10");//Column labels

        for (int row = 0; row < board.length; row++) {
            System.out.print(rowChars[row] + "| ");
            for (int column = 0; column < board[1].length; column++) {
                System.out.print(board[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
    public void AttackingMenu(){
        System.out.println("Attack what Coords?(Column,Row)");


        int[] coords = new int[]{};
        do{
        coords = input.GetCoords();
        if(board[coords[0]][coords[1]] == 'X' || board[coords[0]][coords[1]] == 'O'){
            System.out.println("Already been attacked! Try a different spot!");
        }
        } while (board[coords[0]][coords[1]] == 'X' || board[coords[0]][coords[1]] == 'O');

        boolean HitShip = Gameplay.HitShip(coords);
        if (HitShip) {
            System.out.println("Hit");
        } else {
            System.out.println("Miss");
        }

    }

}
