package exemplos.recursao;

import java.util.ArrayList;

public class TotalZeros {

	public static void main(String[] args) {

		int[] array = {0,9,0,21,3,5,0,0,0,7};
		

		// startTimeI guarda tempo antes da execução do metodo
		// totalZerosIterativo
		long startTimeI = System.nanoTime();
		int resultadoI = totalZerosIterativo(array);
		// runtimeI guarda tempo de execução do metodo totalZerosIterativo
		long runtimeI = System.nanoTime() - startTimeI;

		// startTimeR guarda tempo antes da execução do metodo
		// totalZerosRecursivo
		long startTimeR = System.nanoTime();
		int resultadoR = totalZerosComRecursao(array);
		// runtimeR guarda tempo de execução do metodo totalZerosRecursivo
		long runtimeR = System.nanoTime() - startTimeR;

		System.out.println("Resultado recursivo:" + resultadoR);
		System.out.println("Tempo recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado iterativo:" + resultadoI);
		System.out.println("Tempo iterativo:" + runtimeI);

	}

	/**
	 * Metodo calcula quantidade de zeros em array de forma iterativa
	 */
	public static int totalZerosIterativo(int[] array) {
		int zeros = 0;
		for (int i : array) {
			if (i == 0) {
				zeros += 1;
			}
		}
		return zeros;
	}

	/**
	 * Metodo calcula quantidade de zeros em array de forma recursiva
	 * indiretamente, pela chamada de um metodo recursivo direto
	 */
	public static int totalZerosComRecursao(int[] array) {
		return totalZerosRecursivo(array, 0);
	}

	/**
	 * Metodo calcula quantidade de zeros em array de forma recursiva direta
	 */
	public static int totalZerosRecursivo(int[] array, int indice) {
		int zeros = 0;

		if (indice == array.length - 1) {
			if (array[indice] == 0) {
				zeros = 1;
			}

		} else {
			if (array[indice] == 0) {
				zeros = 1;
			}
			zeros = zeros + totalZerosRecursivo(array, indice + 1);
		}

		return zeros;

	}

}
