package io.github.arjunpanickssery.pawngameai;

import java.util.ArrayList;


public class MinimaxPlayer extends Player {
    public static final int DEPTH = 10;

    @Override
    public Move computeMove() {
        int bestScore;
        Move bestMove = null;

        int score;
        if (color == Game.WHITE) {
            bestScore = Integer.MIN_VALUE;
            for (Move move : moves) {
                //System.out.println(moves.size());
                score = minimax(playMove(board, move, color), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, Game.BLACK);
                undoMove(board, move);
                //System.out.println("Score of " + move.getMoveString() + ": " + score);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
            return bestMove;
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Move move : moves) {
                score = minimax(playMove(board, move, color), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, Game.WHITE);
                undoMove(board, move);
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
            return bestMove;
        }
    }

    public int minimax(int[][] board, int depth, int alpha, int beta, int color) {
        if (depth == 0) {
            return heuristicEval(board);
        }
        //Check for wins
        for (int i : board[0]) {
            if (i != 0) {
                //System.out.println("Black Wins at depth = " + depth);
                //Game.printBoard(board);
                return -10 * DEPTH + depth;
            }
        }
        for (int i : board[board.length - 1]) {
            if (i != 0) {
                //System.out.println("Black Wins at depth = " + depth);
                return 10 * DEPTH - depth;
            }
        }

        ArrayList<Move> moves = getMoves(board, color);
        //Check for draws
        if (moves.size() == 0) {
            color *= -1;
            moves = getMoves(board, color);
            if (moves.size() == 0) {
                return 0;
            }
        }

        /*System.out.println("========   " + depth + "   ============");
        for(Move m : moves){
            System.out.println(m.getMoveString());
        }*/
        int bestScore;
        if (color == Game.WHITE) {
            bestScore = Integer.MIN_VALUE;
            for (Move move : moves) {
                bestScore = max(bestScore, minimax(playMove(board, move, color), depth - 1, alpha, beta, Game.BLACK));
                undoMove(board, move);
                alpha = max(alpha, bestScore);
                if (beta <= alpha) {
                    return bestScore;
                }
            }
            return bestScore;
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Move move : moves) {
                bestScore = min(bestScore, minimax(playMove(board, move, color), depth - 1, alpha, beta, Game.WHITE));
                undoMove(board, move);
                beta = min(beta, bestScore);
                if (beta <= alpha) {
                    return bestScore;
                }
            }
            return bestScore;
        }
    }

    public void undoMove(int[][] board, Move move) {
        board[move.getStartRow()][move.getStartColumn()] = move.getStartPiece();

        switch (move.getType()) {
            case Move.ONE_SPACE_MOVE:
                break;
            case Move.TWO_SPACE_MOVE:
                board[move.getStartRow()][move.getStartColumn()] *= Game.CAN_DOUBLE_MOVE;
                break;
            case Move.REGULAR_CAPTURE:
                board[move.getEndRow()][move.getEndColumn()] = move.getCapturedPiece();
                break;
            case Move.EN_PASSANT_LEFT:
                board[move.getStartRow()][move.getStartColumn() - 1] = move.getCapturedPiece();
                break;
            case Move.EN_PASSANT_RIGHT:
                board[move.getStartRow()][move.getStartColumn() + 1] = move.getCapturedPiece();
                break;
        }
    }

    public int[][] playMove(int[][] board, Move move, int color) {
        //Move the piece
        board[move.getStartRow()][move.getStartColumn()] = 0;
        board[move.getEndRow()][move.getEndColumn()] = color;

        //Prevent any piece from being en passanted
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] % Game.CAN_BE_EN_PASSANTED == 0) {
                    board[i][j] /= Game.CAN_BE_EN_PASSANTED;
                }
            }
        }
        switch (move.getType()) {
            case Move.ONE_SPACE_MOVE:
                break;
            case Move.TWO_SPACE_MOVE:
                board[move.getEndRow()][move.getEndColumn()] *= Game.CAN_BE_EN_PASSANTED;
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

        return board;
    }

    public int heuristicEval(int[][] board) {
        int eval = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    eval++;
                } else if (board[i][j] < 0) {
                    eval--;
                }
            }
        }
        return eval;
    }

    @Override
    public String getName() {
        return "Minimax Player";
    }

    public int max(int a, int b) {
        return b > a ? b : a;
    }

    public int min(int a, int b) {
        return b < a ? b : a;
    }
}
