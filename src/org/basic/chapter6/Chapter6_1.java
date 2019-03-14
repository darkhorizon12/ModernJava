package org.basic.chapter6;

import org.basic.CreateDish;
import org.basic.Dish;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Chapter6_1 {

    public static void main(String[] args) {
        List<Dish> menus = CreateDish.createDishes();

//        Comparator<Dish> caloryComparator = Comparator.comparingInt(Dish::getCalories);
//        Optional<Dish> maXCalory = menus.stream()
//                .collect(minBy(caloryComparator));
//        maXCalory.ifPresent(System.out::println);

//        Optional<Integer> totalCal1 = menus.stream().map(Dish::getCalories).reduce(Integer::sum);
//        int totalCal2 = menus.stream().collect(summingInt(Dish::getCalories));

//        IntSummaryStatistics menuStatics = menus.stream().collect(summarizingInt(Dish::getCalories));

//        String shortMenu = menus.stream().map(Dish::getName).collect(joining(", "));

//        Map<Dish.Type, List<Dish>> mapList = menus.stream()
//                .collect(groupingBy(Dish::getType));

//        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> mapList = menus.stream()
//                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
//                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
//                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
//                    else return Dish.CaloricLevel.FAT;
//                })));

//        Map<Dish.Type, Dish> mapList = menus.stream()
//                .collect(groupingBy(Dish::getType,
//                        collectingAndThen(
//                                maxBy(comparingInt(Dish::getCalories)),
//                                Optional::get)));

//        Map<Dish.Type, Set<Dish.CaloricLevel>> mapList = menus.stream()
//                .collect(groupingBy(Dish::getType,
//                        mapping(dish -> {
//                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
//                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
//                            else return Dish.CaloricLevel.FAT;
//                        }, toCollection(HashSet::new))));
//        mapList.forEach((key, value) -> System.out.println(key + " :: " + value));

//        Map<Boolean, List<Dish>> mapList =
//                menus.stream()
//                .collect(partitioningBy(Dish::isVegetarian));
//
//        menus.stream()
//                .filter(Predicate.not(Dish::isVegetarian))
//                .collect(toList());

//        Map<Boolean, Dish> mapList = menus.stream()
////                .collect(
////                        partitioningBy(Dish::isVegetarian,
////                                collectingAndThen(
////                                        maxBy(comparingInt(Dish::getCalories)),
////                                        Optional::get
////                                )
////                        ));
////        mapList.forEach((key, value) -> System.out.println(key + " :: " + value));

    }
}
