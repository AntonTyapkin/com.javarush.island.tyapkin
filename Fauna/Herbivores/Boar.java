package Fauna.Herbivores;


import Core.Settings;
import Fauna.Animal;
import Fauna.Herbivor;
import Fauna.HerbivoreClass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends HerbivoreClass implements Herbivor {

    private static int maxOnCell = Settings.boarMaxOnCell;

    public Boar() {
        super(47, 2, 7, 8);
    }

    public static int getMaxOnCell() {
        return maxOnCell;
    }

    @Override
    public void eat(List<?> listOfFood, List<?> listOfFood1) {
        try {
            if (this.getSatiety() < this.getMaxSatiety()) {
                int eatChance = ThreadLocalRandom.current().nextInt(0, 100);
                if (eatChance > 50) {
                    double diff = this.getMaxSatiety() - this.getSatiety();
                    if (listOfFood.size() > diff) {
                        this.setSatiety(this.getMaxSatiety());
                        this.setStarvingTime(this.getMaxStarvingTime());
                        listOfFood.subList(0, (int) diff).clear();
                    }
                } else if (eatChance > 10 && eatChance < 50) {
                    for (Object predator : listOfFood1) {
                        Animal predator1 = (Animal) predator;
                        if (predator1.getClass().getSimpleName().equals("Hamster")) {
                            listOfFood1.remove(predator);
                        }
                    }
                } else if (eatChance < 10) {
                    for (Object predator : listOfFood1) {
                        Animal predator1 = (Animal) predator;
                        if (predator1.getClass().getSimpleName().equals("Caterpillar")) {
                            listOfFood1.remove(predator);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}