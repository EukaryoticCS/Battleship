public class Gameplay {
    private static char[][] board = new char[10][10];
    private int rowSize;
    private int columnSize;
    public static  final char OPEN_SYMBOL = ' ';


    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }
    public void print() {
        for (int row = 0; row < rowSize; row++) {
            System.out.print("| ");
            for (int column = 0; column < columnSize; column++) {
                System.out.print(board[row][column]);
                if (column < columnSize - 1) System.out.print(" | ");
            }
        }
        System.out.println();
    }

    public void InitializeBoard(char[][] board){
        for (int row = 0; row < board[0].length; row++)
        {
            for (int col = 0; col < board[1].length; col++)
            {
                board[col][row] = '~';
            }
        }
    }


}
