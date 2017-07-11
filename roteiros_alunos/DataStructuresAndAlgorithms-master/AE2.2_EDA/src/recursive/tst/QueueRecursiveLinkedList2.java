package recursive.tst;
//package tst;
//
//import java.util.Scanner;
//
//class QueueRecursiveLinkedList2 {
//	
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in);
//		Queue queue = new Queue();
//		
//		String[] action;
//		
//		do {
//			action = sc.nextLine().split(" ");
//			
//			if (action[0].equals("print")) {
//				System.out.println(queue.toString());
//			} else if (action[0].equals("add")) {
//				int element = Integer.parseInt(action[1]);
//				queue.add(element);
//			} else if (action[0].equals("remove")) {
//				if (queue.isEmpty()) {
//					System.out.println("empty");
//				} else {
//					queue.remove();
//				}
//			} else if (action[0].equals("element")) {
//				System.out.println(queue.element());
//			} else if (action[0].equals("search")) {
//				int element = Integer.parseInt(action[1]);
//				System.out.println(queue.search(element));
//			}
//		} while (! (action[0].equals("end")));
//	
//		sc.close();
//	}
//}
//
//class Queue {
//	
//	private RecursiveLinkedList linkedList;
//	
//	public Queue() {
//		this.linkedList = new RecursiveLinkedList();
//	}
//	
//	public void add(Integer element) {
//		linkedList.insertLast(element);
//	}
//	
//	public void remove() {
//		linkedList.removeFirst();
//	}
//	
//	public String element() {
//		return linkedList.element();
//	}
//	
//	public int search(Integer element) {
//		return linkedList.search(element);
//	}
//	
//	public boolean isEmpty() {
//		return linkedList.isEmpty();
//	}
//	
//	public String toString() {
//		return linkedList.toString();
//	}
//}
//
//class RecursiveLinkedList {
//
//	private Node head;
//
//	public RecursiveLinkedList() {
//		this.head = new Node();
//	}
//
//	public void insertLast(Integer element) {
//		if (element == null) return;
//		
//		if (isEmpty()) {
//			Node newNode = new Node(element);
//			
//			Node leftNil = new Node();
//			Node rigthNil = new Node();
//
//			newNode.setNext(rigthNil);
//			rigthNil.setPrevious(newNode);
//
//			newNode.setPrevious(leftNil);
//			leftNil.setNext(newNode);
//			
//			head = newNode;
//			
//		} else {
//			head.insert(element);
//		}
//	}
//
//	public void removeFirst() {
//		if (! (isEmpty())) {	
//		
//			Node next = head.getNext();
//			
//			if (next.isNIL()) {
//				head = new Node();
//			} else {
//			
//				head.getPrevious().setNext(next);
//				next.setPrevious(head.getPrevious());
//
//				head = next;
//			}
//		}
//	}
//
//	public String element() {
//		if (isEmpty()) return "empty";
//		
//		return "" + head.getValue();
//	}
//	
//	public int search(Integer element) {
//		if (element == null || isEmpty()) {
//			return -1;
//		}
//		
//		return head.search(element);
//	}
//	
//	public boolean isEmpty() {
//		return (head.isNIL());
//	}
//	
//	@Override
//	public String toString() {
//		if (isEmpty()) return "empty";
//		
//		return head.toString();
//	}
//}
//
//class Node {
//
//	private Integer value;
//	private Node previous;
//	private Node next;
//
//	public Node() {
//		this.value = null;
//		this.previous = null;
//		this.next = null;
//	}
//	
//	public Node(Integer value) {
//		this.value = value;
//		this.previous = null;
//		this.next = null;
//	}
//
//	public Node insert(Integer element) {
//		if (this.getNext().isNIL()) {
//			Node rigthNil = this.getNext();
//			Node newNode = new Node(element);
//			
//			newNode.setNext(rigthNil);
//			rigthNil.setPrevious(newNode);
//			
//			this.setNext(newNode);
//			newNode.setPrevious(this);
//			
//			return newNode;
//		} else {
//			return this.getNext().insert(element);
//		}
//	}
//	
//	public int search(Integer element) {
//		return search(element, 0);
//	}
//	
//	private int search(Integer element, int position) {		
//		if (this.getNext().isNIL()) {
//			if (this.getValue().equals(element)) {
//				return position;
//			} else {
//				return -1;
//			}
//		} else if (this.getValue().equals(element)) {
//			return position;
//		} else {
//			return this.getNext().search(element, position + 1);
//		}
//	}
//	
//	public boolean isNIL() {
//		return (value == null);
//	}
//
//	/*The getters and Setters methods*/
//
//	public Integer getValue() {
//		return value;
//	}
//
//	public Node getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(Node previous) {
//		this.previous = previous;
//	}
//
//	public Node getNext() {
//		return next;
//	}
//
//	public void setNext(Node next) {
//		this.next = next;
//	}
//	
//	@Override
//	public String toString() {
//		String elements = this.getValue().toString();
//		
//		if (! (this.getNext().isNIL())) {
//			return elements + " " + this.getNext().toString();
//		} else {
//			return elements.trim();
//		}
//	}
//}
