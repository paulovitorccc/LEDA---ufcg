package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int head;
	private int tail;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0;
		tail = 0;
		elements = 0;
	}

	@Override
	public T dequeue() throws QueueUnderFlowException {
		if (isEmpty()) {
			throw new QueueUnderFlowException();
		} else {
			T element = array[head];
			head = (head + 1) % array.length;
			elements--;
			return element;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverFlowException {
		if (isFull()) {
			throw new QueueOverFlowException();
		} else {
			array[tail] = element;
			tail = (tail + 1) % array.length;
			elements++;
		}
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

	@Override
	public T head() {
		return array[head];
	}

}
