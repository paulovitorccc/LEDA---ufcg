package ordenacao.comparacao;

public class QuickSort<T extends Comparable<T>> {

	Util util = new Util();

	public T[] sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int m = particion(array, leftIndex, rightIndex);
			sort(array, leftIndex, m - 1);
			sort(array, m + 1, rightIndex);
		}
		return array;
	}

	public int particion(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex;
		int j = leftIndex + 1;

		while (j <= rightIndex) {
			if (array[j].compareTo(array[leftIndex]) <= 0) {
				i++;
				util.swap(array, i, j);
			}
			j++;
		}
		util.swap(array, leftIndex, i);
		return i;
	}
}
