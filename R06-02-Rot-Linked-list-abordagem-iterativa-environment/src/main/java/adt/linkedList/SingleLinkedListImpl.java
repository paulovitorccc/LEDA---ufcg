package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> nodeAuxiliar = head;
		while (!nodeAuxiliar.isNIL()) {
			size++;
			nodeAuxiliar = nodeAuxiliar.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> nodeAuxiliar = head;

		while (!nodeAuxiliar.isNIL() && !nodeAuxiliar.getData().equals(element)) {
			nodeAuxiliar = nodeAuxiliar.next;
		}

		return nodeAuxiliar.getData();

	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> nodeAdd = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<>());

		if (head.isNIL()) {
			head = nodeAdd;
		} else {
			SingleLinkedListNode<T> nodeAuxiliar = head;

			while (!nodeAuxiliar.next.isNIL()) {
				nodeAuxiliar = nodeAuxiliar.next;
			}

			nodeAdd.next = nodeAuxiliar.next;
			nodeAuxiliar.next = nodeAdd;
		}

	}

	@Override
	public void remove(T element) {
		if (head.getData() == element) {
			head = head.next;
		} else {
			SingleLinkedListNode<T> previousNodeAuxiliar = new SingleLinkedListNode<T>();
			SingleLinkedListNode<T> nodeAuxiliar = head;

			while (!(nodeAuxiliar.getData().equals(element))) {
				previousNodeAuxiliar = nodeAuxiliar;
				nodeAuxiliar = nodeAuxiliar.next;
			}

			if (!nodeAuxiliar.isNIL()) {
				previousNodeAuxiliar.next = nodeAuxiliar.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] arrayResult = (T[]) new Object[this.size()];

		SingleLinkedListNode<T> nodeAuxiliar = head;
		int cont = 0;
		while (!nodeAuxiliar.isNIL()) {
			arrayResult[cont] = nodeAuxiliar.getData();
			nodeAuxiliar = nodeAuxiliar.next;
			cont++;
		}

		return arrayResult;

	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
