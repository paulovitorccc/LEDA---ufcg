package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element){
		if(element == null) return;
		if(this.indexOf(element) != -1) return;
		if(super.capacity() == super.size()){
			throw new HashtableOverflowException();
		}
		insert(element,0);
	}
	
	
	private void insert(T element, int probe) {
		int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if(super.table[hash] == null || super.table[hash] == new DELETED()){
			super.table[hash] = element;
			super.elements++;
		}else{
			super.COLLISIONS++;
			insert(element,probe + 1);			
		}
			
	}

	private void remove(T element, int probe){
		int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if(super.table[hash] == null || super.table[hash] == new DELETED()){
			return;
		}else if(super.table[hash].equals(element)){
				super.table[hash] = new DELETED();
				super.elements--;
				return;
		}else{
			remove(element,probe+1);			
		}
	}
	
	@Override
	public void remove(T element) {
		if(element == null) return;
		remove(element,0);
		
	}

	private T search(T element, int probe){
		int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if(super.table[hash] == null || super.table[hash] == new DELETED()){
			return null;
		}else if(super.table[hash].equals(element))
			return (T) super.table[hash];
		return search(element,probe+1);
		
	}
	
	@Override
	public T search(T element) {
		return search(element,0);
	}
	
	private int indexOf(T element, int probe){
		int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		if(super.table[hash] == null || super.table[hash] == new DELETED()){
			return -1;
		}else if(super.table[hash].equals(element))
			return hash;
		return indexOf(element,probe+1);
	}

	@Override
	public int indexOf(T element) {
		return indexOf(element,0);
	}

}
