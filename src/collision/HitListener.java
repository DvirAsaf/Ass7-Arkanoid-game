package collision;

import sprites.Ball;
import sprites.Block;

/**
 * @author dvir asaf 313531113.
 */

public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the SpritePkg.Ball that's doing the hitting.
     *
     * @param beingHit called whenever the object hit.
     * @param hitter   ball that doing the hitting.
     */

    void hitEvent(Block beingHit, Ball hitter);
}
