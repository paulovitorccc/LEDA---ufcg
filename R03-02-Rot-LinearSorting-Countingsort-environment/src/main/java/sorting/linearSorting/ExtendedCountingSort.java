package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && array[0] != null) {
			Integer menorValor = this.minValor(array, leftIndex, rightIndex);
			Integer maiorValor = this.maxValor(array, leftIndex, rightIndex);

			Integer[] auxiliar = new Integer[(maiorValor - menorValor) + 1];
			this.preencherArrayIntegerCom0(auxiliar);

			for (int m = leftIndex; m <= rightIndex; m++) {
				int indexDeNormatizacao = array[m] - menorValor;
				auxiliar[indexDeNormatizacao]++;
			}

			for (int n = 1; n < auxiliar.length; n++) {
				auxiliar[n] = auxiliar[n] + auxiliar[n - 1];
			}

			Integer[] arrayResposta = new Integer[array.length];
			for (int o = rightIndex; o >= leftIndex; o--) {
				int indexDeNormatizacao = array[o] - menorValor;
				int indexPosFinal = --auxiliar[indexDeNormatizacao];
				arrayResposta[indexPosFinal] = array[o];
			}

			this.copiarArrayDeInteger(array, leftIndex, rightIndex, arrayResposta);

		}

	}

	private Integer maxValor(Integer[] array, int leftIndex, int rightIndex) {
		Integer maiorValor = array[leftIndex];
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > maiorValor) {
				maiorValor = array[i];
			}
		}
		return maiorValor;
	}

	private Integer minValor(Integer[] array, int leftIndex, int rightIndex) {
		Integer menorValor = array[leftIndex];
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j] < menorValor) {
				menorValor = array[j];
			}
		}
		return menorValor;
	}

	private void preencherArrayIntegerCom0(Integer[] array) {
		for (int l = 0; l < array.length; l++) {
			array[l] = 0;
		}
	}

	private void copiarArrayDeInteger(Integer[] arrayOriginal, int leftIndexOriginal, int rightIndexOriginal,
			Integer[] arrayResposta) {
		int cont = 0;
		for (int p = leftIndexOriginal; p <= rightIndexOriginal; p++) {
			arrayOriginal[p] = arrayResposta[cont++];
		}
	}

}
