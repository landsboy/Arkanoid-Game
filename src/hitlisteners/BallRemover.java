package hitlisteners;

import gamesettings.Counter;
import gamesettings.GameLevel;
import gamesprites.Ball;
import gamesprites.Block;
import interfaces.HitListener;
/**
 * This class is responsible for removing the ball if he hit the deathRegion.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBalls;
    /**
     * construct a gameSprites.Ball remover from a game and and counter of blocks.
     * @param gameLevel a game to remove balls from.
     * @param removedBalls the number of balls to remove.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.removedBalls = removedBalls;
    }
    /**
     * This method removes the ball if he hit the deathRegion.
     * @param beingHit the block being hit.
     * @param hitter the ball that hits the deathRegion.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.removedBalls.decrease(1);
    }
}
