package gamesettings;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import java.util.List;
/**
 * This class is responsible to running the level's games one after other.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private int screenWidth;
    private int screenHeight;
    /**
     * this method construct a gameFlow object from..
     * @param ar an animation runner.
     * @param ks the keyboard sensor.
     * @param screenWidth the width of the screen.
     * @param screenHeight the height of the screen.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int screenWidth, int screenHeight) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.score = new Counter(0);
    }
    /**
     * this method gets a list of levelInformation and runs them one after other.
     * @param levels the given list.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score,
                    this.screenWidth, this.screenHeight);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.isHasNoMoreBall()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndScreen(this.score.getValue(), false)));
                return;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                "space", new EndScreen(this.score.getValue(), true)));
    }
}

