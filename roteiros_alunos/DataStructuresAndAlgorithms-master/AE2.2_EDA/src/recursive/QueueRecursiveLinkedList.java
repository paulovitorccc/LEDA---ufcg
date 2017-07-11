package recursive;

import java.util.Scanner;

/**
 * This class constains the main method.
 * @author Lucas L. Vieira
 */
class QueueRecursiveLinkedList {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String operation = "";
		
		Queue<String> queue = new Queue<>();
		
		while (! (operation.equals("end"))) {
			String[] action = sc.nextLine().split(" ");
			operation = action[0];
			
			if (action[0].equals("print")) {
				System.out.println(queue.toString());
			} else if (action[0].equals("add")) {
				queue.add(action[1]);
			} else if (action[0].equals("remove")) {
				if (queue.isEmpty()) {
					System.out.println("empty");
				} else {
					queue.remove();
				}
			} else if (action[0].equals("element")) {
				System.out.println(queue.element());
			} else if (action[0].equals("search")) {
				System.out.println(queue.search(action[1]));
			}
		}
		
		sc.close();
	}
}

/**
 * This class that represent the queue using recursive linked list.
 * @author Lucas L. Vieira
 */
class Queue<T> {
	
	private RecursiveLinkedList<T> linkedList;
	
	public Queue() {
		this.linkedList = new RecursiveLinkedList<>();
	}
	
	public void add(T element) {
		linkedList.insertLast(element);
	}
	
	public void remove() {
		linkedList.removeFirst();
	}
	
	public String element() {
		return linkedList.element();
	}
	
	public int search(T element) {
		return linkedList.search(element);
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public String toString() {
		return linkedList.toString();
	}
}

/**
 *  This class is a recursive linked list.
 * @author Lucas L. Vieira
 */
class RecursiveLinkedList<T> {

	private Node<T> head;

	public RecursiveLinkedList() {
		this.head = new Node<T>();
	}

	public void insertLast(T element) {
		if (element == null) return;

		Node<T> newNode = new Node<T>(element);
		
		if (isEmpty()) {
			Node<T> leftNil = new Node<T>();
			Node<T> rigthNil = new Node<T>();

			newNode.setNext(rigthNil);
			rigthNil.setPrevious(newNode);

			newNode.setPrevious(leftNil);
			leftNil.setNext(newNode);
			
			head = newNode;
			
		} else {
			head.insert(element);
		}
	}

	public void removeFirst() {
		if (! (isEmpty())) {	
		
			Node<T> next = head.getNext();
			
			if (next.isNIL()) {
				head = new Node<T>();
			} else {
			
				head.getPrevious().setNext(next);
				next.setPrevious(head.getPrevious());

				head = next;
			}
		}
	}

	public String element() {
		if (isEmpty()) return "empty";
		
		return "" + head.getValue();
	}
	
	public int search(T element) {
		if (element == null || isEmpty()) {
			return -1;
		}
		
		return head.search(element);
	}
	
	public boolean isEmpty() {
		return (head.isNIL());
	}
	
	@Override
	public String toString() {
		if (isEmpty()) return "empty";
		
		return head.toString();
	}
}

/**
 * This class is the representation of a node in linked list. 
 * @author Lucas L. Vieira
 */
class Node<T> {

	private T value;
	private Node<T> previous;
	private Node<T> next;

	public Node() {
		this.value = null;
		this.previous = null;
		this.next = null;
	}
	
	public Node(T value) {
		this.value = value;
		this.previous = null;
		this.next = null;
	}

	public Node<T> insert(T element) {
		if (this.getNext().isNIL()) {
			Node<T> rigthNil = this.getNext();
			Node<T> newNode = new Node<T>(element);
			
			newNode.setNext(rigthNil);
			rigthNil.setPrevious(newNode);
			
			this.setNext(newNode);
			newNode.setPrevious(this);
			
			return newNode;
		} else {
			return this.getNext().insert(element);
		}
	}
	
	public int search(T element) {
		return search(element, 0);
	}
	
	private int search(T element, int position) {		
		if (this.getNext().isNIL()) {
			if (this.getValue().equals(element)) {
				return position;
			} else {
				return -1;
			}
		} else if (this.getValue().equals(element)) {
			return position;
		} else {
			return this.getNext().search(element, position + 1);
		}
	}
	
	public boolean isNIL() {
		return (value == null);
	}

	/*The getters and Setters methods*/

	public T getValue() {
		return value;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		String elements = this.getValue().toString();
		
		if (! (this.getNext().isNIL())) {
			return elements + " " + this.getNext().toString();
		} else {
			return elements.trim();
		}
	}
}
