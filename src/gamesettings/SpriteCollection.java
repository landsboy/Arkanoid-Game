package gamesettings;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.util.List;
import java.util.ArrayList;

/**
 * This class stores a collection of Sprites.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;
    /**
     * this method constructs the gameSettings.SpriteCollection by creating list of Sprites.
     */
    public SpriteCollection() {
        this.listOfSprites = new ArrayList<Sprite>();
    }
    /**
     * this method adds a given sprites object to the list of Sprites.
     * @param s the given sprites object.
     */
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }
    /**
     * this method remove a given sprites object from the list of Sprites.
     * @param s the given sprites object.
     */
    public void removeSprite(Sprite s) {
        this.listOfSprites.remove(s);
    }
    /**
     * this method call timePassed() method on all sprites in the list.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < listOfSprites.size(); i++) {
            listOfSprites.get(i).timePassed();
        }
    }
    /**
     * this method call drawOn(d) method on all sprites in the list.
     * @param d the given Surface to draw on it all the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < listOfSprites.size(); i++) {
            listOfSprites.get(i).drawOn(d);
        }
    }
}