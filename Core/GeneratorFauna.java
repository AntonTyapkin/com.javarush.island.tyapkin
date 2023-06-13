package Core;



import Fauna.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class GeneratorFauna {

    public GeneratorFauna() {
    }

    public List<Animal> generateFauna(Set<Class> faunaClasses) {
        //Создаем потокобезопасный List
        List<Animal> faunaArray = new CopyOnWriteArrayList<>();
        //Обходим Set с классами, полученными при сканировании пакетов с животными
        for (Class c : faunaClasses) {
            try {
                //Получаем доступ к статическому полю и генериуем объекты
                Field maxOnCellField = c.getDeclaredField("maxOnCell");
                //Поле приватное - поэтому получаем к нему доступ
                maxOnCellField.setAccessible(true);
                int count = 1 + (int) (Math.random() * (((int) maxOnCellField.get(null) - 1)));
                //получаем конструктор класса и создаем объект
                Constructor<Animal> constr = c.getConstructor();
                for (int k = 0; k <= count; k++) {
                    Animal animal = constr.newInstance();
                    faunaArray.add(animal);
                }
            } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }
        }

        return faunaArray;
    }

    public void reproductionFauna(Set<Class> faunaClasses, List<Animal> fauna) {
        //Обходим Set с классами, ищем объект этого класса в List и считаем особей
        for (Class c : faunaClasses) {
            int reproductionChance = (int) (Math.random() * 2 + 1);
            if (reproductionChance == 1) {
                int male = 0;
                int female = 0;
                int childCount = 0;
                for (Animal predator : fauna) {
                    if (c.getSimpleName().equals(predator.getClass().getSimpleName())) {
                        //если животное сыто, то считаем его как репродуктивного
                        if (predator.reproduction()) {
                            if (predator.getGender() == 1) {
                                male++;
                            } else {
                                female++;
                            }
                        }
                    }
                }

                if (male >= female && female > 0) {
                    childCount = female;
                }
                if (female >= male && male > 0) {
                    childCount = male;
                }
                if (childCount != 0) {
                    try {
                        //Та же самая схема что и в generateFauna()
                        Field maxOnCellField = c.getDeclaredField("maxOnCell");
                        maxOnCellField.setAccessible(true);
                        int count = (int) maxOnCellField.get(null);
                        if ((male + female + childCount) < count) {
                            Constructor<Animal> constr = c.getConstructor();
                            for (int k = 0; k <= childCount; k++) {
                                Animal animal = constr.newInstance();
                                fauna.add(animal);
                            }
                        } else {
                            count = count - (male + female);
                            if (count > 0) {
                                Constructor<Animal> constr = c.getConstructor();
                                for (int k = 0; k < count; k++) {
                                    Animal animal = constr.newInstance();
                                    fauna.add(animal);
                                }
                            }
                        }
                    } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException |
                             InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
