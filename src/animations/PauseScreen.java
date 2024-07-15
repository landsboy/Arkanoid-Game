package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
/**
 * this class creating the game's pause screen.
 */
public class PauseScreen implements Animation {
    /**
    * empty construct PauseScreen object.
    */
    public PauseScreen() {
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}