package questoes.pilha_fila;

import queue.QueueOverflowException;
import queue.QueueUnderflowException;
import stack.StackImpl;
import stack.StackOverflowException;
import stack.StackUnderflowException;

public class Main {

	public static void main(String[] args) throws QueueOverflowException, StackOverflowException, StackUnderflowException, QueueUnderflowException, questoes.heap.StackOverflowException, questoes.heap.StackUnderflowException {
		
		StackImpl<Integer> stack = new StackImpl<Integer>(5);
		ElementInseridoEm<Integer> el = new ElementInseridoEm<>();
		
		stack.push(1);
		stack.push(2);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		
		System.out.println(el.elementoPorInsercao(stack, 3));
		
		
		
	}
}
