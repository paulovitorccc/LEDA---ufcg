
package LinkedList;

public class SingleLinkedList<T> {

	protected SingleLinkedListNode<T> head;
	
	public SingleLinkedList() {
		head = new SingleLinkedListNode<T>();
	}
	
	public void insert(T element) {
		if(head.isNIL()) {
			head.setData(element);
		} else {
			SingleLinkedListNode<T> aux = head;
			while(!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			SingleLinkedListNode<T> novo = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
			aux.setNext(novo);
		}
	}
	
	public void remove(T element) {
		if(!head.isNIL()) {
			SingleLinkedListNode<T> auxPrevious = head;
			SingleLinkedListNode<T> aux = head;
			while(!aux.getData().equals(element) && !aux.getNext().isNIL()) {
				aux = aux.getNext();
				auxPrevious = aux;
			}
			if(aux.getData().equals(element)) {
				auxPrevious.setNext(aux.getNext());
			}
		}
	}
	
	public T search(T element) {
		T result = null;
		if(!head.isNIL()) {
			SingleLinkedListNode<T> aux = head;
			while(!aux.getData().equals(element) && !aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			if(aux.getData().equals(element)) {
				result = aux.getData();
			}
		}
		return result;
	}
	
	public int size() {
		SingleLinkedListNode<T> aux = head;
		int cont = 0;
		while(!aux.isNIL()) {
			aux = aux.getNext();
			cont++;
		}
		return cont;
	}
	
	public boolean isEmpty() {
		return head.isNIL();
	}
	
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int cont = 0;
		while(!aux.isNIL()) {
			array[cont] = aux.getData();
			aux = aux.getNext();
			cont++;
		}
		return array;
	}
}
