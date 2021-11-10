package read;
import biuoop.KeyboardSensor;
import game.GameFlow;
import animation.AnimationRunner;
import game.scores.HighScoresTable;
import levels.LevelInformation;
import menus.MenuAnimation;
import menus.tasks.PlayGameTask;
import utillity.Task;
import java.io.FileInputStream;

import java.io.*;
import java.util.List;
/**
 * this class responsible of the type level set reader.
 * @author Dvir Asaf 313531113.
 */
public class LevelsRead {
    private File highScore;
    private String name;
    private HighScoresTable table;
    private String title;
    private GameFlow game;
    private AnimationRunner runner;
    private KeyboardSensor key;
    /**
     * constructor for this class.
     *
     * @param f   a given file name.
     * @param t   a given title.
     * @param k   a given keyboard sensor.
     * @param r   a given animation runner.
     * @param g   a given game flow.
     * @param ta  a given table.
     * @param h   a given file.
     */
    public LevelsRead(final String f, final String t, final KeyboardSensor k, final AnimationRunner r,
                      final GameFlow g, final HighScoresTable ta, final File h) {
        this.name = f;
        this.title = t;
        this.key = k;
        this.runner = r;
        this.game = g;
        this.table = ta;
        this.highScore = h;
    }
    /**
     * this method create sub menu from file and return menu animation.
     *
     * @return menu animation.
     */
    public MenuAnimation<Task<Void>> createSubMenuFromFile() {
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>(this.title, this.key, this.runner);
        File file = new File(name);
        if (file.exists()) {
            System.out.println("file.exists");
        } else {

            System.out.println("file NOT exists");
        }
        InputStream inputStream1  = null;
        try {

            inputStream1 = new FileInputStream(name);

        } catch (Exception e) {
            e.printStackTrace();

        }
        assert inputStream1 != null;
        Reader readerInfo = new InputStreamReader(inputStream1);
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(readerInfo);
            String lineThatRead = lineNumberReader.readLine();
            while (lineThatRead != null) {
                if ((!lineThatRead.isEmpty()) && (!lineThatRead.startsWith("#"))) {
                    String[] levelSymbol = lineThatRead.split(":");
                    lineThatRead = lineNumberReader.readLine();
                    List<LevelInformation> levelInfoList;
                    LevelSpecificationReader levelReader = new LevelSpecificationReader();

                    InputStream inputStream2  = null;
                    try {
                        inputStream2 = new FileInputStream(lineThatRead);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    assert inputStream2 != null;
                    Reader levelSetsInfoReader = new InputStreamReader(inputStream2);
                    levelInfoList = levelReader.fromReader(levelSetsInfoReader);
                    menu.addSelection(levelSymbol[0], levelSymbol[1], new PlayGameTask(game,
                            levelInfoList, table, highScore));
                }
                lineThatRead = lineNumberReader.readLine();
            }
            menu.setAnimationRunner(runner);
            return menu;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
