package ordenacao.tempoLinear;

import java.util.Arrays;

/**
 * Idéia básica Determinar, para cada elemento x da entrada, o número de
 * elementos menores que x. Essa informacao é usada para colocar o x na posicao
 * correta do array de resposta.
 * 
 * Idéia 1. Criamos um array C de k elementos inicializados com 0 2. Percorremos
 * cada um dos n do array A números e adicionamos C[n-1]++; 3. Soma dos prefixos
 * C[i + 1] = C[i+1] + C[i] 4. Depois ordenamos o arra y A de acordo com C
 */

public class CountingSort {

	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && array[0] != null) {
			Integer maiorValor = this.maxValor(array, leftIndex, rightIndex);

			Integer[] auxiliar = new Integer[maiorValor + 1];
			this.preencherArrayIntegerCom0(auxiliar);

			for (int m = leftIndex; m <= rightIndex; m++) {
				int indexDeNormatizacao = array[m];
				auxiliar[indexDeNormatizacao]++;
			}

			for (int n = 1; n < auxiliar.length; n++) {
				auxiliar[n] = auxiliar[n] + auxiliar[n - 1];
			}

			Integer[] arrayResposta = new Integer[array.length];
			for (int o = rightIndex; o >= leftIndex; o--) {
				int indexDeNormatizacao = array[o];
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
