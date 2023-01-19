public class Gameplay {
    public static char[][] board;
    private int rowSize;
    private int columnSize;

    public void GameBoard(int columnSize, int rowSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        board = new char[rowSize][columnSize];
    }
    public void print() {
        for (int row = 0; row < board[0].length; row++) {
            System.out.print("| ");
            for(int column = 0; column < board[1].length; column++){
                System.out.print(board[row][column] = '~');
                System.out.print(" | ");
            }
            System.out.println();
        }
    }


}
