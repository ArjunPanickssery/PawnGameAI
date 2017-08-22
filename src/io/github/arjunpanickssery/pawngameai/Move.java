package io.github.arjunpanickssery.pawngameai;

public class Move {
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private int type;
    private int startPiece;
    private int capturedPiece;

    public static final int ONE_SPACE_MOVE = 0;
    public static final int TWO_SPACE_MOVE = 1;
    public static final int REGULAR_CAPTURE = 2;
    public static final int EN_PASSANT_LEFT = 3;
    public static final int EN_PASSANT_RIGHT = 4;

    public static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Move(int startRow, int startColumn, int endRow, int endColumn, int type, int startPiece, int capturedPiece) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.type = type;
        this.startPiece = startPiece;
        this.capturedPiece = capturedPiece;
    }

    public String getMoveString() {
        return getColumnLetter(startColumn)
                + (startRow + 1)
                + "-"
                + getColumnLetter(endColumn)
                + (endRow + 1);
    }

    private String getColumnLetter(int i) {
        return Character.toString(alphabet[i]);
    }

    public int getStartRow() {
        return startRow;
    }
    public int getStartColumn() {
        return startColumn;
    }
    public int getEndRow() {
        return endRow;
    }
    public int getEndColumn() {
        return endColumn;
    }
    public int getType() {
        return type;
    }

    public int getStartPiece() {
        return startPiece;
    }

    public int getCapturedPiece() {
        return capturedPiece;
    }
}
