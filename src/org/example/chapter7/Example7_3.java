package org.example.chapter7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 * Spliterator
 */
public class Example7_3 {
	
	final String SENTENCE = 
			"Nel  mezzo del cammin  di nostra  via " +
			"mi   irtoval in unal  selva oscura" +
			" che la   gritta via era   smarrita";
	
	public static void main(String[] args) {
		Example7_3 ex = new Example7_3();
		
		Stream<Character> stream = IntStream.range(0, ex.SENTENCE.length())
				.mapToObj(ex.SENTENCE::charAt);
		
		Spliterator<Character> spliterator = new WordCounterSpliterator(ex.SENTENCE);
		Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
		System.out.println("SEQUENTIALLY FOUND         :: " +  ex.countWordsIteratively(ex.SENTENCE));
		System.out.println("SPLITERATOR FOUND          :: " + ex.countWord(stream));
		System.out.println("PARALLEL SPLITERATOR FOUND :: " + ex.countWord(stream1));
	}
	
	public int countWordsIteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		
		for (char ch : s.toCharArray()) {
			if (Character.isWhitespace(ch)) {
				lastSpace = true;
			} else {
				if (lastSpace) counter++;
				lastSpace = false;
			}
		}
		
		return counter;
	}
	
	private int countWord(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		
		return wordCounter.getCounter();
	}
	
}
