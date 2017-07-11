package exemplos.recursao;

public class Palindromo {
	public static void main(String[] args) {

		String word = "subinoonibus";

		// startTimeI guarda tempo antes da execução do metodo iterativo
		long startTimeI = System.nanoTime();
		boolean resultadoI = isPalindromoIterativo(word);
		// runtimeI guarda tempo de execução do metodo iterativo
		long runtimeI = System.nanoTime() - startTimeI;

		// startTimeR guarda tempo antes da execução do metodo recursivo
		long startTimeR = System.nanoTime();
		boolean resultadoR = isPalindromoRecursivo(word);
		// runtimeR guarda tempo de execução do metodo recursivo
		long runtimeR = System.nanoTime() - startTimeR;

		System.out.println("Resultado recursivo:" + resultadoR);
		System.out.println("Tempo recursivo:" + runtimeR);
		System.out.println("");
		System.out.println("Resultado iterativo:" + resultadoI);
		System.out.println("Tempo iterativo:" + runtimeI);

	}

	/**
	 * Verifica se uma palavra é palindromo de forma iterativa
	 */
	public static boolean isPalindromoIterativo(String word) {
		boolean palindromo = true;
		int i = 0;
		while (i < word.length() / 2 && palindromo) {
			palindromo = word.charAt(i) == word.charAt(word.length() - 1 - i);
			i++;
		}
		return palindromo;
	}

	/**
	 * Verifica se uma palavra é palindromo de forma recursiva
	 */
	public static boolean isPalindromoRecursivo(String word) {
		boolean palindromo = true;
		if (word.equals("") || word.length() == 1) {

		} else {
			palindromo = word.charAt(0) == word.charAt(word.length() - 1);
			if(palindromo){
				palindromo = isPalindromoRecursivo(word.substring(1, word.length() - 1));
			}
		}
		return palindromo;
	}
}
