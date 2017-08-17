package io.github.arjunpanickssery.pawngameai;

public class Game {

    public static final int WHITE = 1;
    public static final int BLACK = -1;
    public static final int CAN_DOUBLE_MOVE = 2;
    public static final int CAN_BE_EN_PASSANTED = 3;

    private int numberOfRows;
    private int numberOfColumns;
    private Player whitePlayer;
    private Player blackPlayer;

    private int[][] board;

    public Game(int numberOfRows, int numberOfColumns, Player whitePlayer, Player blackPlayer) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public GameResult startGame() {
        GameResult gameResult = new GameResult(numberOfRows, numberOfColumns, whitePlayer, blackPlayer);
        initializeBoard();

        Move move;
        int color = WHITE;

        while (true) {
            if (color == WHITE) {
                move = whitePlayer.getMove(board, WHITE);
            } else {
                move = blackPlayer.getMove(board, BLACK);
            }

            //Check for draws
            if (move == null) {
                color *= -1;

                if (color == WHITE) {
                    move = whitePlayer.getMove(board, WHITE);
                } else {
                    move = blackPlayer.getMove(board, BLACK);
                }

                if (move == null) {
                    gameResult.setResult(GameResult.RESULT_DRAW);
                    //System.out.println("Drawn");
                    return gameResult;
                }
            }

            //Move the piece
            board[move.getStartRow()][move.getStartColumn()] = 0;
            board[move.getEndRow()][move.getEndColumn()] = color;

            //Prevent any piece from being en passanted
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (board[i][j] % CAN_BE_EN_PASSANTED == 0) {
                        board[i][j] /= CAN_BE_EN_PASSANTED;
                    }
                }
            }
            switch (move.getType()) {
                case Move.ONE_SPACE_MOVE:
                    break;
                case Move.TWO_SPACE_MOVE:
                    board[move.getEndRow()][move.getEndColumn()] *= CAN_BE_EN_PASSANTED;
                    break;
                case Move.REGULAR_CAPTURE:
                    break;
                case Move.EN_PASSANT_LEFT:
                    board[move.getStartRow()][move.getStartColumn() - 1] = 0;
                    break;
                case Move.EN_PASSANT_RIGHT:
                    board[move.getStartRow()][move.getStartColumn() + 1] = 0;
                    break;
            }

            printBoard();

            //Check if someone won
            for (int i : board[0]) {
                if (i != 0) {
                    gameResult.setResult(GameResult.RESULT_BLACK_WINS);
                    //System.out.println("Black wins");
                    return gameResult;
                }
            }
            for (int i : board[numberOfRows - 1]) {
                if (i != 0) {
                    gameResult.setResult(GameResult.RESULT_WHITE_WINS);
                    //System.out.println("White wins");
                    return gameResult;
                }
            }
            color *= -1;
        }
    }

    public void initializeBoard() {
        board = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfColumns; i++) {
            board[1][i] = WHITE * CAN_DOUBLE_MOVE;
            board[numberOfRows - 2][i] = BLACK * CAN_DOUBLE_MOVE;
        }
    }

    public void printBoard() {
        System.out.println("|---+---+---+---+---+---+---+---|");
        for (int i = numberOfRows; --i >= 0; ) {
            System.out.print("| ");
            for (int n : board[i]) {
                System.out.print((n == 0 ? " " : (n > 0 ? "X" : "O")) + " | ");
            }
            System.out.println();
            System.out.println("|---+---+---+---+---+---+---+---|");
        }
        //System.out.println("+-------------------------------+");

        System.out.println();
        System.out.println();
        System.out.println("=============================================");
        System.out.println();
        System.out.println();

    }
}
