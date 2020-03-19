package org.gfg.backtrack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class KnightsTourTest{
    @Test
    public void generateKnightsTour(){
        int[][] board = new int[8][8];
        boolean result = KnightsTour.knightsTour(board);
        assertThat(result, is(true));
        assertThat(isValidTour(board), is(true));
        printBoard(board);
    }

    private final int[] dc = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    private final int[] dr = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
    private boolean insideBoard(int[][] board, int i, int j){
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
    private boolean isValidTour(int[][] board){ return isValidTour(board, 0, 0, 1); }
    private boolean isValidTour(int[][] board, int row, int col, int n){
        if(board[row][col] == board.length * board[0].length) return true;
        for(int i = 0; i < dr.length; i++){
            int newRow = row + dr[i]; int newCol = col + dc[i];
            if(insideBoard(board, newRow, newCol) && board[newRow][newCol] == n + 1){
                return isValidTour(board, newRow, newCol, n + 1);
            }
        }
        return false;
    }

    private void printBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}