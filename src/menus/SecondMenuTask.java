package menus;
import animation.AnimationRunner;
import utillity.Task;
/**
 * this class is responsible for menu task.
 *
 * @param <T> the type parameter.
 * @author Dvir Asaf 313531113.
 */
public class SecondMenuTask<T> implements Task<Void> {
    private AnimationRunner runner;
    private MenuAnimation<Task<Void>> subMenu;
    /**
     * constructor for this class.
     *
     * @param r       a given animation runner.
     * @param menu    a given T.
     */
    public SecondMenuTask(final AnimationRunner r, final Menu<T> menu) {
        this.runner = r;
        this.subMenu = (MenuAnimation<Task<Void>>) menu;
    }
    @Override
    public Void run() {
        this.subMenu.reset();
        runner.run(this.subMenu);
        Task<Void> task = this.subMenu.getStatus();
        task.run();
        return null;
    }
}
