
package menus;


import animation.Animation;

/**
 * this interface menu extends animation.
 *
 * @param <T>
 * @author Dvir Asaf 313531113.
 */
public interface Menu<T> extends Animation {
    /**
     * add selection.
     *
     * @param key       a given key.
     * @param message   a given message.
     * @param returnVal a given T.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get status.
     *
     * @return returnVal.
     */
    T getStatus();

    /**
     * @param key     a given key.
     * @param message a given message.
     * @param subMenu a given T.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
