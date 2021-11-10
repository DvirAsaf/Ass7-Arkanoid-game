package collision;

import geometry.Point;

/**
 * @ @author dvir asaf 313531113. Class Description - save as a set an object
 * (collision.Collidable) and the hit point.
 */

public class CollisionInfo {

    /**
     * collision.Collidable object.
     */
    private Collidable object;

    /**
     * colPoint.
     */
    private Point colPoint;

    /**
     * the constructor.
     *
     * @param collision - collision point
     * @param obj       - collision pbject
     */

    public CollisionInfo(final Point collision, final Collidable obj) {
        this.colPoint = collision;
        this.object = obj;
    }

    /**
     * @return the hit point (the point at which the collision occurs).
     */
    public Point collisionPoint() {

        return colPoint;
    }

    /**
     * set the hit point.
     *
     * @param p hit point
     */
    public void setColPoint(final Point p) {

        this.colPoint = p;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {

        return object;
    }

    /**
     * set the collision.Collidable object.
     *
     * @param object1 collision.Collidable object.
     */
    public void setObject(final Collidable object1) {

        this.object = object1;
    }
}
