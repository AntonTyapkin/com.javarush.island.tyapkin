package Fauna.Herbivores;



import Core.Settings;
import Fauna.Herbivor;
import Fauna.HerbivoreClass;

public class Caterpillar extends HerbivoreClass implements Herbivor {

    private static int maxOnCell = Settings.caterpillarMaxOnCell;

    public Caterpillar() {
        super(0.01, 1, 0.0025, 2);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }
}