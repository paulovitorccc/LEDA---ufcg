
public class SingleLinkedListIterativo<T> implements LinkedList<T> {

	protected NodeSingleLinkedList<T> head;

	public SingleLinkedListIterativo() {
		this.head = new NodeSingleLinkedList<T>();
	}

	@Override
	public void insert(T element) {
		NodeSingleLinkedList<T> nodeAdd = new 	NodeSingleLinkedList<T>(element, new NodeSingleLinkedList<>());

		if (head.isNIL()) {
			head = nodeAdd;
		} else {
			NodeSingleLinkedList<T> nodeAuxiliar = head;

			while (!nodeAuxiliar.next.isNIL()) {
				nodeAuxiliar = nodeAuxiliar.next;
			}

			nodeAdd.next = nodeAuxiliar.next;
			nodeAuxiliar.next = nodeAdd;
		}
	}

	@Override
	public void remove(T element) {
		NodeSingleLinkedList<T> nodeAuxParent = this.head;
		NodeSingleLinkedList<T> nodeAux = this.head;
		if (head.getData() == element) {
			head = head.getNext();
		} else {
			while (!nodeAux.isNIL() && !nodeAux.getData().equals(element)) {
				nodeAuxParent = nodeAux;
				nodeAux = nodeAux.getNext();
			}
			if (nodeAux.getData().equals(element)) {
				nodeAuxParent.setNext(nodeAux.getNext());
			}
		}
	}

	@Override
	public T search(T element) {
		T result;
		NodeSingleLinkedList<T> nodeAux = this.head;
		while (!nodeAux.isNIL() && !nodeAux.getData().equals(element)) {
			nodeAux = nodeAux.getNext();
		}
		result = nodeAux.getData();
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;
		NodeSingleLinkedList<T> nodeAux = this.head;
		while (!nodeAux.isNIL()) {
			nodeAux = nodeAux.getNext();
			cont++;
		}
		return cont;
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		int cont = 0;
		NodeSingleLinkedList<T> nodeAux = this.head;
		while (!nodeAux.isNIL()) {
			array[cont] = nodeAux.getData();
			nodeAux = nodeAux.getNext();
		}
		return array;
	}
}
