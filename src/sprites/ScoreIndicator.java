package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import listeners.Counter;

import java.awt.Color;

/**
 * @author dvir asaf 313531113.
 * Class Description - the goal of this class is to hold the score indicator.
 * the shape, color and counter of score.
 */

public class ScoreIndicator implements Sprite {

    private Rectangle scoreRectangle;
    private java.awt.Color scoreColor;
    private Counter scoreCounter;
    /**
     * constructor for this class.
     *
     * @param rec   a given rect.
     * @param color a given color.
     * @param score a given counter.
     */
    public ScoreIndicator(final Rectangle rec, final java.awt.Color color, final Counter score) {
        this.scoreColor = color;
        this.scoreRectangle = rec;
        this.scoreCounter = score;
    }
    @Override
    public void drawOn(final DrawSurface d) {
        d.setColor(this.scoreColor);
        int recX = (int) this.scoreRectangle.getUpperLeft().getX();
        int recY = (int) this.scoreRectangle.getUpperLeft().getY();
        int recW = (int) this.scoreRectangle.getWidth();
        int recH = (int) this.scoreRectangle.getHeight();
        d.fillRectangle(recX, recY, recW, recH);
        d.setColor(Color.BLACK);
        d.drawRectangle(recX, recY, recW, recH);
        d.setColor(Color.BLACK);
        String scoreString = "Score:" + this.scoreCounter.getValue();
        d.drawText(recW / 2, (int) (recH / 1.5), scoreString, 13);
        d.setColor(this.scoreColor);
    }
    /**
     * this method does nothing.
     */
    @Override
    public void timePassed() { }
    /**
     * @param g added to the game.
     */
    public void addToGame(final GameLevel g) {
        g.getSpriteCollection().addSprite(this);
    }
}
