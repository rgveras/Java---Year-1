/**
 * This file Ski.java takes in an array and converts it's contained data into a binary tree representing the possible paths of a skier, using file TreeBuilder.java. 
 * It then checks for the best path a skier should take, given the information from the tree, and adds the best paths into a sequence.
 * 
 * @author Ricardo Veras
 * 
 */
public class Ski<T> {
	private BinaryTreeNode<SkiSegment> root;
	
	//Converts a string array into a SkiSegment array, creating objects in the array of type SkiSegment and its subclasses JumpSegment and SlalomSegment
	public Ski(String[] data) {
		SkiSegment[] segments =  new SkiSegment[data.length];
		
		for (int i=0; i<data.length; i++) {
			if (data[i] == null) segments[i] = null;
			else {
				
			//If the data of the string array contains "jump" a JumpSegment object containing the info from data at the current index is added to segments
			if (data[i].contains("jump")) 
				segments[i] = new JumpSegment(String.valueOf(i), data[i]);
			
			//If the data contains "slalom" a SlalomSegment object containing the corresponding info from data is added into segments
			else if (data[i].contains("slalom")) 
				segments[i] = new SlalomSegment(String.valueOf(i), data[i]);
			
			//If the data does not contain "slalom" or "jump" a SkiSegment object containing the corresponding info from data is added into segments
			else segments[i] = new SkiSegment(String.valueOf(i), data[i]);
		}
			}
		
		//A Tree is made using segments and it's root store in variable "root"
		TreeBuilder<SkiSegment> tree = new TreeBuilder<SkiSegment>();
		root = tree.buildTree(segments).getRoot();
	}
	
	//Returns root
	public BinaryTreeNode<SkiSegment> getRoot() {
		return root;
	}
	
	//Checks the best path for a skier to follow
	private BinaryTreeNode<SkiSegment> bestPath(BinaryTreeNode<SkiSegment> node) {
		//If one node is null, the other is returned
		if (node.getLeft() == null) return node.getRight();
		if (node.getRight() == null) return node.getLeft();

		
		SkiSegment left = node.getLeft().getData();
		SkiSegment right = node.getRight().getData();
		
		//If both nodes are JumpSegments, if the right node is greater than or equal to the left it is returned, otherwise the left node is returned
		if (right instanceof JumpSegment && left instanceof JumpSegment) {
			if (((JumpSegment)right).getHeight() >= ((JumpSegment)left).getHeight()) return node.getRight();
			else return node.getLeft();
		}
		
		if (right instanceof JumpSegment) return node.getRight();
		if (left instanceof JumpSegment) return node.getLeft();
		
		//If both nodes are SlalomSegments, the node in the leeward direction is returned
		if (right instanceof SlalomSegment && left instanceof SlalomSegment) {
			if (((SlalomSegment)right).getDirection().equals("L")) return node.getRight();
			else return node.getLeft();
		}
		
		//If one node is a SlalomSegment, that segment is returned if it is in the leeward direction
		if (right instanceof SlalomSegment || left instanceof SlalomSegment) {
			if (right instanceof SlalomSegment) {
				if (((SlalomSegment)right).getDirection().equals("L")) return node.getRight();
				else return node.getLeft();
			}
			if (left instanceof SlalomSegment) {
				if (((SlalomSegment)left).getDirection().equals("L")) return node.getLeft();
				else return node.getRight();
			}
		}
		//Otherwise the right node is returned
		return node.getRight();
	}
	
	//Uses recursion to add subsequent best paths to a sequence
	public void skiNextSegment(BinaryTreeNode<SkiSegment> node, ArrayUnorderedList<SkiSegment> sequence) {
		sequence.addToRear(node.getData());
		
		if (node.getLeft() == null && node.getRight() == null) return;

		skiNextSegment(bestPath(node), sequence);
	}

}
