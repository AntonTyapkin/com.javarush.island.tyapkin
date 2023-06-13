package Fauna.Predators;



import Core.Settings;
import Fauna.Predator;
import Fauna.PredatorClass;

public class Fox extends PredatorClass implements Predator {

    private static int maxOnCell = Settings.foxMaxOnCell;

    public Fox() {
        super(4, 3, 1, 8);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }
}