package adt.btree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
		if (isEmpty())
			return -1;
		return height(this.root);
	}

	private int height(BNode<T> node) {

		if (node.isLeaf())
			return 0;
		return 1 + height(node.getChildren().get(0));

	}

	@Override
	public BNode<T>[] depthLeftOrder() {

		return depthLeftOrder(this.root);

	}

	private BNode<T>[] depthLeftOrder(BNode<T> node) {

		ArrayList<BNode<T>> lista = new ArrayList<>();
		if (node.isEmpty())
			return new BNode[0];
		Deque<BNode<T>> fila = new ArrayDeque<>();
		fila.add(node);
		while (!fila.isEmpty()) {
			BNode<T> atual = fila.removeFirst();
			lista.add(atual);
			if (!atual.isLeaf()) {
				for (int i = 0; i < atual.getChildren().size(); i++) {
					fila.add(atual.getChildren().get(i));
				}
			}
		}
		BNode<T>[] result = new BNode[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			result[i] = lista.get(i);
		}
		return result;
	}

	@Override
	public int size() {

		if (isEmpty())
			return 0;

		return size(this.root);
	}

	private int size(BNode<T> node) {

		int tamanho = node.getElements().size();

		if (!node.getChildren().isEmpty()) {

			for (BNode<T> child : node.getChildren()) {

				tamanho += size(child);
			}
		}

		return tamanho;
	}

	@Override
	public BNodePosition<T> search(T element) {

		return search(this.root, element);

	}

	private BNodePosition<T> search(BNode<T> node, T element) {

		int index = 0;
		while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
			index += 1;
		}

		if (index <= node.getElements().size() && element.equals(node.getElementAt(index))) {
			return new BNodePosition<>(node, index);
		}
		if (node.isLeaf()) {
			return new BNodePosition<>();
		}

		return search(node.getChildren().get(index), element);

	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		if (root.isEmpty()) {
			BNode<T> node = new BNode<>(order);
			node.addElement(element);
			root = node;
		} else {
			insert(root, element);
		}
	}

	private void insert(BNode<T> node, T element) {

		if (node.isLeaf()) {
			if (node.isFull()) {
				node.addElement(element);
				split(node);
			} else {
				node.addElement(element);
			}

		} else {
			int index = 0;

			while ((index != node.size()) && (node.getElementAt(index).compareTo(element) < 0)) {
				index++;
			}

			if (index == node.size()) {
				insert(node.getChildren().get(node.size()), element);
			} else {
				insert(node.getChildren().get(index), element);
			}
		}
	}

	private void split(BNode<T> node) {

		int meio = (node.size()) / 2;

		T elementoMeio = node.getElementAt(meio);

		BNode<T> rightNode = createRightNode(node);
		node.removeElement(meio);

		for (int i = 0; i < rightNode.size(); i++) {
			node.removeElement(rightNode.getElementAt(i));
		}

		for (int i = node.size() + 1; i < node.getChildren().size(); i++) {
			rightNode.getChildren().add(node.getChildren().get(i));
			node.getChildren().get(i).setParent(rightNode);
		}

		for (int i = 0; i < node.getChildren().size() - 1; i++) {
			node.getChildren().remove(rightNode.getChildren().get(i));
		}

		BNode<T> parent = node.getParent();

		if (parent == null) {
			BNode<T> novoRoot = new BNode<>(order);
			root = novoRoot;

			root.getChildren().add(node);

			parent = root;
		}

		node.setParent(parent);
		rightNode.setParent(parent);

		if (parent.isFull()) {
			parent.addElement(elementoMeio);

			int indexMiddle = parent.getElements().indexOf(elementoMeio);
			parent.getChildren().add(indexMiddle + 1, rightNode);

			split(parent);
		} else {
			parent.addElement(elementoMeio);

			int indexMiddle = parent.getElements().indexOf(elementoMeio);
			parent.getChildren().add(indexMiddle + 1, rightNode);
		}

	}

	private BNode<T> createRightNode(BNode<T> node) {

		BNode<T> rightNode = new BNode<>(order);

		int indexControl = 0;

		if (order % 2 == 0) {
			indexControl = 1;
		}

		for (int i = (node.size() / 2) + indexControl; i < node.size(); i++) {
			rightNode.addElement(node.getElementAt(i));
		}

		return rightNode;
	}

	// METODOS ALTERNATIVOS
	public int sumKey() {

		if (isEmpty())
			return 0;

		return sumKey(this.root);
	}

	private int sumKey(BNode<T> node) {

		int soma = 0;
		for (T element : node.getElements()) {
			soma += (Integer) element;
		}

		if (!node.getChildren().isEmpty()) {

			for (BNode<T> child : node.getChildren()) {

				soma += sumKey(child);
			}
		}

		return soma;
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
