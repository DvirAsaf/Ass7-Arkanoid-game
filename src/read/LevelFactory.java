package read;
import collision.Velocity;
import levels.LevelInformation;
import sprites.Block;
import sprites.Sprite;
import java.util.ArrayList;
import java.util.List;
/**
 * this class responsible of level factory.
 * @author Dvir Asaf 313531113.
 */
public class LevelFactory implements LevelInformation {
    private List<Velocity> ballVelocities;
    private List<Object> blocksProperties;
    private String nameLevel;
    private Sprite background;
    private int ballsNumbers;
    private int blocksNumbers;
    private int paddleSpeed;
    private int paddleWidth;
    /**
     * constructor for this class.
     *
     * @param velocities      a given list of velocities.
     * @param speed           a given paddle speed.
     * @param width           a given paddle width.
     * @param name            a given level name.
     * @param b               a given background.
     * @param props      a given blocks properties.
     * @param numberBlocks       a given num blocks to remove.
     */
    public LevelFactory(final List<Velocity> velocities, final int speed, final int width, final String name,
                        final Sprite b, final List<Object> props, final int numberBlocks) {
        this.ballVelocities = velocities;
        this.blocksProperties = new ArrayList<>(props);
        this.nameLevel = name;
        this.background = b;
        this.ballsNumbers = this.ballVelocities.size();
        this.blocksNumbers = numberBlocks;
        this.paddleWidth = width;
        this.paddleSpeed = speed;
    }
    /**
     * this method create blocks list.
     *
     * @param props the block properties
     * @return list of blocks.
     */
    public List<Block> createBlocks(final List<Object> props) {
        List<String> blocksInformation = (List<String>) props.get(3);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = (BlocksFromSymbolsFactory) props.get(4);
        List<Block> blocks = new ArrayList<>();
        int currentHeight = (int) props.get(2);
        int currentWidth;
        for (String string : blocksInformation) {
            currentWidth = (int) props.get(1);
            int len = string.length();
            for (int i = 0; i < len; i++) {
                char a = string.charAt(i);
                if (blocksFromSymbolsFactory.isBlockSymbol(a + "")) {
                    Block newB = blocksFromSymbolsFactory.getBlock(a + "", currentWidth, currentHeight);
                    blocks.add(newB);
                    currentWidth += (int) newB.getCollisionRectangle().getWidth();
                } else if (blocksFromSymbolsFactory.isSpaceSymbol(a + "")) {
                    currentWidth += (blocksFromSymbolsFactory.getSpaceWidth(a + ""));
                } else {
                    throw new RuntimeException("Error symbol " + a);
                }
            }
            currentHeight += (int) props.get(0);
        }
        return blocks;
    }
    /**
     * @return the level name.
     */
    public String levelName() {
        return this.nameLevel;
    }
    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.ballsNumbers;
    }
    /**
     * @return speed of paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * @return width of paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the background.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * @return list of blocks.
     */
    public List<Block> blocks() {
        return createBlocks(this.blocksProperties);
    }
    /**
     * @return the number of blocks that we need to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.blocksNumbers;
    }
    /**
     * @return list of velocities for the balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }
}
