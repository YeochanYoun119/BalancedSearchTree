////////////////////////////////////////////////////////////////////////////////
// Main File:        BALST.java
// This File:        BALST.java
// Other Files:      BALST.java, BSTNode.java, BALSTTest.java
// Semester:         CS 400 Fall 2019
//
// Author:           Yeochan Youn
// Email:            yyoun5@wisc.edu
// CS Login:         yeochan
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black. Note
 * which tree you implement here and as a comment when you submit.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

	/*
	 * private class Node{ private K key; private V value; private Node left;
	 * private Node right;
	 * 
	 * private Node(K key, V value){ this.key = key; this.value = value; this.left =
	 * null; this.right = null; } }
	 */

	private BSTNode<K, V> root;

	private int numKeys;

	public BALST() {
	}

	/**
	 * Returns the key that is in the root node of this BST. If root is null,
	 * returns null.
	 * 
	 * @return key found at root node, or null
	 */
	@Override
	public K getKeyAtRoot() {
		// TODO Auto-generated method stub
		if (root == null) { // if root is null, return null
			return null;
		}
		return root.key; // else, return root.key
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the left child. If
	 * the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (key == null) { // if key is null, throw IllegalNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if tree does not have key, throw KeyNotFoundException
			throw new KeyNotFoundException();
		}
		return getKeyOfLeftChildOfHelper(root, key); // else, return getKeyOfLeftChildOfHelper(root, key);
	}

	private K getKeyOfLeftChildOfHelper(BSTNode<K, V> node, K key) throws IllegalNullKeyException {
		K temp = null; // temp to store key
		if (node == null) { // if node is null, return null
			return null;
		}
		if (node.key == key) { // if current node's key match with finding key,
			if(node.left == null) { // if node does not have left child, return null
				return null;
			}
			return node.left.key; // else, return node's left child's key
		}
		if (key.compareTo(node.key) < 0) { // if key is smaller than current node's key 
			temp = getKeyOfLeftChildOfHelper(node.left, key); // recursive with node's left child
		}
		if (temp != null) { // if temp is not null, return temp
			return temp;
		}
		if (key.compareTo(node.key) > 0) { // if key is larger than current node's key
			temp = getKeyOfLeftChildOfHelper(node.right, key); // recursive with current node's right child
		}
		return temp; // return temp value
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the right child. If
	 * the right child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (key == null) { // if key is null, throw IllegalNullKeyException
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) { // if tree does not have key, throw KeyNotFoundException
			throw new KeyNotFoundException();
		}
		return getKeyOfRightChildOfHelper(root, key); // else, return getKeyOfRightChildOfHelper(root, key);
	}

	private K getKeyOfRightChildOfHelper(BSTNode<K, V> node, K key) throws IllegalNullKeyException {
		K temp = null; // temp to store key
		if (node == null) { // if node is null, return null
			return null;
		}
		if (node.key == key) { // if current node's key match with finding key,
			if(node.right == null) { // if node does not have right child, return null
				return null;
			}
			return node.right.key; // else, return node's right child's key
		}

		if (key.compareTo(node.key) < 0) { // if key is smaller than current node's key
			temp =  getKeyOfRightChildOfHelper(node.left, key); // recursive with node's left child
		}
		if(temp != null) { // if temp is not null, return temp
			return temp;
		}
		if (key.compareTo(node.key) > 0) { // if key is larger than current node's key
			temp =  getKeyOfRightChildOfHelper(node.right, key); // recursive with current node's right child
		}
		return temp; // return temp value
	}

	/**
	 * Returns the height of this BST. H is defined as the number of levels in the
	 * tree.
	 * 
	 * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max(
	 * height(root.left), height(root.right) )
	 * 
	 * Examples: A BST with no keys, has a height of zero (0). A BST with one key,
	 * has a height of one (1). A BST with two keys, has a height of two (2). A BST
	 * with three keys, can be balanced with a height of two(2) or it may be linear
	 * with a height of three (3) ... and so on for tree with other heights
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return getHeightHelper(root); // return root's height
	}

	private int getHeightHelper(BSTNode<K, V> node) { 
		if (node == null) { // if root is null, return 0
			return 0;
		}
		if (node.left == null & node.right == null) { // if root is leaf, return 1 
			return 1;
		}
		return 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right)); // else compare left and right height and return + 1 of it
	}

	/**
	 * Returns the keys of the data structure in sorted order. In the case of binary
	 * search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
	@Override
	public List<K> getInOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> list = new ArrayList<K>(); // list to store keys
		return getInOrderTraversalHelper(root, list);
	}

	private List<K> getInOrderTraversalHelper(BSTNode<K, V> node, List<K> list) {
		if (node == null) { // if node is null, return list
			return list;
		}
		if (node.left != null) { // if node has left child
			getInOrderTraversalHelper(node.left, list); // recursive with node's left child 
		}
		list.add(node.key); // add node's key to list
		if (node.right != null) {
			getInOrderTraversalHelper(node.right, list); // if node has right child, recursive with node's right child
		}
		return list; // return list
	}

	/**
	 * Returns the keys of the data structure in pre-order traversal order. In the
	 * case of binary search trees, the order is: V L R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in pre-order
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> list = new ArrayList<K>(); // list to store keys
		return getPreOrderTraversalHelper(root, list); 
	}

	private List<K> getPreOrderTraversalHelper(BSTNode<K, V> node, List<K> list) {
		if (node == null) { // if node is null, return list
			return list;
		}
		list.add(node.key); // add node's key
		if (node.left != null) { // if node has left child, recursive with node's left child
			getPreOrderTraversalHelper(node.left, list);
		}
		if (node.right != null) { // if node has right child, recursive with ndoe's right child
			getPreOrderTraversalHelper(node.right, list);
		}
		return list; // return list
	}

	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> list = new ArrayList<K>(); // list to store keys
		return getPostOrderTraversalHelper(root, list);
	}

	private List<K> getPostOrderTraversalHelper(BSTNode<K, V> node, List<K> list) {
		if (node == null) { // if node is null, return list
			return list;
		}
		if (node.left != null) { // if node has left child, recursive with node's left child
			getPostOrderTraversalHelper(node.left, list);
		}
		if (node.right != null) { // if node has right child, recursive with node's right child
			getPostOrderTraversalHelper(node.right, list);
		}
		list.add(node.key); // add node's key to list 
		return list; // return list
	}

	/**
	 * Returns the keys of the data structure in level-order traversal order.
	 * 
	 * The root is first in the list, then the keys found in the next level down,
	 * and so on.
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in level-order
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> list = new ArrayList<K>(); // list to store keys 
		Queue<BSTNode<K, V>> queue = new LinkedList<BSTNode<K, V>>(); // que to store keys

		if (root == null) { // if root is null, return empty list
			return list;
		}

		queue.add(root); // add root key to que
		while (!queue.isEmpty()) { // loop until que is empty
			BSTNode<K, V> temp = queue.poll(); // add first key in que to temp and remove from que
			list.add(temp.key); // add temp to list
 
			if (temp.left != null) { // if temp has left child, add temp's left child key to que
				queue.add(temp.left);
			}

			if (temp.right != null) { // if temp has right child, add temp's right child key to que
				queue.add(temp.right);
			}
		}

		return list; // return list
	}

	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException(); Do not increase the num of keys in
	 * the structure, if key,value pair is not added.
	 */
	@Override
	
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if(key == null) {throw new IllegalNullKeyException();} // if key is null, throw IllegalNullKeyException
		if(contains(key)) {throw new DuplicateKeyException();} // if tree contains key, throw DuplicateKeyException
		root = insertHelper(root, key, value); // change root to returned node from insert helper method
		numKeys++; // increase numKeys
	}
	
	private BSTNode<K,V> insertHelper(BSTNode<K,V> node, K key, V value){
		if(node == null) {node = new BSTNode<K,V>(key,value);} // if node is null, set new node to that position
		if(key.compareTo(node.key) < 0) { // if key is smaller than current node's key, set node's left node to returned value from insertHelper(node.left, key, value)
			node.left = insertHelper(node.left, key, value);
		}
		if(key.compareTo(node.key) > 0) { // if key is larger than current node's key, set node's right node to returned value from insertHelper(node.right, key, value)
			node.right = insertHelper(node.right, key, value);
		}
		int balance = getBalance(node); // get balance to check balance error

		if (balance > 1 && key.compareTo(node.left.key) < 0) { // left left return
			return rightRotate(node); // right rotate
		}

		if (balance < -1 && key.compareTo(node.right.key) > 0) { // right right
			return leftRotate(node); // left rotate
		}

		if (balance > 1 && key.compareTo(node.left.key) > 0) { // left right
			node.left = leftRotate(node.left); // left rotate
			return rightRotate(node); // right rotate
		}

		if (balance < -1 && key.compareTo(node.right.key) < 0) { // right left
			node.right = rightRotate(node.right); // right rotate
			return leftRotate(node); // left rotate
		}
		 
		return node; // return node
	}

	private BSTNode<K, V> leftRotate(BSTNode<K, V> node) {

		BSTNode<K, V> newRoot = node.right; // new root 
		BSTNode<K, V> temp = newRoot.left; // temp node

		newRoot.left = node; // set new root's right node to current node
		node.right = temp; // set current node's right node to temp node

		node.height = 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right)); // new height for current node
		newRoot.height = 1 + Math.max(getHeightHelper(newRoot.left), getHeightHelper(newRoot.right)); // height for new root 

		return newRoot; // return new root
	}

	private BSTNode<K, V> rightRotate(BSTNode<K, V> node) {

		BSTNode<K, V> newRoot = node.left; // new root
		BSTNode<K, V> temp = newRoot.right; // temp node

		newRoot.right = node; // set new root's right to current node
		node.left = temp; // set current node's left node to temp node

		node.height = 1 + Math.max(getHeightHelper(node.left), getHeightHelper(node.right)); // new height for current node
		newRoot.height = 1 + Math.max(getHeightHelper(newRoot.left), getHeightHelper(newRoot.right)); // height for new root

		return newRoot; // return new root
	}
