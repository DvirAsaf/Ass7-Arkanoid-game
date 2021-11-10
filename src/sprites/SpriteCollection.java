package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dvir asaf 313531113. Class Description - the goal of this class is
 * to hold collection of sprites this class supports the addition of new
 * sprites.
 */

public class SpriteCollection {

    /**
     * spriteList.
     */
    private List<Sprite> spriteList;

    /**
     * sprites.SpritePkg.SpriteCollection init - init the spriteList.
     */
    public void initSpriteCollection() {
        spriteList = new ArrayList<Sprite>();
    }

    /**
     * This method calls drawOn(d) on all sprites.
     *
     * @param d - given DrawSurface
     */
    public void drawAllOn(final DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(spriteList);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * @return spriteList
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * add a given sprites.SpritePkg.Sprite to the spriteList.
     *
     * @param s a given sprites.SpritePkg.Sprite to add
     */
    public void addSprite(final Sprite s) {
        if (s == null) {
            System.out.println("Sprite is nul");
        } else {
            spriteList.add(s);

        }
    }

    /**
     * this method remove a sprite from list.
     *
     * @param s the sprite that we remove.
     * @return true or false.
     */
    public boolean isRemoveSprite(final Sprite s) {
        if (!spriteList.contains(s)) {
            return false;
        }
        spriteList.remove(s);
        return true;
    }

    /**
     * remove s from sprite list.
     *
     * @param s a given sprite.
     */

    public void removeSprite(final Sprite s) {
        spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

}
