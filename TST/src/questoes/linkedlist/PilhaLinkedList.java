package questoes.linkedlist;

import linkedlist.RecursiveDoubleLinkedListImpl;
import questoes.heap.Stack;
import questoes.heap.StackOverflowException;
import questoes.heap.StackUnderflowException;

public class PilhaLinkedList<T> implements Stack<T> {
	
	RecursiveDoubleLinkedListImpl<T> stack;
	int size;
	
	public PilhaLinkedList(int size) {
		stack = new RecursiveDoubleLinkedListImpl<>();
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()){
			throw new StackOverflowException();
		} else {
			stack.insertFirst(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result;
		if(isEmpty()){
			throw new StackUnderflowException();
		} else {
			result = stack.getData();
			stack.removeFirst();
		}
		return result;
	}

	@Override
	public T top() {
		return stack.getData();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (stack.size() == this.size);
	}

	
}
