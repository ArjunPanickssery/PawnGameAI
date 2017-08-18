package io.github.arjunpanickssery.pawngameai;

public class Main {

    public static void main(String[] args) {
        //testWinRate(new RandomPlayer(), new RandomPlayer());

        Game game = new Game(8, 8, new MinimaxPlayer(), new InputPlayer());
        GameResult gr = game.startGame();
        System.out.println("RESULT:" + gr.getResultString());

        /*int[][] board = new int[8][8];
        for(int i=0; i<8; i++){
            board[1][i] = 2;
            board[6][i] = -2;
        }

        board = Game.playMove(board, new Move(1, 0, 3, 0, 1), 1);

        System.out.println("  |---+---+---+---+---+---+---+---|");
        for (int i = 8; --i >= 0; ) {
            System.out.print(i + " | ");
            for (int n : board[i]) {
                System.out.print((n == 0 ? " " : (n > 0 ? "W" : "B")) + " | ");
            }
            System.out.println();
            System.out.println("  |---+---+---+---+---+---+---+---|");
        }
        System.out.println("    0   1   2   3   4   5   6   7  ");
        //System.out.println("+-------------------------------+");

        System.out.println();
        System.out.println();
        System.out.println("=============================================");
        System.out.println();
        System.out.println();*/
    }

    public static void testWinRate(Player playerOne, Player playerTwo) {
        Game game = new Game(8, 8, playerOne, playerTwo);
        int whiteWins = 0;
        int blackWins = 0;
        int draws = 0;

        final int TIMES = 1000000;
        int interval = 20;
        for (int i = 0; i < TIMES; i++) {
            if (i % (TIMES / interval) == 0) {
                System.out.println(i * 100 / TIMES + "%");
            }
            switch (game.startGame().getResult()) {
                case 1:
                    whiteWins++;
                    break;
                case 0:
                    draws++;
                    break;
                case -1:
                    blackWins++;
                    break;
            }
        }
        System.out.println("White Wins: " + whiteWins);
        System.out.println("Black Wins: " + blackWins);
        System.out.println("Draws: " + draws);
        System.out.println();
        System.out.println("White Win %: " + whiteWins * 100.0 / TIMES + "%");
        System.out.println("Black Win %: " + blackWins * 100.0 / TIMES + "%");
        System.out.println("     Draw %: " + draws * 100.0 / TIMES + "%");
    }
}
