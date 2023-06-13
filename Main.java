/**
 * @author Anton Tyapkin
 * @project Island
 */

import Core.PrintStatictic;
import Core.Settings;
import Core.SimulateLife;
import Island.Island;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        //инициализируем класс с настройками, что бы сработал блок инициализации
        Settings settings = new Settings();
        //Генерируем карту
        Island island = new Island();

        //Объект отвечающий за рост растений и статистику системы
        PrintStatictic prs = new PrintStatictic(island.getIsland());
        //Объект отвечающий за симуляцию жизни
        SimulateLife simLife = new SimulateLife(island.getIsland());
        //Шедулер потока статистики и роста
        ScheduledExecutorService sesStatictic = Executors.newScheduledThreadPool(1);
        //Шедулер потока симуляции
        ScheduledExecutorService sesSimulateLife = Executors.newScheduledThreadPool(10);
        //Переодически запускаем таски
        ScheduledFuture<?> sF = sesStatictic.scheduleAtFixedRate(prs, 0, 3, TimeUnit.SECONDS);
        ScheduledFuture<?> simulateLife = sesSimulateLife.scheduleAtFixedRate(simLife, 1, 1, TimeUnit.SECONDS);

        // вариант ограничения.
        int iter = 0;
        while (true) {
            try {
                Thread.sleep(3000);
                iter++;
                if (iter > 7) {
                    System.out.println("Game Over!");
                    sF.cancel(true);
                    simulateLife.cancel(true);
                    sesStatictic.shutdown();
                    sesSimulateLife.shutdown();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
