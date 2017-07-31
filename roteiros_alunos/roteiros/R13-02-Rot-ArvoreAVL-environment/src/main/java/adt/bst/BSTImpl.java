package adt.bst;

import java.util.ArrayDeque;
import java.util.Deque;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		else {
			int heightLeft = height((BSTNode<T>) node.getLeft());
			int heightRight = height((BSTNode<T>) node.getRight());

			int max = (heightLeft > heightRight) ? heightLeft : heightRight;
			return 1 + max;
		}
	}

	@Override
	public BSTNode<T> search(T element) {

		if (this.root.isEmpty()) {
			return new BSTNode<T>();
		} else {
			return search(this.root, element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.getData().equals(element)) {
			return node;

		} else {
			if (node.getData().compareTo(element) > 0) {
				if (node.getLeft().isEmpty()) {
					return new BSTNode<T>();
				} else {
					return search((BSTNode<T>) node.getLeft(), element);
				}
			} else {
				if (node.getRight().isEmpty()) {
					return new BSTNode<T>();
				} else {
					return search((BSTNode<T>) node.getRight(), element);
				}
			}
		}

	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (this.root.isEmpty()) {
			this.root.setData(element);
			this.root.setLeft(new BSTNode<T>());
			this.root.setRight(new BSTNode<T>());
			this.root.setParent(new BSTNode<T>());
			this.root.getLeft().setParent(this.root);
			this.root.getRight().setParent(this.root);

		} else {
			insert(this.root, element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.root.isEmpty())
			return null;

		BSTNode<T> maximo = this.root;
		while (!maximo.getRight().isEmpty()) {
			maximo = (BSTNode<T>) maximo.getRight();
		}
		return maximo;

	}

	@Override
	public BSTNode<T> minimum() {
		if (this.root.isEmpty())
			return null;

		BSTNode<T> minimo = this.root;
		while (!minimo.getLeft().isEmpty()) {
			minimo = (BSTNode<T>) minimo.getLeft();
		}
		return minimo;

	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		if (!node.getRight().isEmpty()) {
			BSTNode<T> aux = (BSTNode<T>) node.getRight();
			while (!aux.getLeft().isEmpty()) {
				aux = (BSTNode<T>) aux.getLeft();
			}
			return aux;
		}
		BSTNode<T> otherNode = node;
		while (!otherNode.getParent().isEmpty() && otherNode.getParent().getRight() == otherNode) {
			otherNode = (BSTNode<T>) otherNode.getParent();
		}
		if (otherNode.getParent().isEmpty())
			return null;
		return (BSTNode<T>) otherNode.getParent();
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		if (!node.getLeft().isEmpty()) {
			BSTNode<T> aux = (BSTNode<T>) node.getLeft();
			while (!aux.getRight().isEmpty()) {
				aux = (BSTNode<T>) aux.getRight();
			}
			return aux;
		}
		BSTNode<T> otherNode = node;
		while (!otherNode.getParent().isEmpty() && otherNode.getParent().getLeft() == otherNode) {
			otherNode = (BSTNode<T>) otherNode.getParent();
		}
		if (otherNode.getParent().isEmpty())
			return null;
		return (BSTNode<T>) otherNode.getParent();
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return;

		if (element.equals(this.root.getData())) {
			if (this.size() == 1) {
				this.root.setData(null);
			} else {
				BSTNode<T> otherNode = sucessor(this.root.getData());
				if (otherNode != null) {
					this.root.setData(otherNode.getData());
					if (otherNode.isLeaf()) {
						removeLeaf(otherNode);
					} else {
						removeGrauUm(otherNode);
					}
				} else {
					this.root = (BSTNode<T>) this.root.getLeft();
					this.root.setParent(new BSTNode<T>());
				}

			}
		} else {
			if (node.isLeaf()) {
				removeLeaf(node);
			} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				removeGrauDois(node);
			} else {
				removeGrauUm(node);
			}
		}
	}

	private void removeGrauDois(BSTNode<T> node) {
		BSTNode<T> otherNode = sucessor(node.getData());

		node.setData(otherNode.getData());
		if (otherNode.isLeaf()) {
			removeLeaf(otherNode);
		} else {
			removeGrauUm(otherNode);
		}
	}

	private void removeGrauUm(BSTNode<T> node) {
		BSTNode<T> otherNode = null;

		if (node.getLeft().isEmpty()) {
			otherNode = (BSTNode<T>) node.getRight();
		} else {
			otherNode = (BSTNode<T>) node.getLeft();
		}

		if (node.getParent().getRight().getData() == node.getData()) {
			otherNode.setParent(node.getParent());
			node.getParent().setRight(otherNode);
		} else {
			otherNode.setParent(node.getParent());
			node.getParent().setLeft(otherNode);

		}
	}

	private void removeLeaf(BSTNode<T> node) {
		if (node.getParent().getRight().getData() == node.getData()) {
			node.getParent().setRight(new BSTNode<T>());
		} else {
			node.getParent().setLeft(new BSTNode<T>());
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		return preOrder(this.root, array);
	}

	private T[] preOrder(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			int i = 0;
			while (array[i] != null) {
				i++;
			}
			array[i] = node.getData();
			preOrder((BSTNode<T>) node.getLeft(), array);
			preOrder((BSTNode<T>) node.getRight(), array);
		}
		return array;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		return order(this.root, array);
	}

	private T[] order(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
			int i = 0;
			while (array[i] != null) {
				i++;
			}
			array[i] = node.getData();
			order((BSTNode<T>) node.getRight(), array);
		}
		return array;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		return postOrder(this.root, array);
	}

	private T[] postOrder(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			int i = 0;
			while (array[i] != null) {
				i++;
			}
			postOrder((BSTNode<T>) node.getLeft(), array);
			postOrder((BSTNode<T>) node.getRight(), array);
			array[i] = node.getData();
		}
		return array;
	}

	// METODOS FEITOS POR MIM
	public void walkLevels(BSTNode<T> no) {
		if (no == null)
			throw new IllegalArgumentException("Tree node cannot be null!");
		Deque<BSTNode<T>> fila = new ArrayDeque<>();
		fila.add(no);
		while (!fila.isEmpty()) {
			BSTNode<T> atual = fila.removeFirst();
			System.out.printf("%s ", atual.getData());
			if (!atual.getLeft().isEmpty())
				fila.add((BSTNode<T>) atual.getLeft());
			if (!atual.getRight().isEmpty())
				fila.add((BSTNode<T>) atual.getRight());
		}
	}

	public int countLevels() {

		return countLevels(this.root);

	}

	private int countLevels(BSTNode<T> node) {

		if (node.isEmpty())
			return 0;

		if (!node.isLeaf()) {
			return 0 + countLevels((BSTNode<T>) node.getLeft()) + countLevels((BSTNode<T>) node.getRight());
		}

		return 1 + countLevels((BSTNode<T>) node.getLeft()) + countLevels((BSTNode<T>) node.getRight());

	}

	public int sum() {

		return sum(this.root);

	}

	private int sum(BSTNode<T> node) {

		if (!node.isEmpty()) {
			return (Integer) node.getData() + sum((BSTNode<T>) node.getLeft()) + sum((BSTNode<T>) node.getRight());
		}

		return 0;
	}

	public boolean comparaBst(BSTImpl<T> bstUm, BSTImpl<T> bstDois) {

		return comparaBst(bstUm.root, bstDois.root);

	}

	private boolean comparaBst(BSTNode<T> node1, BSTNode<T> node2) {

		if (node1.isEmpty() && node2.isEmpty()) {
			return true;
		} else if (node1.isEmpty() && !node2.isEmpty()) {
			return false;
		} else if (!node1.isEmpty() && node2.isEmpty()) {
			return false;
		} else if (!node1.isEmpty() && !node2.isEmpty()) {
			if (node1.getData().equals(node2.getData()))
				return comparaBst((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
						&& comparaBst((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return false;

	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}