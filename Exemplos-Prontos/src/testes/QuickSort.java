package testes;

public class QuickSort<T extends Comparable<T>> {

	public void sort(T[] array, int leftIndex, int rigthIndex) {
		if (leftIndex < rigthIndex) {
			int m = particion(array, leftIndex, rigthIndex);
			sort(array, leftIndex, m - 1);
			sort(array, m + 1, rigthIndex);
		}
	}

	private int particion(T[] array, int leftIndex, int rigthIndex) {
		int i = leftIndex;
		int j = i + 1;

		while (j <= rigthIndex) {
			if (array[j].compareTo(array[leftIndex]) < 0) {
				i++;
				swap(array, i, j);
			}
			j++;
		}
		swap(array, i, leftIndex);
		return i;
	}

	private void swap(T[] array, int index1, int index2) {
		T temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

}
