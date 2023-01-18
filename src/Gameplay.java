public class Gameplay {
    private char[][] board;
    private int rowSize;
    private int columnSize;
    public static  final char OPEN_SYMBOL = ' ';


    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void GameBoard(int columnSize, int rowSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        board = new char[rowSize][columnSize];
    }
    public void print() {
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                System.out.print(board[row][column]);
                if (column < columnSize - 1) System.out.print("|");
            }
            if (row < rowSize - 1) {
                System.out.println();
                for (int column = 0; column < columnSize; column++) {
                    System.out.println(" | ");
                }
                System.out.println();
            }
        }
        System.out.println();

    }
}
