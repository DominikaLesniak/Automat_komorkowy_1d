package sample;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import sample.rules.BaseRule;

public class Simulation {
    public int[][] runSimulation(BaseRule rule, int xSize, int ySize) throws WrongNumberArgsException {
        int[][] board = initiateBoard(ySize, xSize);

        for (int i = 0; i < ySize-1; i++) {
            for (int j = 0; j < xSize; j++) {
                int leftCellIndex = (j == 0 ? xSize-1 : j-1);
                int rightCellIndex = (j == xSize-1 ? 0 : j+1);
                int nextGenerationValue = rule.getNextGenerationValue(board[i][leftCellIndex], board[i][j], board[i][rightCellIndex]);
                board[i+1][j] = nextGenerationValue;
            }
        }
        //printBoard(board);
        return board;
    }

    private int[][] initiateBoard(int ySize, int xSize) {
        int[][] board = new int[ySize][xSize];

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                board[i][j] = 0;
            }
        }
        int middle = xSize / 2;
        board[0][middle] = 1;
        return board;
    }

    private void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                print(board[i][j]);
            }
            System.out.println("");
        }
    }

    private void print(int sign) {
        if (sign == 0)
            System.out.print("  ");
        else
            System.out.print("# ");
    }
}
