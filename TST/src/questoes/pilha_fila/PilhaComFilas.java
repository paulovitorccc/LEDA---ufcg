package questoes.pilha_fila;

import questoes.heap.StackOverflowException;
import questoes.heap.StackUnderflowException;
import queue.QueueImpl;
import queue.QueueOverflowException;
import queue.QueueUnderflowException;

public class PilhaComFilas<T> {

	QueueImpl<T> fila1;
	QueueImpl<T> fila2;

	public PilhaComFilas(int size) {
		fila1 = new QueueImpl<T>(size);
		fila2 = new QueueImpl<T>(size);
	}

	public void push(T element) throws StackOverflowException, QueueOverflowException, QueueUnderflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			while (!fila1.isEmpty()) {
				fila2.enqueue(fila1.dequeue());
			}
			fila1.enqueue(element);
			while (!fila2.isEmpty()) {
				fila1.enqueue(fila2.dequeue());
			}
		}
	}

	public T pop() throws StackUnderflowException, QueueUnderflowException {
		T result;
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			result = fila1.dequeue();
		}
		return result;
	}

	public T top() {
		if (fila1.isEmpty()) {
			return null;
		}
		return fila1.head();
	}

	public boolean isEmpty() {
		return fila1.isEmpty();
	}

	public boolean isFull() {
		return fila1.isFull();
	}

}
