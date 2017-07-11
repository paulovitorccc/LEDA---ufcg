package ordenacao.comparacao;

public class BubbleSort<T extends Comparable<T>> {
	
	public T[] sort(T[] array, int i, int f) {
		int indiceFinal = f;
		boolean troca = true;
		while (troca) {
			troca = false;

			for (int k = i; k < indiceFinal; k++) {
				if (array[k].compareTo(array[k + 1]) > 0) {
					T temp = array[k];
					array[k] = array[k + 1];
					array[k + 1] = temp;
					troca = true;
				}
			}

			indiceFinal = indiceFinal - 1;
		}
		return array;
	}

}
