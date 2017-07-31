package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(array == null || array.length == 0){
			return;
		}else{
			if(leftIndex < 0){
				leftIndex = 0;
			}if(rightIndex > array.length -1){
				rightIndex = array.length - 1;
			}
		}
		
		countingSort(array, leftIndex, rightIndex);
	}

	private void countingSort(Integer[] v, int leftIndex, int rightIndex) {

		Integer maior = Util.retornaMaior(v, leftIndex, rightIndex);

		int[] c = new int[maior + 1];
		Integer[] b = new Integer[rightIndex + 1];

		for (int i = leftIndex; i <= rightIndex; i++) {
			c[v[i]] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}

		for (int i = rightIndex; i >= leftIndex; i--) {
			b[c[v[i]] - 1] = v[i];
			c[v[i]] -= 1;
		}

		for (int i = 0; i < b.length; i++) {
			v[i] = b[i];
		}

	}

}
