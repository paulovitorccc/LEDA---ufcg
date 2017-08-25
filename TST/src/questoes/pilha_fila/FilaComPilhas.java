package questoes.pilha_fila;

import queue.Queue;
import queue.QueueOverflowException;
import queue.QueueUnderflowException;
import stack.StackImpl;
import stack.StackOverflowException;
import stack.StackUnderflowException;

public class FilaComPilhas<T> {

	StackImpl<T> pilha1;
	StackImpl<T> pilha2;

	public FilaComPilhas(int size) {
		pilha1 = new StackImpl<T>(size);
		pilha2 = new StackImpl<T>(size);
	}

	public void enqueue(T element) throws QueueOverflowException, StackOverflowException, StackUnderflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			while (!pilha1.isEmpty()) {
				pilha2.push(pilha1.pop());
			}
			pilha1.push(element);
			while (!pilha2.isEmpty()) {
				pilha1.push(pilha2.pop());
			}
		}
	}

	public T dequeue() throws QueueUnderflowException, StackUnderflowException {
		T result;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			result = pilha1.pop();
		}
		return result;
	}

	public T head() {
		if (pilha1.isEmpty()) {
			return null;
		}
		return pilha1.top();
	}

	public boolean isEmpty() {
		return pilha1.isEmpty();
	}

	public boolean isFull() {
		return pilha1.isFull();
	}

}
