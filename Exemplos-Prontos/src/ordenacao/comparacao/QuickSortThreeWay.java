package ordenacao.comparacao;


public class QuickSortThreeWay<T extends Comparable<T>> {

	Util util = new Util();

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int li = leftIndex;
			int ri = rightIndex;
			int i = leftIndex + 1;

			int pivotIndex = leftIndex;
			T pivotValue = array[pivotIndex];

			while (i <= ri) {

				if (array[i].compareTo(pivotValue) < 0) {
					util.swap(array, li, i);
					li++;
					i++;
				}

				if (array[i].compareTo(pivotValue) > 0) {
					util.swap(array, ri, i);
					ri--;
				}
				if (array[i].compareTo(pivotValue) == 0)
					i++;

			}

			sort(array, leftIndex, li - 1);
			sort(array, ri + 1, rightIndex);

		}
	}

}
