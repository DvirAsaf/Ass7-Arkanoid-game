package geometry;

/**
 * @author dvir asaf 313531113.
 * <p>
 * the class create a point from 2 double number.
 */
public class Point {
    /**
     * This double gives access only to this class.
     */
    private double x;

    /**
     * This double gives access only to this class.
     */
    private double y;

    /**
     * constructor - coordinates arguments. the method get 2 double numbers that the
     * first is x value of point. and the second is y value of point.
     *
     * @param x2 the double number of point.
     * @param y2 the double number of point.
     */
    public Point(final double x2, final double y2) {
        x = x2;
        y = y2;
    }

    /**
     * constructor of the class.
     *
     * @param point of class.
     */

    public Point(final Point point) {
        x = point.getX();
        y = point.getY();
    }

    /**
     * the method get point and return the distance between the point we have and
     * the point we get.
     *
     * @param other point that we want to know the distance from our point.
     * @return double that is the distance between the points.
     */

    public double distance(final Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * the method check if 2 points are equals. if value x of our point equal to
     * value x of other point. the same in y value between the 2 points.
     *
     * @param other point that we want to check if equal to our point.
     * @return if the points are equals then return true else return false.
     */

    public boolean equals(final Point other) {
        return ((x == other.x) && (this.y == other.y));
    }

    /**
     * the method return the value of x point.
     *
     * @return double value of x point.
     */

    public double getX() {
        return x;
    }

    /**
     * the method get double number and set that number to be x point.
     *
     * @param x1 the x point.
     */

    public void setX(final double x1) {
        x = x1;
    }

    /**
     * the method return the value of y point.
     *
     * @return double value of y point.
     */

    public double getY() {
        return y;
    }

    /**
     * the method get double number and set that number to be y point.
     *
     * @param y1 the y point.
     */

    public void setY(final double y1) {
        y = y1;
    }
}
