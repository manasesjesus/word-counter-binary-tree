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
	 * @param ignore_case
	 */
	public void ignoreCase (boolean ignore_case) {
		this.ignore_case = ignore_case;
	}

	/**
	 * @param filename the name of the file
	 * @return a sorted map containing the word counting
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public Map<String, Integer> getWordsCountFromFile(String filename) throws FileNotFoundException {
		String line;
		String words = " ";
		Map<String, Integer> words_count = new HashMap<>();
		Scanner scanner = new Scanner(new File(filename)).useDelimiter("\n");

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
