package LinkedList;

public class SingleLinkedListNode<T> {

	private T data;
	private SingleLinkedListNode<T> next;

	public SingleLinkedListNode() {
	}

	public SingleLinkedListNode(T data, SingleLinkedListNode<T> next) {
		this.data = data;
		this.next = next;
	}

	public boolean isNIL() {
		return this.data == null;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result;
		if (obj instanceof SingleLinkedListNode) {
			SingleLinkedListNode<T> outroNo = (SingleLinkedListNode<T>) obj;
			if(!isNIL()) {
				result = outroNo.getData().equals(this.data);
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result;
		if(!isNIL()) {
			result = this.data.toString();
		} else {
			result = "NIL";
		}
		return result;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SingleLinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(SingleLinkedListNode<T> next) {
		this.next = next;
	}

}
