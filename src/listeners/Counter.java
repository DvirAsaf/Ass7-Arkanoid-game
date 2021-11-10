package listeners;

/**
 * @author dvir asaf 313531113.
 * the goal of this class is to update the value of counter: increase, decrease and get the value.
 */

public class Counter {

    private int counter;

    /**
     * constractor for this class.
     *
     * @param c a given int.
     */

    public Counter(final int c) {
        counter = c;
    }

    /**
     * @param number add number to current count.
     */

    public void increase(final int number) {
        counter += number;
    }

    /**
     * @param number subtract number from current count.
     */

    public void decrease(final int number) {
        counter -= number;
    }

    /**
     * @return get current count.
     */

    public int getValue() {
        return counter;
    }
}
