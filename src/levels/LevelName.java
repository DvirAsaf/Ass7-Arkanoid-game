package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class draw the name of the level on top of the up frame.
 *
 * @author Dvir Asaf 313531113.
 */
public class LevelName implements Sprite {
    private Rectangle rect;
    private java.awt.Color color;
    private String name;

    /**
     * this is the constructor of NameOfLevel.
     *
     * @param r - as a recatangle
     * @param c - as color
     * @param n - as a string
     */
    public LevelName(final Rectangle r, final Color c, final String n) {
        this.rect = r;
        this.color = c;
        this.name = n;
    }

    /**
     * this method draw the name level the the game he is now.
     *
     * @param surface - a given surface.
     */
    public void drawOn(final DrawSurface surface) {
        surface.setColor(Color.BLACK);
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        double w = this.rect.getWidth();
        double h = this.rect.getHeight();
        String stringScore = "Level Name: " + this.name;
        surface.drawText((int) (x + w / 1.4), (int) (y + h / 1.5), stringScore, 13);
        surface.setColor(this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * this method add a gameLevel to the game.
     *
     * @param g - as a GameLevel object
     */
    public void addToGame(final GameLevel g) {
        g.addSprite(this);
    }
}
