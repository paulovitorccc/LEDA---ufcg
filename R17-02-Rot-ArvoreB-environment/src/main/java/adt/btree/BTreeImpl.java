package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int height = 0;
		if (node.isLeaf()) {
			height = height + 0;
		} else {
			height = 1 + height(node.getChildren().get(0));
		}
		return height;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] array = new BNode[size()];
		depthLeftOrder(array, root, 0);
		return array;
	}

	private int depthLeftOrder(BNode<T>[] array, BNode<T> node, int cont) {
		array[cont] = node;
		cont++;
		if (!node.getChildren().isEmpty()) {
			for (BNode<T> bNode : node.getChildren()) {
				cont = depthLeftOrder(array, bNode, cont);
			}
		}
		return cont;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BNode<T> node) {
		int size = node.getElements().size();
		if (!node.getChildren().isEmpty()) {
			for (BNode<T> children : node.getChildren()) {
				size = size + size(children);
			}
		}
		return size;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(root, element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		BNodePosition<T> result = new BNodePosition<>();
		int index = 0;
		while (index < node.getElements().size() && (element).compareTo(node.getElementAt(index)) < 0) {
			index = index + 1;
		}
		if (index < node.getElements().size() && node.getElementAt(index).equals(element)) {
			result = new BNodePosition<>(node, index);
		} else if (node.isLeaf()) {
			result = new BNodePosition<>();
		} else {
			result = search(node.getChildren().get(index), element);
		}
		return result;
	}

	@Override
	public void insert(T element) {
		int index = 0;
		while (index < root.getElements().size() && (element).compareTo(root.getElementAt(index)) < 0) {
			index = index + 1;
		}
		
	}

	private void split(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
