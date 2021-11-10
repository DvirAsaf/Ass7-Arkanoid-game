package levels;

import biuoop.DrawSurface;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;

/**
 * @author Dvir Asaf 313531113.
 * this class draw the background for 3 level.
 */
public class BackGroundTwo implements Sprite {

    @Override
    public void timePassed() {
    }

    /**
     * this method draw the background for 2 level.
     *
     * @param d - the DrawSurface
     */
    public void drawOn(final DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(Utilities.GAME_START_X, Utilities.GAME_START_Y, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);
        // drawing the sun rays
        d.setColor(new Color(255, 255, 153));
        for (int i = 0; i < 90; i++) {
            d.drawLine((int) (Utilities.GAME_WIDTH * 0.2), (int) (0.2 * Utilities.GAME_HEIGHT),
                    Utilities.GAME_WIDTH * 6 / 7 - i * 10, (int) (0.4 * Utilities.GAME_HEIGHT));
        }
        // drawing the sun.
        d.setColor(new Color(255, 253, 73));
        d.fillCircle((int) (0.2 * Utilities.GAME_WIDTH), (int) (0.2 * Utilities.GAME_HEIGHT), 70);
        d.setColor(new Color(255, 255, 102));
        d.fillCircle((int) (0.2 * Utilities.GAME_WIDTH), (int) (0.2 * Utilities.GAME_HEIGHT), 60);
        d.setColor(new Color(255, 255, 153));
        d.fillCircle((int) (0.2 * Utilities.GAME_WIDTH), (int) (0.2 * Utilities.GAME_HEIGHT), 50);
    }
}
