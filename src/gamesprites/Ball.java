package gamesprites;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
import gamesettings.CollisionInfo;
import gamesettings.GameLevel;
import gamesettings.GameEnvironment;
import gamegeometry.Line;
import gamegeometry.Point;
import gamegeometry.Velocity;

/**
 * this class making a ball.
 */
public class Ball implements Sprite {
    private Point location;
    private int radius;
    private Color ballColor;
    private Velocity vBall;
    private GameEnvironment ballGE;

    /**
     * construct ball from center point, Color and radius.
     * @param center the location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.location = center;
        this.radius = r;
        this.ballColor = color;
    }
    /**
     * construct  ball from two values of point, radius and color.
     * @param x the x value of  the ball's center.
     * @param y the y value of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(double x, double y, int r, Color color)  {
        this.location = new Point(x, y);
        this.radius = r;
        this.ballColor = color;
    }


    /**
     * Constructor of gameSprites.Ball with game environment.
     * @param p the center point of the ball
     * @param r the radius of tha ball
     * @param color the  color of the ball
     * @param gEnviroment is the gameSettings.GameEnvironment
     */
    public Ball(Point p, int r, Color color, GameEnvironment gEnviroment) {
        this.location = p;
        this.radius = r;
        this.ballColor = color;
        this.ballGE = gEnviroment;
    }

    /**
     * this method returns the x coordinate of the ball's center.
     * @return the x coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.location.getX();
    }
    /**
     * this method returns the y coordinate of the ball's center.
     * @return the y coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.location.getY();
    }
    /**
     * this method returns the ball's size.
     * @return the ball's size.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * this method returns the ball's color.
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return this.ballColor;
    }


    /**
     * this method draws the ball on given DrawSurface.
     * @param surface the DrawSurface to draw on it this gameSprites.Ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.ballColor);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
    this.moveOneStep();
    }

    /**
     * this method sets the ball's velocity.
     * @param v gameGeometry.Velocity to set on the ball.
     */
    public void setVelocity(Velocity v) {
        this.vBall = v;
    }

    /**
     * this method sets the ball's gameSettings.GameEnvironment.
     * @param gE gameSettings.GameEnvironment to set on the ball.
     */
    public void setGameEnvironment(GameEnvironment gE) {
        this.ballGE = gE;
    }

    /**
     * this method sets the ball's velocity.
     * @param dx the change in position on the x axes.
     * @param dy the change in position on the y axes.
     */
    public void setVelocity(double dx, double dy) {
        this.vBall = new Velocity(dx, dy);
    }

    /**
     * this method returns the ball's velocity.
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.vBall;
    }

    /**
     * this method making a line that is the ball path.
     * @return the ball path.
     */
    public Line getTrajectory() {
        Point endBallTrajectory = this.vBall.applyToPoint(this.location);
        return new Line(this.location, endBallTrajectory);
    }

    /**
     * this method moves the ball according to it's velocity.
     */
    public void moveOneStep() {
        CollisionInfo collision = this.ballGE.getClosestCollision(this.getTrajectory());
      if (collision != null) {
          this.location = new Point(collision.collisionPoint().getX() - this.vBall.getX(),
                  collision.collisionPoint().getY() - this.vBall.getY());
          this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity()));
      }
      this.location = this.getVelocity().applyToPoint(this.location);
    }

    /**
     * this method adding this ball to the game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * this method removing this ball from the game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
