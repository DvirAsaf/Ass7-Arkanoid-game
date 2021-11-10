package listeners;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Dvir Asaf 313531113.
 * this class is count down before each animation. 3 -> 2 -> 1 : start.
 */
public class CountdownAnimation implements Animation {
    private double secondNumber;
    private int from;
    private SpriteCollection screen;
    private long countPerNumber;

    /**
     * constructor for this class.
     *
     * @param numbersOfSeconds the number of seconds before we start the game.
     * @param countFrom        the number that we start to count down from.
     * @param gameScreen       a given sprite collection.
     */
    public CountdownAnimation(final double numbersOfSeconds, final int countFrom, final SpriteCollection gameScreen) {
        this.from = countFrom;
        this.screen = gameScreen;
        this.secondNumber = numbersOfSeconds;
        this.countPerNumber = (long) (secondNumber / from) * 1000;
    }

    /**
     * this method draw on the screen the count down of the numbers.
     *
     * @param d a given surface.
     */
    public void doOneFrame(final DrawSurface d) {
        // drawing all of the sprites
        this.screen.drawAllOn(d);
        d.setColor(Color.LIGHT_GRAY);
        // drawing a text to the user
        d.drawText((d.getWidth() / 3), d.getHeight() / 2, "The game will start in " + (this.from), 30);
        long startTime = System.currentTimeMillis();
        Sleeper sleeper = new Sleeper();
        long usedTime = System.currentTimeMillis() - startTime;
        long leftTime = this.countPerNumber - usedTime;
        if (leftTime > 0) {
            sleeper.sleepFor(leftTime);
        }
        this.from--;
    }

    /**
     * @return false if the count down did not ended, else return true.
     */
    public boolean shouldStop() {
        if (from >= 0) {
            return false;
        }
        return true;
    }
}
