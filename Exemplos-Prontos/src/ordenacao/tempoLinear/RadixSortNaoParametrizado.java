package ordenacao.tempoLinear;

import java.util.ArrayList;

public class RadixSortNaoParametrizado {

	public int[] sort(int array[], int leftIndex, int rightIndex) {

		Integer maiorInteiro = 0;
		for (int h = leftIndex; h <= rightIndex; h++) {
			if (array[h] > maiorInteiro) {
				maiorInteiro = array[h];
			}
		}

		int casasDecimais = (int) maiorInteiro.toString().length();

		for (int casa = 1; casa <= casasDecimais; casa++) {

			ArrayList<Integer>[] listaDeArray = new ArrayList[10];
			for (int i = 0; i < listaDeArray.length; i++) {
				listaDeArray[i] = new ArrayList<Integer>();
			}

			int potencia = (int) Math.pow(10, casa);
			for (int j = leftIndex; j <= rightIndex; j++) {
				int num = array[j];

				int div = (int) potencia / 10;
				int lsd = (num % potencia) / div;
				listaDeArray[lsd].add(num);
			}

			ArrayList<Integer> auxiliar = new ArrayList<Integer>();
			for (ArrayList<Integer> a : listaDeArray) {
				for (int elemento : a) {
					auxiliar.add(elemento);
				}
			}

			int indiceAuxiliar = 0;
			for (int k = leftIndex; k <= rightIndex; k++) {
				array[k] = auxiliar.get(indiceAuxiliar);
				indiceAuxiliar++;
			}

		}
		return array;

	}

}
