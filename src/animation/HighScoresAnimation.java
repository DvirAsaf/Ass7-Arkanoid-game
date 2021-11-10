package animation;
import biuoop.DrawSurface;
import game.scores.HighScoresTable;
import utillity.Utilities;
import java.awt.Color;
/**
 * this class responsible of the high score animation and write on the screen the user score.
 * @author Dvir Asaf 313531113.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    /**
     * constructor - for this class.
     *
     * @param scoresTable the scores table
     */
    public HighScoresAnimation(final HighScoresTable scoresTable) {
        this.highScoresTable = scoresTable;
    }
    @Override
    public void doOneFrame(final DrawSurface d) {
        int size = highScoresTable.getSize();
        int width = d.getWidth();
        int height = d.getHeight();
        int lineX = (int) (width * 0.05);
        int lineY = (int) (height * 0.10);
        int headX1 =  (int) (width * 0.10);
        int headX2 =  (int) (width * 0.65);
        int tailX = (int) (width * 0.90);
        int headY =  (int) (height * 0.20);
        int line = (headY + 10);
        int userName = line + 40;
        int max = 0;
        int maxScoreIndex = 0;
        if (size == 0) {
            d.setColor(Color.WHITE);
            d.fillRectangle(Utilities.GAME_START_X, Utilities.GAME_START_Y, width, height);
            d.setColor(Color.BLACK);
            d.drawText(lineX, lineY, "Highest Score", Utilities.FONT_SIZE);
        } else {
            d.setColor(Color.WHITE);
            d.fillRectangle(Utilities.GAME_START_X, Utilities.GAME_START_Y, width, height);
            d.setColor(Color.BLACK);
            d.drawText(lineX, lineY, "Highest Score", Utilities.FONT_SIZE);
            d.setColor(Color.DARK_GRAY);
            d.drawText(headX1, headY, "Name", Utilities.FONT_SIZE - Utilities.FIX_NUMBER);
            d.drawText(headX2, headY, "Your score", Utilities.FONT_SIZE - Utilities.FIX_NUMBER);
            d.drawLine(headX1, line, tailX, line);
            for (int i = 0; i < size; i++) {
                int score = this.highScoresTable.getHighScores().get(i).getScore();
                if (score > max) {
                    max = score;
                    maxScoreIndex = i;
                }
            }
            d.setColor(Color.PINK);
            d.drawText(headX1, (userName), this.highScoresTable.getHighScores().get(maxScoreIndex).getName(),
                    Utilities.FONT_SIZE - Utilities.FIX_NUMBER);
            d.drawText(headX2, (userName), Integer.toString(max), Utilities.FONT_SIZE - Utilities.FIX_NUMBER);
        }
    }

    @Override
    public boolean shouldStop() {
            return false;
        }
}
