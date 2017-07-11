package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
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

	}
}
