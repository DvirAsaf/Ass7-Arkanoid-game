package sprites;

import biuoop.DrawSurface;

/**
 * @author dvir asaf 313531113.
 * Interface Description - a sprites.SpritePkg.Sprite is a game object that can be drawn to.
 * the screen (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc).
 * In our design, all of the game objects (sprites.SpritePkg.Ball, Block, collision.Paddle, ...) are Sprites --
 * they will implement the sprites.SpritePkg.Sprite interface
 */

public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d - the DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
