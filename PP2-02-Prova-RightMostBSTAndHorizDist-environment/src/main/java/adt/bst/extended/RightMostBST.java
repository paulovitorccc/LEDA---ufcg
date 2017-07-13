package adt.bst.extended;

import java.util.List;

import adt.bst.BST;

/**
 * BST contendo metodo para achar elementos a uma distancia "d" da raiz e outro para 
 * retornar a lista dos elementos mais a direita da BST, por nivel.
 * A distancia de um noh para ele mesmo eh 0, para seus filhos eh 1, para os netos
 * eh 2, e assim por diante. 
 * Os elementos mais a direita de uma BST sao os que se encontram mais a direita
 * em cada nivel da BST. 
 *  
 * @author Adalberto
 *
 */
public interface RightMostBST<T extends Comparable<T>> extends BST<T> {
	/**
	 * Retorna uma lista dos elementos que se encontram a uma distancia "d" da raiz.
	 * 
	 * Restricoes:
	 * - A implementacao deve ser recursiva
	 * - a implementacao deve ter tempo O(n)
	 * @param d
	 * @return
	 */
	public List<T> elementsAtDistance(int d);
	
	/**
	 * Retorna a lista dos elementos mais a direita da BST.
	 * 
	 * @return
	 */
	public List<T> rightMost();
	
}
