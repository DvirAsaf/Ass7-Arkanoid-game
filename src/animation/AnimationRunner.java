package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import utillity.Utilities;

/**
 * this class create and run the animation for the game.
 *
 * @author Dvir Asaf 313531113.
 */

public class AnimationRunner {
    private GUI gameGui;
    private Sleeper sleeper;
    private KeyboardSensor key;

    /**
     * this is the constructor for AnimationRunner.
     *
     * @param gui a given GUI.
     */

    public AnimationRunner(final GUI gui) {
        gameGui = gui;
        sleeper = new Sleeper();
        this.key = this.gameGui.getKeyboardSensor();
    }

    /**
     * this method takes a animation and runs it.
     *
     * @param animation a given animation.
     */
    public void run(final Animation animation) {
        int millisecondsPerFrame = Utilities.MILISEC_PER_FRAME;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gameGui.getDrawSurface();
            animation.doOneFrame(d);
            gameGui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }

    /**
     * @return the game gui.
     */
    public GUI getGameGui() {
        return this.gameGui;
    }

    /**
     * @return the keyboard for this animation.
     */
    public KeyboardSensor getKeyboard() {
        return this.key;
    }
}
