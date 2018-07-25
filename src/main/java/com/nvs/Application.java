/**
 * 
 */
package com.nvs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.nvs.file.WordSplitter;
import com.nvs.tree.MagicTree;

/**
 * The application receives a filename as a parameter. It is then parsed to get the words and 
 * number of occurrences stored in a map which is use to build the tree. Optionally, elements 
 * to be ignored can be passed as parameters, e.g. ( ) . , 
 * 
 * By default it is not case sensitive, but it is possible to call the WordSplitter ignoreCase method
 * and set it to false.
 * 
 * Execution: 
 * 		WordCounterBinaryTree  filename [elements to be ignored]
 *
 * Sample execution:
 * 		WordCounterBinaryTree  resources/test01.txt ( ) . , ; 
 * 
 * 
 * @author Manasés Jesús
 * @github manasesjesus
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main (String[] args) {
		
		// The file to be parsed must be passed as first parameter  
		if (args.length == 0) {
			System.err.println("Missing parameter: file");
			System.err.println("Run the application passing a text file as parameter");
			return;
		}
		
		WordSplitter ws = new WordSplitter();
		
		// Optionally, elements to be ignored can be passed as parameters, e.g. ( ) . , 
		if (args.length > 1) {
			List<String> unwanted = new ArrayList<String>(Arrays.asList(args)); 
			
			unwanted.remove(0);
			ws.ignore(unwanted);
		}

		// Get the words counting as a map, create the tree from the map and print the tree
		try {
			Map<String, Integer> words = ws.getWordsCountFromFile(args[0]); 
			MagicTree tree = new MagicTree(words);

			System.out.println(tree);
			
		} catch (FileNotFoundException e) {
			System.err.println("[ERR] File not found: " + args[0]);
		}
		
	}
}
