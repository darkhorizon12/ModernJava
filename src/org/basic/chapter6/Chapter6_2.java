package org.basic.chapter6;

import org.basic.CreateDish;
import org.basic.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Chapter6_2 {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimeWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    public static Map<Boolean, List<Integer>> partitionPrimeWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
