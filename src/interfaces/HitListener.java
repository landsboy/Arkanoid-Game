package interfaces;

import gamesprites.Ball;
import gamesprites.Block;

/**
 * this interface represents a object that can listens other objects obout some event.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that is being hit.
     * @param hitter the hitting ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}