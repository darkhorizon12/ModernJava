package org.basic.chapter4;

import org.basic.Dish;
import org.basic.chapter2.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Chapter4 {

    public static void main(String[] args) {
        List<Dish> dishes = createDishes();

        List<String> filteredDishes =  dishes.stream()
                .filter(d -> {
                    System.out.println("filtering: " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping: " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(filteredDishes);
    }


    private static List<Dish> createDishes() {
        return Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }
}
