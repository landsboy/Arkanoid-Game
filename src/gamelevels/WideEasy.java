package gamelevels;

import gamegeometry.Point;
import gamegeometry.Velocity;
import gamesprites.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class creates the second level.
 */
public class WideEasy implements LevelInformation {
    private String levelName;
    private Sprite background;
    private List<Velocity> ballsVelocities;
    private List<Block> blockLists;
    public static final int PADDLEWIDTH = 500;
    /**
     * construct the second level - Wide Easy.
     */
    public WideEasy() {
        this.levelName = "Wide Easy";
        this.ballsVelocities = new ArrayList<>();
        for (int i = -100; i <= 100; i += 20) {
            if (i == 0) {
                continue;
            }
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(i, 5));
        }
        this.background = new Background2(new Point(100, 150));
        this.blockLists = new ArrayList<>();
        Color newColor = randomColor();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                 newColor = randomColor();
            }
            Block block = new Block(new Point((double) i * 50 + 25, (double) 250), 50, 20, newColor);
            this.blockLists.add(block);
        }
    }
    /**
     * This method create a random color and return it.
     * @return the random color.
     */
    public  static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return  new Color(r, g, b);
    }

    @Override
    /**
     * this method returns balls number in this level.
     * @return balls number.
     */
    public int numberOfBalls() {
        return this.ballsVelocities.size();
    }

    @Override
    /**
     * this method returns the ball's list of velocities.
     * @return the list.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return PADDLEWIDTH;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blockLists;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blockLists.size();
    }
}
