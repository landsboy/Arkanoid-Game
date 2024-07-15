package gamesprites;

import biuoop.DrawSurface;
import gamegeometry.Point;
import gamegeometry.Rectangle;
import gamegeometry.Velocity;
import gamesettings.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a gameSprites - Block.
 * @author Netanel Landesman 315873588
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;
    private int life;

    /**
     * Constructor that build a gameSprites.Block from rectangle.
     * @param r is a rectangle
     * @param color is the gameSprites.Block color
     */
    public Block(Rectangle r, Color color) {
        this.rect = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.life = 0;
    }

    /**
     * Constructor that build a gameSprites.Block from rectangle.
     * @param p is the upper-left of yhe gameSprites.Block
     * @param width is the width of the gameSprites.Block
     * @param height is the height of the gameSprites.Block
     * @param color is the gameSprites.Block color
     */
    public Block(Point p, double width, double height, Color color) {
        this.rect = new Rectangle(p, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.life = 0;
    }

    /**
     * This method return the gameSprites.Block.
     * @return the rectangle object of gameSprites.Block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method returns a new velocity according to the hit location on the gameSprites.Block.
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity according to the hit location on the gameSprites.Block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean flagX = false;
        boolean flagY = false;
        if (this.rect.getLeftSide().lineContainPoint(collisionPoint)
                || this.rect.getRightSide().lineContainPoint(collisionPoint)) {
            flagX = true;
        }
        if (this.rect.getUpSide().lineContainPoint(collisionPoint)
                || this.rect.getDownSide().lineContainPoint(collisionPoint)) {
            flagY = true;
        }
        if (flagX && flagY) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getX(), -currentVelocity.getY());
        } else if (flagX) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
        } else if (flagY) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getX(), -currentVelocity.getY());
        }
        return currentVelocity;
    }


    /**
     * This method draws a block on a surface.
     * @param d is the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * this method adding this block to the game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    @Override
    public void timePassed() {
    }

    /**
     * This method removes this block from the game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }
    /**
     * this method adds a given hit to the list of listeners to hit events.
     * @param hl the given hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * this method removes a given hit from the list of listeners to hit events.
     * @param hl the given hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * this method gets a ball from the game that is hitting the block and notify all the hit listeners about that.
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * this method returns this block's life number.
     * @return the block's life number.
     */
    public int getLife() {
        return this.life;
    }

}
