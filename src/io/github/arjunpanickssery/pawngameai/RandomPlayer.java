package io.github.arjunpanickssery.pawngameai;

import java.util.Random;

/**
 * Created by arjun on 8/15/2017.
 */
public class RandomPlayer extends Player {
    @Override
    public Move computeMove() {
        return moves.get(new Random().nextInt(moves.size()));
    }

    @Override
    public String getName() {
        return "Random Player";
    }
}
