package utillity;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import java.awt.Image;
import java.awt.Color;
/**
 * this class responsible of background with image.
 * @author Dvir Asaf 313531113.
 */
public class ImageBackground implements Sprite, BackGround {
    private Rectangle rectangle;
    private Image image;
    /**
     * Instantiates a new Image background.
     *
     * @param i the image
     */
    public ImageBackground(final Image i) {
        image = i;
        rectangle = new Rectangle(new Point(Utilities.GAME_START_X, Utilities.GAME_START_Y), Utilities.GAME_WIDTH,
            Utilities.GAME_HEIGHT);
    }
    /**
     * this method set rectangle.
     *
     * @param rec a given rectangle.
     */
    public void setRec(final Rectangle rec) {
        rectangle = rec;
    }
    /**
     * @return our rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
    /**
     * For this class, currently we do nothing.
     *
     * @param color a given color.
     */
    public void setStroke(final Color color) {
    }
    /**
     * draw on.
     *
     * @param d a given draw surface.
     */
    public void drawOn(final DrawSurface d) {
        int recX = (int) rectangle.getUpperLeft().getX();
        int recY = (int) rectangle.getUpperLeft().getY();
        d.drawImage(recX, recY, image);
    }
    /**
     * this method add to the game a given game level.
     *
     * @param gameLevel a given game level.
     */
    public void addToGame(final GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * For this class, currently we do nothing.
     */
    public void timePassed() {
    }
}
