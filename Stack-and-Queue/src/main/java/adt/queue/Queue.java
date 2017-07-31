package adt.queue;

public interface Queue<T> {

	public T dequeue() throws QueueUnderFlowException;

	public void enqueue(T element) throws QueueOverFlowException;

	public boolean isEmpty();

	public boolean isFull();

	public T head();

}
