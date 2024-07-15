package hitlisteners;

import gamesettings.Counter;
import gamesprites.Ball;
import gamesprites.Block;
import interfaces.HitListener;

/**
 * This class makes a score tracking listener object.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * construct a score tracking listener from the game score counter.
     * @param scoreCounter this game score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * this method increases the game score by 5 points when a block is being hit.
     * @param beingHit the block that is being hit.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}