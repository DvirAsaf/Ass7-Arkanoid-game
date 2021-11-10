package collision;

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * @ @author dvir asaf 313531113. The collision.Collidable interface will be used by
 * things that can be collided with. this means the Blocks and the collision.Paddle (in
 * order for the ball to bounce from the edges of the screen, we will place
 * large blocks there). What do we need to know about things we may collide
 * with? First, we need to know their location and size (to know if we are
 * colliding with it or not). Second, we need to know what happens when the
 * collision occurs.
 */

public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     *
     * @param hitter          the ball that got hit.
     * @param collisionPoint  the hit point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
