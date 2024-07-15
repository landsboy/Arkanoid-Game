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
 * This class creates the third level.
 */
public class Green3 implements LevelInformation {
    private String levelName;
    private Sprite background;
    private List<Velocity> ballsVelocities;
    private List<Block> blockLists;
    public static final int PADDLEWIDTH = 120;
    /**
     * construct the third level - Green 3.
     */
    public Green3() {
        this.background = new Background3();
        this.levelName = "Green 3";
        this.ballsVelocities =  new ArrayList<>();
        this.ballsVelocities.add(new Velocity(5, -5));
        this.ballsVelocities.add(new Velocity(-5, -5));
        this.blockLists = new ArrayList<>();
        Color color;
        for (int i = 0; i < 5; i++) {
            color = randomColor();
            for (int j = i + 1; j <= 10; j++) {
                Block newBlock = new Block(new Point(775 - (j - i) * 50, 180 + i * 20), 50, 20, color);
                this.blockLists.add(newBlock);
            }
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
    public int numberOfBalls() {
        return this.ballsVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
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
