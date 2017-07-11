package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element == null || this.indexOf(element) != -1) {
			return;
		}
		if (super.capacity() == super.size()) {
			throw new HashtableOverflowException();
		}
		insert(element, 0);
	}

	private void insert(T element, int probe) {
		int indexHash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if (super.table[indexHash] == null || super.table[indexHash] instanceof DELETED) {

			super.table[indexHash] = element;
			super.elements++;

		} else {

			super.COLLISIONS++;
			insert(element, probe + 1);

		}
	}

	@Override
	public void remove(T element) {
		if (element != null || this.indexOf(element) != -1) {
			remove(element, 0);
		}
	}

	private void remove(T element, int probe) {
		int indexHash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		T elementoAtual = (T) super.table[indexHash];
		if (elementoAtual == null) {
			return;
		} else if (elementoAtual.equals(element)) {
			super.table[indexHash] = new DELETED();
			super.elements--;
			return;
		} else {
			remove(element, probe + 1);
		}
	}

	@Override
	public T search(T element) {
		return search(element, 0);
	}

	private T search(T element, int probe) {
		int indexHash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		T elementoAtual = (T) super.table[indexHash];
		if (elementoAtual == null) {
			return null;
		} else if (elementoAtual.equals(element)) {
			return (T) super.table[indexHash];
		} else {
			return search(element, probe + 1);
		}
	}

	@Override
	public int indexOf(T element) {
		return indexOf(element, 0);
	}

	private int indexOf(T element, int probe) {
		int indexHash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if (super.table[indexHash] == null) {
			return -1;
		} else if (super.table[indexHash].equals(element)) {
			return indexHash;
		} else {
			return indexOf(element, probe + 1);
		}
	}

}
