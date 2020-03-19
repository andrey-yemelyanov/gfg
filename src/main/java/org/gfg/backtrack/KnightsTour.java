package org.gfg.backtrack;

public class KnightsTour{

    private static final int[] dc = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] dr = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};

    private static boolean insideBoard(int[][] board, int i, int j){
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }

    private static boolean knightsTour(int[][] board, int row, int col, int n){
        board[row][col] = n;
        if(board[row][col] == board.length * board[0].length) return true;
        for(int i = 0; i < dr.length; i++){
            int newRow = row + dr[i]; int newCol = col + dc[i];
            if(insideBoard(board, newRow, newCol) && board[newRow][newCol] == 0){
                if(knightsTour(board, newRow, newCol, n + 1)) return true;
            }
        }
        board[row][col] = 0;
        return false;
    }

    public static boolean knightsTour(int[][] board){
        if(board == null || board.length == 0 || board[0] == null) return false;
        return knightsTour(board, 0, 0, 1);
    }
}