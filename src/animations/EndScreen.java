package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;
/**
 * this class creating the game's end screen.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean isWin;
    /**
     * construct EndScreen object from a score counter and a boolean variable.
     * @param score the game's score.
     * @param win the boolean variable.
     */
    public EndScreen(int score, boolean win) {
        this.score = score;
        this.isWin = win;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(204, 0, 0));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        if (this.isWin) {
            d.drawText(70, 230, "You Won!!", 150);
        } else {
            d.drawText(70, 230, "You Lose..", 150);
        }
        d.setColor(Color.BLACK);
        d.drawText(250, 350, "Press space to continue", 30);
        d.setColor(Color.WHITE);
        d.drawText(320, 400, "Yore score is: " + this.score, 20);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
