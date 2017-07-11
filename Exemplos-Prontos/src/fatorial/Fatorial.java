package fatorial;

public class Fatorial {

	public static void main(String[] args) {

		int valor = 10;

		//startTimeR guarda tempo antes da execução do metodo recursiveFactorial
		long startTimeR = System.nanoTime(); 
		int resultadoR = recursiveFactorial(valor);
		//runtimeR guarda tempo de execução do metodo recursiveFactorial
		long runtimeR = System.nanoTime() - startTimeR;
		
		//startTimeI guarda tempo antes da execução do metodo iterativeFactorial
		long startTimeI = System.nanoTime(); 
		int resultadoI = iterativeFactorial(valor);
		//runtimeI guarda tempo de execução do metodo iterativeFactorial
		long runtimeI = System.nanoTime() - startTimeR;
		
		System.out.println("Resultado fatorial recursivo:" + resultadoR);
		System.out.println("Tempo fatorial recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado fatorial iterativo:" + resultadoI);
		System.out.println("Tempo fatorial iterativo:" + runtimeI);
		
	}

	/**
	 * Calculando fatorial de forma recursiva
	 */
	private static int recursiveFactorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return (n * recursiveFactorial(n - 1));
		}

	}

	/**
	 * Calculando fatorial de forma iterativa
	 */
	private static int iterativeFactorial(int n) {
		int result = 1;
		if (n > 0) {
			for (int i = n; i > 0; i--) {
				result = result * i;
			}
		}

		return result;
	}
}
