package interfaces;

import biuoop.DrawSurface;
/**
 * this interface represents a object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * this method draw the sprite object to the screen.
     * @param d a given surface to draw on it.
     */
    void drawOn(DrawSurface d);
    /**
     * this method notify the sprite that time has passed.
     */
    void timePassed();
}
