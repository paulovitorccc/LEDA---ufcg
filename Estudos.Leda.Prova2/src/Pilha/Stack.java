package Pilha;

public class Stack<T extends Comparable<T>> {

	T[] array;
	int top;

	public Stack(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			top++;
			array[top] = element;
		}
	}

	public T pop() throws StackUnderflowException {
		T result = null;
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			result = array[top];
			top--;
		}
		return result;
	}

	public T top() {
		return array[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == array.length - 1;
	}
}
