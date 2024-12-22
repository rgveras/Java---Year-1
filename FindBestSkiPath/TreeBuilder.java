/**
 * This file builds a Binary Tree from an array that is taken in as a parameter
 * 
 * @author ricardoveras
 *
 * @param <T>
 */

public class TreeBuilder<T> {
	
	public LinkedBinaryTree<T> buildTree (T[] data) {
		LinkedQueue<T> dataQueue = new LinkedQueue<T>();

		//The info from the array is enqueued onto the newly created LinkedQueue<T> dataQueue
		for (int i=0; i<data.length; i++) {
			dataQueue.enqueue(data[i]);
		}
		
		//A new linked Queue containing BinaryTreeNode's is created
		LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>();

		//A root node is created containing the first element of dataQueue and that node is enqueue on parentQueue
		BinaryTreeNode<T> root = new BinaryTreeNode<T>(dataQueue.dequeue());
		parentQueue.enqueue(root);
		
		//While dataQueue is not empty, it takes in it's values and builds the appropriate links to be able to create a tree 
		while (!dataQueue.isEmpty()) {

			//The first and second elements are dequeued and stored
			T a = dataQueue.dequeue();
			T b = dataQueue.dequeue();
						
			//A BinaryTreeNode containing parentQueue's dequeued element is created
			BinaryTreeNode<T> parent = parentQueue.dequeue();
			
			//If a isn't null, a BinaryTreeNode storing a is created, set as the left node of parent and then the node is enqueued onto parentQueue
			if (a != null) {
				BinaryTreeNode<T> aNode = new BinaryTreeNode<T>(a);
				parent.setLeft(aNode);
				parentQueue.enqueue(aNode);
			}
			
			//If b isn't null, a BinaryTreeNode storing b is created, set as the right node of parent and then the node is enqueued onto parentQueue
			if (b != null) {
				BinaryTreeNode<T> bNode = new BinaryTreeNode<T>(b);
				parent.setRight(bNode);
				parentQueue.enqueue(bNode);
			}
		}
		//The root node containing the linked tree nodes is set as the root node of a new LinkedBinaryTree<T> and returned
		return new LinkedBinaryTree<T>(root);
	}
}
