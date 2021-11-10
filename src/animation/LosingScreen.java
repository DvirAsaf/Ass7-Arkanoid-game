package animation;
import biuoop.DrawSurface;
import listeners.Counter;
import java.awt.Color;
/**
 * this class is responsible for the losing screen.
 * @author Dvir Asaf 313531113.
 */
public class LosingScreen implements Animation {
    private Counter score;
    /**
     * constructor for this class.
     *
     * @param score the score
     */
    public LosingScreen(final Counter score) {
        this.score = score;
    }
    /**
     * this method create the screen massage about losing the game.
     *
     * @param d a given draw surface.
     */
    public void doOneFrame(final DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawText(145, 195, "Game Over! Your score is:" + this.score.getValue(), 40);
        d.setColor(Color.RED);
        d.drawText(240, 242, "press space to continue", 30);
    }
    /**
     * @return false.
     */
    public boolean shouldStop() {
        return false;
    }
}
