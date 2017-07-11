package exemplos.recursao;

public class InversaoDeString {
	public static void main(String[] args) {

		String word = "paulovitor";

		// startTimeI guarda tempo antes da execução do metodo iterativo
		long startTimeI = System.nanoTime();
		String resultadoI = inverteIterativo(word);
		// runtimeI guarda tempo de execução do metodo iterativo
		long runtimeI = System.nanoTime() - startTimeI;

		// startTimeR guarda tempo antes da execução do metodo recursivo
		long startTimeR = System.nanoTime();
		String resultadoR = inverteRecursivo(word);
		// runtimeR guarda tempo de execução do metodo recursivo
		long runtimeR = System.nanoTime() - startTimeR;

		System.out.println("Resultado recursivo:" + resultadoR);
		System.out.println("Tempo recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado iterativo:" + resultadoI);
		System.out.println("Tempo iterativo:" + runtimeI);

	}

	/**
	 * Forma iterativa
	 */
	public static String inverteIterativo(String word) {
		String result = "";
		
		return result;
	}

	/**
	 * Forma recursiva
	 */
	public static String inverteRecursivo(String word) {
		String palindromo = "";
		
		return palindromo;
	}
}
