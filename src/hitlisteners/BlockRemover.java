package hitlisteners;

import gamesettings.Counter;
import gamesettings.GameLevel;
import gamesprites.Ball;
import gamesprites.Block;
import interfaces.HitListener;
/**
 * This class is responsible for removing the block if hit occurs.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBlocks;

    /**
     * construct a block remover from a game and and counter of blocks.
     * @param gameLevel a game to remove blocks from.
     * @param removedBlocks the number of blocks to remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.removedBlocks = removedBlocks;
    }

    /**
     * This method removes the block if he being hit by a ball.
     * @param beingHit the block being hit.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getLife() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.removedBlocks.decrease(1);
        }
    }
}