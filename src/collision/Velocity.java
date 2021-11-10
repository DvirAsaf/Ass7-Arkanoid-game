package collision;

import geometry.Line;
import geometry.Point;

/**
 * @author dvir asaf 313531113.
 * <p>
 * the class create a velocity for the ball and change the center of the
 * ball. according to the movement.
 */
public class Velocity {

    /**
     * This double gives access only to this class.
     */
    private double dx;

    /**
     * This double gives access only to this class.
     */
    private double dy;

    /**
     * constructor - coordinates arguments. the method get 2 double numbers that are
     * the values of velocity and set. them as our velocity.
     *
     * @param dx2 the our dx velocity.
     * @param dy2 the our dy velocity.
     */
    public Velocity(final double dx2, final double dy2) {
        dx = dx2;
        dy = dy2;
    }

    /**
     * the function get 2 doubles numbers, the first is the angle of velocity. the
     * second is the speed of velocity.
     *
     * @param angle the dx velocity after we change him to dx value.
     * @param speed the dy velocity after we change him to dy value.
     * @return new velocity.
     */

    public static Velocity fromAngleAndSpeed(final double angle, final double speed) {
        double ang = Math.toRadians(angle);
        double dy = -speed * Math.cos(ang);
        double dx = speed * Math.sin(ang);

        return new Velocity(dx, dy);
    }

    /**
     * From angle and speed patch velocity.
     *
     * @param angleDegrees the angle degrees
     * @param speed        the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeedPatch(final double angleDegrees, final double speed) {


        // convert degrees to radians
        double angleRadians = Math.toRadians(angleDegrees);

        // calculating dx, dy for velocity by using sin() and cos()
        double dx = -Math.sin(angleRadians) * speed;
        double dy = Math.cos(angleRadians) * speed;

        return new Velocity(dx, dy);

    }

    /**
     * the method change the center ball(point) value to the new value(after. the
     * movement).
     *
     * @param p the cemter ball(point).
     * @return the new center ball after the movement.
     */

    public Point applyToPoint(final Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }

    /**
     * @return the dx value of velocity.
     */

    public double getDx() {
        return dx;
    }

    /**
     * the method get double and set him as our dx velocity.
     *
     * @param dx1 the value of our dx velocity.
     */

    public void setDx(final double dx1) {
        dx = dx1;
    }

    /**
     * @return the dy value of velocity.
     */

    public double getDy() {
        return dy;
    }

    /**
     * the method get double and set him as our dy velocity.
     *
     * @param dy1 the value of our dy velocity.
     */
    public void setDy(final double dy1) {
        dy = dy1;
    }

    /**
     * create line from the same direction computed by start point and velocity.
     *
     * @param start the value of our start point.
     * @return line of direction
     */
    public Line createLine(final Point start) {
        Point end = new Point(start.getX() + dx, start.getY() + dy);
        Line path = new Line(start, end);
        return path;
    }
}
