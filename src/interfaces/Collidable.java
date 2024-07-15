package interfaces;
import gamegeometry.Point;
import gamegeometry.Rectangle;
import gamegeometry.Velocity;
import gamesprites.Ball;

/**
 * this interface represents all the collidable object.
 */

public interface Collidable {
    /**
     * This method return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * this method calculates a new velocity of the object that Collided.
     * @param hitter the hitting ball.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the current velocity of the colliding object.
     * @return the new velocity after the hit.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}