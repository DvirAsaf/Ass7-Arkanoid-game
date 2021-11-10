package listeners;

import collision.HitListener;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author Dvir Asaf 313531113.
 * of the number of blocks that remain.
 */

public class BallRemover implements HitListener {

    private GameLevel game;

    private Counter remainingBulls;

    /**
     * constructor - for this class.
     *
     * @param game         a given game.
     * @param removedbulls a given counter.
     */

    public BallRemover(final GameLevel game, final Counter removedbulls) {
        this.game = game;
        this.remainingBulls = removedbulls;
    }

    /**
     * If a hit occurs then lower the counter in one.
     *
     * @param beingHit called whenever the object hit.
     * @param hitter   ball that doing the hitting.
     */

    public void hitEvent(final Block beingHit, final Ball hitter) {
        if (hitter.isRemoveFromGame(this.game)) {
            remainingBulls.decrease(1);
        }
    }
}
