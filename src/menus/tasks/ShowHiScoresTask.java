package menus.tasks;
import animation.Animation;
import animation.AnimationRunner;
import utillity.Task;
/**
 * this class show the high score.
 * @author Dvir Asaf 313531113.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScores;

    /**
     * constructor for this class.
     *
     * @param runner              the AnimationRunner.
     * @param highScoresAnimation - highScoresAnimation.
     */
    public ShowHiScoresTask(final AnimationRunner runner, final Animation highScoresAnimation) {
        this.runner = runner;
        this.highScores = highScoresAnimation;
    }

    /**
     * this method run the class.
     *
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScores);
        return null;
    }
}
