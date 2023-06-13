package Fauna.Predators;



import Core.Settings;
import Fauna.Predator;
import Fauna.PredatorClass;

public class Bear extends PredatorClass implements Predator {

    private static int maxOnCell = Settings.bearMaxOnCell;

    public Bear() {
        super(250, 2, 38, 15);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }

}