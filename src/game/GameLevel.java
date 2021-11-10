package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.HitListener;
import collision.Paddle;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import levels.LevelName;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.CountdownAnimation;
import listeners.Counter;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import utillity.Utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dvir asaf 313531113. Class Description - The game.Game class will hold.
 * the sprites and the collidables, and will be in charge of the animation
 * (This class is currently fairly basic, we will change it in future
 * assignments
 */

public class GameLevel implements Animation {

    private SpriteCollection spriteCollection;
    private Counter blocksCounter;
    private Counter scoreCounter;
    private Counter ballsCounter;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private GUI gameGui;
    private GameEnvironment env;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreListener;
    private LevelInformation levelInfo;

    /**
     * constructor for this class.
     *
     * @param info a given level information.
     * @param key  a given keyboard sensor.
     * @param r    a given animation runner.
     * @param c    a given counter.
     * @param g    a given GUI.
     */
    public GameLevel(final LevelInformation info, final KeyboardSensor key, final AnimationRunner r, final Counter c,
                     final GUI g) {
        this.gameGui = g;
        this.scoreCounter = c;
        this.levelInfo = info;
        this.runner = r;
        this.keyboard = key;
        this.blocksCounter = new Counter(this.levelInfo.blocks().size());
    }

    /**
     * @return our gameGui.
     */
    public GUI getGameGui() {
        return gameGui;
    }

    /**
     * this method set the gameGui.
     *
     * @param gui a given gui.
     */

    public void setGameGui(final GUI gui) {
        this.gameGui = gui;
    }

    /**
     * @return our game.GameEnvironment.
     */
    public GameEnvironment getEnv() {
        return env;
    }

    /**
     * this method set the game.GameEnvironment.
     *
     * @param env1 a given game environment.
     */

    public void setEnv(final GameEnvironment env1) {
        this.env = env1;
    }

    /**
     * @return our sprites.SpritePkg.SpriteCollection.
     */
    public SpriteCollection getSpriteCollection() {
        return spriteCollection;
    }

    /**
     * this method set the sprites list.
     *
     * @param spriteCollection1 given sprite collection.
     */

    public void setSpriteCollection(final SpriteCollection spriteCollection1) {
        this.spriteCollection = spriteCollection1;
    }

    /**
     * @param s add to sprite.
     */

    public void addSprite(final Sprite s) {
        if (spriteCollection.getSpriteList() == null) {
            // call init of sprites list is not init yet.
            spriteCollection.initSpriteCollection();
            spriteCollection.getSpriteList().add(s);
        } else {
            spriteCollection.getSpriteList().add(s);
        }
    }

    /**
     * @param s remove from sprite.
     */

    public void removeSprite(final Sprite s) {
        if (isRemoveSprite(s)) {
            return;
        }
        spriteCollection.removeSprite(s);
    }

    /**
     * the method removes a sprite from the SpritePkg.Sprite collection.
     *
     * @param s the sprite that we remove.
     * @return TRUE OR FALSE.
     */
    public boolean isRemoveSprite(final Sprite s) {
        return spriteCollection.isRemoveSprite(s);
    }

    /**
     * @param c add to collidable.
     */

    public void addCollidable(final Collidable c) {
        env.addCollidable(c);
    }

    /**
     * the method removes a collidable from the game Environment.
     *
     * @param c the collidable that we remove.
     */
    public void removeCollidable(final Collidable c) {
        env.removeCollidable(c);
    }

    /**
     * this method is in charge of inserting a block list into our game.
     *
     * @param frames a given block array.
     */
    public void addBlocksToGame(final Block[] frames) {
        for (Block frame : frames) {
            frame.addToGame(this);
        }
    }

    /**
     * this method is in charge of coloring the blocks list.
     *
     * @param frames a given frames.
     * @param color  a given color.
     */
    public static void colorFrames(final Block[] frames, final Color color) {
        for (Block frame : frames) {
            frame.setColor(color);
        }
    }

    /**
     * this method is in charge of computing the upper left point of each frmae and.
     *
     * @return those points in list
     */
    public static Point[] getStartPointsOfFrames() {
        Point[] points = new Point[Utilities.NUM_OF_FRAME];
        int i = 0;
        points[i++] = new Point(Utilities.GAME_START_X, Utilities.GAME_START_Y + Utilities.FRAME_SIZE);
        points[i++] = new Point(Utilities.GAME_START_X, Utilities.GAME_START_Y + Utilities.FRAME_SIZE);
        points[i++] = new Point(Utilities.GAME_START_X + Utilities.FRAME_SIZE,
                Utilities.GAME_HEIGHT - Utilities.FRAME_SIZE);
        points[i] = new Point(Utilities.GAME_WIDTH - Utilities.FRAME_SIZE,
                Utilities.GAME_START_Y + Utilities.FRAME_SIZE);
        return points;
    }

    /**
     * this method is in charge of inserting out blocks in the same color into our.
     * game, which will be the frame of the game.
     */
    public void createFrames() {
        Block[] frames = new Block[Utilities.NUM_OF_FRAME];
        Point[] points = getStartPointsOfFrames();
        // the gui edges as blocks
        int i = 0;
        int j = 0;
        // Upper frame = 0
        // left frame = 1
        // bottom frame = 2
        // right frame = 3
        frames[i++] = new Block(new Rectangle(points[j++], Utilities.GAME_WIDTH, Utilities.FRAME_SIZE));
        frames[i++] = new Block(new Rectangle(points[j++], Utilities.FRAME_SIZE, Utilities.GAME_HEIGHT));
        frames[i++] = new Block(new Rectangle(points[j++], Utilities.GAME_WIDTH - Utilities.FRAME_SIZE * 2,
                Utilities.FRAME_SIZE));
        frames[i] = new Block(new Rectangle(points[j], Utilities.FRAME_SIZE, Utilities.GAME_HEIGHT));
        addBlocksToGame(frames);
        frames[0].addToGame(this);
        frames[1].addToGame(this);
        frames[3].addToGame(this);
        frames[2].addToGame(this);
        HitListener listener = new BallRemover(this, ballsCounter);
        // adding the deathZoneBlock to the listener
        frames[2].addHitListener(listener);
        colorFrames(frames, Color.DARK_GRAY);
        frames[2].setColor(Color.black);
        String s = this.levelInfo.levelName();
        if (levelInfo.blocks().size() == 1) {
            frames[2].setColor(Color.BLACK);
        } else if (levelInfo.blocks().size() == 15) {
            frames[2].setColor(Color.WHITE);
        } else if (levelInfo.blocks().size() == 40) {
            frames[2].setColor(Color.GREEN);
        } else if (levelInfo.blocks().size() == 133) {
            frames[2].setColor(Color.CYAN);
        }
    }

    /**
     * create score indicator and add him to the game.
     */

    public void insertScore() {
        Point scorePoint = new Point(Utilities.GAME_START_X, Utilities.GAME_START_Y);
        Rectangle scoreRectangle = new Rectangle(scorePoint, Utilities.GAME_WIDTH, Utilities.FRAME_SIZE);
        ScoreIndicator score = new ScoreIndicator(scoreRectangle, Color.lightGray, this.scoreCounter);
        score.addToGame(this);
        score.drawOn(gameGui.getDrawSurface());
    }

    /**
     * this method is in charge of inserting out paddle into our game, with a color.
     * which is chosen by me.
     */
    public void insertPaddle() {
        Point upLeftPaddle = new Point((Utilities.GAME_WIDTH / 2.0) - (this.levelInfo.paddleWidth() / 2.0),
                Utilities.GAME_HEIGHT - Utilities.FRAME_SIZE - Utilities.HEIGHT_OF_PADDLE);
        Rectangle paddle = new Rectangle(upLeftPaddle, (this.levelInfo.paddleWidth()), Utilities.HEIGHT_OF_PADDLE);
        Block block = new Block(paddle, Color.YELLOW);
        Paddle pad = new Paddle(block, Color.YELLOW);
        pad.addToGame(this);
    }

    /**
     * this method check if all the balls out of the game( you loss) or all the black out of the game.
     * this method check if the user press the "P" key and if he is so pause the game.
     *
     * @param d a given surface.
     */

    public void doOneFrame(final DrawSurface d) {
        this.spriteCollection.drawAllOn(d);
        this.spriteCollection.notifyAllTimePassed();
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        if (blocksCounter.getValue() == 0) {
            scoreCounter.increase(100);
            this.running = false;
        }
        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard)));
        }

    }

    /**
     * this method is in charge of inserting balls into our game.
     */
    public void insertBall() {
        // getting the number of balls
        int numberOfBalls = this.levelInfo.numberOfBalls();
        // creating a list of balls
        List<Ball> listOfBalls = new ArrayList<Ball>();
        // creating a list of velocities with the velocities in initialBallVelocities.
        List<Velocity> listOfVelocity = this.levelInfo.initialBallVelocities();
        // in each iteration i add a new ball to the list of balls with the velocity that was given in the list of
        // velocities. than i added the ball to the game.
        for (int i = 0; i < numberOfBalls; i++) {
            Ball b = new Ball(Utilities.GAME_WIDTH / 2, (int) (Utilities.GAME_HEIGHT * 0.92), 6, Color.BLACK);
            listOfBalls.add(b);
            listOfBalls.get(i).setVelocity(listOfVelocity.get(i));
            listOfBalls.get(i).setEnv(env);
            listOfBalls.get(i).addToGame(this);
        }
        this.ballsCounter.increase(numberOfBalls);
    }

    /**
     * this method is responsible for Run the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(3.0, 3, this.spriteCollection));
        this.runner = new AnimationRunner(gameGui);
        this.runner.run(this);
    }

    /**
     * this method return if the game shold stop or not.
     *
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return !(this.running);
    }

    /**
     * @return int that is the value of balls counter.
     */
    public int getBallsNumbers() {
        return ballsCounter.getValue();
    }

    /**
     * @return int that is the value of blocks counter.
     */
    public int getBlacksNumbers() {
        return blocksCounter.getValue();
    }

    /**
     * @return int that is the value of score counter.
     */
    public int getScore() {
        return this.scoreCounter.getValue();
    }

    /**
     * in this method we init our Arkanoid game and adding all the stuff that should.
     * be inside it.
     */
    public void initialize() {
        this.runner = new AnimationRunner(gameGui);
        this.keyboard = runner.getGameGui().getKeyboardSensor();
        env = new GameEnvironment();
        spriteCollection = new SpriteCollection();
        blocksCounter = new Counter(this.levelInfo.blocks().size());
        ballsCounter = new Counter(0);
        scoreListener = new ScoreTrackingListener(scoreCounter);
        blockRemover = new BlockRemover(this, blocksCounter);
        ballRemover = new BallRemover(this, ballsCounter);
        addStuffToGame();
    }

    /**
     * in this method we add all the stuff that should be inside our game.
     */
    public void addStuffToGame() {
        addSprite(this.levelInfo.getBackground());
        insertBall();
        createFrames();
        insertScore();
        insertBlock();
        insertPaddle();
        drawNameOfLevel();
    }

    /**
     * this method is in charge of inserting blocks.
     */
    public void insertBlock() {

        for (Block b : this.levelInfo.blocks()) {
            // adding the block remover listener
            HitListener listener = new BlockRemover(this, blocksCounter);
            // adding the block to the listener
            b.addHitListener(listener);
            // adding the scoreTrackingListener listener
            HitListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
            // adding the block to the listener
            b.addHitListener(scoreListener);
            // than adding it to the game
            b.addToGame(this);
        }
    }

    /**
     * this method draws the name of the level at the top of the screen.
     */
    public void drawNameOfLevel() {
        // creating a new NameOfLevel object with the name of the level
        Point p = new Point(0, 0);
        Rectangle r = new Rectangle(p, Utilities.GAME_WIDTH, (Utilities.GAME_HEIGHT / 30));
        LevelName n = new LevelName(r, Color.BLACK, this.levelInfo.levelName());
        // adding name to the game and drawing it
        n.addToGame(this);
        n.drawOn(gameGui.getDrawSurface());
    }
}
