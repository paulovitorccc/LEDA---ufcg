package questoes.heap;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import heap.HeapImpl;

public class BuildHeap {

	public static void main(String[] args) {

		HeapImpl<Integer> heap = new HeapImpl<Integer>((o1, o2) -> o1.compareTo(o2));

		Scanner sn = new Scanner(System.in);
		String linha = sn.nextLine();
		String[] arrayLinha = linha.split(" ");
		
		Integer[] arrayInteger = new Integer[arrayLinha.length];
		for (int j = 0; j < arrayLinha.length; j++) {
			arrayInteger[j] = Integer.parseInt(arrayLinha[j]);
		}
		heap.buildHeap(arrayInteger);
		
		Comparable[] arraySolucao = new Comparable[heap.size()];
		arraySolucao = heap.getHeap();
		System.out.println(Arrays.toString(arraySolucao));
	}

}
