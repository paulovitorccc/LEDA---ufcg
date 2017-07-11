package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();

	public void sort(T[] array, int leftIndex, int rightIndex) {

		int pivot = 1;
		while (pivot < rightIndex) {

			if (array[pivot].compareTo(array[pivot + 1]) > 0) {
				util.swap(array, pivot, pivot + 1);
				if (pivot != leftIndex) {
					pivot--;
				}
			} else {
				pivot++;
			}

		}
	}
}
