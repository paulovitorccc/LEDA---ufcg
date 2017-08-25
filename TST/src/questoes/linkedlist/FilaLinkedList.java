package questoes.linkedlist;

import linkedlist.RecursiveDoubleLinkedListImpl;

public class FilaLinkedList<T> implements Queue<T> {

	RecursiveDoubleLinkedListImpl<T> queue;
	int size;
	
	public FilaLinkedList(int size) {
		this.size = size;
		this.queue = new RecursiveDoubleLinkedListImpl<>();
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		} else {
			queue.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if(isEmpty()){
			throw new QueueUnderflowException();
		} else {
			result = queue.getData();
			queue.removeFirst();
		}
		return result;
	}

	@Override
	public T head() {
		return queue.getData();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean isFull() {
		return queue.size() == this.size;
	}

}
