package adt.stack;

public interface Stack<T> {

	public void push(T element) throws StackOverFlowException;

	public T pop() throws StackUnderFlowException;

	public T top();

	public boolean isEmpty();

	public boolean isFull();
}
