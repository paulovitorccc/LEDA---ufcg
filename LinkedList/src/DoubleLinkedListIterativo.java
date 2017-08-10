
public class DoubleLinkedListIterativo<T> extends SingleLinkedListIterativo<T> implements LinkedListDouble<T> {

	protected NodeDoubleLinkedList<T> last;

	public DoubleLinkedListIterativo() {
		head = new NodeDoubleLinkedList<>();
		this.last = (NodeDoubleLinkedList<T>) head;
	}

	@Override
	public void insertFirst(T element) {
		NodeDoubleLinkedList<T> newHead = new NodeDoubleLinkedList<T>(element, (NodeDoubleLinkedList<T>) head,
				new NodeDoubleLinkedList<T>());
		((NodeDoubleLinkedList<T>) head).previous = newHead;

		if (head.isNIL()) {
			last = newHead;
		}

		head = newHead;
	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			((NodeDoubleLinkedList<T>) head.getNext()).setPrevious(new NodeDoubleLinkedList<>());
			head = head.getNext();
			if (head.isNIL()) {
				last = (NodeDoubleLinkedList<T>) head;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {
			last = last.getPrevious();
			if (last.isNIL()) {
				head = last;
			}
			last.setNext(new NodeSingleLinkedList<>());
		}
	}

	@Override
	public void insert(T data) {
		NodeDoubleLinkedList<T> newNode = new NodeDoubleLinkedList<T>(data, new NodeDoubleLinkedList<>(), last);
		last.setNext(newNode);
		if (last.isNIL()) {
			head = newNode;
		}
		last = newNode;
	}

	public NodeDoubleLinkedList<T> getLast() {
		return last;
	}

	public void setLast(NodeDoubleLinkedList<T> last) {
		this.last = last;
	}

}
