package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;
/**
 * this class is the game's animation runner.(it contains the main game's graphic loop).
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * construct an animation runner from gui object.
     * @param g the gui object.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * This method contains the main graphic loop of the game.
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}