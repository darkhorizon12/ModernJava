package org.basic.chapter4;

import org.basic.CreateDish;
import org.basic.Dish;
import org.basic.chapter2.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Chapter4 {

    public static void main(String[] args) {
        List<Dish> dishes = CreateDish.createDishes();

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
}