/**
 * getting balance of current node
 * @param node current node
 * @return balance factor of current node
 */
	private int getBalance(BSTNode<K, V> node) {
		if (node == null) { // if node is null, return 0
			return 0;
		}
		return getHeightHelper(node.left) - getHeightHelper(node.right); // return left sub tree height - right sub tree height
	}

	/**
	 * If key is found, remove the key,value pair from the data structure and
	 * decrease num keys. If key is not found, do not decrease the number of keys in
	 * the data structure. If key is null, throw IllegalNullKeyException If key is
	 * not found, throw KeyNotFoundException().
	 */

	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if(key == null) {throw new IllegalNullKeyException();}
		if(!contains(key) || root == null) {throw new KeyNotFoundException();}
		root = removeHelper(root, key); // new root after remove node
		numKeys--; // decrease numKeys
		return true;
	}
	
	/**
	 * remove helper to recursively call the method
	 * @param node current node 
	 * @param key key to remove
	 * @return root after removed
	 */
	
	private BSTNode<K,V> removeHelper(BSTNode<K,V> node, K key) {
		if(key.compareTo(node.key) == 0) {
			int childNum = 0;
			if(node.left != null & node.right != null) {childNum = 2;} // node with 2 children
			else if(node.left != null || node.right != null) {childNum = 1;} // node with single child
			
			if(childNum == 0) {return null;} // if no child, return null
			if(childNum == 1) { // if single child, return that child 
				if(node.left != null) {return node.left;}
				if(node.right != null) {return node.right;}
			}
			if(childNum ==2) { // if two children
				BSTNode<K,V> newKey = getSuccesor(node.left); // change removed place with successor node
				node.key = newKey.key; 
				node.value = newKey.value;
				node.left = removeHelper(node.left, newKey.key);
			}
		}
		else if(key.compareTo(node.key) < 0) { // if key is smaller than current node, recursive with left child
			node.left = removeHelper(node.left, key);
		}else { // if key is larger than current node, recursive with right child
			node.right = removeHelper(node.right, key); 
		}
		return balancing(node); // re balancing after remove
	}
	
