package adt.skipList;

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
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while ((node.getForward(i).getValue() != null) && (node.getForward(i).getKey() < key)) {
				node = node.getForward(i);
			}
			update[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			node.setValue(newValue);
		} else {
			SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
			for (int j = 0; j < height; j++) {
				newNode.forward[j] = update[j].forward[j];
				update[j].forward[j] = newNode;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while ((node.getForward(i).getValue() != null) && (node.getForward(i).getKey() < key)) {
				node = node.getForward(i);
			}
			update[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			for (int j = 0; j < node.height(); j++) {
				update[j].forward[j] = node.forward[j];
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> node = root.getForward(0);
		int height = 0;
		while (!node.equals(NIL)) {
			if (node.height() > height) {
				height = node.height();
			}
			node = node.getForward(0);
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while ((node.getForward(i).getValue() != null) && (node.getForward(i).getKey() < key)) {
				node = node.getForward(i);
			}
		}
		node = node.getForward(0);
		if (node.getKey() != key) {
			node = null;
		}
		return node;
	}

	@Override
	public int size() {
		return size(root.getForward(0));
	}

	private int size(SkipListNode<T> node) {
		if (node.equals(NIL)) {
			return 0;
		} else {
			return 1 + size(node.getForward(0));
		}
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		SkipListNode<T> node = root;
		for (int i = 0; i < array.length; i++) {
			array[i] = node;
			node = node.getForward(0);
		}
		return array;
	}

}
