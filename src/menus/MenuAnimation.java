
package menus;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * this class is responsible for menu animations.
 *
 * @param <T> the type parameter.
 * @author Dvir Asaf 313531113.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys;
    private List<String> messages;
    private T status;
    private Map<String, T> keyStatusMap;
    private String menuTitle;
    private AnimationRunner runner;
    private KeyboardSensor key;
    private boolean stop;
    /**
     * constructor for this class.
     *
     * @param title     a given menu title.
     * @param keyboard  a given keyboard sensor.
     * @param run       a given animation runner.
     */
    public MenuAnimation(final String title, final KeyboardSensor keyboard, final AnimationRunner run) {
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.keyStatusMap = new TreeMap<>();
        this.menuTitle = title;
        this.runner = run;
        this.key = keyboard;
    }
    /**
     * this method draw the menu.
     *
     * @param d a given draw surface.
     */
    public void doOneFrame(final DrawSurface d) {
        MenuBackGround backGround = new MenuBackGround(this.menuTitle, this.keys, this.messages);
        backGround.drawOn(d);
        for (String key : this.keyStatusMap.keySet()) {
            if (this.key.isPressed(key)) {
                this.status = this.keyStatusMap.get(key);
                this.stop = true;
                break;
            }
        }
    }
    /**
     * add selection.
     *
     * @param key       the key to wait for.
     * @param message   the line to print.
     * @param returnVal what to return.
     */
    public void addSelection(final String key, final String message, final T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.keyStatusMap.put(key, returnVal);
    }
    /**
     * @return T the status.
     */
    public T getStatus() {
        return this.status;
    }
    /**
     * this method add the sub menu.
     * @param key     the key to wait for.
     * @param message the line to print.
     * @param secondMenu the interior menu.
     */
    public void addSubMenu(final String key, final String message, final Menu<T> secondMenu) {
        SecondMenuTask secondTask = new SecondMenuTask<>(this.runner, secondMenu);
        addSelection(key, message, (T) secondTask);
    }
    /**
     * @return the value of parameter stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * this method reset the parameter stop.
     */
    public void reset() {
        this.stop = false;
    }
    /**
     * this method sets animation runner.
     *
     * @param r a given animation runner.
     */
    public void setAnimationRunner(final AnimationRunner r) {
        this.runner = r;
    }
}
