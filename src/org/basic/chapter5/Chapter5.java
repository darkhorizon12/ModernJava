package org.basic.chapter5;

import org.basic.Dish;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Chapter5 {

    public static void main(String[] args) {
        List<Dish> dishes = createDishes();

//        System.out.println(dishes.stream()
//            .filter(d -> d.getType().equals(Dish.Type.OTHER))
//            .limit(2)
//            .collect(toList()));

//        Arrays.asList("oriana", "juon", "ramona", "zara", "suerte", "bada")
//                .stream()
//                .map(String::length)
//                .forEach(System.out::println);

//        Arrays.stream(new String[]{"Hello", "World"})
////                .map(n -> n.split(""))
////                .flatMap(Arrays::stream)
////                .distinct()
////                .forEach(System.out::println);

//        List<Integer> aList = Arrays.asList(1, 2, 3);
//        List<Integer> bList = Arrays.asList(4, 5);
//        List<int[]> rList = aList.stream()
//                .flatMap(a -> bList.stream()
//                                .filter(b -> (a + b) % 3 == 0)
//                                .map(b -> new int[]{a, b}))
//                .collect(toList());
//        final StringBuffer str = new StringBuffer();
//        rList.forEach(arr -> {
//            for (int i = 0; i < arr.length; i++) {
//                if (i == 0) str.append("{" + arr[i]);
//                else str.append(", ").append(arr[i]).append("}");
//            }
//            str.append("\n");
//        });
//        System.out.println(str.toString());

//        dishes.stream()
////                .filter(dish -> dish.getCalories() > 800)
////                .findAny()
////                .ifPresent(System.out::println);

        Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(i -> i * i)
                .filter(i -> i % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);

//        Optional<Integer> rst = Arrays.asList(4, 5, 3, 9)
//                .stream()
//                .reduce(Integer::min);
//        System.out.println(rst.get());

        int rst = dishes.stream()
                    .map(d -> 1)
                    .reduce(0, (a, b) -> a + b);
        System.out.println(rst);

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
