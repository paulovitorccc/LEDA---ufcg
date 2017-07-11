package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) head,
				new DoubleLinkedListNode<T>());
		((DoubleLinkedListNode<T>) head).previous = newHead;

		if (head.isNIL()) {
			last = newHead;
		}

		head = newHead;

	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			head = head.next;
			if (head.isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
			((DoubleLinkedListNode<T>) head).previous = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {

			last = last.previous;

			if (last.isNIL()) {
				head = last;
			}

			last.next = new DoubleLinkedListNode<T>();

		}
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newNodeLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), last);
		last.next = newNodeLast;
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
