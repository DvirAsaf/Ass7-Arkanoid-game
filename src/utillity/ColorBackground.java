package utillity;


import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
/**
 * this class is responsible of the color of the background.
 * @author Dvir Asaf 313531113.
 */
public class ColorBackground implements Sprite, BackGround {
    private Color color1;
    private Color color2;
    private Block block;
    /**
     * constructor - for this class.
     */
    public ColorBackground() {
        this.block = new Block(0, 0, 800, 600);
    }
    /**
     * this method draw the surface.
     * @param d the drawSurface
     */
    public void drawOn(final DrawSurface d) {
        block.setColor(this.color1);
        block.drawOn(d);
    }
    /**
     * Set new color.
     *
     * @param c a given color.
     */
    public void setColor(final Color c) {
        this.color1 = c;
    }
    /**
     * @return our color.
     */
    public Color getColor() {
        return this.color1;
    }
    /**
     * set rectangle.
     *
     * @param rectangle the rectangle
     */
    public void setRec(final Rectangle rectangle) {
        this.block.setRectangle(rectangle);
    }
    /**
     * set stroke.
     * @param c a given color.
     */
    public void setStroke(final Color c) {
        this.color2 = c;
    }
    /**
     * add to the game a given game level.
     * @param gameLevel the gameLevel to add the background to.
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
