package collision;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import utillity.Utilities;

import java.awt.Color;


/**
 * @param <Block> the type of paddle.
 * @ @author dvir asaf 313531113. Class Description - The collision.Paddle is the player
 * in the game. It is a rectangle that is controlled by the arrow keys, and
 * moves according to the player key presses. It should implement the sprites.SpritePkg.Sprite
 * and the collision.Collidable interfaces. It should also know how to move to the left
 * and to the right
 * @author Dvir Asaf 313531113.
 */

public class Paddle<Block> implements Sprite, Collidable {
    /**
     * keyboard.
     */
    private biuoop.KeyboardSensor keyboard;
    /**
     * leftBound.
     */
    private double leftBound;
    /**
     * rightBound.
     */
    private double rightBound;
    /**
     * Color.
     */
    private Color col;
    /**
     * Block.
     */
    private sprites.Block block;

    /**
     * constructor for this class.
     *
     * @param block1 is the black of paddle.
     */

    public Paddle(final sprites.Block block1) {
        block = block1;
    }

    /**
     * constructor for this class.
     *
     * @param b is the black of paddle.
     * @param c is the color of paddle.
     */

    public Paddle(final sprites.Block b, final Color c) {
        block = b;
        col = c;
        getCollisionRectangle().setColor(c);
    }

    /**
     * in this method we move the paddle one movement to left.
     */

    public void moveLeft() {
        Point newUpLeftPoint = getCollisionRectangle().getUpperLeft();
        if (validLeftBound(newUpLeftPoint)) {
            return;
        }
        newUpLeftPoint.setX(newUpLeftPoint.getX() - Utilities.MOVEMENT);
        block.putBlockInPosition(newUpLeftPoint);
    }

    /**
     * in this method we move the paddle one movement to right.
     */
    public void moveRight() {
        Point newUpLeftPoint = getCollisionRectangle().getUpperLeft();
        if (validRightBound(newUpLeftPoint)) {
            return;
        }
        newUpLeftPoint.setX(newUpLeftPoint.getX() + Utilities.MOVEMENT);
        block.putBlockInPosition(newUpLeftPoint);
    }

    /**
     * method check if the "left" or "right" keys are pressed, and if so move it
     * accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * draw the black on the game.
     *
     * @param d is the draw Surface.
     */

    public void drawOn(final DrawSurface d) {
        block.getCollisionRectangle().setColor(col);
        block.drawOn(d);
    }

    /**
     * @return getCollisionRectangle
     */

    public Rectangle getCollisionRectangle() {
        return block.getCollisionRectangle();
    }

    /**
     * in this method we find out in which section of the paddle the hit point
     * occurs.
     *
     * @param hitPoint the hit point on the paddle.
     * @return the correct section of the hit in the paddle.
     */
    public int findHitSection(final Point hitPoint) {
        double sectionLen = getCollisionRectangle().getWidth() / Utilities.FIX_NUMBER;
        double upLeftX = getCollisionRectangle().getUpperLeft().getX();
        double hitPointX = hitPoint.getX();
        double endSec1 = upLeftX + sectionLen;
        double endSec2 = endSec1 + sectionLen;
        double endSec3 = endSec2 + sectionLen;
        double endSec4 = endSec3 + sectionLen;
        if (hitPointX <= endSec1) {
            return Utilities.OPTION_1;
        } else if (hitPointX <= endSec2) {
            return Utilities.OPTION_2;
        } else if (hitPointX <= endSec3) {
            return Utilities.OPTION_3;
        } else if (hitPointX <= endSec4) {
            return Utilities.OPTION_4;
        } else {
            return Utilities.OPTION_5;
        }
    }

    /**
     * in this method we find out in which section of the paddle the hit point
     * occurs. after we know the correct section we change to the required velocity.
     *
     * @param hitter          not use.
     * @param collisionPoint  the hit point
     * @param currentVelocity the current velocity
     * @return the required velocity.
     */

    public Velocity hit(final Ball hitter, final Point collisionPoint, final Velocity currentVelocity) {
        Velocity changeVelocity = currentVelocity;
        double constantSpeed = Math.sqrt(
                currentVelocity.getDx() * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy());
        int hitSection = findHitSection(collisionPoint);
        switch (hitSection) {
            case Utilities.OPTION_5:
                changeVelocity = Velocity.fromAngleAndSpeed(Utilities.ANGLE_5, constantSpeed);
                break;
            case Utilities.OPTION_4:
                changeVelocity = Velocity.fromAngleAndSpeed(Utilities.ANGLE_4, constantSpeed);
                break;
            case Utilities.OPTION_3:
                // it should keep its horizontal direction and only change its vertical one
//                changeVelocity = Velocity.fromAngleAndSpeed(Utilities.ANGLE_3, constantSpeed);
                Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
                changeVelocity = new Velocity(v.getDx() * -1, v.getDy());
                break;
            case Utilities.OPTION_2:
                changeVelocity = Velocity.fromAngleAndSpeed(Utilities.ANGLE_2, constantSpeed);
                break;
            case Utilities.OPTION_1:
                changeVelocity = Velocity.fromAngleAndSpeed(Utilities.ANGLE_1, constantSpeed);
                break;
            default:
                break;
        }
        return changeVelocity;
    }

    /**
     * this method validates that we stay in bound and not going over the left bound.
     *
     * @param padUpLeftP the given point.
     * @return true if we cross the left bound else false
     */

    public Boolean validLeftBound(final Point padUpLeftP) {
        int leftLimit = Utilities.GAME_START_X + Utilities.MOVEMENT + Utilities.FRAME_SIZE;
        return padUpLeftP.getX() < leftLimit;
    }

    /**
     * this method validates that we stay in bound and not going over the right.
     * bound
     *
     * @param padUpLeftP the given point.
     * @return true if we cross the right bound else false
     */

    public Boolean validRightBound(final Point padUpLeftP) {
        int rightLimit = Utilities.GAME_WIDTH - Utilities.MOVEMENT - Utilities.FRAME_SIZE;
//        getCollisionRectangle();
        double recWidth = block.getCollisionRectangle().getWidth();
        return recWidth + padUpLeftP.getX() > rightLimit;

    }

    /**
     * this method is in charge of inserting our paddle to the game.
     *
     * @param g the given game.
     */
    public void addToGame(final GameLevel g) {
        keyboard = g.getGameGui().getKeyboardSensor();
        g.getEnv().addCollidable(this);
        g.getSpriteCollection().addSprite(this);

    }

    /**
     * setBounds leftLimit & rightLimit.
     *
     * @param leftLimit  the left linit.
     * @param rightLimit the right limit.
     */
    public void setBounds(final double leftLimit, final double rightLimit) {
        leftBound = leftLimit;
        rightBound = rightLimit;
    }

    /**
     * @return color.
     */
    public Color getCol() {
        return col;
    }

    /**
     * @return leftBound
     */
    public double getLeftBound() {
        return leftBound;
    }

    /**
     * @return rightBound
     */
    public double getRightBound() {
        return rightBound;
    }

    /**
     * @return block
     */
    public sprites.Block getBlock() {
        return block;
    }
}
