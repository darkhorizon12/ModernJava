package org.example.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;

public class Example7_2 {
	public static void main(String[] args) {
		System.out.println("parallelRangedSum result : " +
				measureSumPerf(Example7_2::parallelRangedSum, 10_000_000) + "msecs"
		);
		System.out.println("ForkJoinSumCalculator result : " +
				measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + "msecs"
		);
	}
	
	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long faster = Long.MAX_VALUE;
		
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			
			System.out.println("Result : " + sum);
			
			if (duration < faster) {
				faster = duration;
			}
		}
		
		return faster;
	}
	
	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}
}
