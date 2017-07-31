package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public void enqueue(T element) throws QueueOverFlowException {
		if (isFull()) {
			throw new QueueOverFlowException();
		} else {
			tail++;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderFlowException {
		if (isEmpty()) {
			throw new QueueUnderFlowException();
		} else {
			T element = array[0];
			shiftLeft();
			return element;
		}
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
		tail--;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if (tail == -1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if (tail == array.length - 1) {
			result = true;
		}
		return result;
	}

	@Override
	public T head() {
		T result = null;
		if (!isEmpty()) {
			result = array[0];
		}
		return result;
	}

}
