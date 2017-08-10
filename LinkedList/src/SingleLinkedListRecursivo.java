
public class SingleLinkedListRecursivo<T> implements LinkedList<T> {

	private T data;
	private SingleLinkedListRecursivo<T> next;

	public SingleLinkedListRecursivo() {
	}

	public SingleLinkedListRecursivo(T data, SingleLinkedListRecursivo<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new SingleLinkedListRecursivo<T>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				setData(next.getData());
				setNext(next.getNext());
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()){
			return null;
		} else {
			if(this.data.equals(element)){
				return this.data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (!isEmpty()) {
			size = 1 + next.size();
		}
		return size;
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		toArrayAux(array, 0);
		return array;
	}

	private void toArrayAux(T[] array, int cont) {
		if (!isEmpty()) {
			array[cont] = this.data;
			next.toArrayAux(array, cont + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SingleLinkedListRecursivo<T> getNext() {
		return next;
	}

	public void setNext(SingleLinkedListRecursivo<T> next) {
		this.next = next;
	}

}
