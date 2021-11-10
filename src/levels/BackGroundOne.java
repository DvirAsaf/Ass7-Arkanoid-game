package levels;

import biuoop.DrawSurface;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;

/**
 * @author Dvir Asaf 313531113.
 * this class draw the background for 1 level.
 */
public class BackGroundOne implements Sprite {
    /**
     * this method draw the background for 1 level.
     *
     * @param d - the DrawSurface
     */
    public void drawOn(final DrawSurface d) {
        // filling the backround
        d.setColor(Color.BLACK);
        d.fillRectangle(Utilities.GAME_START_X, Utilities.GAME_START_Y, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);
        d.setColor(Color.RED);
        // drawing the circles
        d.drawCircle(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30),
                40);
        d.drawCircle(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30),
                60);
        d.drawCircle(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30),
                80);
        d.drawCircle(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30),
                100);
        // drawing the lines
        d.drawLine(Utilities.GAME_WIDTH / 2 - 120, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30),
                Utilities.GAME_WIDTH / 2 + 120, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30));
        d.drawLine(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30)
                        - 120,
                Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.3 + Utilities.GAME_HEIGHT / 30)
                        + 120);
    }

    @Override
    public void timePassed() {
    }
}
