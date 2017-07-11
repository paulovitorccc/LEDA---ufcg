package testes;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		//instanciar implementação de sort
		QuickSort2<Integer> sr = new QuickSort2<Integer>();

		Integer[] array1 = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };

		Integer[] array2 = new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 };

		Integer[] array3 = new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 };

		Integer[] array4 = new Integer[] { 6, 6, 6, 6, 6, 6 };

		sr.sort(array1, 0, 9);
		sr.sort(array2, 0, 10);
		sr.sort(array3, 0, 7);
		sr.sort(array4, 0, 5);

		System.out.println("Array 1:");
		System.out.println(Arrays.toString(array1));
		
		System.out.println("Array 2:");
		System.out.println(Arrays.toString(array2));

		System.out.println("Array 3:");
		System.out.println(Arrays.toString(array3));
		
		System.out.println("Array 4:");
		System.out.println(Arrays.toString(array4));
	}

}
