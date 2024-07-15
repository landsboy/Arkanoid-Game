package gamesettings;

import gamegeometry.Line;
import gamegeometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;
/**
 * this class represents the "game environment" i.e.- game's collection of collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> collidableInTheGame;
    /**
     * this method constructs the interfaces.Collidable's List of the game object.
     */
    public GameEnvironment() {
        this.collidableInTheGame = new ArrayList<>();
    }
    /**
     * this method adds the given collidable to the environment.
     * @param c the given collidable.
     */
    public void addCollidable(Collidable c) {
        collidableInTheGame.add(c);
    }
    /**
     * this method removes the given collidable from the environment.
     * @param c the given collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableInTheGame.remove(c);
    }
    /**
     * this method checking if list pf collidable from the environment is empty.
     * @return true if tes, false - otherwise
     */
    public boolean isEmpty() {
        return this.collidableInTheGame.isEmpty();
    }

    /**
     * this method gets a trajectory line and returns the closest collision information
     * if there is no collision, the method returns null.
     * @param trajectory the given line.
     * @return collision information, null if there isn't any.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableInTheGame.isEmpty()) {
            return null;
        }
        Point firstCollision = null;
        int j = 0;
        //Finding if there is some collision, if yes - find the first Collision point.
        while (firstCollision == null && j < this.collidableInTheGame.size()) {
            firstCollision = trajectory.
                    closestIntersectionToStartOfLine(collidableInTheGame.get(j).getCollisionRectangle());
            j++;
        }
        if (firstCollision == null) {
            return null;
        }
        int collisionIndex = j - 1;
        double closetCollision = trajectory.start().distance(firstCollision);
        Point closetPointCollision = firstCollision;
        //Compare the other collidable objects with the first Collision point we found.
        for (int i = collisionIndex + 1; i < this.collidableInTheGame.size(); i++) {
            Point pCollision =
                    trajectory.closestIntersectionToStartOfLine(collidableInTheGame.get(i).getCollisionRectangle());
            if (pCollision != null) {
                if (trajectory.start().distance(pCollision) < closetCollision) {
                    closetPointCollision = pCollision;
                    closetCollision = trajectory.start().distance(pCollision);
                    collisionIndex = i;
                }
            }
        }
        return new CollisionInfo(closetPointCollision, this.collidableInTheGame.get(collisionIndex));
    }
}