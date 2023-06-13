package Fauna;

import Core.MovementDirection;
import Fauna.Predators.Wolf;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private double weight;
    private int speed;
    private double maxSatiety;
    private double satiety;
    private int maxStarvingTime;
    private int starvingTime;
    private int gender;

    public Animal(double weight, int speed, double satiety, int starvingTime) {
        this.weight = weight;
        this.speed = speed;
        this.maxSatiety = satiety;
        this.satiety = satiety;
        this.maxStarvingTime = starvingTime;
        this.starvingTime = starvingTime;
        setGender();
    }

    //костыль, что бы можно было выбрать рацион
    public abstract void eat(List<?> listOfFood, List<?> listOfFood1);

    public MovementDirection setDirection() {
        MovementDirection movementDirection;

        int moveDirection = ThreadLocalRandom.current().nextInt(0, 4);

        if (moveDirection == MovementDirection.UP.ordinal()) {
            movementDirection = MovementDirection.UP;
        } else if (moveDirection == MovementDirection.RIGHT.ordinal()) {
            movementDirection = MovementDirection.RIGHT;
        } else if (moveDirection == MovementDirection.DOWN.ordinal()) {
            movementDirection = MovementDirection.DOWN;
        } else if (moveDirection == MovementDirection.LEFT.ordinal()) {
            movementDirection = MovementDirection.LEFT;
        } else {
            movementDirection = null;
        }

        return movementDirection;
    }

    private void setGender() {
        this.gender = (int) (Math.random() * 2 + 1);
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public void setStarvingTime(int starvingTime) {
        this.starvingTime = starvingTime;
    }

    public int getGender() {
        return this.gender;
    }

    public double getSatiety() {
        return satiety;
    }

    public int getStarvingTime() {
        return starvingTime;
    }

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public int getMaxStarvingTime() {
        return maxStarvingTime;
    }

    public int getSpeed() {
        return speed;
    }

    public void decreaseSatiety() {
        if (this.satiety <= 0) {
            this.starvingTime--;
        } else {
            int rand = 1 + (int) (Math.random() * ((this.maxSatiety - 1)));
            this.satiety = this.satiety - rand;
        }
    }

    public boolean reproduction() {
        boolean canReproduction = false;
        if (this.satiety == this.maxSatiety) {
            canReproduction = true;
        }
        return canReproduction;
    }

}