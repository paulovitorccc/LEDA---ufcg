package ordenacao.comparacao;

/**
 * Algoritmo do tipo dividir para conquistar Dividir: se #sequência > 1, divida
 * em duas menores Conquistar: ordene cada subsequência recursivamente Combinar:
 * junte as duas subseqüências em uma seqüência ordenada Inventor: Neumann(1945)
 */

public class MergeSort<T extends Comparable<T>> {

	public T[] sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int half = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, half);
			sort(array, half + 1, rightIndex);
			merge(array, leftIndex, half, rightIndex);
		}
		return array;
	}

	private void merge(T[] array, int start, int half, int end) {
		T[] help = (T[]) new Comparable[array.length];
		for (int h = 0; h < help.length; h++) {
			help[h] = array[h];
		}

		int i = start;
		int j = half + 1;
		for (int k = start; k <= end; k++) {
			if (i > half) {
				array[k] = help[j];
				j++;
			} else if (j > end) {
				array[k] = help[i];
				i++;
			} else if (help[i].compareTo(help[j]) < 0) {
				array[k] = help[i];
				i++;
			} else {
				array[k] = help[j];
				j++;
			}
		}
	}

}
