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
 * this class create level 2 of the game.
 *
 * @author Dvir Asaf 313531113.
 */
public class WideEasy implements LevelInformation {
    private BackGroundTwo backGround;

    /**
     * constructor for this class.
     */
    public WideEasy() {
        this.backGround = new BackGroundTwo();
    }

    /**
     * @return the number of blacks that the user need for move to the next level.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * @return this level name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return the width of the paddle for this level.
     */
    public int paddleWidth() {
        return Utilities.WIDTH_OF_PADDLE * 2;
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
        return 10;
    }

    /**
     * @return the background for this level.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * @return list of blacks for this level.
     */
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> listOfBlocks = new ArrayList<Block>();
        int x = 0;
        // creating blocks in 1 line
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            java.awt.Color color = getColor(i);
            Point p = new Point(Utilities.FRAME_SIZE + x, Utilities.GAME_HEIGHT / 2.5);
            Rectangle r = new Rectangle(p, Utilities.BLOCK_WIDTH * 1.275, Utilities.BLOCK_HEIGHT);
            Block b = new Block(r, color);
            listOfBlocks.add(b);
            x += (Utilities.BLOCK_WIDTH * 1.275);
        }
        // returning the list of blocks
        return listOfBlocks;
    }

    /**
     * @param num a given int that is location of the blacks.
     * @return the color for the block.
     */
    public java.awt.Color getColor(final int num) {
        if (num == 0 || num == 1) {
            return Color.RED;
        } else if (num == 2 || num == 3) {
            return Color.orange;
        } else if (num == 4 || num == 5) {
            return Color.YELLOW;
        } else if (num == 6 || num == 7 || num == 8) {
            return Color.green;
        } else if (num == 9 || num == 10) {
            return Color.BLUE;
        } else if (num == 11 || num == 12) {
            return Color.PINK;
        } else if (num == 13 || num == 14) {
            return Color.CYAN;
        }
        return Color.WHITE;
    }

    /**
     * @return list of velocity for the balls in this level.
     */
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(-4 + i, -2);
            listOfVelocity.add(v);
        }
        // returning the list
        return listOfVelocity;
    }
}
