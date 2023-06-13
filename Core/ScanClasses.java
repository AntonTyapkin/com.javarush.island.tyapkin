package Core;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;


public class ScanClasses {

    //Класс для сканирования пакетов Herbivores и Predators, что бы получить списки классов для дальнейшей генерации
    //Используется google.guava 31-1. Смотри pom.xml
    private static Set<Class> predatorsClasses;
    private static Set<Class> herbivoresClasses;

    static {
        {
            try {
                predatorsClasses = findAllClassesUsingGoogleGuice(
                        "fauna.predators");
                herbivoresClasses = findAllClassesUsingGoogleGuice(
                        "fauna.herbivores");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Да, это копипаста с baeldung.com
    public static Set<Class> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(clazz -> clazz.load())
                .collect(Collectors.toSet());
    }

    public static Set<Class> getPredatorsClasses() {
        return predatorsClasses;
    }

    public static Set<Class> getHerbivoresClasses() {
        return herbivoresClasses;
    }
}
