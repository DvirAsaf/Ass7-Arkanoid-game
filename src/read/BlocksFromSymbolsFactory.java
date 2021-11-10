package read;
import utillity.BlockCreator;
import sprites.Block;
import java.util.Map;
/**
 * this class responsible of blacks from symbols factory.
 * @author Dvir Asaf 313531113.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, BlockCreator> creatorsOfBlock;
    private Map<String, Integer> spaceWidths;

    /**
     * constructor for this class.
     *
     * @param spacer  a given map.
     * @param block a given map.
     */
    public BlocksFromSymbolsFactory(final Map<String, Integer> spacer, final Map<String, BlockCreator> block) {
        this.spaceWidths = spacer;
        this.creatorsOfBlock = block;
    }

    /**
     * @param string a given string.
     * @return true if 'string' is a valid space symbol.
     */
    public boolean isSpaceSymbol(final String string) {
        return (this.spaceWidths.containsKey(string));
    }

    /**
     * @param string a given string.
     * @return true if 'string' is a valid block symbol.
     */
    public boolean isBlockSymbol(final String string) {
        return (this.creatorsOfBlock.containsKey(string));
    }

    /**
     * Gets block.
     *
     * @param string a given string.
     * @param x the x
     * @param y the y
     * @return a block according to the definitions associated with symbol string.
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(final String string, final int x, final int y) {
        return this.creatorsOfBlock.get(string).create(x, y);
    }

    /**
     * this method get the space width.
     *
     * @param string a given string.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(final String string) {
        return this.spaceWidths.get(string);
    }
}
