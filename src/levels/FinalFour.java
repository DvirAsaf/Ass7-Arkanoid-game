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
 * this class create level 4 of the game.
 *
 * @author Dvir Asaf 313531113.
 */
public class FinalFour implements LevelInformation {

    private BackGroundFour backGround;

    /**
     * constructor for this class.
     */
    public FinalFour() {
        this.backGround = new BackGroundFour();
    }

    /**
     * @return the number of balls we create for this level.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * @return this level name.
     */
    public String levelName() {
        return "Final Four";
    }
    /**
     * @return the number of blacks that the user need for move to the next level.
     */
    public int numberOfBlocksToRemove() {
        return 133;
    }

    /**
     * @return the width of the paddle for this level.
     */
    public int paddleWidth() {
        return Utilities.WIDTH_OF_PADDLE;
    }

    /**
     * @return the speed of the paddle for this level.
     */
    public int paddleSpeed() {
        return Utilities.MOVEMENT;
    }

    /**
     * @return the background for this level.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * @return list of velocity for the balls in this level.
     */
    public List<Velocity> initialBallVelocities() {
        // creating a list of velocities
        List<Velocity> listOfVelocity = new ArrayList<>();
        // adding velocities to the list
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(-4 + i * 4, -4);
            listOfVelocity.add(v);
        }
        // returning the list
        return listOfVelocity;
    }

    /**
     * @return list of blacks for this level.
     */
    public List<Block> blocks() {
        // creating a list of blocks
        List<Block> blockList = new ArrayList<>();
        int blocksNumber = 19;
        int colorsNumber = 7;
        int positionInRow = 0;
        int positionInColumn = 0;
        for (int i = 0; i < colorsNumber; i++) {
            java.awt.Color color = getColor(i);
            // the internal loop runs on the blocks of each line
            for (int j = 0; j < blocksNumber; j++) {
                // creating a new block
                Point p = new Point(Utilities.GAME_WIDTH - Utilities.FRAME_SIZE - Utilities.BLOCK_WIDTH - positionInRow,
                        Utilities.FIRST_ROW_OF_BLOCKS_HEIGHT + positionInColumn);
                Rectangle r = new Rectangle(p, Utilities.BLOCK_WIDTH, Utilities.BLOCK_HEIGHT);
                Block b = new Block(r, color);
                positionInRow += Utilities.BLOCK_WIDTH;
                blockList.add(b);

            }
            positionInRow = 0;
            positionInColumn += Utilities.BLOCK_HEIGHT;
        }
        // returning the list
        return blockList;
    }

    /**
     * @param num a given int that is the row of the blacks.
     * @return the color for the row of blacks.
     */
    public java.awt.Color getColor(final int num) {
        if (num == 0) {
            return Color.GRAY;
        } else if (num == 1) {
            return Color.RED;
        } else if (num == 2) {
            return Color.YELLOW;
        } else if (num == 3) {
            return Color.GREEN;
        } else if (num == 4) {
            return Color.WHITE;
        } else if (num == 5) {
            return Color.PINK;
        } else if (num == 6) {
            return Color.BLUE;
        } else {
            return Color.BLACK;
        }
    }

}
