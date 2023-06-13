package Fauna.Predators;



import Core.Settings;
import Fauna.Predator;
import Fauna.PredatorClass;

public class Eagle extends PredatorClass implements Predator {

    private static int maxOnCell = Settings.eagleMaxOnCell;

    public Eagle() {
        super(6, 4, 1, 6);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }

}