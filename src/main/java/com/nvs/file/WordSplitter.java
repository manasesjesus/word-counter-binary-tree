/**
 * 
 */
package com.nvs.file;

import java.io.File;
import java.io.FileNotFoundException;
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
	private List<String> ignored_chars = Arrays.asList("(", ")", ",", ".", ";");

	/**
	 * @return the ignored_chars
	 */
	public List<String> getIgnored_chars() {
		return ignored_chars;
	}

	/**
	 * Add one character to the ignored_chars
	 * 
	 * @param char the character to be ignored
	 */
	public void setIgnored_chars(String ignored_char) {
		this.ignored_chars.add(ignored_char);
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

			for (String ic : ignored_chars) {
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
