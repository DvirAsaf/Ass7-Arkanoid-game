
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import animation.AnimationRunner;
import game.scores.HighScoresTable;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import menus.MenuAnimation;
import read.LevelsRead;
import utillity.Task;
import menus.tasks.ExitTask;
import menus.tasks.ShowHiScoresTask;
import utillity.Utilities;

import java.io.File;
/**
 * this class is the main of all class and operate all of them.
 * @author Dvir Asaf 313531113.
 */
public class Ass7Game {
    /**
     * this method initialized game level class.
     * @param args arguments from the user.
     */
    public static void main(final String[] args) {
        GUI gameGui = new GUI("Arkanoid", Utilities.GAME_WIDTH, Utilities.GAME_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gameGui);
        KeyboardSensor keyboardSensor = gameGui.getKeyboardSensor();
        String setLevel = null;
        int length = args.length;
        if (length > 0) {
            setLevel = args[0];
        } else {
            setLevel = "resources/level_config.txt";
        }
        int topScores = 5;
        HighScoresTable highScoresTable = new HighScoresTable(topScores);
        File highScoreF = new File("highscores");
        GameFlow game = new GameFlow(highScoresTable, runner, keyboardSensor, highScoreF, gameGui);
        try {
            highScoresTable.load(highScoreF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>("Welcome to Arkanoid Game", keyboardSensor, runner);
        KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(keyboardSensor, "space",
                new HighScoresAnimation(highScoresTable));
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(runner, key));
        LevelsRead levelsRead =
                new LevelsRead(setLevel, "Choose Mode:", keyboardSensor, runner, game, highScoresTable, highScoreF);
        if (levelsRead == null) {
            System.out.println("levelsRead = null");
        }
        MenuAnimation<Task<Void>> menuAnimation = levelsRead.createSubMenuFromFile();
            menu.addSubMenu("s", "Start", menuAnimation);
        menu.addSelection("q", "Quit", new ExitTask(runner));
        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            key.reset();
            menu.reset();
        }
    }
}
