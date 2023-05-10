import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Kamil Przepiórowski
 *  @version 19/10/2018
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;
		
		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;
	int size;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification: constant running time as only constant runtime operations used
	 *  TODO
	 */
	public boolean isEmpty()
	{
		// TODO
		if(size==0) {
			return true;
		}
		return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: O(N)
	 *
	 * Justification:	If first or last element, only constant operations used. However worst case would be if the element is right in the middle and runtime 
	 * 					would be N/2, but in asymptotic notation thats equivalent to N. This is because the program checks whether the position is closer to head
	 * 					or tail, and then only iterates through that half of the list.
	 *  TODO
	 */
	public void insertBefore( int pos, T data ) 
	{
		//TODO
		DLLNode newNode = new DLLNode(data,null,null);
		if(isEmpty()) {
		    newNode.prev = null; 
		    newNode.next=null;
	        head = newNode; 
	        tail = newNode;
	        size++;
	        return; 
		}
		
		if(pos<=0) {
			newNode.next=head;
			newNode.prev=null;
			if (head != null) {
		        head.prev = newNode; 
			}
			head=newNode;
			size++;
			return;
		}
		
		else if(pos>=size) {
			newNode.next=null;
			newNode.prev=tail;
			if(tail != null) {
				tail.next=newNode;
			}
			tail=newNode;
			size++;
			return;
		}
		
		else if(pos>0 && pos<size) {
			if(pos <= (size/2)) {				//checks whether position is closer to head or tail
				DLLNode tmp = head;
				for(int i=1; i<pos;i++) {
					tmp=tmp.next;
				}
				newNode.prev=tmp;
				newNode.next=tmp.next;
				tmp.next.prev=newNode;
				tmp.next=newNode;	
				size++;
				return;
			}
			else {
				DLLNode tmp=tail;
				for(int i=size-1;i>pos;i--) {     
					tmp=tmp.prev;
				}
				newNode.next=tmp;
				newNode.prev=tmp.prev;
				tmp.prev.next=newNode;
				tmp.prev=newNode;
				size++;
				return;
			}
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: O(N)
	 *
	 * Justification:	worst case program goes through every element in the list
	 * 					
	 *  TODO
	 *
	 */
	public T get(int pos) 
	{
		//TODO
		if(isEmpty()) {
			return null;
		}
		if(pos>=0 && pos<size) {
				DLLNode tmp = head;
				for(int i=0; i<pos;i++) {
					tmp=tmp.next;
				}
				return tmp.data;
			}
		else {
			return null;
		}
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: O(N)
	 *
	 * Justification:	worst case program goes through every element in the list
	 *  TODO
	 */
	public boolean deleteAt(int pos) 
	{
		//TODO
		if(isEmpty()) {
			return false;
		}
		if(pos>=0 && pos<size) {
			DLLNode tmp = head;
			for(int i=0; i<pos;i++) {
				tmp=tmp.next;
			}
			//delete node
			if(tmp.next!=null) {
				tmp.next.prev=tmp.prev;
			}
			if(tmp.prev!=null) {
				tmp.prev.next=tmp.next;
			}
			if(tmp.prev==null) {
				head=tmp.next;
			}
			if(tmp.next==null) {
				tail=tmp.prev;
			}
			
			size--;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: O(N)
	 *
	 * Justification: Program goes through every single node in the list and changes the nodes prev and next values with each other.
	 *  TODO
	 */
	public void reverse()
	{
		//TODO
		if(size>1) {
			DLLNode tmp = null; 
			DLLNode current = head; 
			// if not empty and not one element (more than one)
			if(!isEmpty() && head!=tail) {
				// swap next and prev for all nodes of DLL
				while (current != null) { 
					tmp = current.prev; 
					current.prev = current.next; 
					current.next = tmp; 
					current = current.prev; 
				} 
			}
			tmp = tail;
			tail=head;
			head=tmp;
			return;
		}
		else {
			return;
		}
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: O(N^2)
	 *
	 * Justification:	Has a nested for loop to iterate through every element and compare it to every other element. Nlg(N)????
	 *  TODO
	 */
	public void makeUnique()
	{
		//TODO
		//two loops, one moving across the list elements, other one comparing them with every remaining element
		if(size>1) {
			DLLNode tmp = head;
			DLLNode tmp2 = head.next;
			for(int i=0;i<size;i++) {
				tmp2=tmp.next;
				while(tmp2!=null) {
				//	for(int j=i+1;j<size;j++) {
					if(tmp.data == tmp2.data) {
						size--;
						tmp2.prev.next=tmp2.next;
						if(tmp2.next==null) {
							tail=tmp2.prev;
						}
						else if(tmp2.next!=null) {
							tmp2.next.prev=tmp2.prev;
						}
					}
					tmp2=tmp2.next;	
				}
				tmp=tmp.next;
			}
		}
	}



	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification:	constant running time as only constant runtime operations used
	 *  TODO
	 */
	public void push(T item) 
	{
		//TODO
		DLLNode newNode = new DLLNode(item,null,null);
	    newNode.prev = null; 
	    if(head==null) {
	    	head = newNode;
	    }
	    else if(head!=null) {
	    	head.prev=newNode;
	    	newNode.next=head;
	    	head=newNode;
	    }
	    size++;
	    if(head.next==null) {
	    	tail=head;
	    }
	    return; 
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification:	constant running time as only constant runtime operations used
	 *  TODO
	 */
	public T pop() 
	{
		//TODO
		if(head!=null){
			if(head.next!=null) {
				head.next.prev=null;
			}
			T data = head.data;
			head=head.next;
			size--;
			return data;
		}
		return null;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification:	constant running time as only constant runtime operations used
	 *  TODO
	 */
	public void enqueue(T item) 
	{
		DLLNode newNode = new DLLNode(item,null,null);
	    newNode.prev = null; 
	    if(head==null) {
	    	head = newNode;
	    }
	    else if(head!=null) {
	    	head.prev=newNode;
	    	newNode.next=head;
	    	head=newNode;
	    }
	    size++;
	    if(head.next==null) {
	    	tail=head;
	    }
	    return; 
	}


	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an equeue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: O(1)
	 *
	 * Justification:	constant running time as only constant runtime operations used
	 *  TODO
	 */
	public T dequeue() 
	{
		//TODO
		if(tail!=null){
			T data = tail.data;
			if(tail.prev!=null) {
				tail.prev.next=null;
				tail=tail.prev;
			}
			else if(tail.prev==null) {
				tail=null;
				head=null;
			}
			size--;
			return data;
		}
		return null;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}


}


