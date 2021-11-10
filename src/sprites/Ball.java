package sprites;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.CollisionInfo;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utillity.Utilities;

import java.awt.Color;
import java.util.List;

/**
 * @author dvir asaf 313531113.
 * <p>
 * the class create a ball from radius,color,point/2 double numbers. the
 * class check that the movement of the ball is in the window size.
 */

public class Ball implements Sprite {

    /**
     * This integer gives access only to this class. the radius of the ball.
     */

    private int radius;

    /**
     * This java.awt.Color gives access only to this class. the color of the ball.
     */

    private java.awt.Color color;

    /**
     * This velocity gives access only to this class. the velocity of the ball.
     */

    private Velocity velocity;

    /**
     * this game enviroment access only to this class. the game environment of the
     * ball.
     */

    private GameEnvironment env;

    /**
     * this center point access only to this class. the center of the ball.
     */

    private Point centerPoint;

    /**
     * constructor for this class.
     *
     * @param x      is the center point of the ball.
     * @param y      is the center point of the ball
     * @param r      is the radius.
     * @param color2 is the color.
     */

    public Ball(final int x, final int y, final int r, final java.awt.Color color2) {
        Point center = new Point(x, y);
        centerPoint = center;
        radius = r;
        color = color2;
    }

    /**
     * constructor for this class.
     *
     * @param center the center of the ball.
     * @param r      the radius of the ball.
     * @param col    the color of the ball.
     */

    public Ball(final Point center, final int r, final java.awt.Color col) {
        centerPoint = center;
        radius = r;
        color = col;
    }

    /**
     * notify the sprite that time has passed.
     */

    public void timePassed() {
        moveOneStep();
    }

    /**
     * the method get game.Game and add this to the game.
     *
     * @param g object of game class.
     */

    public void addToGame(final GameLevel g) {
        SpriteCollection spriteCollection = g.getSpriteCollection();
        List<Sprite> sprites = spriteCollection.getSpriteList();
        GameEnvironment environment = g.getEnv();
        setEnv(environment);
        if (sprites == null) {
            // call init of sprites list is not init yet.
            spriteCollection.initSpriteCollection();
            spriteCollection.getSpriteList().add(this);
        } else {
            sprites.add(this);
        }
    }

    /**
     * @param radius1 is the radius of the ball.
     */

    public void setRadius(final int radius1) {
        radius = radius1;
    }

    /**
     * @return the center point
     */

    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * set the center point.
     *
     * @param center ball center point
     */

    public void setCenterPoint(final Point center) {

        this.centerPoint = center;
    }

    /**
     * @return the game environment of the ball.
     */

    public GameEnvironment getEnv() {
        return env;
    }

    /**
     * @param environment is the new game environment of ball.
     */

    public void setEnv(final GameEnvironment environment) {
        env = environment;
    }

    /**
     * the method get 2 double numbers and set them to our center point.
     *
     * @param x the x value of center point.
     * @param y the y value of center point.
     */

    public void setPoint(final double x, final double y) {
        centerPoint.setX(x);
        centerPoint.setY(y);
    }

    /**
     * the method return the value of our radius point.
     *
     * @return the radius point.
     */

    public int getSize() {
        return radius;
    }

    /**
     * the method get int value and set him as radius of our ball.
     *
     * @param r the radius of our ball.
     */

    public void setSize(final int r) {
        radius = r;
    }

    /**
     * the method return the value of our color point.
     *
     * @return the color point.
     */

    public java.awt.Color getColor() {
        return color;
    }

    /**
     * the method ger java.awt.color and set him as color of our ball.
     *
     * @param col the color of our ball.
     */

    public void setColor(final java.awt.Color col) {
        color = col;
    }

    /**
     * in this method we create a path (line) in the direction of the ball. in order.
     * to do so we apply the velocity to the center point several times until we
     * create a full line.
     *
     * @return the route line.
     */

    public Line makeLineToAlmostCollidPoint() {
        Point temporary = new Point(centerPoint);
        int i = 0;
        while (i <= Utilities.NUM_OF_ITERATIONS) {
            i++;
            temporary = velocity.applyToPoint(temporary);
        }
        return new Line(centerPoint, temporary);
    }

