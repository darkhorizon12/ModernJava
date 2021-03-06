package org.basic.chapter2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class Chapter2 {

    public static void main(String[] args) {
        List<Apple> list = createAppleList();
        List<Apple> rstList = filterApple(list, apple -> ColorEnum.WHITE == apple.getColor());

//        list.sort((Apple o1, Apple o2) -> Double.valueOf(o1.getWeight()).compareTo(Double.valueOf(o2.getWeight())));
//        list.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getOrigin));

        Predicate<Apple> redAndHeavyOrGreen =
                redApple(list).and(a -> a.getWeight() > 3)
                                .or(a -> ColorEnum.GREEN == a.getColor());


        System.out.println(filterApple(list, redAndHeavyOrGreen));

    }

    private static Predicate<Apple> redApple(List<Apple> inventory) {
        return a -> a.getColor() == ColorEnum.RED;
    }

    private static <T> List<T> filterApple(List<T> inventory, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        for (T t : inventory) {
            if (predicate.test(t)) list.add(t);
        }

        return list;
    }

    private static List<Apple> createAppleList() {
        return Arrays.asList(
                new Apple(ColorEnum.RED, 1.2, "001"),
                new Apple(ColorEnum.WHITE, 3.2, "001"),
                new Apple(ColorEnum.RED, 1.8, "011"),
                new Apple(ColorEnum.WHITE, 1.4, "031"),
                new Apple(ColorEnum.WHITE, 2.2, "002"),
                new Apple(ColorEnum.GREEN, 2.7, "011"),
                new Apple(ColorEnum.BLACK, 1.23, "001"),
                new Apple(ColorEnum.YELLOW, 2.89, "001"),
                new Apple(ColorEnum.RED, 2.01, "007"),
                new Apple(ColorEnum.RED, 3.01, "007"),
                new Apple(ColorEnum.GREEN, 2.01, "007"),
                new Apple(ColorEnum.RED, 2.01, "001")
        );

    }
}
