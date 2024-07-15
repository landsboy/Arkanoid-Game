package gamelevels;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
/**
 * This class draw's the background of the Final 4 level.
 */
public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //The background color:
        d.setColor(new Color(51, 204, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Creating the rain:
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(150 + 10 * i, 420, 100 + 10 * i, 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(600 + 10 * i, 450, 550 + 10 * i, 600);
        }

        //Creating the clouds:
        d.setColor(new Color(153, 153, 153));
        d.fillCircle(170, 400, 30);
        d.fillCircle(180, 430, 25);
        d.fillCircle(620, 420, 30);
        d.fillCircle(610, 450, 25);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(200, 395, 25);
        d.fillCircle(650, 430, 25);
        d.setColor(new Color(153, 153, 153).darker());
        d.fillCircle(230, 400, 30);
        d.fillCircle(215, 425, 20);
        d.fillCircle(680, 450, 30);
        d.fillCircle(640, 460, 20);
    }

    @Override
    public void timePassed() {
    }
}
