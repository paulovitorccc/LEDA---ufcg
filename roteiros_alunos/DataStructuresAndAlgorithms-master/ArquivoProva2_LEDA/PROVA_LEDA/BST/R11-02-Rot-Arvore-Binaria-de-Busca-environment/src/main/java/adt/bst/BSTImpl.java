package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	private static final int INITIAL_INDEX = 0;
	private static final int ZERO_DEGREE = 0;
	private static final int ONE_DEGREE = 1;
	private static final int TWO_DEGREE = 2;

	public BSTImpl() {
		this.root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		if (isEmpty()) {
			return -1;
		}

		return height(root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}

		return (1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())));
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null || isEmpty()) {
			return new BSTNode<>();
		}

		return search(element, root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			return new BSTNode<>();
		}

		if (element.equals(node.getData())) {
			return node;
		} else if (element.compareTo(node.getData()) > 0) {
			return search(element, (BSTNode<T>) node.getRight());
		} else {
			return search(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		if (isEmpty()) {
			BSTNode<T> leftNil = new BSTNode<>();
			BSTNode<T> rightNil = new BSTNode<>();

			root.setData(element);

			root.setLeft(leftNil);
			leftNil.setParent(root);

			root.setRight(rightNil);
			rightNil.setParent(root);

		} else {
			insert(element, root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			BSTNode<T> leftNil = new BSTNode<>();
			BSTNode<T> rightNil = new BSTNode<>();

			node.setData(element);

			node.setLeft(leftNil);
			leftNil.setParent(node);

			node.setRight(rightNil);
			rightNil.setParent(node);

		} else if (element.compareTo(node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight());
		} else {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty())
			return null;

		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty())
			return null;

		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		if (!(node.getRight().isEmpty())) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			return sucessor(node);
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if ((node.getParent() != null) && (node.getParent().getRight().equals(node))) {
			node = (BSTNode<T>) node.getParent();

			return sucessor(node);
		} else {
			return (BSTNode<T>) node.getParent();
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		if (!(node.getLeft().isEmpty())) {
			return maximum((BSTNode<T>) node.getLeft());
		} else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if ((node.getParent() != null) && (node.getParent().getLeft().equals(node))) {
			node = (BSTNode<T>) node.getParent();

			return predecessor(node);
		} else {
			return (BSTNode<T>) node.getParent();
		}
	}

	@Override
	public void remove(T element) {
		if (element == null)
			return;

		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return;

		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (node.isEmpty())
			return;

		switch (getDegreeNode(node)) {
			case ZERO_DEGREE:
				removeLeaf(node);
				break;
			case ONE_DEGREE:
				removeNodeOneDegree(node);
				break;
			case TWO_DEGREE:
				removeNodeTwoDegree(node);
				break;
			default:
				break;
		}
	}

	private void removeNodeTwoDegree(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());

		T data = node.getData();

		node.setData(sucessor.getData());
		sucessor.setData(data);

		remove(sucessor);
	}

	private void removeNodeOneDegree(BSTNode<T> node) {
		BSTNode<T> aux = null;

		if (!(node.getLeft().isEmpty())) {
			aux = (BSTNode<T>) node.getLeft();
		} else {
			aux = (BSTNode<T>) node.getRight();
		}

		/* if node is the root */
		if (node.getParent() == null) {
			aux.setParent(null);
			root = aux;

		/* if node not is the root */
		} else {
			aux.setParent(node.getParent());

			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(aux);
			} else {
				node.getParent().setRight(aux);
			}
		}
	}

	private void removeLeaf(BSTNode<T> node) {
		node.setData(null);
	}

	private int getDegreeNode(BSTNode<T> node) {
		if ((node.getLeft().isEmpty()) && (node.getRight().isEmpty())) {
			return ZERO_DEGREE;
		} else if ((!node.getLeft().isEmpty()) && (node.getRight().isEmpty())) {
			return ONE_DEGREE;
		} else if ((node.getLeft().isEmpty()) && (!node.getRight().isEmpty())) {
			return ONE_DEGREE;
		} else {
			return TWO_DEGREE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, root, INITIAL_INDEX);

		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int index) {
		if (node.isEmpty()) {
			return index;
		} else {
			array[index] = node.getData();
			index = index + 1;
			index = preOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = preOrder(array, (BSTNode<T>) node.getRight(), index);

			return index;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];
		order(array, root, INITIAL_INDEX);

		return array;
	}

	private int order(T[] array, BSTNode<T> node, int index) {
		if (node.isEmpty()) {
			return index;
		} else {
			index = order(array, (BSTNode<T>) node.getLeft(), index);
			array[index] = node.getData();
			index = index + 1;
			index = order(array, (BSTNode<T>) node.getRight(), index);

			return index;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];
		postOrder(array, root, INITIAL_INDEX);

		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int index) {
		if (node.isEmpty()) {
			return index;
		} else {
			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = postOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index = index + 1;

			return index;
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
		if (!(node.isEmpty())) { // inductive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}

		return result;
	}
}
