/**
 * 
 */
package com.nvs.tree;

/**
 * The tree is built using nodes. Each node can be a parent having two children represented by left/right nodes.
 * Each node has a weight which is the number of occurrences of a word or the sum of two leaves' weights.
 * A leaf node contains the word being counted.
 * 
 * Simple implementation not using inheritance.
 * 
 * @author Manasés Jesús
 * @github manasesjesus
 *
 */
public class Node {
	private String word;
	private int weight;
	private Node left;
	private Node right;

	/**
	 * Create a tree node 
	 * 
	 * @param weight The sum of two leaves' weights
	 */
	public Node (int weight) {
		this.weight = weight;
		word  = null;
		right = null;
		left  = null;
	}
	
	/**
	 * Create a leaf node 
	 * 
	 * @param weight The number of occurrences
	 * @param word   The word being counted
	 */
	public Node (int weight, String word) {
		this(weight);
		this.word = word;
	}

	/**
	 * General public method to print the tree. It calls the private method taking the current node as the root.
	 */
	public void print () {
        print("", this, false, true);
    }

    /**
     * A recursive method to print the tree to the system's default output
     * 
     * @param branch The branches to outline the tree structure
     * @param n		 The current node
     * @param isLeft A flag to identify if the current node is a left child
     * @param isRoot A flag to identify if the current node is the root
     */
    private void print (String branch, Node n, boolean isLeft, boolean isRoot) {
        if (n != null) {
        	String leaf = n.word == null ? "" : "  [ " + n.word + " ]";
        	String bstr = branch + (isLeft ? "│  " : "   "); 
        	
            System.out.println(branch + (isLeft ? "├──" : isRoot ? "   " : "└──") + n.getWeight() + leaf);
            print(bstr, n.getLeft(), true, false);
            print(bstr, n.getRight(), false, false);
        }
    }
	
    /* Getters & Setters */
    
	/**
	 * @return the left
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	
}
