/**
 * 
 */
package com.nvs.tree;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class contains two attributes: the words counting map to be used to build 
 * the tree and a node to identify the root of the tree. The map must be passed when 
 * instantiating a new magic object and the tree is built afterwards.
 * 
 * The toString method is overridden, so a magic tree object can be printed using the System.out. 
 * 
 * @author Manasés Jesús
 * @github manasesjesus
 *
 */
public class MagicTree {
	private Map<String, Integer> words_count = null;
	private Node root = null;
	
	/**
	 * Creates a new magic tree based on the received map 
	 * 
	 * @param words_count
	 */
	public MagicTree (Map<String, Integer> words_count) {
		this.words_count = words_count;
		buildTree();
	}
	
	/**
	 * Where the magic happens
	 */
	private void buildTree () {
		// There must be at least two words, otherwise it isn't a tree rather 
		// just the seed about to germinate ó.ó
		if (words_count != null && words_count.size() >= 2) {
			// Find the pivot  
			int size   = words_count.size();
			int middle = size % 2 == 0 ? size / 2 : size / 2 + 1;
			
			ArrayList<String> words = new ArrayList<String>(words_count.keySet());
			
			Node childL  = null;
			Node childR  = null;
			Node parentL = null;
			Node parentR = null;
			
			// Build the left side of the tree
			parentL = new Node(words_count.get(words.get(0)), words.get(0));
			for (int i=1; i<middle; i++) {
				childR = parentL;
				childL = new Node(words_count.get(words.get(i)), words.get(i));
				parentL = new Node(sumNodesWeight(childL, childR));
				parentL.setLeft(childL);
				parentL.setRight(childR);
			}
			
			// Build the right side of the tree
			parentR = new Node(words_count.get(words.get(size - 1)), words.get(size - 1));
			for (int i=size-2; i>=middle; i--) {
				childR = parentR;
				childL = new Node(words_count.get(words.get(i)), words.get(i));
				parentR = new Node(sumNodesWeight(childL, childR));
				parentR.setLeft(childL);
				parentR.setRight(childR);
			}
		
			// I am Root
			root = new Node(sumNodesWeight(parentL, parentR)); 
			root.setLeft(parentL);
			root.setRight(parentR);
		} 
		else if (words_count != null && !words_count.isEmpty())  {
			// but there might be a case of a file containing the same word several times
			// and the little magic seed wants to be printed
			words_count.forEach((word, count) -> 
				System.out.println("├── " +  words_count.get(word) + "  [ " + word + " ]"));
		}
		else {
			System.err.println("No words were found in the file");
		}
	}

	/**
	 * Returns the sum of two nodes' weights 
	 * 
	 * @param n1 The first node
	 * @param n2 The second node
	 * @return The sum of the nodes' weights
	 */
	private int sumNodesWeight (Node n1, Node n2) {
		return n1.getWeight() + n2.getWeight();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		if (root != null) {
			root.print();
		}
		
		return "";
	}
}
