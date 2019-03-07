package org.basic.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter2_1 {

    public static void main(String[] args) {
        List<Apple> list = createAppleList();
        List<Apple> rstList = filterApple(list, new AppleWeightOverThan2());
        rstList.forEach(System.out::println);

    }

    private static List<Apple> filterApple(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) list.add(apple);
        }

        return list;
    }

    private static List<Apple> createAppleList() {
        return Arrays.asList(
                new Apple("red", 1.2, "001"),
                new Apple("white", 3.2, "001"),
                new Apple("red", 1.8, "011"),
                new Apple("yellow", 1.4, "031"),
                new Apple("white", 2.2, "002"),
                new Apple("green", 2.7, "011"),
                new Apple("black", 1.23, "001"),
                new Apple("red", 2.89, "001"),
                new Apple("red", 2.01, "007")
        );

    }
}
