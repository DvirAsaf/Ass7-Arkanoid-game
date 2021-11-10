package menus.tasks;
import game.GameFlow;
import game.scores.HighScoresTable;
import levels.LevelInformation;
import utillity.Task;
import java.io.File;
import java.util.List;
/**
 * this class is play the task.
 * @author Dvir Asaf 313531113.
 */
public class PlayGameTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levelInformationList;
    private HighScoresTable table;
    private File file;

    /**
     * constructor for this class.
     *
     * @param game    - the gameFlow class that starts the game.
     * @param list    - the level list.
     * @param table   - HighScoresTable.
     * @param file    - File.
     */
    public PlayGameTask(final GameFlow game, final List<LevelInformation> list, final HighScoresTable table,
                        final File file) {
        this.gameFlow = game;
        this.levelInformationList = list;
        this.table = table;
        this.file = file;
    }

    /**
     * this method run this class.
     *
     * @return null.
     */
    public Void run() {
        try {
            this.table.load(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.gameFlow.runLevels(levelInformationList);
        return null;
    }
}

