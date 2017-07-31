package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
	  if(element == null) return;
	  if(this.indexOf(element) != -1) return;
      if (super.capacity() == super.size()) {
         throw new HashtableOverflowException();
      }
      insert(element, 0);
   }

   private void insert(T element, int probe) {
      int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
      if (super.table[hash] == null || super.table[hash] == new DELETED()) {
         super.table[hash] = element;
         super.elements++;
      } else {
         super.COLLISIONS++;
         insert(element, probe + 1);
      }

   }

   private void remove(T element, int probe) {
      int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
      if (probe == super.capacity() || super.table[hash] == null || super.table[hash] == new DELETED()) {
         return;
      } else if (super.table[hash].equals(element)) {
         this.table[hash] = new DELETED();
         this.elements--;
         return;
      } else {
         remove(element, probe + 1);
      }
   }

   @Override
   public void remove(T element) {
	  if(element == null) return;
      remove(element, 0);

   }

   private T search(T element, int probe) {
      int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
      if (probe == super.capacity() || super.table[hash] == null || super.table[hash] == new DELETED()) {
         return null;
      } else if (super.table[hash].equals(element))
         return (T) super.table[hash];
      return search(element, probe + 1);

   }

   @Override
   public T search(T element) {
      return search(element, 0);
   }

   private int indexOf(T element, int probe) {
      int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
      if (super.table[hash] == null || super.table[hash] == new DELETED()) {
         return -1;
      } else if (super.table[hash].equals(element))
         return hash;
      return indexOf(element, probe + 1);
   }

   @Override
   public int indexOf(T element) {
      return indexOf(element, 0);
   }
}
