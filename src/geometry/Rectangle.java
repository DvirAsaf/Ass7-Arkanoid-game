package geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @ @author dvir asaf 313531113. Class Description - the goal of this class is
 * to represent the geometry.Rectangle object that will be used to preform our game's
 * objects
 */

public class Rectangle {

    /**
     * upperLeft.
     */
    private Point upperLeft;
    /**
     * upperRight.
     */
    private Point upperRight;
    /**
     * lowLeft.
     */
    private Point lowLeft;
    /**
     * lowRight.
     */
    private Point lowRight;
    /**
     * width.
     */
    private double width;
    /**
     * height.
     */
    private double height;

    /**
     * color.
     */
    private java.awt.Color color;

    /**
     * geometry.Rectangle Constructor.
     *
     * @param upLeft    - upper left point
     * @param widthRec  width of geometry.Rectangle
     * @param heightRec height of geometry.Rectangle
     */
    public Rectangle(final Point upLeft, final double widthRec, final double heightRec) {
        upperLeft = upLeft;
        height = heightRec;
        width = widthRec;
//        lowLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
//        lowRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
//        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
//        upperEdge = new Line(upperLeft, upperRight);
//        lowerEdge = new Line(lowLeft, lowRight);
//        leftEdge = new Line(upperLeft, lowLeft);
//        rightEdge = new Line(upperRight, lowRight);
//        rectangleEdges.add(upperEdge);
//        rectangleEdges.add(lowerEdge);
//        rectangleEdges.add(leftEdge);
//        rectangleEdges.add(rightEdge);
    }

    /**
     * @return color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * set Color of geometry.Rectangle.
     *
     * @param c Color
     */
    public void setColor(final java.awt.Color c) {
        color = c;
    }

    /**
     * @return leftEdge
     */
    public Line getLeftEdge() {
        return new Line(getUpperLeft(), getLowLeft());
    }

    /**
     * @return lowLeft
     */
    public Point getLowLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * @return lowRight
     */

    public Point getLowRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * @return upperRight
     */

    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }


    /**
     * @return lowerEdge
     */
    public Line getLowerEdge() {
        return new Line(getLowLeft(), getLowRight());
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     *
     * @param line - line to check hit
     * @return list of hit points with that line
     */
    public java.util.List<Point> intersectionPoints(final Line line) {
        List<Point> points = new ArrayList<Point>();
        List<Line> rectangleEdges = List.of(
                getUpperEdge(), getLowerEdge(), getLeftEdge(), getRightEdge());
        int len;

        Point hitPoint;
        for (int i = 0; i < rectangleEdges.size(); ++i) {
            Line cureLine = rectangleEdges.get(i);
            if (line.hasHitPoint(cureLine)) {
                hitPoint = line.lineLineIntersection(cureLine);
                if (line.isPointInSegment(hitPoint)) {
                    if (cureLine.isPointInSegment(hitPoint)) {
                        points.add(hitPoint);
                    }
                }
            }
        }

        len = points.size();
        // If we found only one hit point then return it
        if (1 >= len) {
            return points;
        }

        // create a new list
        List<Point> hitPoints = new ArrayList<Point>();

        // validate the case of hit corner - if there is a point that appears twice in
        // list we
        // should remove it.
        for (Point point : points) {
            if (!hitPoints.contains(point)) {
                hitPoints.add(point);
            }
        }

        // return list of hit points
        return hitPoints;
    }

    /**
     * @return rightEdge
     */
    public Line getRightEdge() {
        return new Line(getUpperRight(), getLowRight());
    }

    /**
     * @return upper Edge
     */
    public Line getUpperEdge() {
        return new Line(getUpperLeft(), getUpperRight());
    }

    /**
     * @return width
     */
    public double getWidth() {
        return width;

    }

    /**
     * @param width1 given double.
     */
    public void setWidth(final double width1) {
        this.width = width1;
    }

    /**
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return upper Left geometry.Point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * set Upper Left geometry.Point.
     *
     * @param p - geometry.Point
     */
    public void setUpperLeft(final Point p) {
        upperLeft = p;
    }
}
