package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * this class is check if a key from the keyboard is press.
 *
 * @author Dvir Asaf 313531113.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean shouldStop;
    private boolean isStop;
    private boolean stop;
    /**
     * constructor for this class.
     *
     * @param s      a given keyboard sensor.
     * @param k      a given string.
     * @param a      a given animation.
     */
    public KeyPressStoppableAnimation(final KeyboardSensor s, final String k, final Animation a) {
        this.animation = a;
        this.key = k;
        this.keyboardSensor = s;
        this.isStop = true; //isStartOfAnimation
        this.stop = false; //shouldIgnore
    }
    /**
     * this method check if key is press only once.
     *
     * @param d a given surface.
     */
    public void doOneFrame(final DrawSurface d) {
        this.animation.doOneFrame(d);
        if (isKeyPress() && (!this.isStop)) {
            this.stop = true;
            this.shouldStop = true;
        } else if (!this.keyboardSensor.isPressed(this.key)) {
            isStop = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
    /**
     * this method reset the should stop.
     */
    public void reset() {
        this.shouldStop = false;
    }
    /**
     * this method check if the key from keyboard sensor is press.
     *
     * @return true or false.
     */
    public boolean isKeyPress() {
        return this.keyboardSensor.isPressed(this.key);
    }
}
