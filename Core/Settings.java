package Core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton Tyapkin
 * @project Island
 */
public class Settings {
    //Мапы с процентовками вероятности охоты
    public static Map<String, Integer> wolfMap = new HashMap<>();
    public static Map<String, Integer> snakeMap = new HashMap<>();
    public static Map<String, Integer> foxMap = new HashMap<>();
    public static Map<String, Integer> bearMap = new HashMap<>();
    public static Map<String, Integer> eagleMap = new HashMap<>();

    static {

        wolfMap.put("Horse", 10);
        wolfMap.put("Deer", 15);
        wolfMap.put("Rabbit", 60);
        wolfMap.put("Hamster", 80);
        wolfMap.put("Goat", 60);
        wolfMap.put("Sheep", 70);
        wolfMap.put("Boar", 15);
        wolfMap.put("Cow", 10);
        wolfMap.put("Duck", 40);

        snakeMap.put("Fox", 15);
        snakeMap.put("Rabbit", 20);
        snakeMap.put("Hamster", 40);
        snakeMap.put("Duck", 10);

        foxMap.put("Rabbit", 70);
        foxMap.put("Hamster", 90);
        foxMap.put("Duck", 40);
        foxMap.put("Caterpillar", 40);

        bearMap.put("Snake", 80);
        bearMap.put("Horse", 40);
        bearMap.put("Deer", 80);
        bearMap.put("Rabbit", 80);
        bearMap.put("Hamster", 90);
        bearMap.put("Goat", 70);
        bearMap.put("Sheep", 70);
        bearMap.put("Boar", 50);
        bearMap.put("Cow", 20);
        bearMap.put("Duck", 10);

        eagleMap.put("Fox", 10);
        eagleMap.put("Rabbit", 90);
        eagleMap.put("Hamster", 90);
        eagleMap.put("Duck", 80);
    }

    //Размеры острова
    public static int maxY = 100;
    public static int maxX = 20;
    //Максимальное кол-во зверей в клетках
    //Хищники
    public static int wolfMaxOnCell = 30;
    public static int snakeMaxOnCell = 30;
    public static int foxMaxOnCell = 30;
    public static int bearMaxOnCell = 5;
    public static int eagleMaxOnCell = 20;
    //Травоядные
    public static int horseMaxOnCell = 20;
    public static int deerMaxOnCell = 20;
    public static int rabbitMaxOnCell = 150;
    public static int hamsterMaxOnCell = 500;
    public static int goatMaxOnCell = 140;
    public static int sheepMaxOnCell = 50;
    public static int boarMaxOnCell = 10;
    public static int cowMaxOnCell = 10;
    public static int duckMaxOnCell = 200;
    public static int caterpillarMaxOnCell = 1000;
    //Растения
    public static int herbsMaxOnCell = 200;

    public Settings() {
    }
}
