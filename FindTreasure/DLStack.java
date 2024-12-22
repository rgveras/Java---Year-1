/**
 * 
 * This program implements DLStackADT<T> and creates a stack using a doubly linked list.
 * 
 * @author Ricardo Veras
 *
 * @param <T>
 */


public class DLStack<T> implements DLStackADT<T> {
	private DoubleLinkedNode<T> top;
	private int numItems;
	
	
	public DLStack() {
		top = new DoubleLinkedNode<T>(null);
		numItems = 0;
	}

	//Adds item to top of stack, increments numItems in the stack and sets the top value to the new node at the top.
	public void push(T dataItem) {
		DoubleLinkedNode<T> next = new DoubleLinkedNode<T>(dataItem);
		
		if (top != null) {
			top.setNext(next);
			next.setPrevious(top);
			top = next;
			numItems++;
		} else {
		top = next;
		numItems++;
		}
	}

	//Removes top element of the stack. Throws an EmptyStackException if the stack is empty. Otherwise returns the removed element.
	public T pop() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("The stack is empty.");
		DoubleLinkedNode<T> removed = top;
		
		if (top.getPrevious() != null) {
			top = top.getPrevious();
			top.setNext(null);
			numItems--;
		}
		return removed.getElement();
	}

	//Removes the k'th element from the top of the stack. Throws InvalidItemException if the k parameter is larger than the stack size or less than or equal to zero.
	public T pop(int k) throws InvalidItemException {
		if (k>numItems || k<=0) throw new InvalidItemException("This is not a valid item.");
		DoubleLinkedNode<T> currNode = top;
		
		//Iterates until currNode is at the node that is the k'th element from the top of the stack.
		for (int i=k; i>1; i--) {
			currNode = currNode.getPrevious();
		}
		
		//Removes the selected node and updates the necessary pointers.
		if (k != 1) {
			DoubleLinkedNode<T> next = currNode.getNext();
			next.setPrevious(currNode.getPrevious());
			currNode.getPrevious().setNext(next);
			return currNode.getElement();
		}
		else return pop();
	}
	
	//Returns top element in the stack. Throws EmptyStackException if the stack is empty.
	public T peek() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("The stack is empty.");
		else return top.getElement();
	}

	//Returns true if the stack is empty, false otherwise.
	public boolean isEmpty() {
		if (numItems == 0) return true;
		else return false;
	}

	//Returns the number of items in the stack.
	public int size() {
		return numItems;
	}

	//Returns the top node of the stack.
	public DoubleLinkedNode<T> getTop() {
		return top;
	}
	
	//Prints a string representation of the elements in the stack.
	public String toString() {
		String str = "[" + top.getElement();
		DoubleLinkedNode<T> curr = top.getPrevious();
		
		for (int i=numItems; i>2; i--) {
			str += curr.getElement();
			curr = curr.getPrevious();
		}
		str += curr + "]";
		
		return str;
	}

}
