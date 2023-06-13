package Fauna.Herbivores;


import Core.Settings;
import Fauna.Herbivor;
import Fauna.HerbivoreClass;

public class Horse extends HerbivoreClass implements Herbivor {

    private static int maxOnCell = Settings.horseMaxOnCell;

    public Horse() {
        super(300, 3, 45, 5);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }
}