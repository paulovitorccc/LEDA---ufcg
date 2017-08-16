package adt.heap.flexible;

import java.util.List;

import adt.heap.Heap;


/**
 * A heap flexivel pode funcionar tanto como uma min heap ou como uma max heap.
 * A heap possui um comparator interno que pode ser ComparatorMaxHeap e ComparatorMinHeap. 
 * Apesar da heap aceitar qualquer comparator, voce pode considerar apenas esses dois 
 * tipos de comparators. A mudanca de min-heap para max-heap pode acontecer 
 * em tempo de excucao. 
 */
public interface FlexibleHeap<T extends Comparable<T>> extends Heap<T> {

	/**
	 * Muda o comportamento/tipo da heap. Se ela esta funcionando como min-heap, 
	 * deve passar a funcionar ocmo max-heap (e vice-versa). Para saber se sua 
	 * heap eh max ou min, olhe para o comparator interno. Quando a heap muda 
	 * seu comportamento seus elementos devem ser reordenados para satisfazer os 
	 * invariantes da heap. 
	 * 
	 * Restricoes:
	 * - Procure usar o maximo possivel os metodos prontos da heap. Um bom design
	 *   usa apenas de um metodo especifico da heap.
	 */
	public void changeHeapType();

	/**
	 * Retorna uma lista de elementos da heap que se encontram em determinado nivel.
	 * O elemento raiz esta no nivel 0 e assim por diante. A lista de elementos 
	 * a ser retornada deve conter a ordem da esquerda para a direira (em que 
	 * os elementos se encontram na heap).
	 * 
	 * Restricoes:
	 * - voce nao pode modificar a heap
	 * - voce nao pdoe tirar uma copia do array interno da heap
	 * - voce nao pode usar o toArray da heap.
	 * 
	 * @param level
	 * @return
	 */
	public List<T> elementsAtLevel(int level);
	
	/**
	 * Calcula o floor de um valor passado como parametro. O floor de um valor passado 
	 * eh o elemento da heap igual que mais se aproxima do valor. Obviamente, se o valor 
	 * estiver na heap, serah ele mesmo. Seu metodo deve funcionar independente da heap 
	 * ser max ou min.
	 * 
	 * Restricoes:
	 * - voce pode modificar a heap usando apenas os metodos/funcionalidades dela
	 *   Um bom design usaria apenas o rootElement e extractRootElement
	 * - voce nao pode ordenar o array interno da heap e procurar pelo floor
	 * - voce nao pode tirar uma copia do array interno da heap e usa-la para calcular o floor
	 * 
	 * @param value
	 * @return
	 */
	public T floor(T value);
	
}
