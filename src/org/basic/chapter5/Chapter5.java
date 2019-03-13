package org.basic.chapter5;

import org.basic.CreateDish;
import org.basic.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Chapter5 {

    public static void main(String[] args) {
        List<Dish> dishes = CreateDish.createDishes();

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

//        Arrays.asList(1, 2, 3, 4, 5)
//                .stream()
//                .map(i -> i * i)
//                .filter(i -> i % 3 == 0)
//                .findFirst()
//                .ifPresent(System.out::println);

//        Optional<Integer> rst = Arrays.asList(4, 5, 3, 9)
//                .stream()
//                .reduce(Integer::min);
//        System.out.println(rst.get());

//        int rst = dishes.stream()
//                    .map(d -> 1)
//                    .reduce(0, (a, b) -> a + b);
//        System.out.println(rst);

//        dishes.stream()
//                .mapToInt(Dish::getCalories)
//                .sum();
//
//
//        OptionalInt maxCalory =
//                dishes.stream()
//                        .mapToInt(Dish::getCalories)
//                        .max();
//
//
//        Stream<double[]> pythoagoreanTriples =
//                IntStream.rangeClosed(1, 100).boxed()
//                        .flatMap(a -> IntStream.rangeClosed(a, 100)
//                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
//                                .filter(arr -> arr[2] % 1 == 0)
//                        );
//
//        pythoagoreanTriples.limit(5)
//                .forEach(arr -> System.out.println("{" + arr[0] + ", " + arr[1] + ", " + arr[2] + "}"));

//        Stream.of("Java 8 ", "in ", "Action")
//                .map(String::toUpperCase)
//                .forEach(System.out::println);

//        long uniqueWords = 0;
//        try (Stream<String> lines =
//                     Files.lines(Paths.get("/Users/juonkim/Documents/workspace-inflearn/ModernJava/src/org/basic/chapter5/test.txt"), Charset.defaultCharset())) {
//
//            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split("")))
//                                .distinct()
//                                .count();
//
//            System.out.println("uniqueWords :: " + uniqueWords);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Stream.iterate(new int[]{0, 1}, arrs -> new int[]{arrs[1], arrs[0] + arrs[1]})
                .limit(20)
                .forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ")"));
    }
}
