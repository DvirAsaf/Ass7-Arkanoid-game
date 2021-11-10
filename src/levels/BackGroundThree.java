package levels;

import biuoop.DrawSurface;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;

/**
 * @author Dvir Asaf 313531113.
 * this class draw the background for 3 level.
 */
public class BackGroundThree implements Sprite {
    /**
     * this method draw the background for 3 level.
     *
     * @param d - the DrawSurface
     */
    public void drawOn(final DrawSurface d) {
        // drawing the building.
        d.setColor(Color.GREEN);
        d.fillRectangle((int) 0, (int) 0, Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);
        d.setColor(new Color(32, 32, 32));
        d.fillRectangle(100, 430, 98, 170);
        d.setColor(new Color(224, 224, 224));
        int x = 0;
        int y = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(105 + x, 437 + y, 12, 22);
                x += 19;
            }
            x = 0;
            y += 30;
        }
        // drawing the pole.
        d.setColor(new Color(50, 50, 50));
        d.fillRectangle(140, 390, 20, 40);
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(145, 240, 10, 150);
        d.setColor(new Color(255, 255, 0));

        // drawing the light.
        d.fillCircle(150, 240, 10);
        d.setColor(Color.red);
        d.fillCircle(150, 240, 7);
        d.setColor(new Color(224, 224, 224));
        d.fillCircle(150, 240, 3);

    }

    @Override
    public void timePassed() {
    }
}
