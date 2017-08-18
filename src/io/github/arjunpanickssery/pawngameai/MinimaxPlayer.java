package io.github.arjunpanickssery.pawngameai;

import java.util.ArrayList;


public class MinimaxPlayer extends Player {
    @Override
    public Move computeMove() {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        int score;
        for (Move move : moves) {
            score = -negamax(playMove(board, move, color), 500, Integer.MIN_VALUE * color, Integer.MAX_VALUE * color, color);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        System.out.println(bestMove.getMoveString());
        return bestMove;
    }

    public int negamax(int[][] board, int depth, int alpha, int beta, int color) {
        //Check for wins
        for (int i : board[0]) {
            if (i != 0) {
                return -1;
            }
        }
        for (int i : board[board.length - 1]) {
            if (i != 0) {
                return 1;
            }
        }

        ArrayList<Move> moves = getMoves(board, color);

        //Check for draws
        if (moves.size() == 0) {
            moves = getMoves(board, -color);
            if (moves.size() == 0) {
                return 0;
            }
        }

        int bestScore = Integer.MIN_VALUE;
        int score;
        for (Move move : moves) {
            score = -negamax(playMove(board, move, color), depth - 1, -beta, -alpha, -color);
            bestScore = (score > bestScore) ? score : bestScore;
            alpha = (score > alpha) ? score : alpha;
            if (alpha >= beta) {
                return bestScore;
            }
        }
        return bestScore;
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

    @Override
    public String getName() {
        return "Minimax Player";
    }
}
