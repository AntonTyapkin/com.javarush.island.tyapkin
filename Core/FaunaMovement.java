package Core;


import Fauna.Animal;
import Fauna.Herbivor;
import Fauna.Predator;
import Island.Cell;
import Island.Island;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FaunaMovement {

    public FaunaMovement() {
    }

    //Ничего сложного в классе. Проверяем возножность движения по сетке.
    //Если двигаться можно - копируем объект животного в соседнюю клетку, удаляем из исходной
    public void movingFaunaInCells(List<Animal> animalsList, Cell[][] island
            , int positionY, int positionX) {

        int MIN_Y = 0;
        int MIN_X = 0;
        int MAX_Y = island.length - 1;
        int MAX_X = island[MAX_Y].length - 1;

        for (Animal animal : animalsList) {
            int movementChance = ThreadLocalRandom.current().nextInt(0, 100);

            if (movementChance > 80) {

                //Получаем случайное число количества шагов объекта животного
                int animalMove = ThreadLocalRandom.current().nextInt(0, animal.getSpeed());

                Field maxOnCellField;
                int maxOnCell;
                try {
                    maxOnCellField = animal.getClass().getDeclaredField("maxOnCell");
                    maxOnCellField.setAccessible(true);
                    maxOnCell = (int) maxOnCellField.get(null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                //Получаем направление для движения от объекта животного
                MovementDirection movementDirection = animal.setDirection();
                try {
                    if (movementDirection.equals(MovementDirection.UP)) {
                        //Проверим - можно ли пойти по указанному направлению
                        if ((positionY - animalMove) >= MIN_Y) {
                            //Проверим не будет ли переселения. Если будет - получаем false и пропускаем ход. Далее по аналогии
                            if (checkCellToMaxPopulation(animal, island, positionY - animalMove, positionX, maxOnCell)) {
                                island[positionY - animalMove][positionX].addAnimal(animal);
                                animalsList.remove(animal);
                            }
                        }
                    } else if (movementDirection.equals(MovementDirection.RIGHT)) {
                        if ((positionX + animalMove) <= MAX_X) {
                            if (checkCellToMaxPopulation(animal, island, positionY, positionX + animalMove, maxOnCell)) {
                                island[positionY][positionX + animalMove].addAnimal(animal);
                                animalsList.remove(animal);
                            }
                        }
                    } else if (movementDirection.equals(MovementDirection.DOWN)) {
                        if ((positionY + animalMove) <= MAX_Y) {
                            if (checkCellToMaxPopulation(animal, island, positionY + animalMove, positionX, maxOnCell)) {
                                island[positionY + animalMove][positionX].addAnimal(animal);
                                animalsList.remove(animal);
                            }
                        }
                    } else if (movementDirection.equals(MovementDirection.LEFT)) {
                        if ((positionX - animalMove) >= MIN_X) {
                            if (checkCellToMaxPopulation(animal, island, positionY, positionX - animalMove, maxOnCell)) {
                                island[positionY][positionX - animalMove].addAnimal(animal);
                                animalsList.remove(animal);
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Метод который проверяет возможность переселения в соседнюю ячейку
    private boolean checkCellToMaxPopulation(Animal animal, Cell[][] island, int positionY, int positionX, int maxOnCell) {
        boolean canAddNewAnimal = false;

        int animalInCell = 0;
        if (animal instanceof Predator) {
            animalInCell = (int) island[positionY][positionX].countPredators();
        } else if (animal instanceof Herbivor) {
            animalInCell = (int) island[positionY][positionX].countHerbivores();
        }

        if ((animalInCell + 1) <= maxOnCell) {
            canAddNewAnimal = true;
        }

        return canAddNewAnimal;
    }
}
