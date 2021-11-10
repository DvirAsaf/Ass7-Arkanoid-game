package menus.tasks;
import animation.AnimationRunner;
import utillity.Task;
/**
 * this class is exit task.
 * @author Dvir Asaf 313531113.
 */
public class ExitTask implements Task<Void> {
    private AnimationRunner runner;

    /**
     * constructor for this class.
     *
     * @param runner - the AnimationRunner.
     */
    public ExitTask(final AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * this methos run this class.
     *
     * @return null.
     */
    public Void run() {
        this.runner.getGameGui().close();
        return null;
    }
}


