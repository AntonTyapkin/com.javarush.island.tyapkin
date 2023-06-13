package Fauna;

import Core.Settings;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PredatorClass extends Animal implements Predator {

    public PredatorClass(double weight, int speed, double satiety, int starvingTime) {
        super(weight, speed, satiety, starvingTime);
    }

    @Override
    public void eat(List<?> listOfFood, List<?> listOfFood1) {
        try {
            //Получаем число, на основании которого выберем жертву охоты
            int eatChance = ThreadLocalRandom.current().nextInt(0, 100);
            Map<String, Integer> mapOfChanceHunt = null;
            List<String> listOfHunts = null;
            String animalToEat;
            int listOfHuntsId;

            if (this.getStarvingTime() == 0) {
                //На основании класса животного - выберем конкретную мапу с шансами
                if (this.getClass().getSimpleName().equals("Wolf")) {
                    mapOfChanceHunt = Settings.wolfMap;
                } else if (this.getClass().getSimpleName().equals("Snake")) {
                    mapOfChanceHunt = Settings.snakeMap;
                } else if (this.getClass().getSimpleName().equals("Fox")) {
                    mapOfChanceHunt = Settings.foxMap;
                } else if (this.getClass().getSimpleName().equals("Bear")) {
                    mapOfChanceHunt = Settings.bearMap;
                } else if (this.getClass().getSimpleName().equals("Eagle")) {
                    mapOfChanceHunt = Settings.eagleMap;
                }

                //Из мапы выберем всех животных которые подходят под шанс охоты
                if (mapOfChanceHunt != null) {
                    listOfHunts = mapOfChanceHunt.entrySet().stream()
                            .filter(entry -> eatChance <= entry.getValue())
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());

                }

                if (listOfHunts != null && listOfHunts.size() > 0) {
                    //в случайном порядке выбираем кого съедят
                    listOfHuntsId = ThreadLocalRandom.current().nextInt(0, listOfHunts.size());
                    animalToEat = listOfHunts.get(listOfHuntsId);
                    //Далее костыли - ищем в списке Травоядных совпадение по имени класса
                    //Если не нашли - ищем в списке Хищников
                    for (Object herbivore : listOfFood) {
                        Animal herbivore1 = (Animal) herbivore;
                        if (animalToEat.equals(herbivore1.getClass().getSimpleName())) {
                            listOfFood.remove(herbivore);
                        } else {
                            for (Object predator : listOfFood1) {
                                Animal predator1 = (Animal) predator;
                                if (animalToEat.equals(predator1.getClass().getSimpleName())) {
                                    listOfFood1.remove(predator);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