    /**
     * the method return our velocity.
     *
     * @return our velocity
     */

    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * the method get velocity and set as our velocity.
     *
     * @param v the velocity of our ball.
     */
    public void setVelocity(final Velocity v) {
        velocity = v;
    }

    /**
     * draw the ball on the screen.
     *
     * @param surface is our DrawSurface.
     */

    public void drawOn(final DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) centerPoint.getX(), (int) centerPoint.getY(), radius);
        surface.setColor(Color.WHITE);
        surface.fillCircle((int) centerPoint.getX(), (int) centerPoint.getY(), radius);
    }

    /**
     * the method get 2 double numbers and set as our velocity.
     *
     * @param dx the dx velocity of our ball.
     * @param dy the dy velocity of our ball.
     */
    public void setVelocity(final double dx, final double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * in this method we validate that is inside a collid. we check it by compute
     * the area and check if the center point is in it.
     *
     * @param collidable a collidable
     * @param center     is the center point .
     * @return the function returns false if the center is out the collid else
     * returns true
     */

    public boolean checkIfBallCenterInBlock(final Collidable collidable, final Point center) {
        Rectangle rectangle = collidable.getCollisionRectangle();
        Point collidablePoint = rectangle.getUpperLeft();
        double colPointX = collidablePoint.getX();
        double colPointY = collidablePoint.getY();
        double centerX = center.getX();
        double centerY = center.getY();

        boolean flag = ((centerX >= colPointX) && (centerY >= colPointY)
                && (centerY <= colPointY + rectangle.getHeight()) && (centerX <= colPointX + rectangle.getWidth()));
        return flag;

    }

    /**
     * this method makes sure that the ball is inside the game limits
     * as required.
     *
     * @param center a given point.
     */
    void validateBallStayInGameLimits(final Point center) {
        forceBallToStayInGameLimit(center);
        setCenterPoint(center);
    }

    /**
     * in this method we check if there is a collision point,and if there is we update the ball velocity.
     *
     * @param flagHit        boolean to indicate if there is a collision point.
     * @param hitInfo        collision info which contains information about the hit.
     * @param newCenterPoint the new center point which we get by apply the velocity.
     */

    void checkHit(final boolean flagHit, final CollisionInfo hitInfo, final Point newCenterPoint) {
        Velocity newVelocity;
        Point hitPoint = hitInfo.collisionPoint();
        Collidable obj = hitInfo.collisionObject();
        Point pointAfterVelocity;

        if (!flagHit) {
            validateBallStayInGameLimits(newCenterPoint);
        } else {
            newVelocity = obj.hit(this, hitPoint, velocity);
            pointAfterVelocity = newVelocity.applyToPoint(centerPoint);
            boolean flagBallInside = checkIfBallInBlock(pointAfterVelocity);
            if (!flagBallInside) {
                newVelocity = obj.hit(this, hitPoint, velocity);
                setVelocity(newVelocity);
            } else {
                setVelocity(checkBallNotStack());
            }
            pointAfterVelocity = velocity.applyToPoint(hitPoint);
            validateBallStayInGameLimits(pointAfterVelocity);
        }
    }

    /**
     * this method check if game is remove from sprite.
     *
     * @param g a given game.
     * @return true or false.
     */

    public boolean isRemoveFromGame(final GameLevel g) {
        return g.isRemoveSprite(this);
    }

    /**
     * this method remove the given game from the game.
     *
     * @param g a given game.
     */

    public void removeFromGame(final GameLevel g) {
        g.removeSprite(this);
        g.getSpriteCollection().removeSprite(this);
    }

    /**
     * move the ball on the surface. check if balls next step is in the domain, if
     * not change direction of the.
     */
    public void moveOneStep() {
        Line road = makeLineToAlmostCollidPoint();
        boolean flagHit;
        Point newCenterPoint;
        Point hitPoint;
        newCenterPoint = velocity.applyToPoint(centerPoint);

        CollisionInfo hitInfo = env.getClosestCollision(road);
        hitPoint = hitInfo.collisionPoint();

        flagHit = hasHit(road, newCenterPoint, hitPoint);

        checkHit(flagHit, hitInfo, newCenterPoint);

    }

    /**
     * the method validate that the ball is not cross up limit of the game limits.
     * and make sure it does
     *
     * @param center ball center point
     */

    void validUpperBound(final Point center) {
        double centerY = center.getY();
        if (Utilities.GAME_HEIGHT - Utilities.FRAME_SIZE <= centerY) {
            center.setY(Utilities.GAME_HEIGHT - Utilities.FRAME_SIZE);
        }
    }

    /**
     * the method validate that the ball is not cross low limit of the game limits.
     * and make sure it does
     *
     * @param center ball center point
     */

    void validBottomBound(final Point center) {
        double centerY = center.getY();
        if (Utilities.GAME_START_Y + Utilities.FRAME_SIZE >= centerY) {
            center.setY(Utilities.GAME_START_Y + Utilities.FRAME_SIZE);
        }
    }

    /**
     * the method validate that the ball is not cross right limit of the game.
     * limits, and make sure it does
     *
     * @param center ball center point
     */

    void validRightBound(final Point center) {
        double centerX = center.getX();
        if (centerX >= Utilities.GAME_WIDTH - Utilities.FRAME_SIZE) {
            center.setX(Utilities.GAME_WIDTH - Utilities.FRAME_SIZE);
        }

    }

    /**
     * the method validate that the ball is not cross left limit of the game limits.
     * and make sure it does
     *
     * @param center ball center point
     */

    void validLeftBound(final Point center) {
        double centerX = center.getX();
        if (centerX <= Utilities.GAME_START_X + Utilities.FRAME_SIZE) {
            center.setX(Utilities.GAME_START_X + Utilities.FRAME_SIZE);
        }
    }

    /**
     * the method validate that the ball is inside the game limits, and make sure it.
     * does
     *
     * @param center ball center point
     */
    public void forceBallToStayInGameLimit(final Point center) {
        validLeftBound(center);
        validUpperBound(center);
        validRightBound(center);
        validBottomBound(center);
    }


    /**
     * this method checks that the ball does not stuck inside a block. for that.
     * reason we check balls with different velocity
     *
     * @param ballWithFirst  ball with first velocity
     * @param ballWithSecond ball with first velocity
     * @param velocityDx     velocity dx
     * @param velocityDy     velocity dy
     * @return in order to prevent this situation we return a new velocity for our
     * ball.
     */
    public Velocity getVelocityToPreventBallStack(final Point ballWithFirst, final Point ballWithSecond,
                                                  final double velocityDx, final double velocityDy) {
        Velocity firstVelocity = new Velocity(velocityDx, -velocityDy);
        Velocity secondVelocity = new Velocity(-velocityDx, velocityDy);
        Velocity thirdVelocity = new Velocity(-velocityDx, -velocityDy);

        if (!checkIfBallInBlock(ballWithSecond)) {
            return secondVelocity;
        } else if (!checkIfBallInBlock(ballWithFirst)) {
            return firstVelocity;

        } else {
            return thirdVelocity;
        }
    }

    /**
     * this method checks that the ball does not stuck inside a block.
     *
     * @return in order to prevent this situation we return a new velocity for our
     * ball.
     */
    public Velocity checkBallNotStack() {
        // check balls with different velocity
        Point ball1 = new Point(centerPoint.getX() + velocity.getDx(), centerPoint.getY() - velocity.getDy());
        Point ball2 = new Point(centerPoint.getX() - velocity.getDx(), centerPoint.getY() + velocity.getDy());

        return getVelocityToPreventBallStack(ball1, ball2, velocity.getDx(), velocity.getDy());
    }

    /**
     * this method check if there is an hit point by computing the lengthes and.
     * compraring them
     *
     * @param route    line in direction of the ball
     * @param center   center point
     * @param colPoint the hit point between the route and the collid
     * @return true if the ball and the block collide.
     */
    public boolean hasHit(final Line route, final Point center, final Point colPoint) {
        double disColPoint = route.start().distance(colPoint);
        double disCenter = route.start().distance(center);
        return disColPoint - disCenter < Utilities.EPS;
    }

    /**
     * this method checks if our ball is inside one of the collidables.
     *
     * @param center center point.
     * @return true if it does
     */
    public boolean checkIfBallInBlock(final Point center) {
        boolean inBlock = false;
        List<Collidable> collidables = env.getCollidableList();
        for (Collidable collidable : collidables) {
            inBlock = checkIfBallCenterInBlock(collidable, center);
            if (inBlock) {
                break;
            }
        }
        return inBlock;
    }
}
