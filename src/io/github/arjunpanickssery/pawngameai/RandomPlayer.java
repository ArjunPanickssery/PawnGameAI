package io.github.arjunpanickssery.pawngameai;

import java.util.Random;

public class RandomPlayer extends Player {
    Random r = new Random();
    @Override
    public Move computeMove() {
        return moves.get(r.nextInt(moves.size()));
    }

    @Override
    public String getName() {
        return "Random Player";
    }
}
