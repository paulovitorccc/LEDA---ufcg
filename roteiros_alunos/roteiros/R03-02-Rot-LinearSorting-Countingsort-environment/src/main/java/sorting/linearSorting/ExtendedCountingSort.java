package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if ( array == null || array.length == 0) {
			return;
		} else {
			if (leftIndex < 0) {
				leftIndex = 0;
			}
			if (rightIndex > array.length - 1) {
				rightIndex = array.length - 1;
			}
		}

		countingSort(array, leftIndex, rightIndex);
	}

	private void countingSort(Integer[] v, int leftIndex, int rightIndex) {

		Integer maior = Util.retornaMaior(v, leftIndex, rightIndex);
		Integer menor = Util.retornaMenor(v,leftIndex, rightIndex);
		
		int[] c = new int[maior - menor + 1];
		Integer[] b = new Integer[rightIndex + 1];

		for (int i = leftIndex; i <= rightIndex; i++) {
			c[v[i] - menor] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}

		for (int i = rightIndex; i >= leftIndex; i--) {
			b[c[v[i] - menor ] - 1] = v[i];
			c[v[i] - menor] -= 1;
		}

		for (int i = 0; i < b.length; i++) {
			v[i] = b[i];
		}

	}
	
}
