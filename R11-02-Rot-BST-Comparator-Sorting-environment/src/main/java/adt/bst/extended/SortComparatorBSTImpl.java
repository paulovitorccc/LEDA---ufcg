package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		this.toClear();
		for (T t : array) {
			insert(t);
		}
		return order();
	}

	private void toClear() {
		root = new BSTNode<T>();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		this.reverseOrder(root, array, 0);
		return array;
	}
	
	private int reverseOrder(BSTNode<T> node, T[] array, int cont){
		if(node.isEmpty()){
			return cont;
		} else {
			cont = reverseOrder((BSTNode<T>) node.getRight(), array, cont);
			array[cont] = node.getData();
			cont = cont + 1;
			cont = reverseOrder((BSTNode<T>) node.getLeft(), array, cont);
			return cont;
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return new BSTNode<T>();
		} else if (this.comparator.compare(node.getData(), element) < 0) {
			return search(((BSTNode<T>) node.getRight()), element);
		} else if (this.comparator.compare(node.getData(), element) > 0) {
			return search(((BSTNode<T>) node.getLeft()), element);
		} else {
			return (BSTNode<T>) node;
		}
	}
	
	@Override
	public void insert(T element) {
		if (this.root.isEmpty()) {
			root.setData(element);
			root.setParent(new BSTNode<T>());
			root.setLeft(new BSTNode<T>());
			root.setRight(new BSTNode<T>());
			root.getLeft().setParent(root);
			root.getRight().setParent(root);
		} else {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if (this.comparator.compare(node.getData(), element) < 0) {
			insert((BSTNode<T>) node.getRight(), element);
		} else if (this.comparator.compare(node.getData(), element) > 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
