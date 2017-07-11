package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int meio = (leftIndex + rightIndex) / 2; // Divindo array em duas metades
			sort(array, leftIndex, meio); // Dividindo a parte esquerda do array até chegar em um tamanho unitário, por meio de recursão
			sort(array, meio + 1, rightIndex); // Dividindo a parte direita do array até chegar em um tamanho unitário, por meio de recursão
			merge(array, leftIndex, meio, rightIndex); // Fazendo o merge (junção ordenada) das duas metades 
		}
	}

	public void merge(T[] array, int inicio, int meio, int fim) {
		T[] auxiliar = (T[]) new Comparable[array.length]; // Array auxiliar para ajudar na hora da comparação dos elementos
		for (int h = 0; h < array.length; h++) { // Preenchendo o array auxiliar com os mesmos elementos do array original
			auxiliar[h] = array[h];
		}

		int i = inicio; // Ponteiro que marca o próximo elemento a ser comparado do lado esquerdo do array
		int j = meio + 1; // Ponteiro que marca o próximo elemento a ser comparado do lado direito do array

		for (int k = inicio; k <= fim; k++) { // Laço para iterar no array preenchendo-o com os elementos ja ordenados
			if (i > meio) { // Se todos os elementos da primeira metade do auxiliar ja estiverem sido utilizados, preencher o array com a segunda metade do auxiliar
				array[k] = auxiliar[j];
				j++;
			} else if (j > fim) { // Se todos os elementos da segunda metade do auxiliar ja estiverem sido utilizados, preencher o array com a primeira metade do auxiliar
				array[k] = auxiliar[i];
				i++;
			} else if (auxiliar[i].compareTo(auxiliar[j]) < 0) { // Se o elemento da primeira metade for menor ele vai para o array original
				array[k] = auxiliar[i];
				i++;
			} else { // Se o elemento da segunda metade for menor ele vai para o array original
				array[k] = auxiliar[j];
				j++;
			}
		}
	}
}
