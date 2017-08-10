
public class NodeSingleLinkedList<T> {

	protected T data;
	protected NodeSingleLinkedList<T> next;

	public NodeSingleLinkedList() {

	}

	public NodeSingleLinkedList(T newData, NodeSingleLinkedList<T> newNext) {
		this.data = newData;
		this.next = newNext;
	}

	public boolean isNIL() {
		return this.data == null;
	}

	@Override
	public String toString() {
		String result = "NIL";
		if (!isNIL()) {
			result = data.toString();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj instanceof NodeSingleLinkedList) {
			if (!this.isNIL()) {
				resp = this.data.equals(((NodeSingleLinkedList<T>) obj).data);
			} else {
				resp = ((NodeSingleLinkedList<T>) obj).isNIL();
			}
		}
		return resp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public NodeSingleLinkedList<T> getNext() {
		return next;
	}

	public void setNext(NodeSingleLinkedList<T> next) {
		this.next = next;
	}

}
