package game;

import animation.*;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.scores.HighScoresTable;
import game.scores.ScoreInfo;
import levels.LevelInformation;
import listeners.Counter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Dvir Asaf 313531113.
 * this class run the game.
 */

public class GameFlow {
    private final GUI gameGui;
    private final AnimationRunner runner;
    private final KeyboardSensor key;
    private Counter counter;
    private int size;
    private Status status;
    private final HighScoresTable highScoresTable;
    private final File file;
    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * Failure status.
         */
        LOSE,
        /**
         * Success status.
         */
        WIN
    }
    /**
     * constructor - for this class.
     * @param highScoresTable a given high scores table.
     * @param r a given animation runner.
     * @param k a given keyboard sensor.
     * @param f a given file.
     * @param g a given GUI.
     */
    public GameFlow(final HighScoresTable highScoresTable, final AnimationRunner r, final KeyboardSensor k,
                    final File f, final GUI g) {
        this.runner = r;
        this.key = k;
        this.counter = new Counter(0);
        this.status = Status.WIN;
        this.highScoresTable = highScoresTable;
        this.gameGui = g;
        this.file = f;
    }

    /**
     * this method show the status of the game. if you win or lose it call for the correct screen.
     */
    private void showGameStatus() {
        if (this.status == Status.WIN) {
            this.runner.run(new KeyPressStoppableAnimation(this.key, "space",
                    new WinningScreen(this.counter)));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(this.key, "space", new
                    LosingScreen(this.counter)));
        }
    }
    /**
     * this method run the game.
     *
     * @param levels a given list of level information.
     */
    public void runLevels(final List<LevelInformation> levels) {
        //load  the scores table:
        try {
            this.highScoresTable.load(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Run levels:
        for (LevelInformation levelInfo : levels) {
            //create level using level information:
            GameLevel level = new GameLevel(levelInfo, key, this.runner, this.counter, gameGui);
            //initialize the level:
            level.initialize();
            level.run();
            this.size = level.getScore();
            if (level.getBallsNumbers() == 0) {
                this.status = Status.LOSE;
                break;
            }
            if (level.getBlacksNumbers() == 0) {
                this.status = Status.WIN;
            }
        }
        showGameStatus();
        addScoreToTable(getUserName());
        this.runner.run(new KeyPressStoppableAnimation(this.key, "space",
                new HighScoresAnimation(this.highScoresTable)));
        this.counter = new Counter(0);
    }
    /**
     * this method pop up question and return the user name.
     * @return the user name.
     */
    private String getUserName() {
        DialogManager dialog = this.runner.getGameGui().getDialogManager();
        return dialog.showQuestionDialog("Name", "Please enter your name?", "BIU-student");
    }

    /**
     * this method add user name to the table.
     * @param name a given name.
     */
    private void addScoreToTable(final String name) {
        this.highScoresTable.add(new ScoreInfo(name, this.size));
        try {
            this.highScoresTable.save(new File("highscores"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
