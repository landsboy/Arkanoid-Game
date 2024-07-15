package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * this class responsible of the user's Keyboard pressing.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean toStop;
    private boolean isAlreadyPressed;
    /**
     * construct KeyPressStoppableAnimation object from..
     * @param sensor the keyboard sensor
     * @param key the key to check if pressed.
     * @param animation the animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.toStop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
            this.animation.doOneFrame(d);
            if (this.keyboardSensor.isPressed(this.key) && !this.isAlreadyPressed) {
                this.toStop = true;
        }
            this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.toStop;
    }
}
