
public class NodeDoubleLinkedList<T> extends NodeSingleLinkedList<T> {
	
	protected NodeDoubleLinkedList<T> previous;
	
	public NodeDoubleLinkedList(){
	}
	
	public NodeDoubleLinkedList(T data, NodeDoubleLinkedList<T> next, NodeDoubleLinkedList<T> previous){
		super(data, next);
		this.previous = previous;
	}

	public NodeDoubleLinkedList<T> getPrevious() {
		return previous;
	}

	public void setPrevious(NodeDoubleLinkedList<T> previous) {
		this.previous = previous;
	}
	
	

}
