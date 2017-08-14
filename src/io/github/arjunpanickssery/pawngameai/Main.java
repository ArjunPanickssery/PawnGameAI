package io.github.arjunpanickssery.pawngameai;

public class Main {

    public static final int WHITE = 1;
    public static final int BLACK = -1;
    public static final int CAN_DOUBLE_MOVE = 2;
    public static final int CAN_BE_EN_PASSANTED = 3;

    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;

    public static void main(String[] args) {
        int[][] board = initializeBoard();
    }

    static int[][] initializeBoard() {
        int[][] board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            board[1][i] = WHITE * CAN_DOUBLE_MOVE;
            board[NUMBER_OF_ROWS - 2][i] = BLACK * CAN_DOUBLE_MOVE;
        }

        return board;
    }

}
