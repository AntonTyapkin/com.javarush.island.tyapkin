package Core;



import Island.Cell;

public class PrintStatictic implements Runnable {

    private int iterPrintStatic;
    private final Cell[][] objects;

    public PrintStatictic(Cell[][] objects) {
        this.objects = objects;
    }

    @Override
    public void run() {
        System.out.println("День - " + this.iterPrintStatic + ". Печатаю статистку");
        addHerbs(objects);
        printStat(objects);
        this.iterPrintStatic++;
    }

    public static void printStat(Cell[][] objects) {
        try {
            for (int i = 0; i < objects.length; i++) {
                for (int j = 0; j < objects[i].length; j++) {
                    System.out.print("Ячейка - " + i + "x" + j);
                    System.out.println(" Растений - " + objects[i][j].countHerbs()
                            + " Хищников - " + objects[i][j].countPredators() + " Травоядных - " + objects[i][j].countHerbivores() + "\n"
                            + objects[i][j].countEachPredator() + "\n"
                            + objects[i][j].countEachHerbivore());
                }
            }
            System.out.println("------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addHerbs(Cell[][] objects) {
        try {
            for (int i = 0; i < objects.length; i++) {
                for (int j = 0; j < objects[i].length; j++) {
                    objects[i][j].addHerb();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
