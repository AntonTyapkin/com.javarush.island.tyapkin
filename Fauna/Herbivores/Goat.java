package Fauna.Herbivores;



import Core.Settings;
import Fauna.Herbivor;
import Fauna.HerbivoreClass;

public class Goat extends HerbivoreClass implements Herbivor {

    private static int maxOnCell = Settings.goatMaxOnCell;

    public Goat() {
        super(65, 1, 10, 5);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }
}