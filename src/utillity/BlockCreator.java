package utillity;
import sprites.Block;
/**
 * @author Dvir Asaf 313531113.
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xPos the x position
     * @param yPos the y position
     * @return the block
     */
    Block create(int xPos, int yPos);
}
