package exemplos.recursao;

public class SomaDeKElementos {
	public static void main(String[] args) {

		int valor = 3;

		// startTimeI guarda tempo antes da execução do metodo iterativo
		long startTimeI = System.nanoTime();
		int resultadoI = somaIterativa(valor);
		// runtimeI guarda tempo de execução do metodo iterativo
		long runtimeI = System.nanoTime() - startTimeI;

		// startTimeR guarda tempo antes da execução do metodo recursivo
		long startTimeR = System.nanoTime();
		int resultadoR = somaRecursiva(valor);
		// runtimeR guarda tempo de execução do metodo recursivo
		long runtimeR = System.nanoTime() - startTimeR;

		System.out.println("Resultado recursivo:" + resultadoR);
		System.out.println("Tempo recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado iterativo:" + resultadoI);
		System.out.println("Tempo iterativo:" + runtimeI);

	}

	/**
	 * Calcula soma dos k primeiros elementos forma iterativa
	 */
	public static int somaIterativa(int k) {
		int soma = 0;
		for (int i = 1; i <= k; i++) {
			soma = soma + i;
		}
		return soma;
	}

	/**
	 * Calcula soma dos k primeiros elementos de forma recursiva direta
	 */
	public static int somaRecursiva(int k) {
		int soma = 0;
		if (k == 0) {
			
		} else {
			soma = k   + somaRecursiva(k - 1);
		}

		return soma;
	}

}
