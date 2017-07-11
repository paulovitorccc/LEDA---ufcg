package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) { // Verificação realizada para ver se o sort nas chamadas recursivas vai executar
			int m = particion(array, leftIndex, rightIndex); // Executando metodo particion.
			sort(array, leftIndex, m - 1); // Executando sort para a metade esquerda do array
			sort(array, m + 1, rightIndex); // Executando sort para a metade direita do array
		}
	}

	// O método particion seleciona um pivot(considerado neste caso o leftIndex) e deixa todos do array que são menores que ele do lado esquerdo e os maiores do lado direito
	public int particion(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex; // indice de controle que delimita pela direita todos os elementos ja comparados e menores que o pivot
		int j = leftIndex + 1; // proximo indice do elemento a comparar com o pivor

		while (j <= rightIndex) { // laço incrementa j até o rightIndex
			if (array[j].compareTo(array[leftIndex]) <= 0) {
				i++;
				util.swap(array, i, j); // Se o elemento indexado com j for menor que o pivot ocorre o swap(troca) com o i incrementado na linha anterior
			}
			j++;
		}
		util.swap(array, leftIndex, i); // pivot é trocado pelo elemento indexado com i, garantindo assim que todos que estão a sua esquerda são menores 
		return i; 
	}
}
