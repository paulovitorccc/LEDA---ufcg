package questoes.heap;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
		
		PilhaUsandoHeap<Informacao> pilha = new PilhaUsandoHeap<Informacao>(10);
		
		Informacao i1 = new Informacao("f", 3);
		Informacao i2 = new Informacao("c", 6);
		Informacao i3 = new Informacao("e", 4);
		Informacao i4 = new Informacao("a", 8);
		Informacao i5 = new Informacao("d", 5);
		Informacao i6 = new Informacao("h", 1);
		Informacao i7 = new Informacao("b", 7);
		Informacao i8 = new Informacao("g", 2);
		
		pilha.push(i1);
		pilha.push(i2);
		pilha.push(i3);
		pilha.push(i4);
		pilha.push(i5);
		pilha.push(i6);
		pilha.push(i7);
		pilha.push(i8);
		System.out.println(Arrays.toString(pilha.heap.elementsByLevel(3)));
		
		for(int i = 0; i < 8; i++){
			System.out.println(pilha.pop());
		}
		
		
		
		
	}
}
