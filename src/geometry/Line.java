package geometry;

import utillity.Utilities;

import java.util.List;

/**
 * @author dvir asaf 313531113.
 * <p>
 * the class create a line with 2 points, check if the lines are equals,
 * the. same slope or intersection.
 */

public class Line {
    /**
     * This point gives access only to this class.
     */
    private Point start;
    /**
     * This point gives access only to this class.
     */
    private Point end;

    /**
     * constructor - coordinates arguments. the method get 2 points and set them to
     * out start point and end point.
     *
     * @param a the start point.
     * @param b the end point.
     */

    public Line(final Point a, final Point b) {
        start = a;
        end = b;
    }

    /**
     * constructor - coordinates arguments. the method get 4 double numbers, the
     * first 2 are start point and the. last 2 is the end point.
     *
     * @param x1 the value of x start point.
     * @param y1 the value of y start point.
     * @param x2 the value of x end point.
     * @param y2 the value of y end point.
     */

    public Line(final double x1, final double y1, final double x2, final double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        start = p1;
        end = p2;
    }

    /**
     * the method return the value of length between start and end points.
     *
     * @return the length between start and end point.
     */

    public double length() {
        double length = start.distance(end);
        return length;
    }

    /**
     * the method return the middle point between start and end points.
     *
     * @return the middle point between start and end points.
     */

    public Point middle() {
        Point p3 = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        return p3;
    }

    /**
     * the method return the start point.
     *
     * @return start point.
     */

    public Point start() {
        return start;
    }

    /**
     * the method return the end point.
     *
     * @return end point.
     */

    public Point end() {
        return end;
    }

    /**
     * the method return the slope of the line.
     *
     * @return slope of the line.
     */

    public double slope() {
        double m = (start.getY() - end.getY() / start.getX() - end.getX());
        return m;
    }

    /**
     * call the lineLineIntersection method by giving it the required parameters.
     *
     * @param other the other line we want to check if there intersection.
     * @return if there a intersection return true else false.
     */
    public Point lineLineIntersection(final Line other) {
        Point a = start;
        Point b = end;
        Point c = other.start();
        Point d = other.end();
        return lineLineIntersection(a, b, c, d);
    }

    /**
     * If this line does not intersect with the rectangle, return null Otherwise,
     * return the closest intersection point to the start of the line.
     *
     * @param rect a given rect.
     * @return return the closest intersection point to the start of the line., If
     * this line does not intersect with the rectangle, return null
     */
    public Point closestIntersectionToStartOfLine(final Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        double min;
        double currentDist;
        Point pointToReturn;
        if (points.size() != 0) {
            // go over all the points to find the one that is closest.
            pointToReturn = points.get(0);
            min = start.distance(pointToReturn);
            for (Point point : points) {
                currentDist = start.distance(point);
                if (min > currentDist) {
                    pointToReturn = point;
                    min = currentDist;
                }
            }
            return pointToReturn;
        }
        // if there is no hit point
        return null;
    }

    /**
     * the method return the point between 2 lines.
     *
     * @param a start point of our line.
     * @param b end point of our line.
     * @param c start point of other line.
     * @param d end point of other line.
     * @return the point between 2 lines.
     */

    public Point lineLineIntersection(final Point a, final Point b, final Point c, final Point d) {
        double x1 = b.getY() - a.getY();
        double y1 = a.getX() - b.getX();
        double z1 = x1 * (a.getX()) + y1 * (a.getY());
        double x2 = d.getY() - c.getY();
        double y2 = c.getX() - d.getX();
        double z2 = x2 * (c.getX()) + y2 * (c.getY());
        double m = x1 * y2 - x2 * y1;
        if (m == 0) {
            return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            double x = (y2 * z1 - y1 * z2) / m;
            double y = (x1 * z2 - x2 * z1) / m;
            return new Point(x, y);
        }
    }

    /**
     * the method check if our line and other line have intersection.
     *
     * @param other the other line we want to check if there intersection.
     * @return if there a intersection return true else false.
     */
    public boolean isIntersectingLines(final Line other) {
        double m1 = this.slope();
        double m2 = other.slope();
        if (m1 != m2) {
            Point p = lineLineIntersection(start, end, other.start(), other.end());
            double px = p.getX();
            double py = p.getY();
            double minInterval1X = Math.min(start.getX(), end.getX());
            double maxInterval1X = Math.max(start.getX(), end.getX());
            double minInterval2X = Math.min(other.start().getX(), other.end().getX());
            double maxInterval2X = Math.max(other.start().getX(), other.end().getX());
            double minInterval1Y = Math.min(start.getY(), end.getY());
            double maxInterval1Y = Math.max(start.getY(), end.getY());
            double minInterval2Y = Math.min(other.start().getY(), other.end().getY());
            double maxInterval2Y = Math.max(other.start().getY(), other.end().getY());
            return (px <= maxInterval1X && px <= maxInterval2X && px >= minInterval1X && px >= minInterval2X
                    && py <= maxInterval1Y && py <= maxInterval2Y && py >= minInterval1Y && py >= minInterval2Y);
        } else {
            return ((!this.equals(other)) && (this.start.equals(other.start) || (this.start.equals(other.end))
                    || (this.end.equals(other.start)) || (this.end.equals(other.end))));
        }
    }

    /**
     * check if there is a hit point by comparing the slope of each line.
     *
     * @param other a given line.
     * @return true if there is a hit point, else false.
     */
    public boolean hasHitPoint(final Line other) {
        double s1 = slope();
        double s2 = other.slope();
        return s1 != s2;
    }

    /**
     * @param other is other line.
     * @return true if the lines are equal, false if not.
     */
    public boolean equals(final Line other) {
        return ((start.equals(other.start)) && (end.equals(other.end)));
    }

    /**
     * @param other the hit point
     * @return check if the hit point is on that line in segment
     */
    public boolean isPointInSegment(final Point other) {
        double lenTillPoint = start.distance(other);
        double lenFromPoint = other.distance(end);
        double lineLen = length();
        double distSub = lenTillPoint + lenFromPoint - lineLen;
        return distSub <= Utilities.EPS;
    }
}
