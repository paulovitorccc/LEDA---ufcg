package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight(this.root);
	}

	private int blackHeight(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			if (((RBNode<T>) node).getColour() == Colour.BLACK) {
				return 1 + blackHeight((BSTNode<T>) node.getLeft());
			} else {
				return 0 + blackHeight((BSTNode<T>) node.getLeft());
			}
		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes(this.root);
	}

	private boolean verifyChildrenOfRedNodes(BSTNode<T> node) {
		boolean invariante = true;
		if (!node.isEmpty()) {
			if (!node.getParent().isEmpty() && ((RBNode<T>) node).getColour() == Colour.RED
					&& (((RBNode<T>) node.getParent()).getColour() == Colour.RED)) {
				invariante = false;
			}
			if (invariante) {
				invariante = verifyChildrenOfRedNodes((BSTNode<T>) node.getLeft());
			}
			if (invariante) {
				invariante = verifyChildrenOfRedNodes((BSTNode<T>) node.getRight());
			}
		}
		return invariante;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		boolean status = verifyBlackHeight(this.root);
		if(status == false){
			throw new RuntimeException();
		} else {
			return status;
		}
	}

	private boolean verifyBlackHeight(BSTNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else {
			int leftBlackHeight = blackHeight((BSTNode<T>) node.getLeft());
			int rightBlackHeight = blackHeight((BSTNode<T>) node.getRight());
			if (leftBlackHeight == rightBlackHeight) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void insert(T value) {
		if (this.root.isEmpty()) {
			root.setData(value);
			root.setParent(new RBNode<T>());
			root.setLeft(new RBNode<T>());
			root.setRight(new RBNode<T>());
			root.getLeft().setParent(root);
			root.getRight().setParent(root);
			((RBNode<T>) root).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) root);
		} else {
			insert((RBNode<T>) root, value);
		}
	}

	private void insert(RBNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else if (node.getData().compareTo(element) < 0) {
			insert((RBNode<T>) node.getRight(), element);
		} else if (node.getData().compareTo(element) > 0) {
			insert((RBNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] array = (RBNode<T>[]) new RBNode[this.size()];
		rbPreOrder(array, root, 0);
		return array;
	}

	private int rbPreOrder(RBNode<T>[] array, BSTNode<T> node, int cont) {
		if (node.isEmpty()) {
			return cont;
		} else {
			array[cont] = (RBNode<T>) node;
			cont = cont + 1;
			cont = rbPreOrder(array, (BSTNode<T>) node.getLeft(), cont);
			cont = rbPreOrder(array, (BSTNode<T>) node.getRight(), cont);
			return cont;
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (!(parent.getColour() == Colour.BLACK)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandfather = (RBNode<T>) parent.getParent();
		RBNode<T> uncle;
		if (grandfather.getLeft().equals(parent)) {
			uncle = (RBNode<T>) grandfather.getRight();
		} else {
			uncle = (RBNode<T>) grandfather.getLeft();
		}
		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandfather.setColour(Colour.RED);
			fixUpCase1(grandfather);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandfather = (RBNode<T>) parent.getParent();
		RBNode<T> next = node;
		if (parent.getRight().equals(node) && grandfather.getLeft().equals(parent)) {
			Util.leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (parent.getLeft().equals(node) && grandfather.getRight().equals(parent)) {
			Util.rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandfather = (RBNode<T>) parent.getParent();
		parent.setColour(Colour.BLACK);
		grandfather.setColour(Colour.RED);
		if (parent.getLeft().equals(node)) {
			Util.rightRotation(grandfather);
		} else {
			Util.leftRotation(grandfather);
		}
		if (parent.getParent().isEmpty()) {
			root = parent;
		}
	}
}
