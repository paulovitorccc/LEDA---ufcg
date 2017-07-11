package dataEstructures.elementary;

public class QueueImpl<T> {

	private T[] array;
	private int tail;

	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	public T head() {
		return array[0];
	}

	public boolean isEmpty() {
		return tail == -1;
	}

	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
		tail--;
	}

	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			tail++;
			array[tail] = element;
		}
	}

	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T result = array[0];
			shiftLeft();
			return result;
		}
	}

}
