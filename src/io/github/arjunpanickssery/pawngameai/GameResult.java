package io.github.arjunpanickssery.pawngameai;

import java.util.ArrayList;

public class GameResult {
    public static final int RESULT_WHITE_WINS = 1;
    public static final int RESULT_DRAW = 0;
    public static final int RESULT_BLACK_WINS = -1;

    private int numberOfRows;
    private int numberOfColumns;

    private Player whitePlayer;
    private Player blackPlayer;

    private ArrayList<Move> moves;
    private int result;

    public GameResult(int numberOfRows, int numberOfColumns, Player whitePlayer, Player blackPlayer) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        moves = new ArrayList<>();
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public int getResult() {
        return result;
    }

}
