package adt.bst.extended;

import adt.bst.BST;

/**
 * A distancia horizontal de um no em uma BST eh a distancia horizontal dele 
 * par aa raiz. 
 * A distancia horizontal entre dois nos eh calculada pela quantidade 
 * de passos que se desce para a esquerda (-1) e para a direita(+1)
 * date se chegar ao nh desejado. Por exemplo,
 * 
 *       4
 *   2       8
 * 1   3   6
 * 
 * Distancia horizontal de 4 = 0
 * Distancia horizontal de 2 = -1 (desce um para esquerda)
 * Distancia horizontal de 1 = -2 (desce dois para esquerda)
 * Distancia horizontal de 3 = 0  (desce um para esquerda e um para direita)
 * Distancia horizontal de 8 = +1 (desce um para a direita)
 * Distancia horizontal de 6 = 0  (desce um para a direita e um para a esquerda)
 * @author Adalberto
 *
 */
public interface HorizontalDistanceBST<T extends Comparable<T>> extends BST<T> {

	/**
	 * Retorna a distancia horizontal de um noh para a raiz.
	 * Se o elemento nao esta na BST a distancia horizontal deve ser zero.
	 * 
	 * Restrição:
	 * - A implementação deve ser recursiva e ter tempo O(log n)
	 * 
	 * @return
	 */
	public int horizontalDistance(T elem);
	
}
