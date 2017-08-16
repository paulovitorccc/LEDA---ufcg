package questoes.arvorepv;


public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> son = (BSTNode<T>) node.getRight();
		if (!parent.isEmpty()) {
			if (parent.getLeft().equals(node)) {
				parent.setLeft(son);
			} else {
				parent.setRight(son);
			}
		}
		node.setParent(son);
		BSTNode<T> sonOfSonLeft = (BSTNode<T>) son.getLeft();
		node.setRight(sonOfSonLeft);
		if (!sonOfSonLeft.isEmpty()) {
			sonOfSonLeft.setParent(node);
		}
		son.setParent(parent);
		son.setLeft(node);
		return son;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> son = (BSTNode<T>) node.getLeft();
		if (!parent.isEmpty()) {
			if (parent.getLeft().equals(node)) {
				parent.setLeft(son);
			} else {
				parent.setRight(son);
			}
		}
		node.setParent(son);
		BSTNode<T> sonOfSonRight = (BSTNode<T>) son.getRight();
		node.setLeft(sonOfSonRight);
		if (!sonOfSonRight.isEmpty()) {
			sonOfSonRight.setParent(node);
		}
		son.setParent(parent);
		son.setRight(node);
		return son;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
