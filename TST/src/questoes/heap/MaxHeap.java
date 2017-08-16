package questoes.heap;

import java.util.Scanner;

import heap.HeapImpl;

public class MaxHeap {

	public static void main(String[] args) {

		Scanner sn = new Scanner(System.in);
		String linha = sn.nextLine();
		String[] arrayLinha = linha.split(" ");

		Integer[] arrayInteger = new Integer[arrayLinha.length];
		for (int j = 0; j < arrayLinha.length; j++) {
			arrayInteger[j] = Integer.parseInt(arrayLinha[j]);
		}
		
		boolean valido = true;
		int index = arrayInteger.length - 1;
		while(index >= 0 && valido){
			int parent = (index - 1) / 2;
			if(parent >= 0){
				if((arrayInteger[index].compareTo(arrayInteger[parent]) > 0)){
					valido = false;
				}
			}
			index--;
		}
		System.out.println(valido);

	}

}
