package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private static final double FATOR = 1.25;

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int gap = array.length;

		boolean troca = true;
		do {
			gap = novoGap(gap);
			troca = false;

			int i = leftIndex;
			int j = i + gap;
			while (j <= rightIndex) {
				if (array[i].compareTo(array[j]) > 0) {
					util.swap(array, i, j);
					troca = true;
				}
				i++;
				j++;
			}

		} while (troca || gap > 1);
	}

	private int novoGap(int gapAnterior) {
		int gapAtual = (int) (gapAnterior / FATOR);
		if (gapAtual < 1) {
			gapAtual = 1;
		}
		return gapAtual;
	}
}
