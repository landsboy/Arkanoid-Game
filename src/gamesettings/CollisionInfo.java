package gamesettings;

import gamegeometry.Point;
import interfaces.Collidable;

/**
 * This class stores the information of the interfaces.Collidable objects and the collisionPoint.
 */
public class CollisionInfo {
    private Point collisionPointInfo;
    private Collidable collisionObjectInfo;
    /**
     * Constructor that build a gameSettings.CollisionInfo by a gameGeometry.Point and a some collidable object.
     * @param p point where the collision occurs
     * @param object object that involved in the collision
     */
    public  CollisionInfo(Point p, Collidable object) {
        this.collisionPointInfo = p;
        this.collisionObjectInfo = object;
    }
    /**
     * This method returns the point where the collision occurs.
     * @return the point where the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPointInfo;
    }
    /**
     * This method returns the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObjectInfo;
    }
}