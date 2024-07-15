package gamelevels;

import gamegeometry.Point;
import gamegeometry.Rectangle;
import gamegeometry.Velocity;
import gamesprites.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * This class creates the first level.
 */
public class DirectHit implements LevelInformation {
    private String levelName;
    private Sprite background;
    private List<Velocity> ballsVelocities;
    private List<Block> blockLists;
    public static final int PADDLEWIDTH = 100;
    /**
     * construct the first level - DirectHit.
     */
    public DirectHit() {
        this.levelName = "Direct Hit";
        this.ballsVelocities =  new ArrayList<>();
        this.ballsVelocities.add(new Velocity(0, -5));
        this.blockLists = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 150), 30, 30), Color.RED);
        this.blockLists.add(block);
        Point target = new Point(400, 165);
        this.background = new Background1(target);
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
        return 3;
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
