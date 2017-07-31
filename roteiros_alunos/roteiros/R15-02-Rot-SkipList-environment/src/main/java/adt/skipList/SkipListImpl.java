package adt.skipList;

import java.util.ArrayList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {

		SkipListNode[] novoNode = new SkipListNode[this.maxHeight];
		SkipListNode<T> node = this.root;

		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (node.getForward(i).getValue() != null && node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			novoNode[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			node.setValue(newValue);
		} else {
			if (height >= this.maxHeight) {
				for (int i = this.maxHeight; i > height; i--) {
					novoNode[i] = this.root;
				}
				maxHeight = height;
			}
			node = new SkipListNode<T>(key, height, newValue);
			for (int j = 0; j < height; j++) {
				if (novoNode[j] != null) {
					node.forward[j] = novoNode[j].getForward(j);
					novoNode[j].forward[j] = node;
				}
			}
		}

	}

	@Override
	public void remove(int key) {

		SkipListNode[] novoNode = new SkipListNode[this.maxHeight];
		SkipListNode<T> node = this.root;

		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (node.getForward(i).getValue() != null && node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			novoNode[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			for (int i = 0; i < this.maxHeight; i++) {
				if (novoNode[i].forward[i] != node) {
					break;
				}
				novoNode[i].forward[i] = node.forward[i];

			}
		}

	}

	@Override
	public int height() {

		SkipListNode<T> node = this.root;
		int altura = 0;
		while (!node.getForward(0).equals(NIL)) {
			if (node.getForward(0).height() > altura) {
				altura = node.getForward(0).height();
			}
			node = node.getForward(0);
		}
		return altura;

	}

	@Override
	public SkipListNode<T> search(int key) {

		SkipListNode<T> node = this.root;

		for (int i = node.height() - 1; i >= 0; i--) {
			while (node.getForward(i).getValue() != null && node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			return node;
		} else {
			return null;
		}
	}

	@Override
	public int size() {

		return size(this.root) - 1;

	}

	private int size(SkipListNode<T> node) {

		if (node.equals(this.NIL))
			return 0;

		return 1 + size(node.getForward(0));

	}

	@Override
	public SkipListNode<T>[] toArray() {

		SkipListNode[] novoNode = new SkipListNode[size() + 2];
		SkipListNode<T> node = this.root;
		for (int j = 0; j < novoNode.length; j++) {
			novoNode[j] = node;
			node = node.forward[0];
		}
		return novoNode;

	}

	// METODOS ALTERNATIVOS

	
	//QUESTAO 1
	public SkipListNode<T> searchRecursive(int key) {

		return searchRecursive(this.root, key, this.maxHeight - 1);

	}

	private SkipListNode<T> searchRecursive(SkipListNode<T> node, int key, int control) {

		if (control < 0)
			return null;

		if (node.getForward(control).getKey() > key) {
			return searchRecursive(node, key, control - 1);
		} else if (node.getForward(control).getKey() < key) {
			return searchRecursive(node.getForward(control), key, control);
		}
		return node.getForward(control);

	}
	//QUESTAO 2
	public T[] getNodesAtHeight(int height) {

		ArrayList<T> list = new ArrayList<>();
		getNodesAtHeight(height, this.root.getForward(0), list);
		return (T[]) list.toArray(new Comparable[0]);

	}

	private void getNodesAtHeight(int height, SkipListNode<T> node, ArrayList<T> list) {

		if (node.getValue() == null)
			return;
		if (node.height() == height)
			list.add(node.getValue());
		getNodesAtHeight(height, node.getForward(0), list);

	}

	//QUESTAO 3
	public boolean greatRelation() {
		return greatRelation(root, 0, this.size());
	}

	private boolean greatRelation(SkipListNode<T> node, int height, int sizeAnterior) {
		
		if(height == this.maxHeight) return true;
		
		int sizeNivelAtual = 0;
		while (node.getForward(height + 1) != NIL) {
			node = node.getForward(height + 1);
			sizeNivelAtual++;
		}
		
		if(sizeNivelAtual == 0) return true;

		if (sizeNivelAtual != (sizeAnterior / 2)) {
			return false;
		}
		return greatRelation(root, height + 1, sizeNivelAtual);
		
	}

	public int heightRecursive() {

		return heightRecursive(this.maxHeight - 1);

	}

	private int heightRecursive(int level) {

		if (level < 0)
			return -1;

		if (!this.root.getForward(level).equals(NIL))
			return level + 1;

		return heightRecursive(level - 1);

	}
	
	public SkipListNode<T>[] allElements(){
		
		SkipListNode<T>[] array = new SkipListNode[this.size()];
		int indexArray = 0;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			SkipListNode<T> aux = this.root.getForward(i);
			while(!aux.equals(NIL) && aux.height() -1 == i){
				array[indexArray] = aux;
				aux = aux.getForward(i);
				indexArray++;
			}
		}
		return array;
		
	}

}
