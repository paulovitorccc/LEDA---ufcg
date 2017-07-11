package testes;

public class QuickSort2<T extends Comparable<T>> {

	public void sort(T[] array, int leftIndex, int rigthIndex) {
		if (leftIndex < rigthIndex) {
			int m = particion(array, leftIndex, rigthIndex);
			sort(array, leftIndex, m);
			sort(array, m + 1, rigthIndex);
		}
	}

	private int particion(T[] array, int leftIndex, int rigthIndex) {
		T pivot = array[((rigthIndex - leftIndex) / 2) + leftIndex];
		int i = leftIndex;
		int j = rigthIndex;

		while (i < j) {
			while (array[i].compareTo(pivot) < 0) {
				i++;
			}
			while (array[j].compareTo(pivot) > 0) {
				j--;
			}
			if (i < j) {
				swap(array, i, j);
				i++;
				j--;
			}
		}

		return j;
	}

	private void swap(T[] array, int index1, int index2) {
		T temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

}
