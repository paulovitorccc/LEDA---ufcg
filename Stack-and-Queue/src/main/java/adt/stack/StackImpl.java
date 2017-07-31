package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public void push(T element) throws StackOverFlowException {
		if (isFull()) {
			throw new StackOverFlowException();
		} else {
			top++;
			array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderFlowException {
		if (isEmpty()) {
			throw new StackUnderFlowException();
		} else {
			T element = array[top];
			top--;
			return element;
		}
	}

	@Override
	public T top() {
		T element = null;
		if (!isEmpty()) {
			element = array[top];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == array.length - 1;
	}

}
