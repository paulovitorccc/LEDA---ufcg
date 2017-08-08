
public class NodeSingleLinkedList<T> {

	private T data;
	private NodeSingleLinkedList<T> next;
	
	public boolean isNIL(){
		return this.data == null;
	}

	@Override
	public String toString() {
		return "NodeSingleLinkedList [data=" + data + ", next=" + next + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeSingleLinkedList other = (NodeSingleLinkedList) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
