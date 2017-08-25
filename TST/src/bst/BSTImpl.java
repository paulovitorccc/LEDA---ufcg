package bst;

import java.security.DigestInputStream;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	private static final String LEFT_SON = "left";
	private static final String RIGHT_SON = "right";
	private static final int ZERO_CHILDREN = 0;
	private static final int ONE_CHILD = 1;
	private static final int TWO_CHILDREN = 2;

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
		return height(root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			int heightLeft = height((BSTNode<T>) node.getLeft());
			int heightRight = height((BSTNode<T>) node.getRight());
			int maior;
			if (heightLeft > heightRight) {
				maior = heightLeft;
			} else {
				maior = heightRight;
			}
			return 1 + maior;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return new BSTNode<T>();
		} else if (node.getData().compareTo(element) < 0) {
			return search(((BSTNode<T>) node.getRight()), element);
		} else if (node.getData().compareTo(element) > 0) {
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
		} else if (node.getData().compareTo(element) < 0) {
			insert((BSTNode<T>) node.getRight(), element);
		} else if (node.getData().compareTo(element) > 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if (!root.isEmpty()) {
			result = this.root;
			while (!result.getRight().isEmpty()) {
				result = (BSTNode<T>) result.getRight();
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if (!root.isEmpty()) {
			result = this.root;
			while (!result.getLeft().isEmpty()) {
				result = (BSTNode<T>) result.getLeft();
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = search(element);

		if (node.isEmpty()) {
			result = null;
		} else if (!node.getRight().isEmpty()) {
			BSTNode<T> auxCase1 = (BSTNode<T>) node.getRight();
			while (!auxCase1.getLeft().isEmpty()) {
				auxCase1 = (BSTNode<T>) auxCase1.getLeft();
			}
			result = auxCase1;
		} else {
			BSTNode<T> auxCase2 = node;
			while (!auxCase2.getParent().isEmpty() && auxCase2.getParent().getRight().equals(auxCase2)) {
				auxCase2 = (BSTNode<T>) auxCase2.getParent();
			}
			if (auxCase2.getParent().isEmpty()) {
				result = null;
			} else {
				result = (BSTNode<T>) auxCase2.getParent();
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> node = search(element);

		if (node.isEmpty()) {
			result = null;
		} else if (!node.getLeft().isEmpty()) {
			BSTNode<T> auxCase1 = (BSTNode<T>) node.getLeft();
			while (!auxCase1.getRight().isEmpty()) {
				auxCase1 = (BSTNode<T>) auxCase1.getRight();
			}
			result = auxCase1;
		} else {
			BSTNode<T> auxCase2 = node;
			while (!auxCase2.getParent().isEmpty() && auxCase2.getParent().getLeft().equals(auxCase2)) {
				auxCase2 = (BSTNode<T>) auxCase2.getParent();
			}
			if (auxCase2.getParent().isEmpty()) {
				result = null;
			} else {
				result = (BSTNode<T>) auxCase2.getParent();
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (numberOfChildren(node) == ZERO_CHILDREN) {
				removeNodeWithZeroChildren(node);
			} else if (numberOfChildren(node) == ONE_CHILD) {
				removeNodeWithOneChild(node);
			} else {
				removeNodeWithTwoChildren(node);
			}
		}
	}

	private int numberOfChildren(BSTNode<T> node) {
		int result;
		if (((BSTNode<T>) node.getLeft()).isEmpty() && ((BSTNode<T>) node.getRight()).isEmpty()) {
			result = 0;
		} else if (((BSTNode<T>) node.getLeft()).isEmpty() && !(((BSTNode<T>) node.getRight()).isEmpty())) {
			result = 1;
		} else if (!(((BSTNode<T>) node.getLeft()).isEmpty()) && ((BSTNode<T>) node.getRight()).isEmpty()) {
			result = 1;
		} else {
			result = 2;
		}
		return result;
	}

	private void removeNodeWithZeroChildren(BSTNode<T> node) {
		if (node.getParent().equals(new BSTNode<T>())) {
			node.setData(null);
		} else {
			if (isTheChildLeftOrRight(node).equals(LEFT_SON)) {
				node.getParent().setLeft(new BSTNode<T>());
			} else {
				node.getParent().setRight(new BSTNode<T>());
			}
		}
	}

	private void removeNodeWithOneChild(BSTNode<T> node) {
		if (node.getParent().equals(new BSTNode<T>())) {
			if (node.getLeft().isEmpty()) {
				BSTNode<T> sonOfNode = (BSTNode<T>) node.getRight();
				root = sonOfNode;
				root.setParent(new BSTNode<T>());
			} else {
				BSTNode<T> sonOfNode = (BSTNode<T>) node.getLeft();
				root = sonOfNode;
				root.setParent(new BSTNode<T>());
			}
		} else {
			if (isTheChildLeftOrRight(node).equals(LEFT_SON)) {
				if (node.getLeft().isEmpty()) {
					BSTNode<T> sonOfNode = (BSTNode<T>) node.getRight();
					sonOfNode.setParent(node.getParent());
					node.getParent().setLeft(sonOfNode);
				} else {
					BSTNode<T> sonOfNode = (BSTNode<T>) node.getLeft();
					sonOfNode.setParent(node.getParent());
					node.getParent().setLeft(sonOfNode);
				}
			} else {
				if (node.getLeft().isEmpty()) {
					BSTNode<T> sonOfNode = (BSTNode<T>) node.getRight();
					sonOfNode.setParent(node.getParent());
					node.getParent().setRight(sonOfNode);
				} else {
					BSTNode<T> sonOfNode = (BSTNode<T>) node.getLeft();
					sonOfNode.setParent(node.getParent());
					node.getParent().setRight(sonOfNode);
				}
			}
		}
	}

	private void removeNodeWithTwoChildren(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		node.setData(sucessor.getData());
		remove(sucessor);
	}

	private String isTheChildLeftOrRight(BSTNode<T> node) {
		String result;
		if (node.getParent() == null) {
			result = null;
		} else {
			if (node.getParent().getLeft().equals(node)) {
				result = "left";
			} else {
				result = "right";
			}
		}
		return result;
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(root, array, 0);
		return array;
	}

	private int preOrder(BSTNode<T> node, T[] array, int cont) {
		if (node.isEmpty()) {
			return cont;
		} else {
			array[cont] = node.getData();
			cont = cont + 1;
			cont = preOrder((BSTNode<T>) node.getLeft(), array, cont);
			cont = preOrder((BSTNode<T>) node.getRight(), array, cont);
			return cont;
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(root, array, 0);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int cont) {
		if (node.isEmpty()) {
			return cont;
		} else {
			cont = order((BSTNode<T>) node.getLeft(), array, cont);
			array[cont] = node.getData();
			cont = cont + 1;
			cont = order((BSTNode<T>) node.getRight(), array, cont);
			return cont;
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		posOrder(root, array, 0);
		return array;
	}

	private int posOrder(BSTNode<T> node, T[] array, int cont) {
		if (node.isEmpty()) {
			return cont;
		} else {
			cont = posOrder((BSTNode<T>) node.getLeft(), array, cont);
			cont = posOrder((BSTNode<T>) node.getRight(), array, cont);
			array[cont] = node.getData();
			cont = cont + 1;
			return cont;
		}
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

	/// LISTA DE EXERCICIOS

	public T[] descendingOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		descendingOrder(array, root, 0);
		return array;
	}

	private int descendingOrder(T[] array, BSTNode<T> node, int cont) {
		if (!node.isEmpty()) {
			cont = descendingOrder(array, (BSTNode<T>) node.getRight(), cont);
			array[cont] = node.getData();
			cont++;
			cont = descendingOrder(array, (BSTNode<T>) node.getLeft(), cont);
		}
		return cont;
	}

	public boolean equals(BSTImpl<T> bst) {
		return equals(root, bst.getRoot());
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = true;
		if (node1.isEmpty() || node2.isEmpty()) {
			result = node1.equals(node2);
		} else {
			result = node1.equals(node2);
			if (result) {
				result = equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
			}
			if (result) {
				result = equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			}
		}
		return result;
	}

	public int qtdFolhas() {
		return qtdFolhas(root);
	}

	private int qtdFolhas(BSTNode<T> node) {
		int sum = 0;
		if (!node.isEmpty() && node.isLeaf()) {
			sum++;
		} else {
			if (!node.getLeft().isEmpty()) {
				sum += qtdFolhas((BSTNode<T>) node.getLeft());
			}
			if (!node.getRight().isEmpty()) {
				sum += qtdFolhas((BSTNode<T>) node.getRight());
			}
		}
		return sum;
	}

	// questoes lista

	public BSTNode<T> minimumRecursivo() {
		BSTNode<T> result = null;
		if (!root.isEmpty()) {
			result = minimumRecursivo(root);
		}
		return result;
	}

	private BSTNode<T> minimumRecursivo(BSTNode<T> node) {
		BSTNode<T> result;
		if (node.getLeft().isEmpty()) {
			result = node;
		} else {
			result = minimumRecursivo((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	public BSTNode<T> sucessorRecusivo(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			node = null;
		} else if (!node.getRight().isEmpty()) {
			node = sucessorRight((BSTNode<T>) node.getRight());
		} else {
			node = sucessorNotRight(node);
			if (node.isEmpty()) {
				node = null;
			}
		}
		return node;
	}

	private BSTNode<T> sucessorRight(BSTNode<T> node) {
		BSTNode<T> result;
		if (node.getLeft().isEmpty()) {
			result = node;
		} else {
			result = sucessorRight((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	private BSTNode<T> sucessorNotRight(BSTNode<T> node) {
		BSTNode<T> result;
		if (node.getParent().isEmpty() || !node.getParent().getRight().equals(node)) {
			result = (BSTNode<T>) node.getParent();
		} else {
			result = sucessorNotRight((BSTNode<T>) node.getParent());
		}
		return result;
	}

	// O no d eh descendente do no p???
	public boolean isDecendent(T d, T p) {
		boolean result;
		BSTNode<T> node = search(p);
		if (node.isEmpty()) {
			result = false;
		} else {
			result = isDecendent(node, d);
		}
		return result;
	}

	private boolean isDecendent(BSTNode<T> node, T element) {
		boolean result = false;
		if (node.isEmpty() || node.getData().equals(element)) {
			if (!node.isEmpty()) {
				result = true;
			}
		} else {
			result = isDecendent((BSTNode<T>) node.getLeft(), element);
			if (!result) {
				result = isDecendent((BSTNode<T>) node.getRight(), element);
			}
		}
		return result;
	}

	// distancia do no x1 ate seu descendente x2
	public int distance(T x1, T x2) {
		int result = -1;
		BSTNode<T> node = search(x1);
		if (!node.isEmpty() && isDecendent(x2, x1)) {
			result = distance(node, x2);
		}
		return result;
	}

	private int distance(BSTNode<T> node, T x2) {
		int result = 0;
		if (node.isEmpty()) {
			result += 0;
		} else {
			if (!node.getLeft().isEmpty() && x2.compareTo(node.getData()) < 0) {
				result += 1 + distance((BSTNode<T>) node.getLeft(), x2);
			} else if (!node.getRight().isEmpty() && x2.compareTo(node.getData()) > 0) {
				result += 1 + distance((BSTNode<T>) node.getRight(), x2);
			}
		}
		return result;
	}

}
