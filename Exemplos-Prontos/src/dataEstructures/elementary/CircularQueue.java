package dataEstructures.elementary;

public class CircularQueue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0;
		tail = 0;
		elements = 0;
	}

	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		array[tail] = element;
		tail = (tail + 1) % array.length;
		elements++;

	}

	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = array[head];
		head = (head + 1) % array.length;
		elements--;
		return result;
	}

	public T head() {
		return array[head];
	}

	public boolean isEmpty() {
		return elements == 0;
	}

	public boolean isFull() {
		return elements == array.length;
	}

}
