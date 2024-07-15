package interfaces;

import gamegeometry.Velocity;
import gamesprites.Block;

import java.util.List;
/**
 * this interface is an object that stores a level's information.
 */
public interface LevelInformation {
    /**
     * this method returns the number of balls in this level.
     * @return the number of balls.
     */
    int numberOfBalls();
    /**
     * this method returns initial velocity of each ball in this level.
     * @return the list of velocity.
     */
    List<Velocity> initialBallVelocities();
    /**
     * this method returns the paddle Speed in this level.
     * @return the speed.
     */
    int paddleSpeed();
    /**
     * this method returns the paddle Width in this level.
     * @return the Width.
     */
    int paddleWidth();
    /**
     * this method returns the level name.
     * @return the name.
     */
    String levelName();
    /**
     * this method Returns a sprite with the background of the level.
     * @return the sprite.
     */
    Sprite getBackground();
    /**
     * this method Returns a list with the blocks of the level.
     * @return the list.
     */
    List<Block> blocks();
    /**
     * this method returns the number of the level's blocks.
     * @return the list.
     */
    int numberOfBlocksToRemove();
}