package linkedlist;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if (data == null) {
			result = true;
		}
		return result;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else {
			if (data.equals(element)) {
				return data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.data = next.getData();
				this.next = next.next;
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		toArrayAux(result, this, 0);
		return result;
	}

	private void toArrayAux(T[] array, RecursiveSingleLinkedListImpl<T> node, int count) {
		if (!node.isEmpty()) {
			array[count] = node.getData();
			toArrayAux(array, node.next, count + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
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
			result = next.indexOf(element, index);
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
			int newResult = next.lastIndexOf(element, index);
			if (newResult != -1) {
				result = newResult;
			}
		}
		return result;
	}
	
	//depois pensar em forma recursiva
	public void inverter(){
		T[] array = toArray();
		inverter(array, array.length - 1);
	}
	
	private void inverter(T[] array, int cont){
		if(!isEmpty()){
			this.setData(array[cont]);
			cont--;
			next.inverter(array, cont);
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
			next.removeByIndexAux(index);
		}
	}
	
	
	
}
