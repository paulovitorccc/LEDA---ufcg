package recursive.tst;

import java.util.Scanner;

class QueueRecursiveLinkedList {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		Queue queue = new Queue();
		
		String[] action = {};
		
		do {
			action = sc.nextLine().split(" ");
			
			if (action[0].equals("print")) {
				System.out.println(queue);
			} else if (action[0].equals("add")) {
				queue.add(Integer.parseInt(action[1]));
			} else if (action[0].equals("remove")) {
				
				try{
					queue.remove();
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			} else if (action[0].equals("element")) {
				
				try{
					System.out.println(queue.element());
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			} else if (action[0].equals("search")) {
				System.out.println(queue.search(Integer.parseInt(action[1])));
			}
		
		} while (! (action[0].equals("end")));
	}
}

class Queue {
	
	private RecursiveLinkedList list;
	
	public Queue() {
		this.list = new RecursiveLinkedList();
	}
	
	public void add(Integer element) {
		list.add(element);
	}
	
	public void remove() {
		list.remove();
	}
	
	public int search(Integer element) {
		return list.search(element);
	}
	
	public int element() {
		return list.element();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}

class RecursiveLinkedList {
	
	private Node head;
	
	public RecursiveLinkedList() {
		this.head = new Node();
	}
	
	public void add(Integer element) {
		if (isEmpty()) {
			this.head = new Node(element, new Node(), new Node());
		} else {
			this.head.add(element);
		}
	}
	
	public void remove() {
		if (! (isEmpty())) {
			this.head = head.getNext();
			this.head.setPrevious(new Node());
		} else {
			throw new RuntimeException("empty");
		}
	}
	
	public int search(Integer element) {
		if (isEmpty()) {
			return -1;
		} else {
			return head.search(element);
		}
	}
	
	public int element() {
		if (! (isEmpty())) {
			return this.head.getValue();
		} else {
			throw new RuntimeException("empty");
		}
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

class Node {
	
	private Integer value;
	private Node previous;
	private Node next;
	
	public Node() {
		this.value = null;
		this.previous = null;
		this.next = null;
	}
	
	public Node(Integer value, Node previous, Node next) {
		this.value = value;
		this.previous = previous;
		this.next = next;
	}

	public void add(Integer element) {
		if (this.getNext().isNIL()) {
			Node newNode = new Node(element, this, new Node());
			this.setNext(newNode);
		} else {
			this.getNext().add(element);
		}
	}
	
	public int search(Integer element) {
		return search(element, 0);
	}
	
	private int search(Integer element, int position) {
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
	
	@Override
	public String toString() {
		String elements = this.getValue().toString();
		
		if (! (this.getNext().isNIL())) {
			return elements + " " + this.getNext().toString();
		} else {
			return elements.trim();
		}
	}
	
	/* getters and setters methods */
	
	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Integer getValue() {
		return value;
	}
}
