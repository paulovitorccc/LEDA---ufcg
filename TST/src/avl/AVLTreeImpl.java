package avl;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	private Util util;

	public AVLTreeImpl() {
		util = new Util();
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
		BSTNode<T> node = search(element);
		rebalanceUp(node);
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
				rebalanceUp(node);
			} else if (numberOfChildren(node) == ONE_CHILD) {
				removeNodeWithOneChild(node);
				rebalanceUp(node);
			} else {
				removeNodeWithTwoChildren(node);
				rebalanceUp(node);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result;
		if (node == null || node.isEmpty()) {
			result = 0;
		} else {
			result = super.height(((BSTNode<T>) node.getLeft())) - super.height(((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balaceFactor;
		balaceFactor = calculateBalance(node);
		if (balaceFactor > 1) { // raiz pesa para a esquerda
			BSTNode<T> nodeSon = (BSTNode<T>) node.getLeft();
			if (calculateBalance(nodeSon) < 0) { // filho pesa para a direita
				leftRotation(nodeSon);
			} 
			rightRotation(node);
		} else if (balaceFactor < -1) { // raiz pesa para a direita
			BSTNode<T> nodeSon = (BSTNode<T>) node.getRight();
			if (calculateBalance(nodeSon) > 0) { // filho pesa para a esquerda
				rightRotation(nodeSon);
			}
			leftRotation(node);
		} 
	}

	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> nodeAux = util.leftRotation(node);
		if (nodeAux.getParent().isEmpty()) {
			root = nodeAux;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> nodeAux = util.rightRotation(node);
		if (nodeAux.getParent().isEmpty()) {
			root = nodeAux;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (!parent.isEmpty()) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
