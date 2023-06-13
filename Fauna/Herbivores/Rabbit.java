package Fauna.Herbivores;



import Core.Settings;
import Fauna.Herbivor;
import Fauna.HerbivoreClass;

public class Rabbit extends HerbivoreClass implements Herbivor {

    private static int maxOnCell = Settings.rabbitMaxOnCell;

    public Rabbit() {
        super(3, 3, 0.45, 7);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }
}