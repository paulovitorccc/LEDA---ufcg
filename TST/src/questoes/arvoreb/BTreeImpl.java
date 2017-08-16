package questoes.arvoreb;

import java.util.LinkedList;

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
		if (root.isEmpty()) {
			return -1;
		} else {
			return height(this.root);
		}
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
		LinkedList<BNode<T>> list = new LinkedList<BNode<T>>();
		depthLeftOrder(list, root);
		BNode<T>[] arrayResult = new BNode[list.size()];
		for (int e = 0; e < list.size(); e++) {
			arrayResult[e] = list.get(e);
		}
		return arrayResult;
	}

	private void depthLeftOrder(LinkedList<BNode<T>> array, BNode<T> node) {
		array.add(node);
		if (!node.getChildren().isEmpty()) {
			for (BNode<T> bNode : node.getChildren()) {
				depthLeftOrder(array, bNode);
			}
		}
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
		if (element != null) {
			if (root.isEmpty()) {
				BNode<T> newNode = new BNode<>(this.order);
				newNode.addElement(element);
				root = newNode;
			} else {
				insert(root, element);
			}
		}
	}

	private void insert(BNode<T> node, T element) {
		if (node.isLeaf()) {
			if (!node.isFull()) {
				node.addElement(element);
			} else {
				node.addElement(element);
				this.split(node);
			}
		} else {
			int index = 0;
			while (index < node.size() && (node.getElementAt(index)).compareTo(element) < 0) {
				index = index + 1;
			}
			if (index == node.size()) {
				insert(node.getChildren().get(node.size()), element);
			} else {
				if ((node.getElementAt(index) != element)) {
					insert(node.getChildren().get(index), element);
				}
			}
		}
	}

	private void split(BNode<T> node) {
		int mediana = node.size() / 2;

		BNode<T> menores = new BNode<>(this.order);
		for (int i = 0; i < mediana; i++) {
			menores.addElement(node.getElementAt(i));
		}

		BNode<T> maiores = new BNode<>(this.order);
		for (int j = mediana + 1; j < node.size(); j++) {
			maiores.addElement(node.getElementAt(j));
		}

		if (!node.getChildren().isEmpty()) {
			LinkedList<BNode<T>> filhosMenores = new LinkedList<>();
			for (int l = 0; l <= mediana; l++) {
				filhosMenores.add(node.getChildren().get(l));
			}
			LinkedList<BNode<T>> filhosMaiores = new LinkedList<>();
			for (int m = mediana + 1; m < node.getChildren().size(); m++) {
				filhosMaiores.add(node.getChildren().get(m));
			}
			menores.setChildren(filhosMenores);
			maiores.setChildren(filhosMaiores);
		}

		LinkedList<BNode<T>> filhos = new LinkedList<>();
		filhos.add(menores);
		filhos.add(maiores);
		node.setChildren(filhos);
		promote(node);

	}

	private void promote(BNode<T> node) {
		if (root == node) {
			BNode<T> newRoot = new BNode<>(this.order);
			T element = node.getElementAt(node.size() / 2);
			newRoot.addElement(element);
			newRoot.addChild(0, node.getChildren().get(0));
			newRoot.addChild(1, node.getChildren().get(1));
			root = newRoot;
		} else {
			T element = node.getElementAt(node.size() / 2);
			BNode<T> parent = node.getParent();
			parent.elements.add(element);
			int indexElement = elementKey(parent, element);
			parent.addChild(indexElement, node.getChildren().get(0));
			parent.addChild(indexElement + 1, node.getChildren().get(1));

			parent.getChildren().remove(node);

			if (parent.getElements().size() == this.order) {
				split(parent);
			}
		}
	}

	private int elementKey(BNode<T> node, T element) {
		int index = 0;
		while (index < node.size() && node.getElementAt(index).compareTo(element) < 0) {
			index = index + 1;
		}
		if (node.getElementAt(index) != element) {
			index = -1;
		}
		return index;
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

	// questoes lista

	public int somaDeChaves() {
		return somaDeChaves(root);
	}

	private int somaDeChaves(BNode<T> node) {
		int soma = 0;
		if (!node.getElements().isEmpty()) {
			for (T el : node.getElements()) {
				soma += (int) el;
			}
		}
		if (!node.getChildren().isEmpty()) {
			for (BNode<T> n : node.getChildren()) {
				soma += somaDeChaves(n);
			}
		}
		return soma;
	}
}
