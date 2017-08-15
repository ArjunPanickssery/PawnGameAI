package io.github.arjunpanickssery.pawngameai;

public class Move {
    public static final String[] alphabet = {};
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private int type;

    public static final int ONE_SPACE_MOVE = 0;
    public static final int TWO_SPACE_MOVE = 1;
    public static final int REGULAR_CAPTURE = 2;
    public static final int EN_PASSANT_LEFT = 3;
    public static final int EN_PASSANT_RIGHT = 4;


    public Move(int startRow, int startColumn, int endRow, int endColumn, int type) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.type = type;
    }

    public String getMoveString() {
        return getColumnLetter(startRow)
                + startColumn
                + "-"
                + getColumnLetter(endRow)
                + endColumn;
    }

    private String getColumnLetter(int i) {
        return ((i > 0) && (i < 27)) ? String.valueOf((char) (i + 64)) : null;
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
}