/**
 * getting successor of removing node 
 * @param node current node 
 * @return successor
 */
	private BSTNode<K, V> getSuccesor(BSTNode<K, V> node) {
		BSTNode<K, V> current = node;
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}
	
	/**
	 * re balancing tree after remove
	 * @param node current node
	 * @return root after balanced
	 */
	private BSTNode<K,V> balancing(BSTNode<K,V> node){
		int balFac = getBalance(node); // get balance
		if(balFac > 1) { // if left heavy 
			if(getBalance(node.left) < 0) { // left right
				node.left = leftRotate(node.left); // left rotate
			}
			return rightRotate(node); // right rotate
		}
		if(balFac < -1) { // right heavy 
			if(getBalance(node.right) > 0) { // right left
				node.right = rightRotate(node.right); // rotate right
			}
			return leftRotate(node); // left rotate
		}
		return node; // return node
	}
	

	/**
	 * Returns the value associated with the specified key
	 *
	 * Does not remove key or decrease number of keys If key is null, throw
	 * IllegalNullKeyException If key is not found, throw KeyNotFoundException().
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}
		return getHelper(root, key);
	}
/**
 * helper method of get
 * @param node current node
 * @param key key to find
 * @return value of found node
 */
	private V getHelper(BSTNode<K, V> node, K key) {
		if (key.compareTo(node.key) == 0) { // if node if found, return value of it
			return node.value;
		}
		if (key.compareTo(node.key) < 0) { // if key is smaller, go to left child
			return getHelper(node.left, key);
		}
		if (key.compareTo(node.key) > 0) { // if key is larger, go to right child
			return getHelper(node.right, key);
		}
		return null; 
	}

	/**
	 * Returns true if the key is in the data structure If key is null, throw
	 * IllegalNullKeyException Returns false if key is not null and is not present
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		return containsHelper(root, key);
	}

	/**
	 * contains helper method
	 * @param node current node
	 * @param key key to find
	 * @return true if found, false otherwise
	 */
	private boolean containsHelper(BSTNode<K, V> node, K key) {
		if (node == null) { // if node is null, return false
			return false;
		}
		if (key.compareTo(node.key) == 0) { // if key found, return true
			return true;
		}
		if (key.compareTo(node.key) < 0) { // key is smaller, go to left child
			return containsHelper(node.left, key);
		}
		if (key.compareTo(node.key) > 0) { // key is larget, go to right child
			return containsHelper(node.right, key);
		}
		return false;
	}

	/**
	 * Returns the number of key,value pairs in the data structure
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return numKeys; // return numKeys
	}

	/**
	 * Print the tree.
	 *
	 * For our testing purposes: all keys that we insert in the tree will have a
	 * string length of exactly 2 characters. example: numbers 10-99, or strings aa
	 * - zz, or AA to ZZ
	 *
	 * This makes it easier for you to not worry about spacing issues.
	 *
	 * You can display in any of a variety of ways, but we should see a tree that we
	 * can identify left and right children of each node
	 *
	 * For example:
	 * 
	 * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
	 * 
	 * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
	 * 
	 * Or, you can display a tree of this kind.
	 * 
	 * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
	 * 
	 * Or, you can come up with your own orientation pattern, like this.
	 * 
	 * 10 20 30 35 40 50
	 * 
	 * The connecting lines are not required if we can interpret your tree.
	 * 
	 */
	@Override
	public void print() {
	    printHelper(root, 4); 
	  }

/**
 * print tree
 * @param root root of tree
 * @param space space between node
 */
	  private void printHelper(BSTNode<K, V> root, int space) {

		    if (root == null) { // print nothing if root is null
		      return;
		    }
		    int num = 15; // space between each node
		    space += num; // starting space
		    printHelper(root.right, space); // print right sub tree of root

		    for (int i = 0; i < space; i++) {
		      System.out.print(" ");
		    }
		    System.out.print(root.key + "------------\n"); // print root

		    printHelper(root.left, space); // print left sub tree of root

		  }

}
