package questoes.heap;

import heap.HeapImpl;

public class PilhaUsandoHeap<T extends Comparable<T>> {

	HeapImpl<T> heap;
	int size;

	public PilhaUsandoHeap(int size) {
		this.heap = new HeapImpl<T>((o1, o2) -> o1.compareTo(o2)); // MaxHeap
		this.size = size;
	}

	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			heap.insert(element);
		}
	}

	public T pop() throws StackUnderflowException {
		T element;
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			element = heap.extractRootElement();
		}
		return element;
	}

	public T top() {
		return heap.rootElement();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public boolean isFull() {
		return (heap.size() == this.size);
	}

}
