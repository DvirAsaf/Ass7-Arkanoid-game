package animation;
import biuoop.DrawSurface;
import listeners.Counter;
import java.awt.Color;
/**
 * this class is responsible for the winning screen.
 * @author Dvir Asaf 313531113.
 */
public class WinningScreen implements Animation {
        private final Counter score;
        /**
         * constructor for this class.
         *
         * @param score the score
         */
        public WinningScreen(final Counter score) {
            this.score = score;
        }
    /**
     * this method create the screen massage about wining the game.
     *
     * @param d a given draw surface.
     */
        public void doOneFrame(final DrawSurface d) {
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.BLUE);
            d.drawText(155, 170, "You Win! Your score is " + this.score.getValue(), 40);
            d.setColor(Color.BLUE);
            d.drawText(289, 230, "Please press space to continue", 30);
        }
        /**
         * @return false.
         */
        public boolean shouldStop() {
            return false;
        }

}
