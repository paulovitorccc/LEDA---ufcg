package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int indiceFinal = rightIndex;
		boolean troca = true;
		while (troca) {
			troca = false;

			for (int k = leftIndex; k < indiceFinal; k++) {
				if (array[k].compareTo(array[k + 1]) > 0) {
					util.swap(array, k, k+1);
					troca = true;
				}
			}

			indiceFinal = indiceFinal - 1;
		}
	}
}
