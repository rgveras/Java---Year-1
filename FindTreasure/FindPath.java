/**
 * 
 * This program reads a file containing a map and computes the safest path from the entrance of a pyramid to all treasures within the pyramid.
 * It creates a stack of the entered chambers and marks chambers that have been entered.
 * If a chamber has been exited to return to a previous chamber, the chamber that was exited is popped off the stack and marked as popped.
 * 
 * @author Ricardo Veras
 * 
 */


import java.io.FileNotFoundException;
import java.io.IOException;


public class FindPath {
	private Map pyramidMap;
	
	//Creates a new Map object from a file name as a parameter. Catches necessary exceptions
	public FindPath(String fileName) {
		try {
			pyramidMap = new Map(fileName);
		}
		
		catch (InvalidMapCharacterException Invalid) {
			Invalid.getMessage();
		}
		catch (FileNotFoundException Invalid) {
			Invalid.getMessage();
		}
		catch (IOException IOException) {
			IOException.getMessage();
		}
		
	}
	
	
	//Finds the path from the entrance to all treasures and creates a stack containing the chambers that make up the path.
	public DLStack<Chamber> path() {
		DLStack<Chamber> stack = new DLStack<Chamber>();
		int N = pyramidMap.getNumTreasures();
		
		stack.push(pyramidMap.getEntrance());
		stack.peek().markPushed();
		
		//While the stack is not empty, the best path is computed until all treasures are found.
		while (!stack.isEmpty()) {
			Chamber curr = stack.peek();
			
			//If a treasure is found and the found treasure count now equals all treasures in the pyramid the loop exits.
			if (curr.isTreasure() && N == 0) {
				break;
				} else {
				Chamber c = bestChamber(stack.peek());
				
				//Decrements the number of treasures remaining if the neighbouring chamber has a treasure.
				if (c != null && c.isTreasure()) N--;
				
				//Best chamber to enter is added to the stack
				if (c != null) {
					stack.push(c);
					c.markPushed();
				} else {
					Chamber popped = stack.pop();
					popped.markPopped();
				}
			}
		}
		return stack;
			}
	

	public Map getMap() {
		return pyramidMap;
	}
	
	//Computes if a chamber is dim and returns true if it is, false otherwise.
	public boolean isDim(Chamber currentChamber) {
		
		if (currentChamber.isSealed() || currentChamber.isLighted() || currentChamber == null) {
			return false;
		} else {
			for (int i=0; i<6; i++) {
				if (currentChamber.getNeighbour(i) != null) {
				if (currentChamber.getNeighbour(i).isLighted()) return true;
			}
			}
		}
		return false;
	}
		
	//Computes the best chamber to enter next. Prioritized by unmarked treasure, then unmarked lighted followed by unmarked dim chamber.
	public Chamber bestChamber(Chamber currentChamber) {
		for (int i=0; i<6; i++) {
			if (currentChamber.getNeighbour(i) != null) {
			if (currentChamber.getNeighbour(i).isTreasure() && !currentChamber.getNeighbour(i).isMarked()) return currentChamber.getNeighbour(i);
		}
		}
		for (int i=0; i<6; i++) {
			if (currentChamber.getNeighbour(i) != null) {
			if (currentChamber.getNeighbour(i).isLighted() && !currentChamber.getNeighbour(i).isMarked()) return currentChamber.getNeighbour(i);
		}
		}
		for (int i=0; i<6; i++) {
			if (currentChamber.getNeighbour(i) != null) {
			if (isDim(currentChamber.getNeighbour(i)) && !currentChamber.getNeighbour(i).isMarked()) return currentChamber.getNeighbour(i);
		}
		}
		return null;
		}
		
	}
