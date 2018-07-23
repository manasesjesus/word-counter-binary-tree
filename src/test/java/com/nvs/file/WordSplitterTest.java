/**
 * 
 */
package com.nvs.file;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Manasés Jesús
 * @github manasesjesus
 *
 */
public class WordSplitterTest {

	/**
	 * Tests methods for {@link com.nvs.file.WordSplitter#getWordsCountFromFile(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */

	@Test
	public final void testFileNotFound () {
		WordSplitter ws = new WordSplitter();
		String filename = "resources/filedoesnotexist.txt";
		
		try {
			ws.getWordsCountFromFile(filename);
		} catch (FileNotFoundException e) {
			assertTrue(true, "The file doesn't exist");
		}
	}
	
	@Test
	public final void testEmptyFile () throws FileNotFoundException {
		WordSplitter ws = new WordSplitter();
		String filename = "resources/test02.txt";
		
		assertEquals(null, ws.getWordsCountFromFile(filename), "The file is empty");
	}
	
	@Test
	public final void testFileContainsWords () throws FileNotFoundException {
		WordSplitter ws = new WordSplitter();
		String filename = "resources/test01.txt";
		
		assertNotEquals(null, ws.getWordsCountFromFile(filename), "The file must contain at least one word");
	}

}
