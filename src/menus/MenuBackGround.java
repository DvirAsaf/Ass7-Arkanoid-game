package menus;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;
import java.util.List;
/**
 * this class is responsible of background menu.
 * @author Dvir Asaf 313531113.
 */
public class MenuBackGround implements Sprite {
    private String title;
    private List<String> keys;
    private List<String> messages;


    /**
     * constructor for this class.
     *
     * @param t     the title
     * @param k     the keys
     * @param m     the messages
     */
    public MenuBackGround(final String t, final List<String> k, final List<String> m) {
        this.title = t;
        this.keys = k;
        this.messages = m;
    }

    /**
     * this method draw the background for the menu.
     * @param d a given draw surface.
     */
    public void drawOn(final DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawText(100, 100, this.title, 40);
        int y = 300;
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(100, y, "(" + this.keys.get(i) + ") " + this.messages.get(i), 30);
            y += 40;
        }

    }

    @Override
    public void timePassed() {
    }
}


