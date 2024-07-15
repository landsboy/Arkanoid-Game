package gamelevels;

import biuoop.DrawSurface;
import gamegeometry.Point;
import interfaces.Sprite;

import java.awt.Color;
/**
 * This class creating the background of the Wide Easy level.
 */
public class Background2 implements Sprite {
    private Point centerOfSun;
    /**
     * construct Background2 object from the place of the Sun.
     * @param center the sun's center.
     */
    public Background2(Point center) {
        this.centerOfSun = center;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int sunSize = 50;
        d.setColor(Color.ORANGE);
        d.fillCircle((int) this.centerOfSun.getX(), (int) this.centerOfSun.getY(),  (sunSize * 3) / 2);
        d.setColor(Color.YELLOW);
        d.fillCircle((int) this.centerOfSun.getX(), (int) this.centerOfSun.getY(),  sunSize);
        d.setColor(new Color(240, 230, 140));
        for (int i = 0; i < 700; i += 10) {
            d.drawLine((int) this.centerOfSun.getX(), (int) this.centerOfSun.getY(),
                    i + 20, (int) this.centerOfSun.getY() + 100);
        }
    }

    @Override
    public void timePassed() {

    }
}
