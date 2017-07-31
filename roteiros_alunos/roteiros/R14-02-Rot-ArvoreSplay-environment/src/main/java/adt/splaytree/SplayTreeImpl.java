package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty())
			splay((BSTNode<T>) node.getParent());
		else
			splay(node);
		return node;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> added = insert(this.root, element);
			splay(added);
		}
	}

	@Override
	public void remove(T element) {

		BSTNode<T> nodeRemove = super.search(element);
		BSTNode<T> nodeSplay = (BSTNode<T>) nodeRemove.getParent();
		if ((element != null) && (nodeRemove != null))
			super.remove(nodeRemove);
		splay(nodeSplay);
	}

	private void splay(BSTNode<T> node) {
		if (node != null && node.getParent() == null) {
			this.root = node;
		} else if (node != null) {
			boolean hasGrandpa = node.getParent().getParent() != null;
			if (isLeftChild(node)) {
				boolean isParentLeft = hasGrandpa && isLeftChild((BSTNode<T>) node.getParent());
				if (isParentLeft) {
					Util.rightRotation((BSTNode<T>) node.getParent().getParent());
				}
				Util.rightRotation((BSTNode<T>) node.getParent());
				if (hasGrandpa && !isParentLeft) {
					Util.leftRotation((BSTNode<T>) node.getParent());
				}
			} else {
				boolean isParentRightChild = hasGrandpa && !isLeftChild((BSTNode<T>) node.getParent());
				if (isParentRightChild) {
					Util.leftRotation((BSTNode<T>) node.getParent().getParent());
				}
				Util.leftRotation((BSTNode<T>) node.getParent());
				if (hasGrandpa && !isParentRightChild) {
					Util.rightRotation((BSTNode<T>) node.getParent());
				}
			}
			splay(node);
		}
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}
}