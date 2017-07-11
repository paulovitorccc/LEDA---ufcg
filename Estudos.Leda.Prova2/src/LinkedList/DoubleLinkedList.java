package LinkedList;

public class DoubleLinkedList<T> extends SingleLinkedList<T> {

	private DoubleLinkedListNode<T> last;

	public DoubleLinkedList() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) head,
				new DoubleLinkedListNode<T>());
		((DoubleLinkedListNode<T>) head).previous = newNode;
		if (head.isNIL()) {
			last = (DoubleLinkedListNode<T>) newNode;
		}
		head = newNode;
	}

	public void removeFirst() {
		if (!head.isNIL()) {
			DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) head.getNext();
			head = newHead;
			if (head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
			((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<T>());
		}
	}

	public void removeLast() {
		if (!last.isNIL()) {
			if (head.equals(last)) {
				head.setData(null);
				last = (DoubleLinkedListNode<T>) head;
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) last.getPrevious();
				aux.setNext(new DoubleLinkedListNode<T>());
				last = aux;
			}
		}
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newNodeLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), last);
		last.setNext(newNodeLast);
		if (last.isNIL()) {
			head = newNodeLast;
		}
		last = newNodeLast;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
