package questoes.pilha_fila;

import stack.StackImpl;
import stack.StackOverflowException;
import stack.StackUnderflowException;

public class ElementInseridoEm<T> {

	// Pensar em um algoritmo que encontra um elemento da pilha dada sua ordem
	// de inserção (sem alterar a pilha) e usa uma pilha auxiliar para isto. Por
	// exemplo, inserir a,b,c,d nessa ordem numa pilha.Buscar o 2º elemento
	// inserido deverá
	// retornar‘b’ mas não deve modificar a pilha.
	
	//metodo errado pensar melhor depois
	public T elementoPorInsercao(StackImpl<T> stack, int ordem) throws StackOverflowException, StackUnderflowException {
		T result;
		StackImpl<T> stackAux = stack;
		int qtdElements = 0;
		while (!stackAux.isEmpty()) {
			stackAux.pop();
			ordem++;
		}
		stackAux = stack;
		if (qtdElements < ordem || ordem == 0) {
			result = null;
		} else {
			for (int j = 0; j <= (qtdElements - ordem); j++) {
				stackAux.pop();
			}
			result = stackAux.pop();
		}
		return result;
	}
}
