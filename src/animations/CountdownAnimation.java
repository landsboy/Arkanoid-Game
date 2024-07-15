package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesettings.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;
/**
 * This class is a count down animation object that will show a countdown from countFrom back to 1 on the screen.
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean toStop;
    private int counting;
    /**
     * construct a CountdownAnimation object from..
     * @param numOfSeconds the time to display this the count down animation.
     * @param countFrom the number to count down from.
     * @param gameScreen the game's sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.counting = countFrom + 1;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.toStop = false;
        this.sleeper = new Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.counting == 0) {
            this.toStop = true;
        }
        if (this.counting != this.countFrom + 1) { // To avoid that the screen will stop before the counting!
            this.sleeper.sleepFor((long) this.numOfSeconds * 1000 / this.countFrom);
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        if (this.counting == 1) {
            d.drawText(330, d.getHeight() / 2 + 30, "GO!", 100);
        } else {
            d.drawText(370, d.getHeight() / 2 + 30, Integer.toString(this.counting - 1), 100);
        }
        this.counting--;
    }

    @Override
    public boolean shouldStop() {
        return this.toStop;
    }
}