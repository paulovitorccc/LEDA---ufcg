package Lista;

public class Queue<T extends Comparable<T>> {

	T[] array;
	int tail;

	public Queue(int size) {
		this.array = (T[]) new Object[size];
		this.tail = -1;
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
		T result = null;
		if(isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			result = array[0];
			shift(array);
			tail--;
		}
		return result;
	}
	
	private void shift(T[] array) {
		for(int i = 0; i < tail; i++) {
			T temp = array[i];
			array[i] = array[i+1];
			array[i+1] = temp;
		}
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
}
