package questoes.heap;

import java.util.Arrays;

import heap.HeapImpl;

public class MergeDeArrays {

	public static void main(String[] args) {

		HeapImpl<Integer> heap = new HeapImpl<>((o1, o2) -> o2.compareTo(o1));

		Integer[] array = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
		Integer[] array2 = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };

		heap.buildHeap(array);
		for (Integer integer : array2) {
			heap.insert(integer);
		}
		System.out.println(heap.size());
		Integer[] arrayFinal = new Integer[array.length + array2.length];
		for (int i = 0; i < arrayFinal.length; i++) {
			arrayFinal[i] = heap.extractRootElement();
		}
		System.out.println(Arrays.toString(heap.heapsort(arrayFinal)));

	}

}
