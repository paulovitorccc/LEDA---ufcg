package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = Util.makeArray(index + 1);
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		
		int left = this.left(position);
		int right = this.right(position);
		int max = position;
		if (left <= index && comparator.compare(this.heap[left], this.heap[position]) > 0){
			max = left;
		}if (right <= index && comparator.compare(this.heap[right], this.heap[max]) > 0){
			max = right;
		}
		if (max != position){
			Util.swap(this.heap, position, max);
			heapify(max);
		}
		
	}
	
	public T[] elementsByLevel(int level){
		
		int sizeArray = (int) Math.pow(2,level);
		int inicio = sizeArray -1;
		int fim = inicio + inicio;
		T[] array = Util.makeArray(sizeArray);
		int j = 0;
		for (int i = inicio; i <= fim ; i++) {
			if(this.heap[i] != null)
				array[j] = this.heap[i];
			j+=1;
		}
		return array;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		index++;
		if(index == 0){
			this.heap[index] = element;
		}else{
			this.heap[index] = element;
			int i = index;
			while(i > 0 && comparator.compare(this.heap[this.parent(i)], this.heap[i]) < 0){
				Util.swap(this.heap, i, this.parent(i));
				i = this.parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		
		for (int i = 0; i < array.length; i++) {
			this.heap[i] = array[i];
		}
		this.index = array.length -1;
		for(int i = this.parent(index); i >= 0; i--){
			this.heapify(i);
		}
		
	}

	@Override
	public T extractRootElement() {
		
		if(this.isEmpty()) return null;
		
		T element = this.heap[0];
		this.heap[0] = this.heap[index];
		this.index--;
		this.heapify(0);
		return element;
		
	}

	@Override
	public T rootElement() {
		
		if(this.isEmpty()){
			return null;
		}
		return this.heap[0];
		
	}

	@Override
	public T[] heapsort(T[] array) {
		
		Comparator<Integer> oldComparator = (Comparator<Integer>) this.comparator;
		Comparator<Integer> newComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}

		};
		this.comparator = (Comparator<T>) newComparator;
		this.buildHeap(array);
		for (int i = 0; i < array.length; i++) {
			array[i] = this.extractRootElement();
		}
		this.comparator = (Comparator<T>) oldComparator;
		return array;
		
	}

	@Override
	public int size() {
		
		return this.index + 1;
		
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
