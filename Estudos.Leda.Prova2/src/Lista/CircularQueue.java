package Lista;

public class CircularQueue<T extends Comparable> {

	T[] array;
	int head;
	int tail;
	int elements;

	public CircularQueue(int size) {
		this.array = (T[]) new Object[size];
		this.head = 0;
		this.tail = 0;
		this.elements = 0;
	}

	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			array[tail] = element;
			tail = (tail + 1) % array.length;
			elements++;
		}
	}

	public T dequeue() throws QueueUnderflowException{
		T result = null;
		if(isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			result = array[head];
			head = (head + 1) % array.length;
			elements--;
		}
		return result;
	}

	public T head() {
		return array[head];
	}

	public boolean isEmpty() {
		return elements == 0;
	}

	public boolean isFull() {
		return elements == array.length - 1;
	}

}
