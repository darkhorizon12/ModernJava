package org.basic.Chapter7;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Chapter7 {
    public static void main(String[] args) {
//        int processorCnt = Runtime.getRuntime().availableProcessors();
//        System.out.println("Processor count :: " + processorCnt);
//
//        System.out.println(measureSumPerformance(Chapter7::iterativeSum, 10_000_000));
//        System.out.println(measureSumPerformance(Chapter7::sequentialSum, 10_000_000));
//        System.out.println(measureSumPerformance(Chapter7::parallelSum, 10_000_000));
//        System.out.println(measureSumPerformance(Chapter7::sideEffectSum, 10_000_000));

        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura " +
                        " ch la dritta via era smarrite ";

//        System.out.println(countWordsIteratively(SENTENCE));

//        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
//                .mapToObj(SENTENCE::charAt);
//
//        System.out.println("Found " + countWords(stream.parallel()) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        System.out.println("Found " + countWords(stream) + " words");
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter =
                stream.reduce(new WordCounter(0, true),
                        WordCounter::accumulate,
                        WordCounter::combine);

        return wordCounter.getCounter();
    }

    public static long measureSumPerformance(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result :: " + sum);
            if (duration < fastest) fastest = duration;
        }

        return fastest;
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result = Long.sum(result, i);
        }

        return result;
    }

    public static long sequentialSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        (LongStream.rangeClosed(1, n)).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

}


class Accumulator {
    public long total = 0;

    public void add(long value) {
        total += value;
    }
}
