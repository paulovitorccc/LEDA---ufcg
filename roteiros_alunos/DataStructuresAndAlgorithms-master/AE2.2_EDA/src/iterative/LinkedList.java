package iterative;

class LinkedList<T> {

	private Node<T> head;
	private Node<T> last;

	public LinkedList() {
		this.head = new Node<T>();
		this.last = new Node<T>();
	}

	public void insertFirst(T element) {
		if (element == null) return;

		Node<T> newNode = new Node<T>(element);
		
		if (isEmpty()) {
			Node<T> leftNil = new Node<T>();
			Node<T> rigthNil = new Node<T>();

			newNode.setNext(rigthNil);
			rigthNil.setPrevious(newNode);

			newNode.setPrevious(leftNil);
			leftNil.setNext(newNode);
			
			last = newNode;
		} else {
			head.getPrevious().setNext(newNode);
			newNode.setNext(head);

			newNode.setPrevious(head.getPrevious());
			head.setPrevious(newNode);
		}

		head = newNode;
	}

	public void removeFirst() {
		if (! (isEmpty())) {			
			if (head.getNext().isNIL()) {
				head = new Node<T>();
			} else {
			
				head.getPrevious().setNext(head.getNext());
				head.getNext().setPrevious(head.getPrevious());

				head = head.getNext();
			}
		}
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
			last.getNext().setPrevious(newNode);
			newNode.setPrevious(last);

			newNode.setNext(last.getNext());
			last.setNext(newNode);
		}

		last = newNode;
	}

	public void removeLast() {
		if (! (isEmpty())) {
			Node<T> rigthNil = new Node<T>();

			last.getPrevious().setNext(rigthNil);
			rigthNil.setPrevious(last.getPrevious());

			// if exist only one element
			if (last.getPrevious().isNIL()) {
				head = last.getPrevious();
			}

			last = last.getPrevious();
		}
	}

	public void remove(T element) {
		if (element == null) return;

		if (! (isEmpty())) {
			Node<T> aux = head;

			while ((! aux.isNIL()) && (! aux.getValue().equals(element))) {
				aux = aux.getNext();
			}

			if (! (aux.isNIL())) {
				Node<T> nextAux = aux.getNext();

				aux.getPrevious().setNext(nextAux);
				nextAux.setPrevious(aux.getPrevious());

				// if the removed element is the last
				if (nextAux.isNIL()) {
					last = last.getPrevious();
				}
			}
		}
	}

	@Override
	public String toString() {
		if (isEmpty()) return "";
		
		String elements = "";

		Node<T> aux = head;

		while (! (aux.isNIL())) {
			elements += aux.getValue() + " ";
			aux = aux.getNext();
		}

		return elements.trim();
	}

	public boolean isEmpty() {
		return (head.isNIL());
	}

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<>();

		ll.insertFirst(1);
		ll.insertLast(2);
		ll.insertFirst(0);

		ll.removeLast();
		ll.removeFirst();
		ll.removeFirst();
		ll.removeFirst();
		ll.removeFirst();
		System.out.println(ll);
	}
}

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
		return "" + value;
	}
}
