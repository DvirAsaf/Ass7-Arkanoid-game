package sprites;
import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utillity.BackGround;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Class Description - the sprites.Block class is implementing the collision Collidable interface.
 * @author dvir asaf 313531113.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private int hitPoint;
    private List<HitListener> hitListeners;
    private BackGround backGround;
    private Map<Integer, BackGround> backGroundTreeMap;
    /**
     * constructor - for this class.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param c         the color
     * @param hits      the number of hits
     */
    public Block(final Point upperLeft, final double width, final double height, final Color c, final int hits) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = c;
        this.hitPoint = hits;
        this.hitListeners = new ArrayList<>();
        this.backGroundTreeMap = new TreeMap<>();
    }
    /**
     * a constructor of the block get geometry.Rectangle only.
     *
     * @param rect is block rectangle.
     */
    public Block(final Rectangle rect) {
        rectangle = rect;
        this.hitListeners = new ArrayList<>();
        this.backGroundTreeMap = new TreeMap<>();
    }
    /**
     * constructor - for this class.
     *
     * @param upperLeftX the upper left x
     * @param upperLeftY the upper left y
     * @param width      the width
     * @param height     the height
     * @param hits       the number of hits
     */
    public Block(final double upperLeftX, final double upperLeftY, final double width, final double height,
                 final int hits) {
        this.rectangle = new Rectangle(new Point(upperLeftX, upperLeftY), width, height);
        this.hitPoint = hits;
        this.hitListeners = new ArrayList<>();
        this.backGroundTreeMap = new TreeMap<>();
    }
    /**
     * constructor - for this class.
     * @param rec a given rectangle.
     * @param c   a given color.
     */
    public Block(final Rectangle rec, final java.awt.Color c) {
        rectangle = rec;
        color = c;
        rectangle.setColor(c);
        this.backGroundTreeMap = new TreeMap<>();
    }
    /**
     * constructor - for this class.
     *
     * @param upperLeftX the upper left x
     * @param upperLeftY the upper left y
     * @param width      the width
     * @param height     the height
     */
    public Block(final double upperLeftX, final double upperLeftY, final double width, final double height) {
        this(upperLeftX, upperLeftY, width, height, 0);
    }
    /**
     * constructor - for this class.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param c     the color
     */
    public Block(final Point upperLeft, final double width, final double height, final Color c) {
        this(upperLeft, width, height, c, 0);
    }
    /**
     * this method set a rectangle.
     *
     * @param rec a given  rectangle
     */
    public void setRectangle(final Rectangle rec) {
        this.rectangle = rec;
    }
    /**
     * @return our rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this method set a hit point.
     *
     * @param hit a given hit point.
     */
    public void setHitPoint(final int hit) {
        this.hitPoint = hit;
    }
    /**
     * this method get a hit number.
     *
     * @return hit number.
     */
    public int getHitPoints() {
        return hitPoint;
    }
    /**
     * set the block in the desired location by the upper left point.
     * @param p a given point.
     */
    public void putBlockInPosition(final Point p) {
        rectangle.setUpperLeft(p);
    }
    /**
     * the hit method. here we check for collision and if there is we return a new collision Velocity.
     *
     * @param hitter          a given ball.
     * @param collisionPoint  a given point.
     * @param currentVelocity a given velocity.
     * @return new velocity
     */
    public Velocity hit(final Ball hitter, final Point collisionPoint, final Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        Line up = rectangle.getUpperEdge();
        Line low = rectangle.getLowerEdge();
        Line left = rectangle.getLeftEdge();
        Line right = rectangle.getRightEdge();
        // if the point is between start point x to end point x and the y value is
        // equals change direction.
        // the same change direction if it come from up or down.
        if ((collisionPoint.getY() == low.start().getY()) || (collisionPoint.getY() == up.start().getY())) {
            v.setDy(v.getDy() * -1);
        }
        // if the point is between start point y to end point y and the x value is
        // equals change direction.
        // the same change direction if it come from left or right.
        if ((collisionPoint.getX() == right.start().getX()) || (collisionPoint.getX() == left.start().getX())) {
            v.setDx(v.getDx() * -1);
        }
        this.notifyHit(hitter);
        return v;
    }
    /**
     * draw the block to the screen.
     *
     * @param surface given draw surface.
     */
    public void drawOn(final DrawSurface surface) {
        if (this.backGroundTreeMap.containsKey(this.hitPoint)) {
            this.backGround = this.backGroundTreeMap.get(this.hitPoint);
            this.backGroundTreeMap.get(this.hitPoint).drawOn(surface);
        } else if (this.backGround != null) {
            this.backGround.drawOn(surface);
        } else {
            defaultDrawOn(surface);
        }
    }
    /**
     * For the block, currently we do nothing.
     */
    public void timePassed() {
    }
      /**
     * This method will be in charge of adding the ball and the block to the game.
     * calling the appropriate game methods.
     *
     * @param game given game.
     */
        public void addToGame(final GameLevel game) {
        game.getSpriteCollection().addSprite(this);
        game.getEnv().addCollidable(this);
    }
    /**
     * this method remove from gameLevel.
     *
     * @param gameLevel a given game level.
     */
    public void removeFromGame(final GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    /**
     * @param hitter given ball.
     */
    private void notifyHit(final Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (this.hitListeners != null) {
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }
    /**
     * Sets new color.
     *
     * @param c a given color.
     */
    public void setColor(final Color c) {
        this.color = c;
    }
    /**
     * add a new listener to a list of listeners from different kinds: for example - BlockRemover BallRemover.
     * ScoreListener
     *
     * @param h add to hit listeners.
     */
    public void addHitListener(final HitListener h) {
        this.hitListeners.add(h);
    }
    /**
     * @param h remove from hit listeners.
     */
    public void removeHitListener(final HitListener h) {
        this.hitListeners.remove(h);
    }
    /**
     * @param backGroundMap a given background.
     */
    public void addBackground(final Map<Integer, BackGround> backGroundMap) {
        this.backGroundTreeMap = backGroundMap;
    }
    /**
     * @param surface a given draw surface.
     */
    private void defaultDrawOn(final DrawSurface surface) {
        int recX = (int) this.rectangle.getUpperLeft().getX();
        int recY = (int) this.rectangle.getUpperLeft().getY();
        int recW = (int) this.rectangle.getWidth();
        int recH = (int) this.rectangle.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(recX, recY, recW, recH);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(recX, recY, recW, recH);
        //the names are: middle width or height of block.
        int blockMidW = (int) this.getCollisionRectangle().getWidth() / 2;
        int blockMidH = (int) this.getCollisionRectangle().getHeight() / 2;
        if (this.hitPoint != 0) {
            String hitsNumber = "" + this.hitPoint;
            surface.drawText(recX + blockMidW, recY + blockMidH, hitsNumber, 9);
        }
    }
}
