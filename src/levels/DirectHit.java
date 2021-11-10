package levels;

import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Block;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class create level 1 of the game.
 *
 * @author Dvir Asaf 313531113.
 */
public class DirectHit implements LevelInformation {
    private BackGroundOne backGroundOne;

    /**
     * constructor for this class.
     */
    public DirectHit() {
        this.backGroundOne = new BackGroundOne();
    }

    /**
     * @return the number of blacks that the user need for move to the next level.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * @return this level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return the width of the paddle for this level.
     */
    public int paddleWidth() {
        return Utilities.WIDTH_OF_PADDLE / 2;
    }

    /**
     * @return the speed of the paddle for this level.
     */
    public int paddleSpeed() {
        return Utilities.MOVEMENT;
    }

    /**
     * @return the number of balls we create for this level.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * @return list of blacks for this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point upLeft = new Point((Utilities.GAME_WIDTH / 2) - (Utilities.BLOCK_HEIGHT / 2),
                (Utilities.GAME_HEIGHT / 3.25));
        Rectangle rect = new Rectangle(upLeft, Utilities.BLOCK_HEIGHT, Utilities.BLOCK_HEIGHT);
        Block block = new Block(rect, Color.RED);
        blocks.add(block);
        return blocks;
    }

    /**
     * @return the background for this level.
     */
    public Sprite getBackground() {
        return this.backGroundOne;
    }

    /**
     * @return list of velocity for the balls in this level.
     */
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Velocity velocity = new Velocity(Utilities.VELOCITY_DX, Utilities.VELOCITY_DY);
        velocities.add(velocity);
        return velocities;
    }

}
