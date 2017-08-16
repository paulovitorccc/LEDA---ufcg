package questoes.heap;

import heap.HeapImpl;

public class FilaUsandoHeap<T extends Comparable<T>> implements Queue<T> {

	HeapImpl<T> heap;
	int size;

	public FilaUsandoHeap(int size) {
		heap = new HeapImpl<T>((o1, o2) -> o2.compareTo(o1)); // MinHeap
		this.size = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			heap.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			return heap.extractRootElement();
		}
	}

	@Override
	public T head() {
		return heap.rootElement();
	}

	@Override
	public boolean isEmpty() {
		return (heap.size() == 0);
	}

	@Override
	public boolean isFull() {
		return (heap.size() == size);
	}

}
