package io.github.arjunpanickssery.pawngameai;

public class Main {

    public static void main(String[] args) {
        //testWinRate(new RandomPlayer(), new RandomPlayer());

        Game game = new Game(8, 8, new RandomPlayer(), new InputPlayer());
        GameResult gr = game.startGame();
        System.out.println("RESULT:" + gr.getResult());
        System.out.println("RESULT:" + gr.getResult());
        System.out.println("RESULT:" + gr.getResult());
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
