package read;
import geometry.Point;
import geometry.Rectangle;
import utillity.BackGround;
import utillity.BlockCreator;
import parsers.BackGroundParser;
import sprites.Block;
import java.util.Map;
import java.util.TreeMap;
/**
 * this class responsible of factory block creator.
 * @author Dvir Asaf 313531113.
 */
public class FactoryBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private int hitPoint;
    private Map<String, String> fillStartsMap;

    /**
     * constructor for this class.
     *
     * @param w    a given width.
     * @param h   a given height.
     * @param fillMap  a given string that start with fill and have map values.
     * @param hit      a given hit point.
     */
    public FactoryBlockCreator(final int w, final int h, final Map<String, String> fillMap, final int hit) {
        this.width = w;
        this.height = h;
        this.hitPoint = hit;
        this.fillStartsMap = fillMap;
    }


    /**
     * this method create from 2 int block.
     *
     * @param positionX the x position
     * @param positionY the y position
     * @return block
     */
    public Block create(final int positionX, final int positionY) {

        int size = fillStartsMap.keySet().size();
        String[] fillStarts = new String[fillStartsMap.keySet().size()];
        Map<Integer, BackGround> backGrounds = new TreeMap<>();


        int i = 0;
        while (i < size) {
            fillStarts[i] = (String) fillStartsMap.keySet().toArray()[i];
            i++;
        }

        for (String fillStart : fillStarts) {
            BackGroundParser backGroundParser = new BackGroundParser();
            BackGround newBackground = backGroundParser.backGroundFromString(fillStartsMap.get(fillStart));
            int x;
            if (fillStart.length() > "fill".length()) {
                x = Integer.parseInt(fillStart.substring("fill-".length()));
            } else {
                x = 1;
            }
            backGrounds.put(x, newBackground);
        }
        Block newBlock = new Block(positionX, positionY, this.width, this.height);
        newBlock.setHitPoint(this.hitPoint);

        for (BackGround value : backGrounds.values()) {
            Rectangle rectangle = new Rectangle(new Point(positionX, positionY), this.width, this.height);
            value.setRec(rectangle);
        }
        newBlock.addBackground(backGrounds);
        return newBlock;
    }
}
