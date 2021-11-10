package utillity;

/**
 * a generic interface.
 *
 * @param <T>
 * @author Dvir Asaf 313531113.
 */
public interface Task<T> {
    /**
     * run.
     *
     * @return a value.
     */
    T run();
}
