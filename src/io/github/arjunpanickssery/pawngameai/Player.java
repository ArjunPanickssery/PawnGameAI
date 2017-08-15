package io.github.arjunpanickssery.pawngameai;

import java.util.ArrayList;

public abstract class Player {
    private int[][] board;
    private int color;
    private ArrayList<Move> moves;

    public Player() {
    }

    private ArrayList<Move> getMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        int piece;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                piece = board[i][j];
                //If the piece is of the correct color
                if (isCorrectColor(piece)) {
                    //Check for one-space moves
                    if (board[i + color][j] == 0) {
                        moves.add(new Move(i, j, i + color, j, Move.ONE_SPACE_MOVE));
                        //Check for two space moves
                        if (piece % Game.CAN_DOUBLE_MOVE == 0) {
                            if (board[i + 2 * color][j] == 0) {
                                moves.add(new Move(i, j, i + 2 * color, j, Move.TWO_SPACE_MOVE));
                            }
                        }
                    }
                    if (j > 0) {
                        //Check for regular captures (going left)
                        if (board[i + color][j - 1] != 0 && !isCorrectColor(board[i + color][j - 1])) {
                            moves.add(new Move(i, j, i + color, j - 1, Move.REGULAR_CAPTURE));
                        }
                        //Check for en passant captures (going left)
                        if ((color == Game.WHITE && i == board.length - 4) || (color == Game.WHITE && i == 3)) {
                            if ((board[i][j - 1] % Game.CAN_BE_EN_PASSANTED == 0) && (board[i + color][j - 1] == 0)) {
                                moves.add(new Move(i, j, i + color, j - 1, Move.EN_PASSANT_LEFT));
                            }
                        }
                    }
                    if (j < board[0].length - 1) {
                        //Check for regular captures (going right)
                        if (board[i + color][j - 1] != 0 && !isCorrectColor(board[i + color][j + 1])) {
                            moves.add(new Move(i, j, i + color, j + 1, Move.REGULAR_CAPTURE));
                        }
                        //Check for en passant captures (going right)
                        if ((color == Game.WHITE && i == board.length - 4) || (color == Game.WHITE && i == 3)) {
                            if ((board[i][j + 1] % Game.CAN_BE_EN_PASSANTED == 0) && (board[i + color][j + 1] == 0)) {
                                moves.add(new Move(i, j, i + color, j + 1, Move.EN_PASSANT_RIGHT));
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    public Move getMove(int[][] board, int color) {
        this.board = board;
        this.color = color;
        this.moves = getMoves();

        if (getMoves().size() == 0) {
            return null;
        }
        return computeMove();
    }

    private boolean isCorrectColor(int piece) {
        return (piece != 0) && ((piece > 0) == (color == Game.WHITE));
    }

    //Abstract methods
    public abstract Move computeMove();

    public abstract String getName();
}
