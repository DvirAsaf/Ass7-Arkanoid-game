package game;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @ @author dvir asaf 313531113. Class Description - The game.GameEnvironment class
 * will be a collection of such things. The ball will know the game
 * environment, and will use it to check for collisions and direct its
 * movement.
 */

public class GameEnvironment {

    /**
     * collidableList.
     */
    private List<Collidable> collidableList = new ArrayList<>();

    /**
     * @return collidableList.
     */
    public List<Collidable> getCollidableList() {
        return collidableList;
    }

    /**
     * add a collision.Collidable to the collidableList.
     *
     * @param c a given collidable.
     */
    public void addCollidable(final Collidable c) {
        collidableList.add(c);
    }

    /**
     * this method remove a collidable from list.
     *
     * @param c the collidable that we remove.
     */
    public void removeCollidable(final Collidable c) {
        collidableList.remove(c);
    }

    /**
     * @param line       the line of the ball direction.
     * @param collidable a collidable
     * @return flase if there is not a hit point, else true
     */
    public boolean hasHit(final Line line, final Collidable collidable) {
        Rectangle rectangle = collidable.getCollisionRectangle();
        return rectangle.intersectionPoints(line) != null;
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object will.
     * not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to
     * occur.
     *
     * @param line the line of the ball direction
     * @return If this object will not collide with any of the collidables in this
     * collection, return null Else, return the information about the
     * closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(final Line line) {
        Point hitPoint = null;

        List<Point> hitPoints;
        Collidable collidable = null;
        Rectangle rectangle = null;

        for (Collidable collid : collidableList) {
            rectangle = collid.getCollisionRectangle();
            hitPoints = rectangle.intersectionPoints(line);
            if (!hitPoints.isEmpty()) {
                hitPoint = line.closestIntersectionToStartOfLine(rectangle);
                collidable = collid;
                break;
            }
        }

        if (collidable != null) {
            for (Collidable collid : collidableList) {
                if (hasHit(line, collid)) {
                    rectangle = collid.getCollisionRectangle();
                    List<Point> interSectionPoints = rectangle.intersectionPoints(line);
                    if (interSectionPoints.size() != 0) {
                        if (findNearestPoint(line, collid, hitPoint)) {
                            hitPoint = line.closestIntersectionToStartOfLine(rectangle);
                            collidable = collid;
                        }
                    }
                }
            }
            return new CollisionInfo(hitPoint, collidable);
        } else {
            // System.out.println("Warning : no hit found");
            return null;
        }
    }

    /**
     * @param l     a given line.
     * @param c     a given collidable.
     * @param point ball trajectory line and the collision rectangle.
     * @return true if the distance between the ball start point and the collision
     * point is smaller than the distance between ball start point and the
     * ball center location if there is no collision.
     */
    public boolean findNearestPoint(final Line l, final Collidable c, final Point point) {
        Rectangle rectangle = c.getCollisionRectangle();
        Point hitPoint = l.closestIntersectionToStartOfLine(rectangle);
        Line seg1 = new Line(l.start(), hitPoint);
        Line seg2 = new Line(l.start(), point);
        double d1 = seg1.length();
        double d2 = seg2.length();
        return d1 <= d2;
    }
}
