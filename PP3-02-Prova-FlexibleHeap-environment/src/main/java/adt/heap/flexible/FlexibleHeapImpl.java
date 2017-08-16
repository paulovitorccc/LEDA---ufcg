package adt.heap.flexible;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import adt.heap.HeapImpl;

public class FlexibleHeapImpl<T extends Comparable<T>> extends HeapImpl<T> implements FlexibleHeap<T> {

	public FlexibleHeapImpl(Comparator<T> comparator) {
		super(comparator);
	}

	@Override
	public void changeHeapType() {
		if(comparator instanceof ComparatorMaxHeap){
			ComparatorMinHeap<T> newComparator = new ComparatorMinHeap<>(); 
			setComparator(newComparator);
			T[] newHeap = shiftLeft(heap);
			buildHeap(newHeap);
		} else {
			ComparatorMaxHeap<T> newComparator = new ComparatorMaxHeap<>(); 
			setComparator(newComparator);
			T[] newHeap = shiftLeft(heap);
			buildHeap(newHeap);
		}
	}
	
	private T[] shiftLeft(T[] array){
		LinkedList<T> list = new LinkedList<>();
		for(T element: array){
			if(element != null){
				list.add(element);
			}
		}
		T[] arrayResult = (T[]) new Comparable[list.size()];
		for (int i = 0; i < arrayResult.length; i++) {
			arrayResult[i] = list.get(i);
		}
		return arrayResult;
	}

	@Override
	public List<T> elementsAtLevel(int level) {
		int indexArray = (int) Math.pow(2, level);
		int inicio = indexArray - 1;
		int fim = inicio + inicio;
		List<T> listResult = new LinkedList<T>();
		for (int i = inicio; i <= fim; i++) {
			if (i <= index) {
				listResult.add(this.heap[i]);
			}
		}
		return listResult;
	}

	@Override
	public T floor(T value) {
		T result = null;
		if (size() > 0) {

			if (size() == 1) {
				if (heap[0].compareTo(value) <= 0) {
					result = heap[0];
				}
			} else if (comparator instanceof ComparatorMaxHeap) { // maxheap
				
				T auxFloor = extractRootElement();
				while (auxFloor != null && comparator.compare(auxFloor, value) > 0) {
					auxFloor = extractRootElement();
				}
				result = auxFloor;
				
			} else if (comparator instanceof ComparatorMinHeap) { // minheap
				
				T floor = extractRootElement();
				T auxFloor = floor;
				while (auxFloor != null && comparator.compare(auxFloor, value) > 0) {
					floor = auxFloor;
					auxFloor = extractRootElement();
				}
				if(!(floor.compareTo(value) > 0)){
					result = auxFloor;
				}
				
			}

		}
		return result;
	}

}
