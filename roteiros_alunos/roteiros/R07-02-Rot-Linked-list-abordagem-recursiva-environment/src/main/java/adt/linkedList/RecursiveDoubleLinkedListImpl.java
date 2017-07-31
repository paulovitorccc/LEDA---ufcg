package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {

		if (isEmpty()) {
			setData(element);
			return;
		}
		RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
		node.setData(this.data);
		node.setNext(this.next);
		node.setPrevious(this.previous);
		this.next = node;
		this.setData(element);

	}

	@Override
	public void removeFirst() {

		if (!isEmpty()) {
			this.setData(getNext().getData());
			this.setNext(getNext().getNext());
		}

	}

	@Override
	public void removeLast() {
		if (getNext().getNext().isEmpty()) {
			setNext(new RecursiveDoubleLinkedListImpl<>());
		} else {
			RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) getNext();
			next.removeLast();
		}
	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.setNext(new RecursiveDoubleLinkedListImpl<>(null, null, this));

		} else {
			this.getNext().insert(element);
		}
	}
	
	public T getLast(){
		
		if(this.getNext().getData() == null){
			return this.getData();
		}else{
			return ((RecursiveDoubleLinkedListImpl<T>) getNext()).getLast();
		}
		
	}
	 
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
