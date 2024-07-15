package gamesprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamegeometry.Point;
import gamegeometry.Rectangle;
import gamegeometry.Velocity;
import gamesettings.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * this class creates a paddle.
 */
public class Paddle implements Collidable, Sprite {
    private int width;
    private int widthOfScreen;
    private int heightOfScreen;
    private int paddleSpeed;
    private final int height = 20;
    private Rectangle paddle;
    private KeyboardSensor keyboard;
    private double gameBorderThickness;

    /**
     * this method constructs the paddle object.
     * @param screenWidth the given screen Width.
     * @param screenHeight the given screen height
     * @param borderThickness the margin size.
     * @param paddleWidth a given paddle's size.
     * @param ks the Key board Sensor.
     * @param speed the given paddle's speed.
     */
    public Paddle(int screenWidth, int screenHeight, double borderThickness,
                  int paddleWidth, KeyboardSensor ks, int speed) {
        this.width = paddleWidth;
        this.widthOfScreen = screenWidth;
        this.heightOfScreen = screenHeight;
        Point upperLeft = new Point((double) this.widthOfScreen / 2 - ((double) this.width / 2),
                (double) this.heightOfScreen -  borderThickness - this.height);
        this.paddle = new Rectangle(upperLeft, this.width, this.height);
        this.keyboard = ks;
        this.gameBorderThickness = borderThickness;
        this.paddleSpeed = speed;
    }
    /**
     * This method moves the paddle left.
     */
    public void moveLeft() {
        Point newP = new Point(this.paddle.getUpperLeft().getX() - this.paddleSpeed,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(newP, this.width, this.height);
    }
    /**
     * This method moves the paddle right.
     */
    public void moveRight() {
        Point newP = new Point(this.paddle.getUpperLeft().getX() + this.paddleSpeed,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(newP, this.width, this.height);
    }

    /**
     * This method notifies the paddle that a time has passed and he need to check the user's keyboard click.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.paddle.getUpperLeft().getX() - this.paddleSpeed >= this.gameBorderThickness) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.paddle.getUpperLeft().getX() + (double) this.width + this.paddleSpeed
                <= this.widthOfScreen - this.gameBorderThickness) {
            this.moveRight();
        }
    }
    /**
     * This method draw the paddle on a given Surface.
     * @param d a given Surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
            (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * This method return the paddle.
     * @return the rectangle object of paddle.
     */
    public Rectangle getCollisionRectangle() {
       return this.paddle;
    }
    /**
     * this method returns a new velocity according to the hit location on the paddle.
     * @param hitter the ball that hit the paddle.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity according to the hit location on the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionPart = (double) this.width / 5.0;
        double collYPoint = collisionPoint.getY();
        double paddYPoint = this.paddle.getUpperLeft().getY();
        double epsilon = Math.pow(10, -5);
        if (Math.abs(collYPoint - paddYPoint) < epsilon) {
            if (collisionPoint.getX() > this.paddle.getUpperLeft().getX()
                    && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + regionPart) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 2.0 * regionPart) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 3.0 * regionPart) {
                return new Velocity(currentVelocity.getX(), -currentVelocity.getY());
            }
            if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 4.0 * regionPart) {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() < this.paddle.getUpperLeft().getX() + 5.0 * regionPart) {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        if (Math.abs(collisionPoint.getX() - this.paddle.getUpperLeft().getX()) < epsilon
                || (Math.abs(collisionPoint.getX() - this.paddle.getUpperRight().getX()) < epsilon)) {
                return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
            }
        if (collisionPoint.equals(this.paddle.getUpperLeft()) || collisionPoint.equals(this.paddle.getUpperRight())) {
            return new Velocity(-currentVelocity.getX(), -currentVelocity.getY());
        }
        return currentVelocity;
    }

    /**
     * This method Add this paddle to the game.
     * @param g a given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}