package levels;

import biuoop.DrawSurface;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;
import java.util.Random;

/**
 * @author Dvir Asaf 313531113.
 * this class draw the background for 4 level.
 */
public class BackGroundFour implements Sprite {
    /**
     * this method draw the background for 4 level.
     *
     * @param d - the DrawSurface
     */
    public void drawOn(final DrawSurface d) {
        // filling the backround
        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);

        // drawing part of the clouds
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(95, 395, 22);
        d.fillCircle(115, 410, 24);
        d.fillCircle(550, 470, 20);
        d.fillCircle(570, 485, 22);

        Random rand = new Random();
        for (int i = 0; i < 90; i++) {
            d.drawLine(550 + i, 480, 545 + i, 470 + rand.nextInt(270));
            d.drawLine(95 + i, 405, 90 + i, 395 + rand.nextInt(270));
        }

        //the clouds drawing
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(125, 385, 25);
        d.fillCircle(580, 460, 25);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle(145, 410, 27);
        d.fillCircle(168, 390, 28);
        d.fillCircle(600, 485, 27);
        d.fillCircle(622, 465, 28);
    }
    @Override
    public void timePassed() { }
}
