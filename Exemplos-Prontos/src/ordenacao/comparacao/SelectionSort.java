package ordenacao.comparacao;

public class SelectionSort<T extends Comparable<T>> {

	public T[] sort(T[] array, int leftIndex, int rightIndex) {
		if (!(array.length == 0) || !(leftIndex > rightIndex)) {
			int indiceInicio = leftIndex;

			while (indiceInicio <= rightIndex) {

				int indiceMenorElemento = indiceInicio;
				for (int k = indiceInicio; k <= rightIndex; k++) {
					if (array[indiceMenorElemento].compareTo(array[k]) > 0) {
						indiceMenorElemento = k;
					}
				}
				T temp = array[indiceInicio];
				array[indiceInicio] = array[indiceMenorElemento];
				array[indiceMenorElemento] = temp;
				indiceInicio = indiceInicio + 1;
			}
		}
		return array;
	}

}
