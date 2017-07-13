package adt.bst.extended;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class RightMostHorizontalDistanceBSTImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RightMostBST<T>, HorizontalDistanceBST<T> {

	@Override
	public List<T> elementsAtDistance(int d) {
		List<T> list = new ArrayList<T>();
		return elementsAtDistance(list, root, 0, d);
	}

	private List<T> elementsAtDistance(List<T> list, BTNode<T> node, int distance, int distanceConstante) {
		if (node.isEmpty()) {
			return list;
		} else {
			elementsAtDistance(list, node.getLeft(), distance + 1, distanceConstante);
			if (distance == distanceConstante) {
				list.add(node.getData());
			}
			elementsAtDistance(list, node.getRight(), distance + 1, distanceConstante);
			return list;
		}
	}

	@Override
	public List<T> rightMost() {
		LinkedList<T> list = new LinkedList<T>();
		List<Integer> niveis = new LinkedList<Integer>();
		return rightMost(root, 0, list, niveis);
	}

	private List<T> rightMost(BTNode<T> node, int nivel, List<T> list, List<Integer> niveis) {
		if (node.isEmpty()) {
			return list;
		} else {
			if (!niveis.contains(nivel)) {
				list.add(node.getData());
				niveis.add(nivel);
			}
			rightMost(node.getRight(), nivel + 1, list, niveis);
			rightMost(node.getLeft(), nivel + 1, list, niveis);
			return list;
		}
	}

	@Override
	public int horizontalDistance(T elem) {
		return horizontalDistance(root, elem, 0);
	}

	private int horizontalDistance(BSTNode<T> node, T element, int distance) {
		if (node.isEmpty()) {
			return 0;
		} else if (node.getData().compareTo(element) > 0) {
			return horizontalDistance((BSTNode<T>) node.getLeft(), element, distance - 1);
		} else if (node.getData().compareTo(element) < 0) {
			return horizontalDistance((BSTNode<T>) node.getRight(), element, distance + 1);
		} else {
			return distance;
		}
	}

}
