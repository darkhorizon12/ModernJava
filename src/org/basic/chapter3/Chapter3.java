package org.basic.chapter3;

import org.basic.chapter2.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Chapter3 {

    public static void main(String[] args) {
        List<Apple> appleList = createAppleList();

        Function<Integer, Integer> f = i -> i + 1;
        Function<Integer, Integer> o = i -> i * i;

        Function<Integer, Integer> c = f.compose(o);
        Arrays.asList(1, 2, 3, 4, 5).forEach(i -> System.out.println(c.apply(i)));

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
                new Apple("red", 2.01, "007"),
                new Apple("red", 3.01, "007"),
                new Apple("green", 2.01, "007"),
                new Apple("red", 2.01, "001")
        );

    }
}


