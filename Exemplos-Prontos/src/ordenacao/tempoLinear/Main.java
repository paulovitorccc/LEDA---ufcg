package ordenacao.tempoLinear;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		//instanciar implementação de sort
		CountingSort sr = new CountingSort();

		Integer[] array1 = new Integer[] { -4, 30, 28, 7, -20, 29, 11, 26, -18, -56, 4, 22, 23, 31 };

		Integer[] array2 = new Integer[] { 0, 6, 41, 32, 7, -2, 0, 26, 4, 37, 49, -25, 11, 18, -8, 36 };

		Integer[] array3 = new Integer[] { 4, 9, 3, -3, -5, 4, 0,-6,-7,-8, 5, 1,-22, 4 };

		Integer[] array4 = new Integer[] { 6, 6, 6, 6, 6, 6 };
		

		sr.sort(array1, 0, 13);
		sr.sort(array2, 0, 15);
		sr.sort(array3, 0, 13);
		sr.sort(array4, 0, 5);
		
		
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));

	}

}
