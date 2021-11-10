package listeners;

import collision.HitListener;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author dvir asaf 313531113.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number of blocks that.
 * remain.
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * @param game          a given game.
     * @param removedBlocks a given counter.
     */

    public BlockRemover(final GameLevel game, final Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
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
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }

    /**
     * @return the counter value of remainig blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
