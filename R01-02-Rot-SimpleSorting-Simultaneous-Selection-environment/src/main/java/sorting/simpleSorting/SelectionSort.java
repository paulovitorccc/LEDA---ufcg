package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(array.length == 0) || !(leftIndex > rightIndex)) {
			int indiceInicio = leftIndex;
			
			while (indiceInicio<=rightIndex) {
				
				int indiceMenorElemento = indiceInicio;
				for (int k = indiceInicio; k <= rightIndex; k++) {
					if (array[indiceMenorElemento].compareTo(array[k]) > 0) {
						indiceMenorElemento = k;
					}
				}
				
				util.swap(array, indiceInicio, indiceMenorElemento);
				indiceInicio = indiceInicio + 1;
			}
		}
	}
}
