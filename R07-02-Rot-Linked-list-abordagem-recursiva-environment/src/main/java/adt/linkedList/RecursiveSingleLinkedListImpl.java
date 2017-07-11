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
		boolean result = false;
		if (data == null) {
			result = true;
		}
		return result;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else {
			if (data.equals(element)) {
				return data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.data = next.getData();
				this.next = next.next;
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		toArrayAux(result, this, 0);
		return result;
	}

	private void toArrayAux(T[] array, RecursiveSingleLinkedListImpl<T> node, int count) {
		if (!node.isEmpty()) {
			array[count] = node.getData();
			toArrayAux(array, node.next, count + 1);
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
