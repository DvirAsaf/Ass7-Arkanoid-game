package listeners;

import collision.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * @author dvir asaf 313531113.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number of blocks.
 * that remain.
 */

public class ScoreTrackingListener implements HitListener {
    private Counter scoreCounter;

    /**
     * a constructor of the score tracking listener.
     *
     * @param score a given counter.
     */
    public ScoreTrackingListener(final Counter score) {
        this.scoreCounter = score;
    }

    /**
     * Blocks that are hit should be removed from the game. Remember to remove this listener from the block.
     * that is being removed from the game.
     *
     * @param beingHit called whenever the object hit.
     * @param hitter   ball that doing the hitting.
     */
    public void hitEvent(final Block beingHit, final Ball hitter) {
        beingHit.removeHitListener(this);
        scoreCounter.increase(5);
    }

    /**
     * @return the value of counter scoreCounter.
     */

    public Counter getRemainingBlocks() {
        return scoreCounter;
    }
}
