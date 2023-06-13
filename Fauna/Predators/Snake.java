package Fauna.Predators;



import Core.Settings;
import Fauna.Predator;
import Fauna.PredatorClass;

public class Snake extends PredatorClass implements Predator {

    private static int maxOnCell = Settings.snakeMaxOnCell;

    public Snake() {
        super(2, 1, 0.3, 15);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }

}
