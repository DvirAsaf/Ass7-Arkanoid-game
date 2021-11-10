package levels;

import collision.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * Dvir Asaf 313531113.
 */

public interface LevelInformation {
    /**
     * @return the balls number.
     */
    int numberOfBalls();

    /**
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * the Blocks that make up this level, each block contains its size, color and location.
     *
     * @return lisr of blacks.
     */
    List<Block> blocks();

    /**
     * number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return this number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}
