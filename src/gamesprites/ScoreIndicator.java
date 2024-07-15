package gamesprites;

import java.awt.Color;
import biuoop.DrawSurface;
import gamegeometry.Point;
import gamegeometry.Rectangle;
import gamesettings.Counter;
import gamesettings.GameLevel;
import interfaces.Sprite;

/**
 * This class is our game-score indicator object.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;

    /**
     * Construct a score indicator.
     * @param gameScore the game score counter.
     */
    public ScoreIndicator(Counter gameScore) {
        this.score = gameScore;
        this.rectangle = new Rectangle(new Point(0, 0), 800, 25);
    }
    /**
     * this method draws the score indicator on a DrawSurface.
     * @param surface the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        String scoreSring = "Score : " + Integer.toString(this.score.getValue());
        surface.drawText((int) ((this.rectangle.getWidth() - this.rectangle.getUpperLeft().getX())
                        / 2 - 4 * scoreSring.length()),
                (int) (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight() - 7), scoreSring, 15);
    }

    /**
     * this method notifies him that a time unit has passed.
     */
    public void timePassed() {
    }

    /**
     * this method adds the score indicator to a game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}