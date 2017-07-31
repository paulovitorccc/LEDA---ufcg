package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {

		if (this.isEmpty())
			return -1;

		return blackHeight(this.root);

	}

	private int blackHeight(BSTNode<T> node) {

		if (node.isEmpty())
			return 0;

		if (((RBNode<T>) node).getColour() == Colour.BLACK) {
			return 1 + blackHeight((BSTNode<T>) node.getLeft());
		}
		return 0 + blackHeight((BSTNode<T>) node.getLeft());

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

		if (node.isEmpty())
			return true;

		if (((RBNode<T>) node).getColour() == Colour.RED) {
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.RED
					|| ((RBNode<T>) node.getRight()).getColour() == Colour.RED)
				return false;
			else {
				return verifyChildrenOfRedNodes((BSTNode<T>) node.getLeft())
						&& verifyChildrenOfRedNodes((BSTNode<T>) node.getRight());
			}
		}
		return verifyChildrenOfRedNodes((BSTNode<T>) node.getLeft())
				&& verifyChildrenOfRedNodes((BSTNode<T>) node.getRight());

	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {

		return verifyBlackHeight(this.root);

	}

	private boolean verifyBlackHeight(BSTNode<T> node) {

		if (node.isEmpty())
			return true;

		int leftHeight = blackHeight((BSTNode<T>) node.getLeft());
		int rightHeight = blackHeight((BSTNode<T>) node.getRight());

		if (leftHeight == rightHeight)
			return true;
		return false;

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
		} else {
			if (node.getData().compareTo(element) > 0) {
				insert((RBNode<T>) node.getLeft(), element);
			} else {
				insert((RBNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public void insert(T value) {
		if(value == null) return;
		
		if (this.root.isEmpty()) {
			this.root.setData(value);
			this.root.setLeft(new RBNode<T>());
			this.root.setRight(new RBNode<T>());
			this.root.getLeft().setParent(this.root);
			this.root.getRight().setParent(this.root);
			((RBNode<T>) this.root).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) this.root);
		} else {
			insert((RBNode<T>) this.root, value);
		}

	}

	@Override
	public RBNode<T>[] rbPreOrder() {

		ArrayList<RBNode<T>> list = new ArrayList<>();
		rbPreOrder(this.root, list);
		return list.toArray(new RBNode[list.size()]);

	}

	private void rbPreOrder(BSTNode<T> node, ArrayList<RBNode<T>> lista) {

		if (!node.isEmpty()) {
			lista.add((RBNode<T>) node);
			rbPreOrder((BSTNode<T>) node.getLeft(), lista);
			rbPreOrder((BSTNode<T>) node.getRight(), lista);
		}

	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(this.root))
			node.setColour(Colour.BLACK);
		else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {

		if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {
			return;
		} else {
			fixUpCase3(node);
		}

	}

	protected void fixUpCase3(RBNode<T> node) {

		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = grandParent.getLeft().equals(parent) ? (RBNode<T>) grandParent.getRight()
				: (RBNode<T>) grandParent.getLeft();
		if (uncle.getColour() == Colour.RED) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			fixUpCase1(grandParent);
		} else {
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {

		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		if (parent.getRight().equals(node) && grandParent.getLeft().equals(parent)) {
			Util.leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (parent.getLeft().equals(node) && grandParent.getRight().equals(parent)) {
			Util.rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		} else {
			fixUpCase5(next);
		}

	}

	protected void fixUpCase5(RBNode<T> node) {

		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);
		if (parent.getLeft().equals(node)) {
			Util.rightRotation(grandParent);
		} else {
			Util.leftRotation(grandParent);
		}

	}
}
