package interfaces;

import biuoop.DrawSurface;
/**
 * this interface is an animation object.
 */
public interface Animation {
    /**
     * this method draws the animation On The SCREEN!
     * @param d the DrawSurface to draw on it the animation.
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method returns true if the animation should be stop and false otherwise.
     * @return true or false
     */
    boolean shouldStop();
}
