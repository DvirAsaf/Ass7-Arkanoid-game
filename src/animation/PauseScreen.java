package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * this class pause the game and if the user press the space key we return to the game.
 * @author Dvir Asaf 313531113.
 */
public class PauseScreen implements Animation {
    private biuoop.KeyboardSensor keyboard;
    private boolean stop;
    /**
     * this is the constructor for PauseScreen.
     *
     * @param k a given keboard sensor.
     */
    public PauseScreen(final KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * this method draw text that write "pause -- press space to continue".
     * and if the space key is press --> continue the game.
     *
     * @param d a given surface.
     */
    public void doOneFrame(final DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(120, d.getHeight() / 2, "Game is paused [to continue please press space]", 16);
    }
    /**
     * this method return the value that pause and continue the game.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
