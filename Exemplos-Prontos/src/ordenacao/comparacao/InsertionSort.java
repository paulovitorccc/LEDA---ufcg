package ordenacao.comparacao;

public class InsertionSort<T extends Comparable<T>> {

	public T[] sort(T[] array, int leftIndex, int rightIndex) {
		int j;
		int k;

		for (j = leftIndex + 1; j <= rightIndex; j++) {
			T key = array[j];

			k = j - 1;
			while (k >= leftIndex && (array[k].compareTo(key) > 0)) {
				array[k + 1] = array[k];
				k--;
			}
			array[k + 1] = key;

		}
		return array;
	}

}
