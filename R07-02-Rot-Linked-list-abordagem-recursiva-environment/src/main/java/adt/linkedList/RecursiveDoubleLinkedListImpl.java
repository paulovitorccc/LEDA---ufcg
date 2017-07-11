package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveDoubleLinkedListImpl<T>();
			if (previous == null) {
				previous = new RecursiveDoubleLinkedListImpl<T>();
			}
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (getData().equals(element)) {
				if (next.isEmpty() && previous.isEmpty()) {
					data = null;
					previous = new RecursiveDoubleLinkedListImpl<T>();
					next = new RecursiveDoubleLinkedListImpl<T>();
				} else {
					data = next.getData();
					next = next.next;
					if (!next.isEmpty()) {
						((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
					}
				}
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
			previous = new RecursiveDoubleLinkedListImpl<T>();
		} else {
			RecursiveDoubleLinkedListImpl<T> oldHeadNode = new RecursiveDoubleLinkedListImpl<T>(data, next, this);
			this.data = element;
			next = oldHeadNode;
			previous = new RecursiveDoubleLinkedListImpl<T>();
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (next.isEmpty()) {
				data = null;
			} else {
				data = next.getData();
				next = next.getNext();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (next.isEmpty()) {
				if (!previous.isEmpty()) {
					previous.next = new RecursiveSingleLinkedListImpl<T>();
				} else {
					data = null;
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
