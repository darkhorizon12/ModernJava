package org.example.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character>{
	
	private final String string;
	private int currentChar = 0;
	
	public WordCounterSpliterator(String string) {
		this.string = string;
	}
	
	/* tryAdvance method behaves in a way similar to a normal Iterator in the sense that it's used to sequentially consume the elements
	 */
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(string.charAt(currentChar++)); // Consume the current character
		return currentChar < string.length();	// Return true if there are further characters to be consumed
	}

	/* trySplit method is invoked on the first Spliterator and getnerates a second one. 
	 * Then it's called again on these two Spliterators... until it returns null to siganl that data structure that it's processing 
	 * is no no longer divisible */
	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length() - currentChar;
		if (currentChar < 10) {
			return null;	// Return null to signal that the String to be parsed is small enough to be processed sequentially
		}
		
		// Set the candidate's split position to be half of the String to be parsed
		for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
			if (Character.isWhitespace(string.charAt(splitPos))) {	// Advanced the split position until the next space
				// Create a new WordCounterSpliterator parsing the String rom the start to the split position
				Spliterator<Character> spliterator = 
					new WordCounterSpliterator(string.substring(currentChar, splitPos)); 
				
				currentChar = splitPos;	// Set the start position of this WordCounterSpliterator to the split position
				return spliterator;
			}
		}
		
		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
	
}
