import biuoop.GUI;
import gamelevels.DirectHit;
import gamelevels.FinalFour;
import gamelevels.Green3;
import gamelevels.WideEasy;
import animations.AnimationRunner;
import gamesettings.GameFlow;
import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * That's the main class of this assignment.
 */
public class Ass6Game {
    /**
     * this is the main method of the class.
     * @param args the user's input, if he put a legal arguments - we will do as he asked.
     * but if there is no args or they are illegal we start a game with four levels that run one after the other.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("OOOOhhh Yaaaa!!!", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        List<LevelInformation> listOfLevels = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            try {
                switch (Integer.parseInt(args[i])) {
                    case 1:
                        listOfLevels.add(new DirectHit());
                        break;
                    case 2:
                        listOfLevels.add(new WideEasy());
                        break;
                    case 3:
                        listOfLevels.add(new Green3());
                        break;
                    case 4:
                        listOfLevels.add(new FinalFour());
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        if (listOfLevels.isEmpty()) {
            listOfLevels.add(new DirectHit());
            listOfLevels.add(new WideEasy());
            listOfLevels.add(new Green3());
            listOfLevels.add(new FinalFour());
        }
        GameFlow gf = new GameFlow(ar, gui.getKeyboardSensor(), gui.getDrawSurface().getWidth(),
                gui.getDrawSurface().getHeight());
        gf.runLevels(listOfLevels);
        gui.close();
    }
}
