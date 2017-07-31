package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {

		if (isEmpty()) {
			return 0;
		} else {
			return 1 + getNext().size();
		}
	}

	@Override
	public T search(T element) {

		if (isEmpty())
			return null;

		if (data.equals(element)) {
			return data;
		} else {
			return next.search(element);
		}

	}

	@Override
	public void insert(T element) {

		if (this.data == null) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<>());
		} else {
			getNext().insert(element);
		}

	}

	@Override
	public void remove(T element) {

		if (isEmpty())
			return;

		if (getData().equals(element)) {
			setData(getNext().getData());
			setNext(getNext().getNext());
		} else {
			getNext().remove(element);
		}

	}

	@Override
	public T[] toArray() {

		T[] array = (T[]) new Object[size()];
		int i = 0;
		if (this.data != null) {
			array[i] = this.data;
			toArray(this.next, array, i + 1);
		}

		return array;

	}

	private void toArray(RecursiveSingleLinkedListImpl<T> prox, T[] array, int i) {

		if (prox.getData() != null) {
			array[i] = prox.getData();
			toArray(prox.next, array, i + 1);
		}

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
