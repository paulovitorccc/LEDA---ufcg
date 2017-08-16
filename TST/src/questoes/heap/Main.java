package questoes.heap;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
		
		PilhaUsandoHeap<Informacao> pilha = new PilhaUsandoHeap<Informacao>(10);
		
		Informacao i1 = new Informacao("f");
		Informacao i2 = new Informacao("c");
		Informacao i3 = new Informacao("e");
		Informacao i4 = new Informacao("a");
		Informacao i5 = new Informacao("d");
		Informacao i6 = new Informacao("h");
		Informacao i7 = new Informacao("b");
		Informacao i8 = new Informacao("g");
		
		pilha.push(i1);
		pilha.push(i2);
		pilha.push(i3);
		pilha.push(i4);
		pilha.push(i5);
		pilha.push(i6);
		pilha.push(i7);
		pilha.push(i8);
		
		for(int i = 0; i < 8; i++){
			System.out.println(pilha.pop());
		}
		
		
		
		
	}
}
