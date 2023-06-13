package Core;


import Island.Cell;

public class SimulateLife implements Runnable {

    private int iterSimulate;
    private final Cell[][] objects;

    public SimulateLife(Cell[][] objects) {
        this.objects = objects;
    }

    @Override
    public void run() {
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                //уменьшаем сытость
                tryDecreaseSatiety(objects[i][j]);
                //едим
                tryToEat(objects[i][j]);
                //перемещаемся
                tryToMove(objects[i][j], i, j);
                //плодимся
                tryToReproduction(objects[i][j]);
            }
        }
        this.iterSimulate++;
    }

    public void tryDecreaseSatiety(Cell cell) {
        cell.decreaseSatiety();
    }

    public void tryToReproduction(Cell cell) {
        cell.tryToReproduction();
    }

    public synchronized void tryToEat(Cell cell) {
        cell.tryToEat();
    }

    public void tryToMove(Cell cell, int positionY, int positionX) {
        cell.tryToMove(positionY, positionX);
    }

}
