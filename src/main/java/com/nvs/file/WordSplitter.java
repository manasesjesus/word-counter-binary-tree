/**
 * 
 */
package com.nvs.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class contains two attributes to be used when parsing a file. 
 * One is a list of elements to be ignored, e.g. ( ) . ,
 * The other is a flag to enable/disable the case sensitive. Useful 
 * in languages, e.g. German, where nouns and verbs are differentiated  
 * by a capital letter.
 * 
 * @author Manasés Jesús
 * @github manasesjesus
 *
 */
public class WordSplitter {
	private List<String> ignored = new ArrayList<>();
	private boolean ignore_case  = true;

	/**
	 * @return the ignored elements
	 */
	public List<String> getIgnored() {
		return ignored;
	}

	/**
	 * Add one element to be ignored 
	 * 
	 * @param ignored The element to be ignored
	 */
	public void ignore (String ignored) {
		this.ignored.add(ignored);
	}
	
	/**
	 * Add a list of elements to be ignored
	 * 
	 * @param ignored The elements to be ignored
	 */
	public void ignore (List<String> ignored) {
		this.ignored.addAll(ignored);
	}
	
	/**
	 * Set the flag to ignore uppercase vs. lowercase 
	 * 
	 * @param ignore_case
	 */
	public void ignoreCase (boolean ignore_case) {
		this.ignore_case = ignore_case;
	}

	/**
	 * Receive the filename as a parameter and scan it by lines. If applicable, remove all unwanted elements.
	 * Then construct a map with the words counting and sort it by number of occurrences. 
	 * 
	 * @param filename The name of the file to be parsed
	 * @return a sorted map containing the words counting
	 * @throws FileNotFoundException If the specified file doesn't exist
	 */
	@SuppressWarnings("resource")
	public Map<String, Integer> getWordsCountFromFile(String filename) throws FileNotFoundException {
		String line;
		String words = " ";
		Map<String, Integer> words_count = new HashMap<>();
		Scanner scanner = new Scanner(new File(filename)).useDelimiter("\n");

		// Scan the file by lines, remove line breaks, remove unwanted elements and trim the lines
		while (scanner.hasNext()) {
			line = scanner.next().trim();
			
			if (ignore_case) {
				line = line.toLowerCase();
			}

			for (String ic : ignored) {
				line = line.replace(ic, " ");
			}

			words += line.trim() + " ";
		}

		// The file must contain at least one word in order to create the word counting sorted map
		if (words != " ") {
			Map<String, Integer> words_sorted = new LinkedHashMap<>();

			Arrays.asList(words.split(" ")).forEach(word -> words_count.merge(word, 1, Integer::sum));
			words_count.remove("");
			words_count.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue())
					.forEachOrdered(entry -> words_sorted.put(entry.getKey(), entry.getValue()));

			return words_sorted;
		}

		return null;
	}
}
