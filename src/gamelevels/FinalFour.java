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
 * This class creates the 4 level.
 */
public class FinalFour implements LevelInformation {
    private String levelName;
    private Sprite background;
    private List<Velocity> ballsVelocities;
    private List<Block> blockLists;
    public static final int PADDLEWIDTH = 140;
    /**
     * construct the 4 level - Final Four.
     */
    public FinalFour() {
        this.background = new Background4();
        this.levelName = "Final Four";
        this.ballsVelocities =  new ArrayList<>();
        this.ballsVelocities.add(new Velocity(4, -4));
        this.ballsVelocities.add(new Velocity(-4, -4));
        this.ballsVelocities.add(new Velocity(0, -4));
        this.blockLists = new ArrayList<>();
        Color color;
        for (int i = 0; i < 7; i++) {
            color = randomColor();
            for (int j = 0; j < 15; j++) {
                Block newBlock = new Block(new Point(725 - j * 50, 100 + i * 20), 50, 20, color);
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
