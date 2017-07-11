package dataEstructures.elementary;

public class StackImpl<T> {

	private T[] array;
	private int top;

	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	public T top() {
		T result = null;
		if (!isEmpty()) {
			result = this.array[top];
		}
		return result;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == array.length - 1;
	}

	public void push(T element) throws StackOverflowException {
		if (top == array.length - 1) {
			throw new StackOverflowException();
		} else {
			top++;
			array[top] = element;
		}
	}

	public T pop() throws StackUnderflowException {
		T result = null;
		if (top == -1) {
			throw new StackUnderflowException();
		} else {
			result = array[top];
			top--;
		}
		return result;
	}
}
