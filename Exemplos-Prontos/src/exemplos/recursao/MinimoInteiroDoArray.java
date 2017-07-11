package exemplos.recursao;

public class MinimoInteiroDoArray {

	public static void main(String[] args) {

		int[] array = { 99, 9, 89, 21, 3, 5, 2, 7, 5, 7 };

		// startTimeI guarda tempo antes da execução do metodo iterativo
		long startTimeI = System.nanoTime();
		int resultadoI = minimoIterativo(array);
		// runtimeI guarda tempo de execução do metodo iterativo
		long runtimeI = System.nanoTime() - startTimeI;

		// startTimeR guarda tempo antes da execução do metodo recursivo
		long startTimeR = System.nanoTime();
		int resultadoR = minimoRecursivo(array);
		// runtimeR guarda tempo de execução do metodo recursivo
		long runtimeR = System.nanoTime() - startTimeR;

		System.out.println("Resultado recursivo:" + resultadoR);
		System.out.println("Tempo recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado iterativo:" + resultadoI);
		System.out.println("Tempo iterativo:" + runtimeI);

	}

	/**
	 * Metodo seleciona menor inteiro de um array de forma iterativa
	 */
	public static int minimoIterativo(int[] array) {
		int minimo = 0;
		if (array.length > 0) {
			minimo = array[0];
			for (int num : array) {
				if (num < minimo) {
					minimo = num;
				}
			}
		}
		return minimo;
	}

	/**
	 * Metodo seleciona menor inteiro de um array de forma recursiva indireta(a
	 * partir de uma chamada pra um outro metodo recursivo)
	 */
	public static int minimoRecursivo(int[] array) {
		int minimo = 0;
		if (array.length > 0) {
			minimo = minimoRecursivo(array, 0);
		}
		return minimo;
	}

	/**
	 * Metodo seleciona menor inteiro de um array de forma recursiva direta
	 */
	private static int minimoRecursivo(int[] array, int posicao) {
		int minimo = array[0];

		if (posicao == array.length - 1) {
			if (array[posicao] < minimo) {
				minimo = array[posicao];
			}

		} else {
			int proxMinimo = minimoRecursivo(array, posicao + 1);
			if (array[posicao] < proxMinimo) {
				minimo = array[posicao];
			} else {
				minimo = proxMinimo;
			}
		}

		return minimo;
	}

}
