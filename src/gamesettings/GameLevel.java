package gamesettings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import animations.CountdownAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesprites.Ball;
import gamesprites.Block;
import gamesprites.Paddle;
import gamesprites.ScoreIndicator;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Sprite;
import interfaces.LevelInformation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Animation;
import gamegeometry.Point;
/**
 * this class create the game.
 * @author Netanel Landesman 315873588
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environmentOfTheGame;
    private int screenWidth;
    private int screenHeight;
    private KeyboardSensor keyboard;
    private Counter gameBlockCounter;
    private Counter gameBallCounter;
    private Counter gameScore;
    private CountdownAnimation gameIntro;
    private LevelInformation gameLevel;
    private List<Block> blocks;

    /**
     * this method constructs a gameLevel object from..
     * @param level the levelInformation of this current level.
     * @param ks the keyboard sensor.
     * @param ar the animation runner.
     * @param score the score counter that keeps the current score.
     * @param screenWidth the width of the screen.
     * @param screenHeight the height of the screen.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar,
                     Counter score, int screenWidth, int screenHeight) {
        this.gameLevel = level;
        this.sprites = new SpriteCollection();
        this.sprites.addSprite(this.gameLevel.getBackground());
        this.environmentOfTheGame = new GameEnvironment();
        this.gameBlockCounter = new Counter(this.gameLevel.blocks().size());
        this.gameBallCounter = new Counter(this.gameLevel.numberOfBalls());
        this.gameScore = score;
        this.running = true;
        this.runner = ar;
        this.keyboard = ks;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameIntro = new CountdownAnimation(3, 3, this.sprites);
        this.blocks = level.blocks();
    }

    /**
     * this method adding a collidable object to game.
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environmentOfTheGame.addCollidable(c);
    }
    /**
     * this method adding a sprite object to game.
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method removing a collidable object from game.
     * @param c the collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environmentOfTheGame.removeCollidable(c);
    }
    /**
     * this method removing a sprite object from game.
     * @param s the sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * this method Initialize a new game: create the Blocks and gameSprites.Ball (and gameSprites.Paddle).
     * and add them to the game.
     */
    public void initialize() {
        //Creating a listeners:
        HitListener br = new BlockRemover(this, this.gameBlockCounter);
        HitListener ballr = new BallRemover(this, this.gameBallCounter);
        HitListener scoreListener = new ScoreTrackingListener(this.gameScore);

        //Creating a Indicators:
        ScoreIndicator si = new ScoreIndicator(this.gameScore);
        si.addToGame(this);

        //Creating the block borders:
        double borderThickness = 25;
        Block topBorder = new Block(new Point(0, borderThickness), WIDTH, borderThickness, Color.GRAY);
        Block rightBorder = new Block(new Point(WIDTH - borderThickness, borderThickness), borderThickness,
                HEIGHT - borderThickness, Color.GRAY);
        Block deathRegion = new Block(new Point(borderThickness, HEIGHT),
                WIDTH - 2 * borderThickness, borderThickness, Color.GRAY);
        Block leftBorder = new Block(new Point(0, borderThickness), borderThickness,
                HEIGHT - borderThickness, Color.GRAY);
        topBorder.addToGame(this);
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballr);

        //Creating a paddle:
        Paddle gamePaddle = new Paddle(this.screenWidth, this.screenHeight,  borderThickness,
                this.gameLevel.paddleWidth(), this.keyboard, this.gameLevel.paddleSpeed());
        gamePaddle.addToGame(this);


        //Creating the blocks:
        for (Block block : this.blocks) {
            block.addToGame(this);
            block.addHitListener(br);
            block.addHitListener(scoreListener);
        }

        //Creating the Balls:
        List<Ball> listOfBall = new ArrayList<>();
        if (this.gameLevel.numberOfBalls() > 3) { //For the second level.
            for (int i = 0; i < this.gameLevel.numberOfBalls(); i++) {
                listOfBall.add(new Ball(new Point(225 + i * 40, 530), 6, Color.RED, this.environmentOfTheGame));
                listOfBall.get(i).setVelocity(this.gameLevel.initialBallVelocities().get(i));
                listOfBall.get(i).addToGame(this);
            }
        } else {
            for (int i = 0; i < this.gameLevel.numberOfBalls(); i++) {
                listOfBall.add(new Ball(new Point(400, 540), 6, Color.WHITE, this.environmentOfTheGame));
                listOfBall.get(i).setVelocity(this.gameLevel.initialBallVelocities().get(i));
                listOfBall.get(i).addToGame(this);
            }
        }
    }

    /**
     * This method returns true if the animation should be stop and false otherwise.
     * @return true or false
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * this method draws the current state of the animation object to the screen.
     * @param d a draw surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.gameLevel.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.BLACK);
        String s = "Level Name: " + this.gameLevel.levelName();
        d.drawText(d.getWidth() - 10 * s.length(), 18, s, 15);
        this.sprites.notifyAllTimePassed();

        //checking if the game paused:
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }

        //checking if the game ended
        if (this.gameBallCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.gameBlockCounter.getValue() == 0) {
            this.gameScore.increase(100);
            this.running = false;
        }
    }

    /**
     * this method run the game and start the animation loop.
     */
    public void run() {
        this.runner.run(this.gameIntro);
        this.runner.run(this);
    }
    /**
     * this method checking if there is no more balls in this level.
     * @return true if yes false otherwise.
     */
    public boolean isHasNoMoreBall() {
        return this.gameBallCounter.getValue() == 0;
    }

   // public boolean isHasNoMoreBlocks() {
    //    return this.gameBlockCounter.getValue() == 0;
    //}
}