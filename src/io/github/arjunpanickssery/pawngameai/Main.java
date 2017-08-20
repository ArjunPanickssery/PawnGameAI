package io.github.arjunpanickssery.pawngameai;

public class Main {

    public static void main(String[] args) {
        testWinRate(new RandomPlayer(), new MinimaxPlayer());

        //Game game = new Game(8, 4, new MinimaxPlayer(), new InputPlayer());
        //GameResult gr = game.startGame();
        //System.out.println("RESULT: " + gr.getResultString());
    }

    public static void testWinRate(Player playerOne, Player playerTwo) {
        long startTime = System.currentTimeMillis();
        Game game = new Game(8, 4, playerOne, playerTwo);
        int whiteWins = 0;
        int blackWins = 0;
        int draws = 0;

        final int TIMES = 100;
        int interval = 20;

        long start = System.currentTimeMillis();
        for (int i = 0; i < TIMES; i++) {
            if (i % (TIMES / interval) == 0) {
                System.out.println(i * 100 / TIMES + "%");
                System.out.println("---  " + (System.currentTimeMillis() - start) + " milliseconds");
                start = System.currentTimeMillis();
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
        System.out.println();
        System.out.println("Played " + TIMES + " games in " + (System.currentTimeMillis() - startTime) + " milliseconds.");
        System.out.println();
        System.out.println("White Wins: " + whiteWins);
        System.out.println("Black Wins: " + blackWins);
        System.out.println("Draws: " + draws);
        System.out.println();
        System.out.println("White Win %: " + whiteWins * 100.0 / TIMES + "%");
        System.out.println("Black Win %: " + blackWins * 100.0 / TIMES + "%");
        System.out.println("     Draw %: " + draws * 100.0 / TIMES + "%");
    }
}
