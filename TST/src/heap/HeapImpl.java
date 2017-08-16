package heap;

import java.util.Arrays;
import java.util.Comparator;


/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
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
		T[] resp = Util.makeArrayOfComparable(index + 1);
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
		int left = left(position);
		int right = right(position);
		int maximo = position;
		if (left <= index && comparator.compare(heap[left], heap[maximo]) > 0) {
			maximo = left;
		}
		if (right <= index && comparator.compare(heap[right], heap[maximo]) > 0) {
			maximo = right;
		}
		if (maximo != position) {
			Util.swap(heap, maximo, position);
			heapify(maximo);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		////////////////////////////////////////////////////////////////////
		index++;
		if (index == 0) {
			heap[index] = element;
		} else {
			heap[index] = element;
			int j = index;
			while (j > 0 && comparator.compare(heap[this.parent(j)], heap[j]) < 0) {
				Util.swap(heap, j, parent(j));
				j = parent(j);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i];
		}
		this.index = array.length - 1;
		for (int j = parent(index); j >= 0; j--) {
			heapify(j);
		}
	}

	@Override
	public T extractRootElement() {
		T root = null;
		if (!isEmpty()) {
			root = heap[0];
			Util.swap(heap, 0, index);
			index--;
			heapify(0);
		}
		return root;
	}

	@Override
	public T rootElement() {
		T root = null;
		if (!isEmpty()) {
			root = heap[0];
		}
		return root;
	}

	@Override
	public T[] heapsort(T[] array) {
		if(array[0] != null){
			Comparator<Integer> oldComparator = (Comparator<Integer>) this.comparator;
			Comparator<Integer> newComparator = (Comparator<Integer>) ((o1, o2) -> o2.compareTo(o1));
			this.comparator = (Comparator<T>) newComparator;
			buildHeap(array);
			for (int i = 0; i < array.length; i++) {
				array[i] = this.extractRootElement();
			}
			this.comparator = (Comparator<T>) oldComparator;
			return array;
		}
		return array;
	}

	@Override
	public int size() {
		return index + 1;
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

	public void setHeap(T[] heap) {
		this.heap = heap;
	}
	
	
	
	//QUESTAO DA LISTA
	
	public T[] elementsByLevel(int level){
		int indexPotencia = (int) Math.pow(2, level);
		int inicio = indexPotencia - 1;
		int fim = inicio + inicio;
		T[] array = (T[]) new Comparable[indexPotencia];
		int cont = 0;
		for(int i = inicio; i <= fim; i++){
			if(i < size() && heap[i] != null){
				array[cont] = heap[i];
				cont++;
			}
		}
		return array;
	}
	

}
