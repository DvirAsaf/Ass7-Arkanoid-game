package animation;

import biuoop.DrawSurface;

/**
 * @author Dvir Asaf 313531113.
 */
public interface Animation {
    /**
     * @param d a given surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return TRUE OR FALSE.
     */
    boolean shouldStop();
}
