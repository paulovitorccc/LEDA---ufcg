package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	protected int calculateBalance(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return 0;
		}
		BSTNode<T> nodeLeft = (BSTNode<T>) node.getLeft();
		BSTNode<T> nodeRight = (BSTNode<T>) node.getRight();
		return height(nodeRight) - height(nodeLeft);
	}

	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> newNodeLeft = (BSTNode<T>) node.getLeft();
		BSTNode<T> newNodeRight = (BSTNode<T>) node.getRight();

		int balance = calculateBalance(node);

		if (balance < -1) {
			if (calculateBalance(newNodeLeft) > 0) {
				leftRotation(newNodeLeft);
			}
			rightRotation(node);
		} else if (balance > 1) {
			if (calculateBalance(newNodeRight) < 0) {
				rightRotation(newNodeRight);
			}
			leftRotation(node);
		}
	}

	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}

	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
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
		rebalanceUp((BSTNode<T>) node.getParent());
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
		rebalanceUp((BSTNode<T>) node.getParent());
	}

	private void removeLeaf(BSTNode<T> node) {
		if (node.getParent().getRight().getData() == node.getData()) {
			node.getParent().setRight(new BSTNode<T>());
		} else {
			node.getParent().setLeft(new BSTNode<T>());
		}
		rebalanceUp((BSTNode<T>) node.getParent());
	}
}
