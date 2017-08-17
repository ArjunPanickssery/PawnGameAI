package io.github.arjunpanickssery.pawngameai;

import java.util.Scanner;

public class InputPlayer extends Player {
    @Override
    public Move computeMove() {
        System.out.println("Please enter a legal move." + "\n" +
                "     ONE_SPACE_MOVE = 0;\n" +
                "     TWO_SPACE_MOVE = 1;\n" +
                "     REGULAR_CAPTURE = 2;\n" +
                "     EN_PASSANT_LEFT = 3;\n" +
                "     EN_PASSANT_RIGHT = 4;\n");
        char[] moveArgs = new Scanner(System.in).next().toCharArray();

        if (moveArgs.length != 5) {
            System.out.println("Illegal move. Please try again.");
            return computeMove();
        }
        for (char c : moveArgs) {
            if (!Character.isDigit(c)) {
                System.out.println("Illegal move. Please try again.");
                return computeMove();
            }
        }
        int startX = Character.getNumericValue(moveArgs[0]);
        int startY = Character.getNumericValue(moveArgs[1]);
        int endX = Character.getNumericValue(moveArgs[2]);
        int endY = Character.getNumericValue(moveArgs[3]);
        int type = Character.getNumericValue(moveArgs[4]);

        for (Move m : moves) {
            if (startX == m.getStartRow()
                    && startY == m.getStartColumn()
                    && endX == m.getEndRow()
                    && endY == m.getEndColumn()
                    && type == m.getType()) {
                return m;
            }
        }
        System.out.println("Illegal move. Please try again.");
        return computeMove();
    }

    @Override
    public String getName() {
        return "Input Player";
    }
}
