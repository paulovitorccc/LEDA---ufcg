package linkedlist;

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
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveDoubleLinkedListImpl<T>();
			if (previous == null) {
				previous = new RecursiveDoubleLinkedListImpl<T>();
			}
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (getData().equals(element)) {
				if (next.isEmpty() && previous.isEmpty()) {
					data = null;
					previous = new RecursiveDoubleLinkedListImpl<T>();
					next = new RecursiveDoubleLinkedListImpl<T>();
				} else {
					data = next.getData();
					next = next.next;
					if (!next.isEmpty()) {
						((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
					}
				}
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
			previous = new RecursiveDoubleLinkedListImpl<T>();
		} else {
			RecursiveDoubleLinkedListImpl<T> oldHeadNode = new RecursiveDoubleLinkedListImpl<T>(data, next, this);
			this.data = element;
			next = oldHeadNode;
			previous = new RecursiveDoubleLinkedListImpl<T>();
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (next.isEmpty()) {
				data = null;
			} else {
				data = next.getData();
				next = next.getNext();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (next.isEmpty()) {
				if (!previous.isEmpty()) {
					previous.next = new RecursiveSingleLinkedListImpl<T>();
				} else {
					data = null;
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

	// questoes listas

	// indice da primeira ocorrencia do elemento
	public int indexOf(T element) {
		return indexOf(element, 0);
	}

	private int indexOf(T element, int index) {
		int result = -1;
		if (isEmpty() || this.data.equals(element)) {
			if (!isEmpty()) {
				result = index;
			}
		} else {
			index++;
			result = ((RecursiveDoubleLinkedListImpl<T>) next).indexOf(element, index);
		}
		return result;
	}

	// ultima ocorrencia do elemento na lista
	public int lastIndexOf(T element) {
		return lastIndexOf(element, 0);
	}

	private int lastIndexOf(T element, int index) {
		int result = -1;
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				result = index;
			}
			index++;
			int newResult = ((RecursiveDoubleLinkedListImpl<T>) next).lastIndexOf(element, index);
			if (newResult != -1) {
				result = newResult;
			}
		}
		return result;
	}

	// depois pensar em forma recursiva
	public void inverter() {
		T[] array = toArray();
		inverter(array, array.length - 1);
	}

	private void inverter(T[] array, int cont) {
		if (!isEmpty()) {
			this.setData(array[cont]);
			cont--;
			((RecursiveDoubleLinkedListImpl<T>) next).inverter(array, cont);
		}
	}
	
	//remove elemento pelo indice
		public void removeByIndex(int index){
			if(index >= 0 && index < this.size()){
				removeByIndexAux(index);
			}
		}
		
		private void removeByIndexAux(int index){
			if(index == 0){
				this.setData(next.getData());
				this.setNext(next.getNext());
			} else {
				index--;
				((RecursiveDoubleLinkedListImpl<T>) next).removeByIndexAux(index);
			}
		}

}
